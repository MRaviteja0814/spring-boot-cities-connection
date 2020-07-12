package com.challenge.connection.cities.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;

@Component
public class CitiesConnectionDataImpl implements CitiesConnectionData {

    private final Map<String, List<String>> citiesConnectionDataMap;

    @Autowired
    public CitiesConnectionDataImpl(@Qualifier("citiesConnectionData") Map<String, List<String>> citiesConnectionData) {
        citiesConnectionDataMap = citiesConnectionData;
    }

    @Override
    public boolean isCitiesConnected(String city1, String city2) {
        return doesConnectionExists(city1, city2) ? true : doesConnectionExists(city2, city1);
    }

    private boolean doesConnectionExists(String source, String destination) {
        List<String> connections = citiesConnectionDataMap.get(source);
        if (CollectionUtils.isEmpty(connections)) return false;
        return connections.contains(destination);
    }
}
