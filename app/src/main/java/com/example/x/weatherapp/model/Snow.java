package com.example.x.weatherapp.model;

public class Snow {

    private int percipitation;

    public int getPercipitation() {
        return percipitation;
    }

    public void setPercipitation(int percipitation) {
        this.percipitation = percipitation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Snow snow = (Snow) o;

        return percipitation == snow.percipitation;

    }

    @Override
    public int hashCode() {
        return percipitation;
    }
}
