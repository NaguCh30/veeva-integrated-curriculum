package model;

public class CitySearchResult {

    private final String cityName;
    private final String stateName;
    private final int population;

    public CitySearchResult(String cityName, String stateName, int population) {
        this.cityName = cityName;
        this.stateName = stateName;
        this.population = population;
    }

    public String getCityName() {
        return cityName;
    }

    public String getStateName() {
        return stateName;
    }

    public int getPopulation() {
        return population;
    }
}