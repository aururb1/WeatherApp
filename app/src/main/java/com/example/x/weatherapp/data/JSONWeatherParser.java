package com.example.x.weatherapp.data;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.x.weatherapp.Util.Utils;
import com.example.x.weatherapp.model.Clouds;
import com.example.x.weatherapp.model.CurrentCondition;
import com.example.x.weatherapp.model.Place;
import com.example.x.weatherapp.model.Temperature;
import com.example.x.weatherapp.model.Weather;
import com.example.x.weatherapp.model.Wind;
import com.google.gson.Gson;

/**
 * Created by X on 2017-06-15.
 */

public class JSONWeatherParser {

    public static Weather getWeather(String data){
        Weather weather = new Weather();

        try {
            Gson gson = new Gson();
            JSONObject jsonObject = new JSONObject(data);

            JSONObject sysObj = Utils.getObject("sys", jsonObject);
            weather.setPlace(gson.fromJson(sysObj.toString(), Place.class));
            Place place = gson.fromJson(jsonObject.toString(), Place.class);
            weather.getPlace().setCity(place.getCity());
            weather.getPlace().setLastupdate(place.getLastupdate());

            JSONArray jsonArray = jsonObject.getJSONArray("weather");
            JSONObject jsonWeather = jsonArray.getJSONObject(0);
            weather.setCurrentCondition(gson.fromJson(jsonWeather.toString(),CurrentCondition.class));

            JSONObject mainObj = Utils.getObject("main", jsonObject);
            weather.setTemperature(gson.fromJson(mainObj.toString(), Temperature.class));

            JSONObject windObj = Utils.getObject("wind", jsonObject);
            weather.setWind(gson.fromJson(windObj.toString(), Wind.class));

            JSONObject cloudObj = Utils.getObject("clouds", jsonObject);
            weather.setClouds(gson.fromJson(cloudObj.toString(), Clouds.class));

            return weather;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
