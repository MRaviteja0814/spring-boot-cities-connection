package com.challenge.connection.cities.controller;

import com.challenge.connection.cities.service.CitiesConnectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CitiesConnectionController {

    private final CitiesConnectionService citiesConnectionService;

    private static final String YES = "yes";
    private static final String NO = "no";

    @Autowired
    public CitiesConnectionController(CitiesConnectionService citiesConnectionService) {
        this.citiesConnectionService = citiesConnectionService;
    }

    @GetMapping("/connected")
    public String cityConnections(@RequestParam String origin, @RequestParam String destination) {

        boolean isConnected = citiesConnectionService.isCitiesConnected(origin, destination);

        return isConnected ? YES : NO;
    }
}
