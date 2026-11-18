package model;

import exception.InvalidCityNameException;
import exception.InvalidPopulationException;

public class City {
    private String name;
    private int population;

    public City(String name, int population) throws InvalidCityNameException, InvalidPopulationException {
        if (name == null || name.isBlank()) {
            throw new InvalidCityNameException("Provide a valid city name.");
        }
        if (population <= 0) {
            throw new InvalidPopulationException("Provide valid population for the city");
        }

        this.name = name;
        this.population = population;
    }

    public String getName() {
        return name;
    }

    public int getPopulation() {
        return population;
    }

    public void setName(String name) throws InvalidCityNameException {
        if (name == null || name.isBlank()) {
            throw new InvalidCityNameException("Please provide a valid city name");
        }
        this.name = name;
    }

    public void setPopulation(int population) throws InvalidPopulationException {
        if (population <= 0) {
            throw new InvalidPopulationException("Please provide a valid population count.");
        }
        this.population = population;
    }
}
