package com.challenge.connection.cities.service;

import com.challenge.connection.cities.data.CitiesConnectionData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CitiesConnectionServiceImpl implements CitiesConnectionService {

    private final CitiesConnectionData citiesConnectionData;

    @Autowired
    public CitiesConnectionServiceImpl(CitiesConnectionData citiesConnectionData) {
        this.citiesConnectionData = citiesConnectionData;
    }

    @Override
    public boolean isCitiesConnected(String origin, String destination) {
        return citiesConnectionData.isCitiesConnected(origin.toLowerCase(), destination.toLowerCase());
    }
}
