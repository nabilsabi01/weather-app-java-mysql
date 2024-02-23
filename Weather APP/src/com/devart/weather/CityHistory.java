package com.devart.weather;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CityHistory {
    // fields
    private int historicalDataId;
    private int cityId;
    private LocalDate eventDate;
    private int temperature;

    public CityHistory() {
    }

    // constructor
    public CityHistory(int historicalDataId, int cityId, LocalDate eventDate, int temperature) {
        this.historicalDataId = historicalDataId;
        this.cityId = cityId;
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

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
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

    // add city history
    public void addCityHistory(Connection connection) throws SQLException {
        String sql = "INSERT INTO CityHistory (historicalDataId, cityId, eventDate, temperature) VALUES (?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, getHistoricalDataId());
        statement.setInt(2, getCityId());
        statement.setDate(3, Date.valueOf(getEventDate()));
        statement.setInt(4, getTemperature());
        statement.executeUpdate();
        connection.close();
        statement.close();
        System.out.println("City history added successfully!");
    }

    // display all city histories
    public List<CityHistory> getAllCityHistories(Connection connection) throws SQLException {
        List<CityHistory> cityHistories = new ArrayList<>();
        String sql = "SELECT ch.*, c.cityName FROM CityHistory ch JOIN City c ON ch.cityId = c.cityId";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            CityHistory cityHistory = new CityHistory();
            cityHistory.setHistoricalDataId(resultSet.getInt("historicalDataId"));
            setCityId(resultSet.getInt("cityId"));
            cityHistory.setEventDate(resultSet.getDate("eventDate").toLocalDate());
            cityHistory.setTemperature(resultSet.getInt("temperature"));
            cityHistories.add(cityHistory);
        }
        connection.close();
        statement.close();
        resultSet.close();
        return cityHistories;
    }

    // update city histories
    public void updateCityHistory(Connection connection) throws SQLException {
        String sql = "UPDATE CityHistory SET cityId = ?, eventDate = ?, temperature = ? WHERE historicalDataId = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, getCityId());
        statement.setDate(2, Date.valueOf(getEventDate()));
        statement.setInt(3, getTemperature());
        statement.setInt(4, getHistoricalDataId());
        statement.executeUpdate();
        connection.close();
        statement.close();
        System.out.println("City history updated successfully!");

    }

    // delete city
    public void deleteCity(int historicalDataId, Connection connection) throws SQLException {
        String sql = "DELETE FROM CityHistory WHERE historicalDataId = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, historicalDataId);
        statement.executeUpdate();
        connection.close();
        statement.close();
        System.out.println("City history deleted successfully!");
    }

    // to string method
    @Override
    public String toString() {
        return "CityHistory{" +
                "historicalDataId=" + historicalDataId +
                ", city=" + cityId +
                ", eventDate=" + eventDate +
                ", temperature=" + temperature +
                '}';
    }
}
