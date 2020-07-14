package com.challenge.connection.cities.data;

import com.challenge.connection.cities.struct.CityGraph;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CitiesConnectionDataImplTest {

    private CitiesConnectionData citiesConnectionData;

    @BeforeEach
    void setUp() {
        CityGraph cityGraph = new CityGraph();

        cityGraph.addCityConnection("Boston", "New York");
        cityGraph.addCityConnection("Philadelphia", "Newark");
        cityGraph.addCityConnection("Newark", "Boston");
        cityGraph.addCityConnection("Trenton", "Albany");

        citiesConnectionData = new CitiesConnectionDataImpl(cityGraph);

    }

    @Test
    void isCitiesConnected_direct_connection() {
        assertTrue(citiesConnectionData.isCitiesConnected("Boston", "New York"));
    }

    @Test
    void isCitiesConnected_transitive_connection() {
        assertTrue(citiesConnectionData.isCitiesConnected("Boston", "Philadelphia"));
    }

    @Test
    void isCitiesConnected_not_connected() {
        assertFalse(citiesConnectionData.isCitiesConnected("Boston", "Albany"));
    }


    @AfterEach
    void tearDown() {
        citiesConnectionData = null;
    }

}