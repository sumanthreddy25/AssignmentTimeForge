package com.Assignment.restdemo.model;

import io.swagger.annotations.ApiModel;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;


@Entity
@Table(name = "weather_forecast")
@ApiModel(description = "This table holds cloud vendor information.")
public class WeatherData {
    @Id
    @GeneratedValue
    private int id;
    private double highTemperature;
    private double lowTemperature;
    private double humidity;

    private Date date;
    private double Precipitation;
    private double windSpeed;

    private String zipCode;


    public WeatherData() {

    }

    public WeatherData(double highTemperature, double lowTemperature, double humidity, Date date, double Precipitation, double windSpeed, String zipCode) {
        super();
        this.id = id;
        this.highTemperature = highTemperature;
        this.lowTemperature = lowTemperature;
        this.humidity = humidity;
        this.date = date;
        this.Precipitation = Precipitation;
        this.windSpeed = windSpeed;
        this.zipCode = zipCode;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getHighTemperature() {
        return highTemperature;
    }

    public void setHighTemperature(double highTemperature) {
        this.highTemperature = highTemperature;
    }

    public double getLowTemperature() {
        return lowTemperature;
    }

    public void setLowTemperature(double lowTemperature) {
        this.lowTemperature = lowTemperature;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getPrecipitation() {
        return Precipitation;
    }

    public void setPrecipitation(double preception) {
        this.Precipitation = preception;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }



}
