package com.wb.payload;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"coord", "weather", "base", "main", "visibility", "wind",
        "clouds", "dt", "sys", "id", "name", "cod"})
public class WeatherMapper {

    @JsonProperty("coord")
    private Coord coord;
    @JsonProperty("weather")
    private List<Weather> weather;
    @JsonProperty("base")
    private String base;
    @JsonProperty("main")
    private GroupWeather groupWeather;
    @JsonProperty("visibility")
    private Integer visibility;
    @JsonProperty("wind")
    private Wind wind;
    @JsonProperty("clouds")
    private Clouds clouds;
    @JsonProperty("dt")
    private Integer dt;
    @JsonProperty("sys")
    private Sys sys;
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("cod")
    private Integer cod;

}
