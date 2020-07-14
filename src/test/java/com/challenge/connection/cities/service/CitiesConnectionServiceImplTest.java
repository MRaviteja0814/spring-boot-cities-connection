package com.challenge.connection.cities.service;

import com.challenge.connection.cities.data.CitiesConnectionData;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CitiesConnectionServiceImplTest {

    @MockBean
    private CitiesConnectionData citiesConnectionData;

    private CitiesConnectionService citiesConnectionService;

    @BeforeEach
    void setUp() {
        citiesConnectionService = new CitiesConnectionServiceImpl(citiesConnectionData);
    }

    @Test
    void isCitiesConnected() {
        Mockito.when(citiesConnectionData.isCitiesConnected("Boston", "Newark")).thenReturn(true);
        assertTrue(citiesConnectionService.isCitiesConnected("Boston", "Newark"));
    }

    @AfterEach
    void tearDown() {
        citiesConnectionData = null;
        citiesConnectionService = null;
    }
}