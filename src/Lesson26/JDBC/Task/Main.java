package Lesson26.JDBC.Task;

public class Main {
    public static void main(String[] args) {
        CountryDao countryDao = new CountryDao();

        Country country = countryDao.getById(1);
        System.out.println(country.getName()); // Германия
        System.out.println(country.getContinent().getName()); // Европа

    }
}
