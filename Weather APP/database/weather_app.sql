create database weather_app;
use weather_app;
create table City(
	cityId int primary key not null,
    cityName varchar(255),
    currentTemperature int,
    currentHumidity int,
    currentWindSpeed int
);
create table CityHistory(
	historicalDataId int primary key not null,
    cityId int,
    eventDate date, 
    temperature int,
	foreign key (cityId) references City(cityId)
);