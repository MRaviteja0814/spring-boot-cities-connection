package com.challenge.connection.cities.data;

import com.challenge.connection.cities.model.City;
import com.challenge.connection.cities.struct.CityGraph;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;


@Component
public class CitiesConnectionDataImpl implements CitiesConnectionData {

    private final CityGraph cityGraph;

    @Autowired
    public CitiesConnectionDataImpl(@Qualifier("cityConnectionsGraph") CityGraph cityGraph) {
        this.cityGraph = cityGraph;
    }

    /**
     * Using the City connection Graph data structure. Breadth First traversal is been used to navigate the graph.
     *
     * @param origin
     * @param destination
     * @return whether connection exists.
     */
    @Override
    public boolean isCitiesConnected(String origin, String destination) {

        //Breadth first traversal is been used to navigate.
        LinkedList<City> citiesToBeSearched = new LinkedList<>();

        citiesToBeSearched.add(new City(origin));

        //visited cities are added to avoid circular lookup.
        Set<City> visitedCities = new HashSet<>();

        City city;

        while (citiesToBeSearched.size() != 0) {
            // always get the first element in the linked list
            // and perform the breadth search.
            city = citiesToBeSearched.getFirst();

            //if the city is already visited and destination match is not found out skip the traversal
            if (!visitedCities.contains(city)) {

                //get the city connections for the current city.
                List<City> cityConnections = cityGraph.getCitiesConnectionGraph().get(city);

                if (!CollectionUtils.isEmpty(cityConnections)) {
                    //verify whether destination city connections exists.
                    //if exists return true.
                    if (cityConnections.contains(new City(destination))) return true;

                    //if not add that city to the visited city
                    visitedCities.add(city);
                    //since we follow breadth first, add the connected cities to the to be search list
                    //and perform the breadth traversal.
                    citiesToBeSearched.addAll(cityConnections);
                }
            }
            //remove the visited city from the linked list.
            citiesToBeSearched.removeFirst();
        }

        return false;
    }
}
