package com.example.x.weatherapp.model;

public class Wind {

    private float speed;
    private float deg;

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public float getDeg() {
        return deg;
    }

    public void setDeg(float deg) {
        this.deg = deg;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Wind wind = (Wind) o;

        if (Float.compare(wind.speed, speed) != 0) return false;
        return Float.compare(wind.deg, deg) == 0;

    }

    @Override
    public int hashCode() {
        int result = (speed != +0.0f ? Float.floatToIntBits(speed) : 0);
        result = 31 * result + (deg != +0.0f ? Float.floatToIntBits(deg) : 0);
        return result;
    }
}
