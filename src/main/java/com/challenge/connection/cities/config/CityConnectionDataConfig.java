package com.challenge.connection.cities.config;

import com.challenge.connection.cities.struct.CityGraph;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

@Configuration
public class CityConnectionDataConfig {

    private final ResourceLoader resourceLoader;

    @Autowired
    public CityConnectionDataConfig(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @Bean("cityConnectionsGraph")
    public CityGraph cityConnectionsGraph() {

        Resource resource = resourceLoader.getResource("classpath:city.txt");
        CityGraph cityGraph = new CityGraph();

        try (Stream<String> fileStream = Files.lines(Paths.get(resource.getURI()))) {

            fileStream.forEach(line -> {
                String[] cityConnectionArr = line.split(",");
                cityGraph.addCityConnection(cityConnectionArr[0], cityConnectionArr[1]);
            });

        } catch (IOException e) {
            //TODO: write logger
            e.printStackTrace();
        }

        return cityGraph;
    }
}
