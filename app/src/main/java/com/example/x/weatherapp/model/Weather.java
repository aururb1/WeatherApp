package com.example.x.weatherapp.model;


public class Weather {

    private Place place = new Place();
    private String iconData;
    private CurrentCondition currentCondition = new CurrentCondition();
    private Temperature temperature = new Temperature();
    private Wind wind = new Wind();
    private Snow snow = new Snow();
    private Clouds clouds = new Clouds();

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public String getIconData() {
        return iconData;
    }

    public void setIconData(String iconData) {
        this.iconData = iconData;
    }

    public CurrentCondition getCurrentCondition() {
        return currentCondition;
    }

    public void setCurrentCondition(CurrentCondition currentCondition) {
        this.currentCondition = currentCondition;
    }

    public Temperature getTemperature() {
        return temperature;
    }

    public void setTemperature(Temperature temperature) {
        this.temperature = temperature;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public Snow getSnow() {
        return snow;
    }

    public void setSnow(Snow snow) {
        this.snow = snow;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public void setClouds(Clouds clouds) {
        this.clouds = clouds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Weather weather = (Weather) o;

        if (place != null ? !place.equals(weather.place) : weather.place != null) return false;
        if (iconData != null ? !iconData.equals(weather.iconData) : weather.iconData != null)
            return false;
        if (currentCondition != null ? !currentCondition.equals(weather.currentCondition) : weather.currentCondition != null)
            return false;
        if (temperature != null ? !temperature.equals(weather.temperature) : weather.temperature != null)
            return false;
        if (wind != null ? !wind.equals(weather.wind) : weather.wind != null) return false;
        if (snow != null ? !snow.equals(weather.snow) : weather.snow != null) return false;
        return clouds != null ? clouds.equals(weather.clouds) : weather.clouds == null;

    }

    @Override
    public int hashCode() {
        int result = place != null ? place.hashCode() : 0;
        result = 31 * result + (iconData != null ? iconData.hashCode() : 0);
        result = 31 * result + (currentCondition != null ? currentCondition.hashCode() : 0);
        result = 31 * result + (temperature != null ? temperature.hashCode() : 0);
        result = 31 * result + (wind != null ? wind.hashCode() : 0);
        result = 31 * result + (snow != null ? snow.hashCode() : 0);
        result = 31 * result + (clouds != null ? clouds.hashCode() : 0);
        return result;
    }
}
