package com.devart.weather;

import java.time.LocalDate;

public class CityHistory {
    // fields
    private int historicalDataId;
    private City city;
    private LocalDate eventDate;
    private int temperature;

    // constructor
    public CityHistory(int historicalDataId, City city, LocalDate eventDate, int temperature) {
        this.historicalDataId = historicalDataId;
        this.city = city;
        this.eventDate = eventDate;
        this.temperature = temperature;
    }

    // methods
    public int getHistoricalDataId() {
        return historicalDataId;
    }

    public void setHistoricalDataId(int historicalDataId) {
        this.historicalDataId = historicalDataId;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public LocalDate getEventDate() {
        return eventDate;
    }

    public void setEventDate(LocalDate eventDate) {
        this.eventDate = eventDate;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }
}
