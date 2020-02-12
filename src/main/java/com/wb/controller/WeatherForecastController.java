package com.wb.controller;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import com.wb.payload.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wb.payload.Weather;
import com.wb.service.WeatherService;
import com.wb.utility.ApplicationConstants;
import com.wb.utility.Translator;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(ApplicationConstants.WB_API_PRIFIX + "/wb")
@Api(tags = "WB", value = "/wb")
public class WeatherForecastController {

    @Autowired
    WeatherService weatherService;

    @ApiOperation(value = "Get Weather Data By City Country")
    @RequestMapping(value = "/cityCountry/{cityCountry}", method = RequestMethod.GET)
    ResponseEntity<?> cityCountry(@PathVariable("cityCountry") String cityCountry) {
        Weather weather = weatherService.fetchWeather(cityCountry);
        return ResponseEntity.ok(new ApiResponse(true, ApplicationConstants.GET_KEY, Translator.toLocale("wb.data.by.city.country"), HttpStatus.OK.value(), weather));
    }

    @ApiOperation(value = "Get Weather Data By City Country & time")
    @RequestMapping(value = "/cityCountryAndTime", method = RequestMethod.GET)
    ResponseEntity<?> cityCountry(@RequestParam String cityCountry, @RequestParam Long startTimeStamp, @RequestParam Long endTimeStamp) {
        Weather weather = weatherService.fetchWeatherByTime(cityCountry, startTimeStamp, endTimeStamp);
        return ResponseEntity.ok(new ApiResponse(true, ApplicationConstants.GET_KEY,
                Translator.toLocale("wb.data.by.city.country.time"), HttpStatus.OK.value(), weather));
    }

    //@RequestMapping(value = "/async/weatherInCity", method = RequestMethod.GET)
    public Weather weatherInCity() throws InterruptedException, ExecutionException {
        Future<Weather> dallas = weatherService.fetchWeatherForCity("Dallas,us");
        // Wait until they are all done
        while (!dallas.isDone()) {
            Thread.sleep(10); // 10-millisecond pause between each check
        }
        return dallas.get();
    }

    public Weather weatherInCityByTime() throws InterruptedException, ExecutionException {
        Future<Weather> dallas = weatherService.fetchWeatherForCityByTime("Dallas,us", 1369728000L, 1369789200L);
        while (!dallas.isDone()) {
            Thread.sleep(10); // 10-millisecond pause between each check
        }
        return dallas.get();
    }
}
