package com.wb.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import com.wb.controller.WeatherForecastController;
import com.wb.service.WeatherService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.wb.payload.Weather;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WeatherForecastApiApplicationTests {

	@Mock
	private WeatherService weatherService;
	@Mock
	Future<Weather> futureWeather = new CompletableFuture<Weather>();
	@InjectMocks
	WeatherForecastController controller = new WeatherForecastController();
	Weather weather;

	@Test
	public void contextLoads() {}

	@Before
	public void setUp() throws Exception {
		weather = new Weather();
		when(futureWeather.isDone()).thenReturn(true);
		when(futureWeather.get()).thenReturn(weather);
		when(weatherService.fetchWeather(Mockito.anyString())).thenReturn(weather);
		when(weatherService.fetchWeatherForCity(Mockito.anyString())).thenReturn(futureWeather);
		when(weatherService.fetchWeatherForCityByTime(Mockito.anyString(),Mockito.anyLong(),Mockito.anyLong())).thenReturn(futureWeather);
	}

	@Test
	public void shouldReturnWeatherObject() throws InterruptedException, ExecutionException {
		assertEquals(weather, controller.weatherInCity());
	}

	@Before
	public void setUpFetchDataByTime() throws Exception {
		weather = new Weather();
		when(futureWeather.isDone()).thenReturn(true);
		when(futureWeather.get()).thenReturn(weather);
		when(weatherService.fetchWeatherByTime(Mockito.anyString(), Mockito.anyLong(), Mockito.anyLong())).thenReturn(weather);
		when(weatherService.fetchWeatherForCityByTime(Mockito.anyString(),Mockito.anyLong(), Mockito.anyLong())).thenReturn(futureWeather);
	}

	@Test
	public void shouldReturnWeatherObjectFetchDataByTime() throws InterruptedException, ExecutionException {
		assertEquals(weather, controller.weatherInCityByTime());
	}

	@Test(expected = InterruptedException.class)
	public void shouldRaiseInterruptedException() throws InterruptedException, ExecutionException {
		when(weatherService.fetchWeatherForCity(Mockito.anyString())).thenThrow(new InterruptedException());
		controller.weatherInCity();
	}

	@Test(expected = InterruptedException.class)
	public void shouldRaiseInterruptedExceptionForFetchDataByTime() throws InterruptedException, ExecutionException {
		when(weatherService.fetchWeatherForCityByTime(Mockito.anyString(), Mockito.anyLong(), Mockito.anyLong())).thenThrow(new InterruptedException());
		controller.weatherInCityByTime();
	}
}
