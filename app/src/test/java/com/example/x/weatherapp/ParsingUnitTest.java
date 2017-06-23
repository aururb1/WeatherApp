package com.example.x.weatherapp;

import android.support.v4.media.MediaMetadataCompat;

import com.example.x.weatherapp.Util.Utils;
import com.example.x.weatherapp.data.JSONWeatherParser;
import com.example.x.weatherapp.data.WeatherHttpClient;
import com.example.x.weatherapp.model.Temperature;
import com.example.x.weatherapp.model.Weather;

import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static org.junit.Assert.*;

public class ParsingUnitTest {
    String data = "{\"coord\":{\"lon\":-0.13,\"lat\":51.51},\"weather\":[{\"id\":300,\"main\":\"Drizzle\",\"description\":\"light intensity drizzle\",\"icon\":\"09d\"}],\"base\":\"stations\",\"main\":{\"temp\":280.32,\"pressure\":1012,\"humidity\":81,\"temp_min\":279.15,\"temp_max\":281.15},\"visibility\":10000,\"wind\":{\"speed\":4.1,\"deg\":80},\"clouds\":{\"all\":90},\"dt\":1485789600,\"sys\":{\"type\":1,\"id\":5091,\"message\":0.0103,\"country\":\"GB\",\"sunrise\":1485762037,\"sunset\":1485794875},\"id\":2643743,\"name\":\"London\",\"cod\":200}";
    Weather weatherExpected = new Weather();
    Weather weatherOutput = new Weather();

    @Test
    public void temperatureParsing() throws Exception {
        weatherExpected.getTemperature().setHumidity(81);
        weatherExpected.getTemperature().setPressure(1012);
        weatherExpected.getTemperature().setMaxTemp((float) 281.15);
        weatherExpected.getTemperature().setMinTemp((float) 279.15);
        weatherExpected.getTemperature().setTemperature(280.32);
        weatherOutput = JSONWeatherParser.getWeather(data);
        assertEquals(weatherExpected.getTemperature(),weatherOutput.getTemperature());
    }

    @Test
    public void currentConditionParsing() throws Exception {
        weatherExpected.getCurrentCondition().setIcon("09d");
        weatherExpected.getCurrentCondition().setCondition("Drizzle");
        weatherExpected.getCurrentCondition().setDescription("light intensity drizzle");
        weatherOutput = JSONWeatherParser.getWeather(data);
        assertEquals(weatherExpected.getCurrentCondition(),weatherOutput.getCurrentCondition());
    }

    @Test
    public void windParsing() throws Exception {
        weatherExpected.getWind().setSpeed((float) 4.1);
        weatherExpected.getWind().setDeg((float) 80);
        weatherOutput = JSONWeatherParser.getWeather(data);
        assertEquals(weatherExpected.getWind(),weatherOutput.getWind());
    }

    @Test
    public void placeParsing() throws Exception {
        weatherExpected.getPlace().setSunset(1485794875);
        weatherExpected.getPlace().setSunrise(1485762037);
        weatherExpected.getPlace().setCountry("GB");
        weatherExpected.getPlace().setCity("London");
        weatherExpected.getPlace().setLastupdate(1485789600);
        weatherOutput = JSONWeatherParser.getWeather(data);
        assertEquals(weatherExpected.getPlace(),weatherOutput.getPlace());
    }
}