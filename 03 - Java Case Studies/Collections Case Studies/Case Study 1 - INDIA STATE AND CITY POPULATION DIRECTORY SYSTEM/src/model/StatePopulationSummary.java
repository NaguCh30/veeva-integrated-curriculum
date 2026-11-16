package model;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class StatePopulationSummary {

    private final String stateName;
    private final Map<String, City> cities;
    private final long totalPopulation;

    public StatePopulationSummary(String stateName, Map<String, City> cities,
                                  long totalPopulation) {
        this.stateName = stateName;
        this.cities = Collections.unmodifiableMap(new HashMap<>(cities));
        this.totalPopulation = totalPopulation;
    }

    public String getStateName() {
        return stateName;
    }

    public Map<String, City> getCities() {
        return cities;
    }

    public long getTotalPopulation() {
        return totalPopulation;
    }
}