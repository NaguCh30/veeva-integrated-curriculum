package model;

public class Address {
    private String street;
    private String city;
    private String state;
    private String postalCode;
    private String country;

    public Address (String street,
                    String city,
                    String state,
                    String postalCode,
                    String country) {
        if (street == null || street.isBlank() ||
            city == null || city.isBlank() ||
            state == null || state.isBlank() ||
            postalCode == null || postalCode.isBlank() ||
            country == null || country.isBlank()) {
            throw new IllegalArgumentException("All Fields are Required!");
        }

        this.street = street;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
        this.country = country;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getCountry() {
        return country;
    }
}
