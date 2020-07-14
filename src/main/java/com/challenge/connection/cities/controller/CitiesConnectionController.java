package com.challenge.connection.cities.controller;

import com.challenge.connection.cities.service.CitiesConnectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

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
    public String cityConnections(@NotNull @RequestParam String origin, @NotNull @RequestParam String destination) {
        //Request validation
        if (!sanitizeRequest(origin, destination)) return NO;

        boolean isConnected = citiesConnectionService.isCitiesConnected(origin, destination);

        return isConnected ? YES : NO;
    }

    /**
     * This method validates the request.
     *
     * @param origin
     * @param destination
     * @return
     */
    private boolean sanitizeRequest(String origin, String destination) {

        if (origin.isEmpty() || destination.isEmpty()) return false;
        if (origin.equalsIgnoreCase(destination)) return false;
        return true;
    }
}
