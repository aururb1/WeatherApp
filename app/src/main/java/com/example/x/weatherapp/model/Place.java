package com.example.x.weatherapp.model;

import com.google.gson.annotations.SerializedName;


public class Place {

    private float lon;
    private float lat;
    private long sunset;
    private long sunrise;
    private String country;
    @SerializedName("name")
    private String city;
    @SerializedName("dt")
    private long lastupdate;

    public float getLon() {
        return lon;
    }

    public void setLon(float lon) {
        this.lon = lon;
    }

    public float getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public long getSunset() {
        return sunset;
    }

    public void setSunset(long sunset) {
        this.sunset = sunset;
    }

    public long getSunrise() {
        return sunrise;
    }

    public void setSunrise(long sunrise) {
        this.sunrise = sunrise;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public long getLastupdate() {
        return lastupdate;
    }

    public void setLastupdate(long lastupdate) {
        this.lastupdate = lastupdate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Place place = (Place) o;

        if (Float.compare(place.lon, lon) != 0) return false;
        if (Float.compare(place.lat, lat) != 0) return false;
        if (sunset != place.sunset) return false;
        if (sunrise != place.sunrise) return false;
        if (lastupdate != place.lastupdate) return false;
        if (country != null ? !country.equals(place.country) : place.country != null) return false;
        return city != null ? city.equals(place.city) : place.city == null;

    }

    @Override
    public int hashCode() {
        int result = (lon != +0.0f ? Float.floatToIntBits(lon) : 0);
        result = 31 * result + (lat != +0.0f ? Float.floatToIntBits(lat) : 0);
        result = 31 * result + (int) (sunset ^ (sunset >>> 32));
        result = 31 * result + (int) (sunrise ^ (sunrise >>> 32));
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (int) (lastupdate ^ (lastupdate >>> 32));
        return result;
    }
}
