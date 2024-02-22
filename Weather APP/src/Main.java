import com.devart.weather.CityHistory;

import java.sql.Connection;
import java.sql.SQLException;

import static com.devart.weather.ConnectionDB.getConnection;

public class Main {
    public static void main(String[] args) throws SQLException {
        System.out.println("Weather APP\n");
        CityHistory cityHistory = new CityHistory();
        Connection connection = getConnection();
        System.out.println(cityHistory.getAllCityHistories(connection));
    }
}
