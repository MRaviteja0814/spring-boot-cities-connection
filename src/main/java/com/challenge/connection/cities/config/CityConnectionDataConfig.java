package com.challenge.connection.cities.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@Configuration
public class CityConnectionDataConfig {

    private final ResourceLoader resourceLoader;

    @Autowired
    public CityConnectionDataConfig(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @Bean("citiesConnectionData")
    public Map<String, List<String>> cityConnectionsData() {

        Map<String, List<String>> cityConnectionMap = new HashMap<>();

        Resource resource = resourceLoader.getResource("classpath:city.txt");

        try (Stream<String> fileStream = Files.lines(Paths.get(resource.getURI()))) {

            fileStream.forEach(line -> {
                String[] cityConnectionArr = line.split(",");

                cityConnectionMap.compute(cityConnectionArr[0], (keyStr, cityList) -> {

                    if (cityList == null) {
                        cityList = new ArrayList<>();
                        cityList.add(cityConnectionArr[1].trim());
                    } else {
                        cityList.add(cityConnectionArr[1].trim());
                    }
                    return cityList;
                });

            });

        } catch (IOException e) {
            //TODO log it
            e.printStackTrace();
        }
        return cityConnectionMap;
    }
}
