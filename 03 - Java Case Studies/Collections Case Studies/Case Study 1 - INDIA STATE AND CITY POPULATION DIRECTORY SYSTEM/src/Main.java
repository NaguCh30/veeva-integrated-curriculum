
import dummydata.DummyDataLoader;
import model.City;
import model.CitySearchResult;
import model.State;
import model.StatePopulationSummary;
import repository.StateRepository;
import service.StateService;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        // Create required instances
        StateRepository stateRepository = new StateRepository();
        StateService stateService = new StateService(stateRepository);

        // Load initial dummy data
        DummyDataLoader loader = new DummyDataLoader(stateService);

        try {
            loader.loadData();
        } catch (Exception e) {
            System.out.println("Failed to load dummy data: " + e.getMessage());
            return;
        }

        Scanner sc = new Scanner(System.in);

        int choice;

        do {
            displayMenu();

            try {
                System.out.print("Enter your choice: ");
                choice = Integer.parseInt(sc.nextLine());

                switch (choice) {

                    case 1 -> {
                        System.out.print("Enter state name: ");
                        String stateName = sc.nextLine();

                        stateService.addState(stateName);

                        System.out.println("State added successfully!");
                    }

                    case 2 -> {
                        System.out.print("Enter state name: ");
                        String stateName = sc.nextLine();

                        System.out.print("Enter city name: ");
                        String cityName = sc.nextLine();

                        System.out.print("Enter population: ");
                        int population = Integer.parseInt(sc.nextLine());

                        stateService.addCity(stateName, cityName, population);

                        System.out.println("City added successfully!");
                    }

                    case 3 -> {
                        System.out.print("Enter state name: ");
                        String stateName = sc.nextLine();

                        System.out.print("Enter city name: ");
                        String cityName = sc.nextLine();

                        System.out.print("Enter new population: ");
                        int population = Integer.parseInt(sc.nextLine());

                        stateService.updateCityPopulation(
                                stateName, cityName, population
                        );

                        System.out.println("Population updated successfully!");
                    }

                    case 4 -> {
                        System.out.print("Enter state name: ");
                        String stateName = sc.nextLine();

                        System.out.print("Enter city name: ");
                        String cityName = sc.nextLine();

                        stateService.deleteCity(stateName, cityName);

                        System.out.println("City deleted successfully!");
                    }

                    case 5 -> {
                        System.out.print("Enter state name to delete: ");
                        String stateName = sc.nextLine();

                        stateService.deleteState(stateName);

                        System.out.println("State deleted successfully!");
                    }

                    case 6 -> {
                        System.out.print("Enter current state name: ");
                        String oldState = sc.nextLine();

                        System.out.print("Enter new state name: ");
                        String newState = sc.nextLine();

                        stateService.updateStateName(oldState, newState);

                        System.out.println("State renamed successfully!");
                    }

                    // QUERY 1
                    case 7 -> {
                        System.out.print("Enter state name: ");
                        String stateName = sc.nextLine();

                        System.out.print("Enter city name: ");
                        String cityName = sc.nextLine();

                        int population = stateService.getCityPopulation(
                                stateName, cityName
                        );

                        System.out.println("Population: " + population);
                    }

                    // QUERY 2
                    case 8 -> {
                        System.out.print("Enter state name: ");
                        String stateName = sc.nextLine();

                        StatePopulationSummary summary =
                                stateService.getStatePopulationSummary(stateName);

                        System.out.println("\nState: " + summary.getStateName());
                        System.out.println("\nCities and Populations:");

                        for (City city : summary.getCities().values()) {
                            System.out.println(
                                    city.getName() + " : " + city.getPopulation()
                            );
                        }

                        System.out.println(
                                "\nTotal Population: " + summary.getTotalPopulation()
                        );
                    }

                    // QUERY 3
                    case 9 -> {
                        System.out.print("Enter city name: ");
                        String cityName = sc.nextLine();

                        List<CitySearchResult> results =
                                stateService.searchCity(cityName);

                        if (results.isEmpty()) {
                            System.out.println("No matching city found.");
                        } else {
                            for (CitySearchResult result : results) {
                                System.out.println("\nCity: " + result.getCityName());
                                System.out.println("State: " + result.getStateName());
                                System.out.println("Population: " + result.getPopulation());
                            }
                        }
                    }

                    // QUERY 4
                    case 10 -> {
                        System.out.print("Enter population threshold: ");
                        long threshold = Long.parseLong(sc.nextLine());

                        List<CitySearchResult> results =
                                stateService.getCitiesAbovePopulation(threshold);

                        if (results.isEmpty()) {
                            System.out.println("No cities found above this threshold.");
                        } else {
                            System.out.println("\nCities above population " + threshold + ":");

                            for (CitySearchResult result : results) {
                                System.out.println("\nCity: " + result.getCityName());
                                System.out.println("State: " + result.getStateName());
                                System.out.println("Population: " + result.getPopulation());
                            }
                        }
                    }

                    case 11 -> {
                        Map<String, State> states = stateService.getAllStates();

                        if (states.isEmpty()) {
                            System.out.println("No states available.");
                            break;
                        }

                        System.out.println("\n========== ALL STATES ==========");

                        for (State state : states.values()) {

                            System.out.println("\nState: " + state.getName());

                            for (City city : state.getAllCities().values()) {
                                System.out.println(
                                        "  " + city.getName()
                                                + " - " + city.getPopulation()
                                );
                            }
                        }
                    }

                    case 0 ->
                            System.out.println("Exiting application. Goodbye!");

                    default ->
                            System.out.println("Invalid choice! Please try again.");
                }

            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid numeric value.");
                choice = -1;

            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                choice = -1;
            }

            System.out.println();

        } while (choice != 0);

        sc.close();
    }


    private static void displayMenu() {

        System.out.println("========== STATE & CITY POPULATION DIRECTORY ==========");

        System.out.println("\n----------- STATE AND CITY MANAGEMENT -----------");
        System.out.println("1.  Add State");
        System.out.println("2.  Add City");
        System.out.println("3.  Update City Population");
        System.out.println("4.  Delete City");
        System.out.println("5.  Delete State");
        System.out.println("6.  Rename State");

        System.out.println("\n-------------------- QUERIES --------------------");
        System.out.println("7.  Get City Population (State + City)");
        System.out.println("8.  Get All Cities and Total Population of a State");
        System.out.println("9.  Search City Across All States");
        System.out.println("10. Get Cities Above Population Threshold");

        System.out.println("\n--------------------- OTHER ---------------------");
        System.out.println("11. Display All States and Cities");
        System.out.println("0.  Exit");

        System.out.println("==================================================");
    }
}
