package com.wb;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withServerError;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException.InternalServerError;

import com.wb.payload.WeatherMapper;
import com.wb.service.WeatherService;

@ExtendWith(SpringExtension.class)

@RestClientTest(WeatherService.class)
public class WeatherForecastApiApplicationUnitTests {

    @Autowired
    private WeatherService weatherService;

    @Autowired
    private MockRestServiceServer server;


    @Test
    public void serviceFetchWeatherByCity() throws Exception {
        this.server.expect(requestTo("http://api.openweathermap.org/data/2.5/weather?q=Warsaw,pl&appid=0b65f02ed4ef88a3598934f2ae5346d5"))
                .andRespond(withSuccess("{\"sys\": {\"country\":\"PL\"}}", MediaType.APPLICATION_JSON));
        WeatherMapper weatherMapper = weatherService.fetchWeatherByCity("Warsaw,pl");
        Assertions.assertEquals(weatherMapper.getSys().getCountry(), "PL");
    }

    @Test
    public void serviceFetchWeatherByCityAndTime() throws Exception {
        this.server.expect(requestTo("http://api.openweathermap.org/data/2.5/weather?q=Warsaw,pl&appid=0b65f02ed4ef88a3598934f2ae5346d5&type=hour&start=1581712620&end=1581799020"))
                .andRespond(withSuccess("{\"sys\": {\"country\":\"PL\"}}", MediaType.APPLICATION_JSON));
        WeatherMapper weatherMapper = weatherService.fetchWeatherByCityAndTime("Warsaw,pl", 1581712620L, 1581799020L);
        Assertions.assertEquals(weatherMapper.getSys().getCountry(), "PL");
    }

    @Test
    public void response4xxForWeatherByCity() throws Exception {

        this.server.expect(requestTo("http://api.openweathermap.org/data/2.5/weather?q=Warsaw,pl111&appid=0b65f02ed4ef88a3598934f2ae5346d5"))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.NOT_FOUND));
        HttpClientErrorException exception = Assertions.assertThrows(HttpClientErrorException.class, () -> {
            weatherService.fetchWeatherByCity("Warsaw,pl111");
        });
        Assertions.assertTrue(exception.getMessage().contains("404 Not Found"));
    }

    @Test
    public void response4xxForWeatherByCityAndTime() throws Exception {
        this.server.expect(requestTo("http://api.openweathermap.org/data/2.5/weather?q=Warsaw,pl111&appid=0b65f02ed4ef88a3598934f2ae5346d5&type=hour&start=1581712620&end=1581799020"))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.NOT_FOUND));
        HttpClientErrorException exception = Assertions.assertThrows(HttpClientErrorException.class, () -> {
            weatherService.fetchWeatherByCityAndTime("Warsaw,pl111", 1581712620L, 1581799020L);
        });
        Assertions.assertTrue(exception.getMessage().contains("404 Not Found"));
    }

    @Test
    public void response5xxForWeatherByCity() {
        this.server.expect(requestTo("http://api.openweathermap.org/data/2.5/weather?q=Warsaw,pl&appid=0b65f02ed4ef88a3598934f2ae5346d5"))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withServerError());
        InternalServerError exception = Assertions.assertThrows(InternalServerError.class, () -> {
            weatherService.fetchWeatherByCity("Warsaw,pl");
        });
        Assertions.assertTrue(exception.getMessage().contains("500 Internal Server Error"));
    }

    @Test
    public void response5xxForWeatherByCityAndTime() throws Exception {
        this.server.expect(requestTo("http://api.openweathermap.org/data/2.5/weather?q=Warsaw,pl&appid=0b65f02ed4ef88a3598934f2ae5346d5&type=hour&start=1581712620&end=1581799020"))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withServerError());
        InternalServerError exception = Assertions.assertThrows(InternalServerError.class, () -> {
            weatherService.fetchWeatherByCityAndTime("Warsaw,pl", 1581712620L, 1581799020L);
        });
        Assertions.assertTrue(exception.getMessage().contains("500 Internal Server Error"));
    }
}


