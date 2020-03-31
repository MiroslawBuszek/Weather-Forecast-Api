package com.wb;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import com.wb.controller.WeatherForecastController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.wb.payload.WeatherMapper;
import com.wb.service.WeatherService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WeatherForecastApiApplicationUnitTests {
    @Mock
    private WeatherService weatherService;
    @Mock
    Future<WeatherMapper> futureWeather = new CompletableFuture<>();
    @InjectMocks
    WeatherForecastController controller = new WeatherForecastController(weatherService);
    WeatherMapper weatherMapper;

    @org.junit.jupiter.api.Test
    public void contextLoads() {
    }

    @Before
    public void setUp1() throws Exception {
        weatherMapper = new WeatherMapper();
        when(futureWeather.isDone()).thenReturn(true);
        when(futureWeather.get()).thenReturn(weatherMapper);
        when(weatherService.fetchWeatherByCity(Mockito.anyString())).thenReturn(weatherMapper);
        when(weatherService.testWeatherByCity(Mockito.anyString())).thenReturn(futureWeather);
    }

    @Test
    public void shouldReturnCityWeatherObject() throws InterruptedException, ExecutionException {
        Future<WeatherMapper> fw = weatherService.testWeatherByCity("Warsaw");
        while (!fw.isDone()) {
            Thread.sleep(10);
        }
        assertEquals(weatherMapper, fw.get());
    }

    @Before
    public void setUp2() throws Exception {
        weatherMapper = new WeatherMapper();
        when(futureWeather.isDone()).thenReturn(true);
        when(futureWeather.get()).thenReturn(weatherMapper);
        when(weatherService.fetchWeatherByCityAndTime(Mockito.anyString(), Mockito.anyLong(), Mockito.anyLong())).thenReturn(weatherMapper);
        when(weatherService.testWeatherByCityAndTime(Mockito.anyString(), Mockito.anyLong(), Mockito.anyLong())).thenReturn(futureWeather);
    }

    @Test
    public void shouldReturnCityTimeWeatherObject() throws InterruptedException, ExecutionException {
        Future<WeatherMapper> fw = weatherService.testWeatherByCityAndTime("Warsaw", 1581712620L, 1581799020L);
        while (!fw.isDone()) {
            Thread.sleep(10);
        }
        assertEquals(weatherMapper, fw.get());
    }

    @Test(expected = InterruptedException.class)
    public void shouldRaiseInterruptedExceptionForFetchDataByCity() throws InterruptedException, ExecutionException {
        when(weatherService.fetchWeatherByCity(Mockito.anyString())).thenThrow(new InterruptedException());
        controller.weatherForCity("Warsaw");
    }

    @Test(expected = InterruptedException.class)
    public void shouldRaiseInterruptedExceptionForFetchDataByTime() throws InterruptedException, ExecutionException {
        when(weatherService.testWeatherByCityAndTime(Mockito.anyString(), Mockito.anyLong(), Mockito.anyLong())).thenThrow(new InterruptedException());
        controller.weatherForCityAndTime("Warsaw", 1581712620L, 1581799020L);
    }
}
