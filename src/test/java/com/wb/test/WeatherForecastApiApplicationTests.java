package com.wb.test;

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

import com.wb.utility.ApplicationConstants;

@SpringBootTest
@AutoConfigureMockMvc
public class WeatherForecastApiApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @ParameterizedTest
    @CsvFileSource(resources = "/findByCityCountry.csv", delimiter = ';')
    public void findByCityCountry(String cityCountry) throws Exception {
        mockMvc.perform(get(ApplicationConstants.WB_API_PRIFIX + "/wb/cityCountry/{cityCountry}", new Object[]{cityCountry}).contentType(MediaType.APPLICATION_JSON))
                .andExpect(matchAll(status().isOk()));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/findByCityCountryAndTime.csv", delimiter = ';')
    public void findByCityCountryAndTime(String cityCountry, long startTimeStamp, long endTimeStamp) throws Exception {
        mockMvc.perform(get(ApplicationConstants.WB_API_PRIFIX + "/wb/cityCountryAndTime?cityCountry={cityCountry}&startTimeStamp={startTimeStamp}&endTimeStamp={endTimeStamp}", new Object[]{cityCountry, startTimeStamp, endTimeStamp})
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(matchAll(status().isOk()));
    }
}
