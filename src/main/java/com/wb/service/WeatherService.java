package com.wb.service;

import com.wb.payload.Weather;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.Future;

@Service
public class WeatherService {

	private static final Logger logger = LoggerFactory.getLogger(WeatherService.class);
	private final RestTemplate restTemplate;

	@Value("${open.weather.appid}")
	private String APP_ID;

	@Value("${open.weather.app.url}")
	private String APP_URL;

	public WeatherService(RestTemplateBuilder restTemplateBuilder) {
		this.restTemplate = new RestTemplate();
	}

	@Cacheable(cacheNames = "weather", key = "#cityCountry")
	public Weather fetchWeather(String cityCountry) throws RestClientException {
		logger.info("Sync : Looking up " + cityCountry);
		String url = String.format(APP_URL + "?q=%s&appid=%s", cityCountry, APP_ID);
		return restTemplate.getForObject(url, Weather.class);
	}

	@Cacheable(cacheNames = "weather", key = "#cityCountryTime")
	public Weather fetchWeatherByTime(String cityCountry, Long startTime, Long endTime) throws RestClientException {
		logger.info("Sync : Looking up " + cityCountry);
		String url = String.format(APP_URL + "?q=%s&appid=%s&type=hour&start=%s&end=%s", cityCountry, APP_ID, startTime,
				endTime);

		return restTemplate.getForObject(url, Weather.class);
	}

	@Async
	public Future<Weather> fetchWeatherForCity(String cityCountry) throws InterruptedException {
		logger.info("Async : Looking up " + cityCountry);
		String url = String.format(APP_URL + "?q=%s&appid=%s", cityCountry, APP_ID);
		Weather results = restTemplate.getForObject(url, Weather.class);
		return new AsyncResult<Weather>(results);
	}

	@Async
	public Future<Weather> fetchWeatherForCityByTime(String cityCountry, Long startTime, Long endTime)
			throws InterruptedException {
		logger.info("Async : Looking up " + cityCountry);
		String url = String.format(APP_URL + "?q=%s&appid=%s&type=hour&start=%s&end=%s", cityCountry, APP_ID, startTime,
				endTime);
		Weather results = restTemplate.getForObject(url, Weather.class);
		return new AsyncResult<Weather>(results);
	}
}
