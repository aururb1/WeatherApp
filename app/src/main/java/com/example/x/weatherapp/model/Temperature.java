package com.example.x.weatherapp.model;

import com.google.gson.annotations.SerializedName;

public class Temperature {

    private float pressure;
    private float humidity;
    @SerializedName("temp_max")
    private float maxTemp;
    @SerializedName("temp_min")
    private float minTemp;
    @SerializedName("temp")
    private double temperature;

    public float getPressure() {
        return pressure;
    }

    public void setPressure(float pressure) {
        this.pressure = pressure;
    }

    public float getHumidity() {
        return humidity;
    }

    public void setHumidity(float humidity) {
        this.humidity = humidity;
    }

    public float getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(float maxTemp) {
        this.maxTemp = maxTemp;
    }

    public float getMinTemp() {
        return minTemp;
    }

    public void setMinTemp(float minTemp) {
        this.minTemp = minTemp;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Temperature that = (Temperature) o;

        if (Float.compare(that.pressure, pressure) != 0) return false;
        if (Float.compare(that.humidity, humidity) != 0) return false;
        if (Float.compare(that.maxTemp, maxTemp) != 0) return false;
        if (Float.compare(that.minTemp, minTemp) != 0) return false;
        return Double.compare(that.temperature, temperature) == 0;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (pressure != +0.0f ? Float.floatToIntBits(pressure) : 0);
        result = 31 * result + (humidity != +0.0f ? Float.floatToIntBits(humidity) : 0);
        result = 31 * result + (maxTemp != +0.0f ? Float.floatToIntBits(maxTemp) : 0);
        result = 31 * result + (minTemp != +0.0f ? Float.floatToIntBits(minTemp) : 0);
        temp = Double.doubleToLongBits(temperature);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
