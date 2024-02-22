package com.devart.weather;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CityHistory {
    // fields
    private int historicalDataId;
    private City city;
    private LocalDate eventDate;
    private int temperature;

    public CityHistory() {
    }

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

    // add city history
    public void addCityHistory(CityHistory cityHistory, Connection connection) throws SQLException {
        String sql = "INSERT INTO CityHistory (historicalDataId, cityId, eventDate, temperature) VALUES (?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, cityHistory.getHistoricalDataId());
        statement.setInt(2, cityHistory.getCity().getCityId());
        statement.setDate(3, Date.valueOf(cityHistory.getEventDate()));
        statement.setInt(4, cityHistory.getTemperature());
        statement.executeUpdate();
        connection.close();
        statement.close();
        System.out.println("City history added successfully!");
    }

    // display all city histories
    public List<CityHistory> getAllCityHistories(Connection connection) throws SQLException {
        List<CityHistory> cityHistories = new ArrayList<>();
        String sql = "SELECT * FROM CityHistory ch, City c WHERE ch.cityId = c.cityId";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            CityHistory cityHistory = new CityHistory();
            city = new City();
            cityHistory.setHistoricalDataId(resultSet.getInt("historicalDataId"));
            city.setCityId(resultSet.getInt("cityId"));
            city.setCityName(resultSet.getString("cityName"));
            city.setCurrentTemperature(resultSet.getInt("currentTemperature"));
            city.setCurrentHumidity(resultSet.getInt("currentHumidity"));
            city.setCurrentWindSpeed(resultSet.getInt("currentWindSpeed"));
            cityHistory.city = city;
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
    public void updateCityHistory(CityHistory cityHistory, Connection connection) throws SQLException {
        String sql = "UPDATE CityHistory SET cityId = ?, eventDate = ?, temperature = ? WHERE historicalDataId = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, cityHistory.getCity().getCityId());
        statement.setDate(2, Date.valueOf(cityHistory.getEventDate()));
        statement.setInt(3, cityHistory.getTemperature());
        statement.setInt(4, cityHistory.getHistoricalDataId());
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
        return "\nCityHistory{" +
                "historicalDataId=" + historicalDataId +
                ", city=" + city.getCityName() +
                ", eventDate=" + eventDate +
                ", temperature=" + temperature +
                '}';
    }
}
