package com.example.x.weatherapp.model;

import com.google.gson.annotations.SerializedName;

public class CurrentCondition {

    private int weatherId;
    @SerializedName("main")
    private String condition;
    private String description;
    private String icon;


    public int getWeatherId() {
        return weatherId;
    }

    public void setWeatherId(int weatherId) {
        this.weatherId = weatherId;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CurrentCondition that = (CurrentCondition) o;

        if (weatherId != that.weatherId) return false;
        if (condition != null ? !condition.equals(that.condition) : that.condition != null)
            return false;
        if (description != null ? !description.equals(that.description) : that.description != null)
            return false;
        return icon != null ? icon.equals(that.icon) : that.icon == null;

    }

    @Override
    public int hashCode() {
        int result = weatherId;
        result = 31 * result + (condition != null ? condition.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (icon != null ? icon.hashCode() : 0);
        return result;
    }
}
