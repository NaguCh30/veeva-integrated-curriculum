package model;

import exception.InvalidNameException;

public class Company {
    private String companyId;
    private String companyName;
    private String companyContact;
    private String companyLocation;

    public Company (String companyId, String companyName, String companyContact, String companyLocation) {
        this.companyId = companyId;
        this.companyName = companyName;
        this.companyContact = companyContact;
        this.companyLocation = companyLocation;
    }

    public String getCompanyId() {
        return companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getCompanyContact() {
        return companyContact;
    }

    public String getCompanyLocation() {
        return companyLocation;
    }

    public void setCompanyName(String newName) throws InvalidNameException {
        if (newName == null || newName.isBlank()) {
            throw new InvalidNameException("Please provide a valid Company Name to update");
        }
        this.companyName = companyName;
    }

    public void setCompanyContact(String newContact) throws InvalidNameException {
        if (newContact == null || newContact.isBlank()) {
            throw new InvalidNameException("Please provide valid contact details for the company.");
        }
        this.companyContact = newContact;
    }

    public void setCompanyLocation(String newLocation) throws InvalidNameException {
        if (newLocation == null || newLocation.isBlank()) {
            throw new InvalidNameException("Please provide valid location details for the company.");
        }
        this.companyLocation = newLocation;
    }
}
