import com.devart.weather.Menu;

import java.sql.SQLException;


public class Main {
    public static void main(String[] args) throws SQLException {
        Menu.mainMenu();
//        CityHistory cityHistory = new CityHistory();
//        Connection connection = ConnectionDB.getConnection();
//        System.out.println(cityHistory.getAllCityHistories(connection));
//        System.out.println("ID History: ");
//        cityHistory.setHistoricalDataId(sc.nextInt());
//        System.out.println("ID City: ");
//        cityHistory.setCityId(sc.nextInt());
//        System.out.println("Date Event: ");
//        String dateEven = sc.next();
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//        LocalDate eventDate = LocalDate.parse(dateEven, formatter);
//        cityHistory.setEventDate(eventDate);
//        System.out.println("Temperature: ");
//        cityHistory.setTemperature(sc.nextInt());
//        cityHistory.addCityHistory(connection);
    }
}
