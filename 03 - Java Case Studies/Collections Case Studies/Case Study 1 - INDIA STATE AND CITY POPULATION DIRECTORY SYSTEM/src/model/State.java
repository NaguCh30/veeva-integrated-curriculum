package model;

import exception.CityNotFoundException;
import exception.DuplicateCityException;
import exception.InvalidCityNameException;
import exception.InvalidPopulationException;

import java.util.HashMap;
import java.util.Map;

public class State {
    private String name;
    private Map<String, City> cities;

    public State(String name) {
        // need to write code for exceptions for name in service layer

        this.name = name;
        this.cities = new HashMap<>();
    }

    public State(String name, Map<String, City> cities) {
        // check city name and etc in service layer

        this.name = name;
        this.cities = cities;
    }

    public String getName() {
        return name;
    }

    public void setName(String newName) throws InvalidCityNameException {
        if (newName == null || newName.isBlank()) {
            throw new InvalidCityNameException("Please provide a valid city name.");
        }
        this.name = newName;
    }

    public void addCity(String cityName, int population) throws InvalidCityNameException, InvalidPopulationException, DuplicateCityException {
        if (cities.containsKey(cityName)) {
            throw new DuplicateCityException("City with name " + cityName + " already exists!");
        }
        cities.put(cityName, new City(cityName, population));
    }

    public void updatePopulation(String city, int newPopulation) throws InvalidPopulationException {
        cities.get(city).setPopulation(newPopulation);
    }

    public int getCityPopulation(String city) {
        return cities.get(city).getPopulation();
    }

    public City getCity(String cityName) {
        return cities.get(cityName);
    }

    public Map<String, City> getAllCities() {
        return cities;
    }

    public void deleteCity(String city) throws InvalidCityNameException, CityNotFoundException {
        if (city == null || city.isBlank()) {
            throw new InvalidCityNameException("Provide a valid city name");
        }
        if (cities.get(city) == null) {
            throw new CityNotFoundException("City with name " + city + " not found");
        }
        cities.remove(city);
    }
}
