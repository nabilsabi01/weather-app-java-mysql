package com.devart.weather;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class City {
    // fields
    private int cityId;
    private String cityName;
    private int currentTemperature;
    private int currentHumidity;
    private int currentWindSpeed;

    public City() {
    }

    // constructor
    public City(int cityId, String cityName, int currentTemperature, int currentHumidity, int currentWindSpeed) {
        this.cityId = cityId;
        this.cityName = cityName;
        this.currentTemperature = currentTemperature;
        this.currentHumidity = currentHumidity;
        this.currentWindSpeed = currentWindSpeed;
    }


    // methods
    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public int getCurrentTemperature() {
        return currentTemperature;
    }

    public void setCurrentTemperature(int currentTemperature) {
        this.currentTemperature = currentTemperature;
    }

    public int getCurrentHumidity() {
        return currentHumidity;
    }

    public void setCurrentHumidity(int currentHumidity) {
        this.currentHumidity = currentHumidity;
    }

    public int getCurrentWindSpeed() {
        return currentWindSpeed;
    }

    public void setCurrentWindSpeed(int currentWindSpeed) {
        this.currentWindSpeed = currentWindSpeed;
    }

    // add city
    public void addCity(Connection connection) throws SQLException {
        String sql = "INSERT INTO City (cityId, cityName, currentTemperature, currentHumidity, currentWindSpeed) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, getCityId());
        statement.setString(2, getCityName());
        statement.setInt(3, getCurrentTemperature());
        statement.setInt(4, getCurrentHumidity());
        statement.setInt(5, getCurrentWindSpeed());
        int r = statement.executeUpdate();
        if (r>0){
            System.out.println("La ville ajoutée avec succès!");
        }
        else {
            System.out.println("La ville n'a pas ajouté");
        }
        connection.close();
        statement.close();
    }

    // display all cities
    public List<City> getAllCities(Connection connection) throws SQLException {
        List<City> cities = new ArrayList<>();
        String sql = "SELECT * FROM City";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            int cityId = resultSet.getInt("cityId");
            String cityName = resultSet.getString("cityName");
            int currentTemperature = resultSet.getInt("currentTemperature");
            int currentHumidity = resultSet.getInt("currentHumidity");
            int currentWindSpeed = resultSet.getInt("currentWindSpeed");
            cities.add(new City(cityId, cityName, currentTemperature, currentHumidity, currentWindSpeed));
        }
        connection.close();
        statement.close();
        resultSet.close();
        return cities;
    }

    // search by id
    public City searchCityById(int idCity, Connection connection) throws SQLException {
        String sql = "SELECT * FROM City WHERE cityId = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, idCity);
        ResultSet rs = ps.executeQuery();
        City city = null;
        if (rs.next()) {
            city = new City();
            city.setCityId(rs.getInt("cityId"));
            city.setCityName(rs.getString("cityName"));
            city.setCurrentTemperature(rs.getInt("currentTemperature"));
            city.setCurrentHumidity(rs.getInt("currentHumidity"));
            city.setCurrentWindSpeed(rs.getInt("currentWindSpeed"));
        }
        return city;
    }

    // update city
    public void updateCity(Connection connection) throws SQLException {
        String sql = "UPDATE City SET cityName = ?, currentTemperature = ?, currentHumidity = ?, currentWindSpeed = ? WHERE cityId = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, getCityName());
        statement.setInt(2, getCurrentTemperature());
        statement.setInt(3, getCurrentHumidity());
        statement.setInt(4, getCurrentWindSpeed());
        statement.setInt(5, getCityId());
        statement.executeUpdate();
        connection.close();
        statement.close();
        System.out.println("La ville a été mise à jour avec succès!");

    }

    // delete city
    public void deleteCity(int cityId, Connection connection) throws SQLException {
        String sql = "DELETE FROM City WHERE cityId = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, cityId);
        statement.executeUpdate();
        connection.close();
        statement.close();
        System.out.println("La ville a été supprimée avec succès !");
    }

    // method to string
    @Override
    public String toString() {
        return "|-----------------------------------------------|"+
                "\nCity ID: " + cityId +
                "\nCity Name: " + cityName +
                "\nCurrent Temperature: " + currentTemperature +
                "\nCurrent Humidity: " + currentHumidity +
                "\nCurrent Wind Speed: " + currentWindSpeed +
                "|-----------------------------------------------|";
    }
}