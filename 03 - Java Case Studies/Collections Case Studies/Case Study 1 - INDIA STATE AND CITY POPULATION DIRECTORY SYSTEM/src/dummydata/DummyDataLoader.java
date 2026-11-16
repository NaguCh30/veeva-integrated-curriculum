package dummydata;

import exception.*;
import model.City;
import service.StateService;

import java.util.HashMap;
import java.util.Map;

public class DummyDataLoader {

    private final StateService stateService;

    public DummyDataLoader(StateService stateService) {
        this.stateService = stateService;
    }

    public void loadData() throws InvalidCityNameException,
            InvalidPopulationException,
            InvalidStateNameException,
            InvalidCitiesWithStateException,
            DupliacteStateException {

        // Andhra Pradesh
        Map<String, City> andhraCities = new HashMap<>();

        andhraCities.put("Visakhapatnam",
                new City("Visakhapatnam", 2000000));

        andhraCities.put("Vijayawada",
                new City("Vijayawada", 1000000));

        andhraCities.put("Guntur",
                new City("Guntur", 700000));

        andhraCities.put("Tadepalligudem",
                new City("Tadepalligudem", 50000));

        stateService.addStateWithCities("Andhra Pradesh", andhraCities);


        // Telangana
        Map<String, City> telanganaCities = new HashMap<>();

        telanganaCities.put("Hyderabad",
                new City("Hyderabad", 10000000));

        telanganaCities.put("Warangal",
                new City("Warangal", 800000));

        telanganaCities.put("Karimnagar",
                new City("Karimnagar", 300000));

        stateService.addStateWithCities("Telangana", telanganaCities);


        // Karnataka
        Map<String, City> karnatakaCities = new HashMap<>();

        karnatakaCities.put("Bengaluru",
                new City("Bengaluru", 8500000));

        karnatakaCities.put("Mysuru",
                new City("Mysuru", 900000));

        karnatakaCities.put("Mangaluru",
                new City("Mangaluru", 500000));

        stateService.addStateWithCities("Karnataka", karnatakaCities);


        // Tamil Nadu
        Map<String, City> tamilNaduCities = new HashMap<>();

        tamilNaduCities.put("Chennai",
                new City("Chennai", 7000000));

        tamilNaduCities.put("Coimbatore",
                new City("Coimbatore", 1500000));

        tamilNaduCities.put("Madurai",
                new City("Madurai", 1500000));

        stateService.addStateWithCities("Tamil Nadu", tamilNaduCities);


        System.out.println("Data loaded successfully!");
    }
}