package com.wb;

import static org.springframework.test.web.servlet.ResultMatcher.matchAll;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class WeatherForecastApiApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @ParameterizedTest
    @CsvFileSource(resources = "/weatherForCity.csv", delimiter = ';')
    public void testWeatherForCity(String city) throws Exception {
        mockMvc.perform(get("/api/v1/wb/city/{city}", new Object[]{city}).contentType(MediaType.APPLICATION_JSON))
                .andExpect(matchAll(status().isOk()));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/weatherForCityAndTime.csv", delimiter = ';')
    public void testWeatherForCityAndTime(String city, long startTimeStamp, long endTimeStamp) throws Exception {
        mockMvc.perform(get("/api/v1/wb/cityAndTime?city={city}&startTimeStamp={startTimeStamp}&endTimeStamp={endTimeStamp}", new Object[]{city, startTimeStamp, endTimeStamp})
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(matchAll(status().isOk()));
    }
}