package repository;

import model.Customer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class CustomerRepository {
    private Map<Long, Customer> customers = new HashMap<>();

    public void addCustomer(Customer customer) {
        if (customer == null) {
            throw new IllegalArgumentException("Provide valid customer details");
        }
        if (customers.containsKey(customer.getCustomerId())) {
            throw new IllegalArgumentException("Customer with ID " + customer.getCustomerId() + " already exists!");
        }

        for (Customer customer1 : customers.values()) {
            if (customer.getEmail().equalsIgnoreCase(customer1.getEmail()) ||
                    customer1.getPhoneNumber().equals(customer.getPhoneNumber())) {
                throw new IllegalArgumentException("Email or Phone Number already exists!");
            }
        }

        customers.put(customer.getCustomerId(), customer);
    }

    public Customer findById(long customerId) {
        return customers.get(customerId);
    }

    public List<Customer> findByName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Provide valid name to search!");
        }

        List<Customer> matchingCustomers = new ArrayList<>();

        for (Customer customer : customers.values()) {
            if (name.equalsIgnoreCase(customer.getName())) {
                matchingCustomers.add(customer);
            }
        }

        return matchingCustomers;
    }

    public List<Customer> findByCity(String city) {
        if (city == null || city.isBlank()) {
            throw new IllegalArgumentException("Provide valid city to search!");
        }

        List<Customer> matchingCustomers = new ArrayList<>();

        for (Customer customer : customers.values()) {
            if (city.equalsIgnoreCase(customer.getAddress().getCity())) {
                matchingCustomers.add(customer);
            }
        }

        return matchingCustomers;
    }

    public Customer findByEmail(String email) {
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Provide a valid email to search!");
        }

        for (Customer customer : customers.values()) {
            if (email.equalsIgnoreCase(customer.getEmail())) {
                return customer;
            }
        }

        return null;
    }

    public Customer findByPhoneNumber(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.isBlank()) {
            throw new IllegalArgumentException("Provide a valid phone number to search!");
        }

        for (Customer customer : customers.values()) {
            if (phoneNumber.equals(customer.getPhoneNumber())) {
                return customer;
            }
        }

        return null;
    }


    public void updateCustomer(long customerId, String newPhoneNumber) {
        if (!customers.containsKey(customerId)) {
            throw new IllegalArgumentException("Customer with ID " + customerId + " does not exist!");
        }

        if (newPhoneNumber == null || newPhoneNumber.isBlank()) {
            throw new IllegalArgumentException("Provide valid phone number to update");
        }

        Customer customer = findById(customerId);
        customer.setPhoneNumber(newPhoneNumber);
    }

    public void deleteCustomer(long customerId) {
        if (!customers.containsKey(customerId)) {
            throw new IllegalArgumentException("Customer with ID " + customerId + " does not exist!");
        }

        customers.remove(customerId);
    }
}
