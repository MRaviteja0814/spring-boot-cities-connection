package com.challenge.connection.cities.struct;

import com.challenge.connection.cities.model.City;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class constructs the Graph data structure.
 * It's a bi-directional graph.
 *
 */
public class CityGraph {

    private Map<City, List<City>> citiesConnectionGraph;

    public CityGraph() {
        citiesConnectionGraph = new HashMap<>();
    }

    public void addCityConnection(String city1, String city2) {

        if (!StringUtils.isEmpty(city1) && !StringUtils.isEmpty(city2)) {
            City c1 = new City(city1);
            City c2 = new City(city2);

            addNewCity(c1);
            addNewCity(c2);

            citiesConnectionGraph.get(c1).add(c2);
            citiesConnectionGraph.get(c2).add(c1);
        }
    }

    public void addNewCity(City city) {
        if (city != null) {
            citiesConnectionGraph.putIfAbsent(city, new ArrayList<>());
        }
    }

    public Map<City, List<City>> getCitiesConnectionGraph() {
        return citiesConnectionGraph;
    }
}
