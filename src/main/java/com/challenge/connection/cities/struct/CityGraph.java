package com.challenge.connection.cities.struct;

import com.challenge.connection.cities.model.City;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CityGraph {

    private Map<City, List<City>> citiesConnectionGraph;

    public CityGraph() {
        citiesConnectionGraph = new HashMap<>();
    }

    public void addNewCity(City city) {
        citiesConnectionGraph.putIfAbsent(city, new ArrayList<>());
    }

    public void addCityConnection(String city1, String city2) {
        //Todo add comments
        City c1 = new City(city1.trim().toLowerCase());
        City c2 = new City(city2.trim().toLowerCase());

        addNewCity(c1);
        addNewCity(c2);


        citiesConnectionGraph.get(c1).add(c2);
        citiesConnectionGraph.get(c2).add(c1);
    }

    public Map<City, List<City>> getCitiesConnectionGraph() {
        return citiesConnectionGraph;
    }
}
