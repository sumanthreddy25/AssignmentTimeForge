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
    private int humidity;
    private String description;
    private String zipCode;
    private Date date;
    private double preception;
    private String service;


    public WeatherData() {

    }

    public WeatherData(double highTemperature, double lowTemperature, int humidity, String description,
                       String zipCode, Date date, double preception) {
        super();
        this.highTemperature = highTemperature;
        this.lowTemperature = lowTemperature;
        this.humidity = humidity;
        this.description = description;
        this.zipCode = zipCode;
        this.date = date;
        this.preception = preception;
    }

    public double getPreception() {
        return preception;
    }

    public void setPreception(double preception) {
        this.preception = preception;
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

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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


}
