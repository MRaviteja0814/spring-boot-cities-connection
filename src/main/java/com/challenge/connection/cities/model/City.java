package com.challenge.connection.cities.model;

import java.util.Objects;

public class City {

    private final String name;

    public City(String name) {
        //the city string is trimmed and converted to lower case for consistency
        this.name = name.trim().toLowerCase();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        City city = (City) o;
        return name.equals(city.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
