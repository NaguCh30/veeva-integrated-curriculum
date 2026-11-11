package model;

public class Customer {
    private final long customerId;
    private String name;
    private String email;
    private String phoneNumber;
    private Address address;

    public Customer(long customerId, String name, String email, String phoneNumber, Address address) {

        if (customerId <= 0 ||
            name == null || name.isBlank() ||
            email == null || email.isBlank() ||
            phoneNumber == null || phoneNumber.isBlank() ||
            address == null) {
            throw new IllegalArgumentException("All Fields are Required!");
        }

        if (email == null || email.isBlank() ||
                email.indexOf("@") <= 0 ||
                email.lastIndexOf(".") <= email.indexOf("@") + 1 ||
                email.lastIndexOf(".") == email.length() - 1) {

            throw new IllegalArgumentException("Invalid Email!");
        }

        this.customerId = customerId;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public long getCustomerId() {
        return customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be empty!");
        }
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Email cannot be blank!");
        }
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.isBlank()) {
            throw new IllegalArgumentException("Phone Number cannot be blank");
        }
        this.phoneNumber = phoneNumber;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        if (address == null) {
            throw new IllegalArgumentException("Address cannot be blank. You must have a valid address!");
        }
        this.address = address;
    }
}
