package repository;

import exception.InvalidCityNameException;
import model.City;
import model.State;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class StateRepository {
    private final Map<String, State> states;

    public StateRepository() {
        this.states = new HashMap<>();
    }

    public void addState(String stateName) {
        states.put(stateName, new State(stateName));
    }

    public void addStateWithCities(String stateName, Map<String, City> cities) {
        states.put(stateName, new State(stateName, cities));
    }

    public State getState(String stateName) {
        return states.get(stateName);
    }

    public void deleteState(String stateName) {
        states.remove(stateName);
    }

    public Map<String, State> getAllStates() {
        return Collections.unmodifiableMap(states);
    }

    public void updateStateName(String oldState, String newState) throws InvalidCityNameException {
        State state = states.remove(oldState);
        state.setName(newState);
        states.put(newState, state);
    }
}
