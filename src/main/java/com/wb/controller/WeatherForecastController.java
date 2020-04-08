package com.wb.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.wb.payload.ApiResponse;
import com.wb.payload.WeatherMapper;
import com.wb.service.WeatherService;
import com.wb.service.TranslatorService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/v1/wb")
@Api(tags = "WB", value = "/wb")
public class WeatherForecastController {

    private final WeatherService weatherService;
    private final TranslatorService translatorService;

    public WeatherForecastController(WeatherService weatherService, TranslatorService translatorService) {
        this.weatherService = weatherService;
        this.translatorService = translatorService;
    }

    @ApiOperation(value = "Get Weather Data By City")
    @GetMapping(value = "/city/{city}")
    public ApiResponse weatherForCity(@PathVariable("city") String city) {
        WeatherMapper weather = weatherService.fetchWeatherByCity(city);
        return new ApiResponse("GetKey", translatorService.toLocale("wb.data.by.city"), HttpStatus.OK.value(), weather);
    }

    @ApiOperation(value = "Get Weather Data By City & Time")
    @GetMapping(value = "/cityAndTime")
    public ApiResponse weatherForCityAndTime(@RequestParam String city, @RequestParam Long startTimeStamp, @RequestParam Long endTimeStamp) {
        WeatherMapper weather = weatherService.fetchWeatherByCityAndTime(city, startTimeStamp, endTimeStamp);
        return new ApiResponse("GetKey", translatorService.toLocale("wb.data.by.city.time"), HttpStatus.OK.value(), weather);
    }
}
