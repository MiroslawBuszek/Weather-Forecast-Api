package com.wb.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.wb.payload.ApiResponse;
import com.wb.payload.WeatherMapper;
import com.wb.service.WeatherService;
import com.wb.utility.Translator;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/v1/wb")
@Api(tags = "WB", value = "/wb")
public class WeatherForecastController {

    private final WeatherService weatherService;

    public WeatherForecastController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @ApiOperation(value = "Get Weather Data By City")
    @GetMapping(value = "/city/{city}")
    ApiResponse weatherForCity(@PathVariable("city") String city) {
        WeatherMapper weather = weatherService.fetchWeatherByCity(city);
        return new ApiResponse("GetKey", Translator.toLocale("wb.data.by.city"), HttpStatus.OK.value(), weather);
    }

    @ApiOperation(value = "Get Weather Data By City & Time")
    @GetMapping(value = "/cityAndTime")
    ApiResponse weatherForCityAndTime(@RequestParam String city, @RequestParam Long startTimeStamp, @RequestParam Long endTimeStamp) {
        WeatherMapper weather = weatherService.fetchWeatherByCityAndTime(city, startTimeStamp, endTimeStamp);
        return new ApiResponse("GetKey", Translator.toLocale("wb.data.by.city.time"), HttpStatus.OK.value(), weather);
    }
}
