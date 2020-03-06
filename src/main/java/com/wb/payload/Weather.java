package com.wb.payload;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "coord", "weather", "base", "property", "visibility", "wind",
		"clouds", "dt", "sys", "id", "name", "cod" })
public class Weather {

	@JsonProperty("coord")
	private Coord coord;
	@JsonProperty("weather")
	private List<Weather_> weather = null;
	@JsonProperty("base")
	private String base;
	@JsonProperty("property")
	private Property property;
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
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("coord")
	public Coord getCoord() {
		return coord;
	}

	@JsonProperty("coord")
	public void setCoord(Coord coord) {
		this.coord = coord;
	}

	@JsonProperty("weather")
	public List<Weather_> getWeather() {
		return weather;
	}

	@JsonProperty("weather")
	public void setWeather(List<Weather_> weather) {
		this.weather = weather;
	}

	@JsonProperty("base")
	public String getBase() {
		return base;
	}

	@JsonProperty("base")
	public void setBase(String base) {
		this.base = base;
	}

	@JsonProperty("property")
	public Property getProperty() {
		return property;
	}

	@JsonProperty("property")
	public void setProperty(Property property) {
		this.property = property;
	}

	@JsonProperty("visibility")
	public Integer getVisibility() {
		return visibility;
	}

	@JsonProperty("visibility")
	public void setVisibility(Integer visibility) {
		this.visibility = visibility;
	}

	@JsonProperty("wind")
	public Wind getWind() {
		return wind;
	}

	@JsonProperty("wind")
	public void setWind(Wind wind) {
		this.wind = wind;
	}

	@JsonProperty("clouds")
	public Clouds getClouds() {
		return clouds;
	}

	@JsonProperty("clouds")
	public void setClouds(Clouds clouds) {
		this.clouds = clouds;
	}

	@JsonProperty("dt")
	public Integer getDt() {
		return dt;
	}

	@JsonProperty("dt")
	public void setDt(Integer dt) {
		this.dt = dt;
	}

	@JsonProperty("sys")
	public Sys getSys() {
		return sys;
	}

	@JsonProperty("sys")
	public void setSys(Sys sys) {
		this.sys = sys;
	}

	@JsonProperty("id")
	public Integer getId() {
		return id;
	}

	@JsonProperty("id")
	public void setId(Integer id) {
		this.id = id;
	}

	@JsonProperty("name")
	public String getName() {
		return name;
	}

	@JsonProperty("name")
	public void setName(String name) {
		this.name = name;
	}

	@JsonProperty("cod")
	public Integer getCod() {
		return cod;
	}

	@JsonProperty("cod")
	public void setCod(Integer cod) {
		this.cod = cod;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

	@Override
	public String toString() {
		return "Weather [coord=" + coord + ", weather=" + weather + ", base="
				+ base + ", main=" + property + ", visibility=" + visibility
				+ ", wind=" + wind + ", clouds=" + clouds + ", dt=" + dt
				+ ", sys=" + sys + ", id=" + id + ", name=" + name + ", cod="
				+ cod + ", additionalProperties=" + additionalProperties + "]";
	}
}