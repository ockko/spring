package org.scoula.weather.dto;

import java.util.List;
import lombok.Data;

@Data
public class WeatherDTO{
	// https://api.openweathermap.org/data/2.5/weather?q=seoul&units=metric&APPID=0b3b55e8f262238476508706d818c2d3&lang=kr
	private int visibility;
	private int timezone;
	private Main main;
	private Clouds clouds;
	private Sys sys;
	private int dt;
	private Coord coord;
	private List<WeatherItem> weather;
	private String name;
	private int cod;
	private int id;
	private String base;
	private Wind wind;
}