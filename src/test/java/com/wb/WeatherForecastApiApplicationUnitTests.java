package com.wb;

import static org.springframework.test.web.servlet.ResultMatcher.matchAll;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.wb.payload.WeatherMapper;
import com.wb.service.WeatherService;

@SpringBootTest
@AutoConfigureMockMvc
public class WeatherForecastApiApplicationUnitTests {

    private final WeatherService weatherService;
    private final MockMvc mockMvc;

    @Autowired
    public WeatherForecastApiApplicationUnitTests(WeatherService weatherService, MockMvc mockMvc) {
        this.weatherService = weatherService;
        this.mockMvc = mockMvc;
    }

    @Test
    public void apiFetchWeatherByCity() throws Exception {
        this.mockMvc.perform(get("/api/v1/wb/city/{city}", "Warsaw,pl").contentType(MediaType.APPLICATION_JSON))
                .andExpect(matchAll(status().isOk()));
    }

    @Test
    public void serviceFetchWeatherByCity() throws Exception {
        WeatherMapper weatherMapper = weatherService.fetchWeatherByCity("Warsaw,pl");
        Assertions.assertEquals(weatherMapper.getSys().getCountry(), "PL");
    }

    @Test
    public void apiFetchWeatherByCityAndTime() throws Exception {
        this.mockMvc.perform(get("/api/v1/wb/cityAndTime?city={city}&startTimeStamp={startTimeStamp}&endTimeStamp={endTimeStamp}", "Warsaw,pl", 1581712620, 1581799020).contentType(MediaType.APPLICATION_JSON))
                .andExpect(matchAll(status().isOk()));
    }

    @Test
    public void serviceFetchWeatherByCityAndTime() throws Exception {
        WeatherMapper weatherMapper = weatherService.fetchWeatherByCityAndTime("Warsaw,pl", 1581712620L, 1581799020L);
        Assertions.assertEquals(weatherMapper.getSys().getCountry(), "PL");
    }
}
