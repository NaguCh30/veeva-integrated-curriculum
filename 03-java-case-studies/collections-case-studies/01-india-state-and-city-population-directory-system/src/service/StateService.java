package service;

import exception.*;
import model.City;
import model.CitySearchResult;
import model.State;
import model.StatePopulationSummary;
import repository.StateRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StateService {
    private final StateRepository stateRepository;

    public StateService(StateRepository stateRepository) {
        this.stateRepository = stateRepository;
    }

    public void addState(String stateName) throws InvalidStateNameException,
            DupliacteStateException {

        if (stateName == null || stateName.isBlank()) {
            throw new InvalidStateNameException("Please provide a valid state name to add.");
        }
        State existingState = stateRepository.getState(stateName);
        if ((existingState != null) || stateName.strip().equalsIgnoreCase(existingState.getName().strip())) {
            throw new DupliacteStateException("State with name " + stateName + " already existed!");
        }

        stateRepository.addState(stateName);
    }

    public State getState(String stateName) throws StateNotFoundException {
        State existingState = stateRepository.getState(stateName);

        if (existingState == null) {
            throw new StateNotFoundException("State with name " + stateName + " Not Found!");
        }

        return existingState;
    }

    public void addStateWithCities(String stateName, Map<String, City> cities) throws InvalidStateNameException,
            InvalidCitiesWithStateException,
            DupliacteStateException {

        if (stateName == null || stateName.isBlank()) {
            throw new InvalidStateNameException("Please provide a valid state name to add.");
        }

        State existingState = stateRepository.getState(stateName);
        if (existingState != null) {
            throw new DupliacteStateException("State with name " + stateName + " already existed!");
        }

        if (cities == null || cities.isEmpty()) {
            throw new InvalidCitiesWithStateException("Please provide valid cities information for this state, or else create state only.");
        }

        stateRepository.addStateWithCities(stateName, cities);
    }

    public void deleteState(String stateName) throws StateNotFoundException {
        State existingState = stateRepository.getState(stateName);
        if (existingState == null) {
            throw new StateNotFoundException("State with name " + stateName + " not found!");
        }
        stateRepository.deleteState(stateName);
    }

    public void updateStateName(String oldState, String newState) throws InvalidStateNameException, StateNotFoundException, DupliacteStateException, InvalidCityNameException {
        if (oldState == null || oldState.isBlank()) {
            throw new InvalidStateNameException("Please provide valid state name");
        }

        State existingState = stateRepository.getState(oldState);
        if (existingState == null) {
            throw new StateNotFoundException("State with name " + oldState + " not found!");
        }

        if (stateRepository.getState(newState) != null) {
            throw new DupliacteStateException("State with name " + newState + " already exists!");
        }

        stateRepository.updateStateName(oldState, newState);
    }

    public Map<String, State> getAllStates() {
        return stateRepository.getAllStates();
    }


    public int getCityPopulation(String stateName, String cityName) throws InvalidStateNameException, InvalidCityNameException, StateNotFoundException, CityNotFoundException {
        if (stateName == null ||  stateName.isBlank()) {
            throw new InvalidStateNameException("Please provide a valid state name");
        }

        if (cityName == null || cityName.isBlank()) {
            throw new InvalidCityNameException("Please provide a valid city name");
        }

        State existingState = stateRepository.getState(stateName);
        if (existingState == null) {
            throw new StateNotFoundException("State with name " + stateName + " not found!");
        }

        City city = existingState.getCity(cityName);
        if (city == null) {
            throw new CityNotFoundException("City '" + cityName + "' in State '" + stateName + "' not found!");
        }

        return city.getPopulation();
    }

    public StatePopulationSummary getStatePopulationSummary(String stateName)
            throws InvalidStateNameException, StateNotFoundException {

        if (stateName == null || stateName.isBlank()) {
            throw new InvalidStateNameException("Please provide a valid state name.");
        }

        State existingState = stateRepository.getState(stateName);

        if (existingState == null) {
            throw new StateNotFoundException("State with name " + stateName + " not found!");
        }

        Map<String, City> cities = existingState.getAllCities();

        long totalPopulation = cities.values()
                .stream()
                .mapToLong(City::getPopulation)
                .sum();

        return new StatePopulationSummary(existingState.getName(), cities, totalPopulation
        );
    }

    public List<CitySearchResult> searchCity(String cityName)
            throws InvalidCityNameException {

        if (cityName == null || cityName.isBlank()) {
            throw new InvalidCityNameException("Please provide a valid city name.");
        }

        List<CitySearchResult> results = new ArrayList<>();

        for (State state : stateRepository.getAllStates().values()) {

            City city = state.getCity(cityName);

            if (city != null) {
                results.add(
                        new CitySearchResult(city.getName(), state.getName(), city.getPopulation())
                );
            }
        }

        return results;
    }

    public List<CitySearchResult> getCitiesAbovePopulation(long threshold) {

        List<CitySearchResult> results = new ArrayList<>();

        for (State state : stateRepository.getAllStates().values()) {

            for (City city : state.getAllCities().values()) {

                if (city.getPopulation() > threshold) {

                    results.add(
                            new CitySearchResult(city.getName(), state.getName(), city.getPopulation()
                            )
                    );
                }
            }
        }

        return results;
    }


    public void addCity(String stateName, String cityName, int population)
            throws InvalidStateNameException, InvalidCityNameException,
            InvalidPopulationException, StateNotFoundException, DuplicateCityException {

        if (stateName == null || stateName.isBlank()) {
            throw new InvalidStateNameException("Invalid state name.");
        }

        if (cityName == null || cityName.isBlank()) {
            throw new InvalidCityNameException("Invalid city name.");
        }

        State state = getState(stateName);

        if (state.getCity(cityName) != null) {
            throw new IllegalArgumentException("City already exists in this state.");
        }

        state.addCity(cityName, population);
    }


    public void updateCityPopulation(String stateName, String cityName, int population)
            throws InvalidStateNameException, InvalidCityNameException,
            InvalidPopulationException, StateNotFoundException, CityNotFoundException {

        State state = getState(stateName);

        if (cityName == null || cityName.isBlank()) {
            throw new InvalidCityNameException("Invalid city name.");
        }

        if (state.getCity(cityName) == null) {
            throw new CityNotFoundException("City not found: " + cityName);
        }

        state.updatePopulation(cityName, population);
    }


    public void deleteCity(String stateName, String cityName)
            throws InvalidStateNameException, InvalidCityNameException,
            StateNotFoundException, CityNotFoundException {

        State state = getState(stateName);
        state.deleteCity(cityName);
    }
}
