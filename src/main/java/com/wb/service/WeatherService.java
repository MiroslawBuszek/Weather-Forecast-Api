package com.wb.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.wb.payload.WeatherMapper;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Future;

@Service
@Slf4j
public class WeatherService {

    private final RestTemplate restTemplate;
    private final String appID;
    private final String appURL;

    public WeatherService(RestTemplateBuilder restTemplateBuilder, @Value("${open.weather.appid}") final String appId,
                          @Value("${open.weather.app.url}") final String appURL) {
        this.restTemplate = restTemplateBuilder.build();
        this.appID = appId;
        this.appURL = appURL;
    }

    @Cacheable(cacheNames = "weather", key = "#cityCountry")
    public WeatherMapper fetchWeatherByCity(String city) throws RestClientException {
        log.info("Sync : Looking up " + city);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(appURL)
                .queryParam("q", city)
                .queryParam("appid", appID);
        return restTemplate.getForObject(builder.toUriString(), WeatherMapper.class);
    }

    @Cacheable(cacheNames = "weather", key = "#cityCountryTime")
    public WeatherMapper fetchWeatherByCityAndTime(String city, Long startTime, Long endTime) throws RestClientException {
        log.info("Sync : Looking up " + city);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(appURL)
                .queryParam("q", city)
                .queryParam("appid", appID)
                .queryParam("type", "hour")
                .queryParam("start", startTime)
                .queryParam("end", endTime);
        return restTemplate.getForObject(builder.toUriString(), WeatherMapper.class);
    }
}