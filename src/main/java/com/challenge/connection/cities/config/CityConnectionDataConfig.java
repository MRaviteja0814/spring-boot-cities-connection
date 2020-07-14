package com.challenge.connection.cities.config;

import com.challenge.connection.cities.struct.CityGraph;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

@Configuration
public class CityConnectionDataConfig {

    Logger logger = LoggerFactory.getLogger(CityConnectionDataConfig.class);

    private final ResourceLoader resourceLoader;

    @Autowired
    public CityConnectionDataConfig(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @Bean("cityConnectionsGraph")
    public CityGraph cityConnectionsGraph() throws Exception {
        //Classpath resource loader is used to load the city file from the classpath
        Resource resource = resourceLoader.getResource("classpath:city.txt");
        CityGraph cityGraph = new CityGraph();

        //using streams read the file content and construct a graph
        try (Stream<String> fileStream = Files.lines(Paths.get(resource.getURI()))) {

            fileStream.forEach(line -> {
                String[] cityConnectionArr = line.split(",");
                //if either origin or destination is empty donot establish the connection
                if(!StringUtils.isEmpty(cityConnectionArr[0]) || !StringUtils.isEmpty(cityConnectionArr[1]))
                cityGraph.addCityConnection(cityConnectionArr[0], cityConnectionArr[1]);
            });

        } catch (IOException e) {
            logger.error("Exception occurred while constructing city connection graph");
            throw e;
        }

        return cityGraph;
    }
}
