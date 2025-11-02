package Lesson26.JDBC.Task;

import Lesson26.JDBC.Homework.Continent;

import java.sql.*;

public class Country {
    int id;
    String name;
    Continent continent;
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "postgres";

    public Country(int id, String name, Continent continent) {
        this.id = id;
        this.name = name;
        this.continent = continent;
    }
    public Country(String name) {
        this.name = name;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Continent getContinent() {
        return continent;
    }

    public void setContinent(Continent continent) {
        this.continent = continent;
    }
}
