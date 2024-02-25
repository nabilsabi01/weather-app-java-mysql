package com.devart.weather;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Menu {
    public static City city;
    public static CityHistory cityHistory;
    public static Scanner sc = new Scanner(System.in);
    public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public static String dateEven;
    private static LocalDate eventDate;
    public static void cityManagementMenu() throws SQLException {
            while (true) {
                System.out.println("||=================================================================================||");
                System.out.println("||------------|                    Gestion des ville                   |-----------||");
                System.out.println("||=================================================================================||");
                System.out.println("||------------|        1: Création d'un Enregistrement ville           |-----------||");
                System.out.println("||------------|        2: Lecture des villes                           |-----------||");
                System.out.println("||------------|        3: Mise à Jour d'un Enregistrement ville        |-----------||");
                System.out.println("||------------|        4: Suppression d'un Enregistrement ville        |-----------||");
                System.out.println("||------------|        5: Quitter application                          |-----------||");
                System.out.println("||=================================================================================||");
                System.out.println("Enter votre choix: ");
                int choice = sc.nextInt();
                switch (choice) {
                    case 1:
                        city = new City();
                        System.out.println("Identifiant de la ville: ");
                        city.setCityId(sc.nextInt());
                        System.out.println("Nom de la ville: ");
                        city.setCityName(sc.next());
                        System.out.println("Température actuelle: ");
                        city.setCurrentTemperature(sc.nextInt());
                        System.out.println("Taux d'humidité actuelle: ");
                        city.setCurrentHumidity(sc.nextInt());
                        System.out.println("Vitesse du vent actuelle: ");
                        city.setCurrentWindSpeed(sc.nextInt());
                        city.addCity(ConnectionDB.getConnection());
                        break;
                    case 2:
                        city = new City();
                        System.out.println(city.getAllCities(ConnectionDB.getConnection()));
                        break;
                    case 3:
                        city = new City();
                        System.out.println("Identifiant de la ville: ");
                        city.setCityId(sc.nextInt());
                        System.out.println("Nom de la ville: ");
                        city.setCityName(sc.next());
                        System.out.println("Température actuelle: ");
                        city.setCurrentTemperature(sc.nextInt());
                        System.out.println("Taux d'humidité actuelle: ");
                        city.setCurrentHumidity(sc.nextInt());
                        System.out.println("Vitesse du vent actuelle: ");
                        city.setCurrentWindSpeed(sc.nextInt());
                        city.updateCity(ConnectionDB.getConnection());
                        break;
                    case 4:
                        city = new City();
                        System.out.println("Identifiant de la ville: ");
                        int id = sc.nextInt();
                        city.deleteCity(id, ConnectionDB.getConnection());
                        break;
                    case 5:
                        return;
                    default:
                        System.out.println("Choix invalide.");
                }
            }
    }

    public  static void cityHistoryMenu() throws SQLException {
        System.out.println("||======================================================================================||");
        System.out.println("||------------|                    Gestion des ville histrorique            |-----------||");
        System.out.println("||======================================================================================||");
        System.out.println("||------------|        1: Création d'un Enregistrement Historique           |-----------||");
        System.out.println("||------------|        2: Lecture de l'Historique                           |-----------||");
        System.out.println("||------------|        3: Mise à Jour d'un Enregistrement Historique        |-----------||");
        System.out.println("||------------|        4: Suppression d'un Enregistrement Historique        |-----------||");
        System.out.println("||------------|        5: Quitter application                               |-----------||");
        System.out.println("||======================================================================================||");
        System.out.println("Enter votre choix: ");
        int choice = sc.nextInt();
        switch (choice) {
            case 1:
                cityHistory = new CityHistory();
                System.out.println("Identifiant unique pour les données historiques: ");
                cityHistory.setHistoricalDataId(sc.nextInt());
                System.out.println("Identifiant de la ville associée: ");
                cityHistory.setCityId(sc.nextInt());
                System.out.println("Date de l'événement météorologique historique ");
                dateEven = sc.next();
                eventDate = LocalDate.parse(dateEven, formatter);
                cityHistory.setEventDate(eventDate);
                System.out.println("Température historique: ");
                cityHistory.setTemperature(sc.nextInt());
                cityHistory.addCityHistory(ConnectionDB.getConnection());
                break;
            case 2:
                cityHistory = new CityHistory();
                System.out.println(cityHistory.getAllCityHistories(ConnectionDB.getConnection()));
                break;
            case 3:
                System.out.println("Identifiant unique pour les données historiques: ");
                cityHistory.setHistoricalDataId(sc.nextInt());
                System.out.println("Identifiant de la ville associée: ");
                cityHistory.setCityId(sc.nextInt());
                System.out.println("Date de l'événement météorologique historique ");
                dateEven = sc.next();
                eventDate = LocalDate.parse(dateEven, formatter);
                cityHistory.setEventDate(eventDate);
                System.out.println("Température historique: ");
                cityHistory.setTemperature(sc.nextInt());
                cityHistory.updateCityHistory(ConnectionDB.getConnection());
                break;
            case 4:
                cityHistory = new CityHistory();
                System.out.println("Identifiant de la historiques: ");
                int id = sc.nextInt();
                cityHistory.deleteCityHistory(id, ConnectionDB.getConnection());
                break;
            case 5:
                return;
            default:
                System.out.println("Choix invalide.");
        }
    }

    public  static void mainMenu() throws SQLException {
        while (true){
            System.out.println("||======================================================================================||");
            System.out.println("||------------|                          Gestion de météo                   |-----------||");
            System.out.println("||======================================================================================||");
            System.out.println("||------------|        1: Gestion des ville                                 |-----------||");
            System.out.println("||------------|        2: Gestion des ville histrorique                     |-----------||");
            System.out.println("||------------|        3: Quitter application                               |-----------||");
            System.out.println("||======================================================================================||");
            int choice = sc.nextInt();
            switch (choice){
                case 1:
                    cityManagementMenu();
                    break;
                case 2:
                    cityHistoryMenu();
                    break;
                case 3:
                    System.out.println("Existing...");
                    return;
                default:
                    System.out.println("choix invalid!!!");
            }
        }
    }
}
