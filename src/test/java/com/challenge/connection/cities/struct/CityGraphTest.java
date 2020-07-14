package com.challenge.connection.cities.struct;

import com.challenge.connection.cities.model.City;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class CityGraphTest {

    private CityGraph cityGraph;
    private City cityBoston;
    private City cityNewyork;

    @BeforeEach
    void setUp() {
        cityGraph = new CityGraph();
        cityBoston = new City("boston");
        cityNewyork = new City("new york");
    }

    @Test
    void addNewCityForSuccess() {
        cityGraph.addNewCity(cityBoston);

        assertTrue(cityGraph.getCitiesConnectionGraph().containsKey(cityBoston));
        assertNotNull(cityGraph.getCitiesConnectionGraph().get(cityBoston));
    }

    @Test
    void addNewCityForNullCity_Failure() {
        cityGraph.addNewCity(null);

        assertTrue(!cityGraph.getCitiesConnectionGraph().containsKey(cityBoston));
        assertNull(cityGraph.getCitiesConnectionGraph().get(cityBoston));
    }

    @Test
    void addCityConnectionForSuccess() {
        cityGraph.addCityConnection("Boston", "New York");

        assertTrue(cityGraph.getCitiesConnectionGraph().containsKey(cityBoston));
        assertTrue(cityGraph.getCitiesConnectionGraph().containsKey(cityNewyork));
        assertNotNull(cityGraph.getCitiesConnectionGraph().get(cityBoston));
        assertEquals(cityGraph.getCitiesConnectionGraph().get(cityBoston).get(0), cityNewyork);
        assertEquals(cityGraph.getCitiesConnectionGraph().get(cityNewyork).get(0), cityBoston);
    }

    @Test
    void addCityConnectionForFailure_NullCity() {
        cityGraph.addCityConnection(null, null);

        assertTrue(!cityGraph.getCitiesConnectionGraph().containsKey(cityBoston));
        assertTrue(!cityGraph.getCitiesConnectionGraph().containsKey(cityNewyork));
        assertNull(cityGraph.getCitiesConnectionGraph().get(cityBoston));
        assertNull(cityGraph.getCitiesConnectionGraph().get(cityNewyork));
    }


    @Test
    void addCityConnectionForFailure_EmptyCity() {
        cityGraph.addCityConnection("", "");

        assertTrue(!cityGraph.getCitiesConnectionGraph().containsKey(cityBoston));
        assertTrue(!cityGraph.getCitiesConnectionGraph().containsKey(cityNewyork));
        assertNull(cityGraph.getCitiesConnectionGraph().get(cityBoston));
        assertNull(cityGraph.getCitiesConnectionGraph().get(cityNewyork));
    }

    @Test
    void getCitiesConnectionGraph() {
        cityGraph.addNewCity(cityBoston);
        Map<City, List<City>> cityConnectionGraph = cityGraph.getCitiesConnectionGraph();

        assertNotNull(cityConnectionGraph);
        assertNotNull(cityConnectionGraph.containsKey(cityBoston));
    }

    @AfterEach
    void tearDown() {
        cityGraph = null;
    }
}