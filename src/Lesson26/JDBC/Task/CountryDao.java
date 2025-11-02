package Lesson26.JDBC.Task;

import Lesson26.JDBC.Example.Employee;
import Lesson26.JDBC.Homework.Continent;
import org.checkerframework.checker.units.qual.C;

import java.sql.*;

public class CountryDao {
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "postgres";

    public Country getById(int id) {
        String sql = """
            SELECT countries.id AS country_id,
                   countries.name AS country_name,
                   continents .id AS continent_id,
                   continents .name AS continent_name
            FROM countries 
            JOIN continents  ON countries.continent_id = continents.id
            WHERE countries.id = ?;
        """;

        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int countryId = rs.getInt("country_id");
                String countryName = rs.getString("country_name");

                int continentId = rs.getInt("continent_id");
                String continentName = rs.getString("continent_name");

                return new Country(countryId, countryName, new Continent(continentId, continentName));
            } else {
                return null;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
