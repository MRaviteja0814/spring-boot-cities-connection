package com.challenge.connection.cities.data;

import com.challenge.connection.cities.model.City;
import com.challenge.connection.cities.struct.CityGraph;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.*;

@Component
public class CitiesConnectionDataImpl implements CitiesConnectionData {

    /*private final Map<String, List<String>> citiesConnectionDataMap;

    @Autowired
    public CitiesConnectionDataImpl(@Qualifier("citiesConnectionData") Map<String, List<String>> citiesConnectionData) {
        citiesConnectionDataMap = citiesConnectionData;
    }*/

    private final CityGraph cityGraph;

    @Autowired
    public CitiesConnectionDataImpl(@Qualifier("cityConnectionsGraph") CityGraph cityGraph) {
        this.cityGraph = cityGraph;
    }

    /*@Override
    public boolean isCitiesConnected(String city1, String city2) {
        return doesConnectionExists(city1, city2) ? true : doesConnectionExists(city2, city1);
    }

    private boolean doesConnectionExists(String source, String destination) {
        List<String> connections = citiesConnectionDataMap.get(source);
        if (CollectionUtils.isEmpty(connections)) return false;
        return connections.contains(destination);
    }*/

    @Override
    public boolean isCitiesConnected(String origin, String destination) {

        LinkedList<City> citiesToBeSearched = new LinkedList<>();

        citiesToBeSearched.add(new City(origin));

        Set<City> visitedCities = new HashSet<>();

        City city;

        while (citiesToBeSearched.size() != 0) {

            city = citiesToBeSearched.getFirst();

            if (!visitedCities.contains(city)) {

                List<City> cityConnections = cityGraph.getCitiesConnectionGraph().get(city);

                if (!CollectionUtils.isEmpty(cityConnections)) {
                    if (cityConnections.contains(new City(destination))) return true;

                    visitedCities.add(city);
                    citiesToBeSearched.addAll(cityConnections);
                }
            }
            citiesToBeSearched.removeFirst();
        }

        return false;
    }
}
