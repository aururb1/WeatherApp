package com.example.x.weatherapp.model;

public class Clouds {
    private int precipitation;

    public int getPrecipitation(){
        return precipitation;
    }

    public void setPrecipitation(int precipitation){
        this.precipitation = precipitation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Clouds clouds = (Clouds) o;

        return precipitation == clouds.precipitation;

    }

    @Override
    public int hashCode() {
        return precipitation;
    }
}
