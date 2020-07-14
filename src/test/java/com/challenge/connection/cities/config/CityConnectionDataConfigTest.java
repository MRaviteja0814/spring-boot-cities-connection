package com.challenge.connection.cities.config;

import com.challenge.connection.cities.model.City;
import com.challenge.connection.cities.struct.CityGraph;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassRelativeResourceLoader;
import org.springframework.core.io.ResourceLoader;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class CityConnectionDataConfigTest {

    @Autowired
    private ResourceLoader resourceLoader;

    private CityConnectionDataConfig connectionDataConfig;
    private City cityBoston;
    private City cityNewYork;
    private City cityNewark;

    @BeforeEach
    void setUp() {

        connectionDataConfig = new CityConnectionDataConfig(resourceLoader);
        cityBoston = new City("Boston");
        cityNewark = new City("Newark");
        cityNewYork = new City("New York");
    }


    @Test
    void cityConnectionsGraphForSuccess() throws Exception {

        CityGraph cityGraph = connectionDataConfig.cityConnectionsGraph();
        assertNotNull(cityGraph);

        assertNotNull(cityGraph.getCitiesConnectionGraph());
        assertTrue(cityGraph.getCitiesConnectionGraph().containsKey(cityBoston));
        assertTrue(cityGraph.getCitiesConnectionGraph().containsKey(cityNewYork));

        assertTrue(cityGraph.getCitiesConnectionGraph().get(cityNewYork).contains(cityBoston));
        assertTrue(cityGraph.getCitiesConnectionGraph().get(cityBoston).contains(cityNewark));

    }


    @AfterEach
    void tearDown() {
        connectionDataConfig = null;
        resourceLoader = null;

    }
}