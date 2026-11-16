package service;

import exception.*;
import model.Company;
import repository.CompanyRepository;


public class CompanyService {
    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public void addCompany(String companyId, String companyName, String companyContact, String companyLocation) throws InvalidNameException, DuplicateCompanyException {
        Company existingCompany = companyRepository.getCompanyById(companyId);

        if (existingCompany != null) {
            throw new DuplicateCompanyException("Company with ID " + companyId + " already exists.");
        }
        if (companyId == null || companyId.isBlank()) {
            throw new InvalidNameException("Please provide a valid Company ID");
        }
        if (companyName == null || companyName.isBlank()) {
            throw new InvalidNameException("Please provide a valid company name to add company.");
        }
        if (companyContact == null || companyContact.isBlank()) {
            throw new InvalidNameException("Please provide valid contact details for the company " + companyName);
        }
        if (companyLocation == null || companyLocation.isBlank()) {
            throw new InvalidNameException("Please provide valid location details for the company " + companyName);
        }

        Company company = new Company(companyId, companyName, companyContact, companyLocation);

        companyRepository.addCompany(company);
    }

    public void deleteCompany(String companyId) throws InvalidCompanyIdException, CompanyNotFoundException {
        if (companyId == null || companyId.isBlank()) {
            throw new InvalidCompanyIdException("Please provide a valid company ID");
        }
        Company company = companyRepository.getCompanyById(companyId);
        if (company == null) {
            throw new CompanyNotFoundException("Company with ID " + companyId + " not found!");
        }
        companyRepository.deleteCompany(company.getCompanyId());
    }

    public void updateCompany(String companyId, String newName, String newContact, String newLocation)
            throws InvalidCompanyIdException,
            InvalidNameException,
            InvalidContactException,
            InvalidLocationException,
            CompanyNotFoundException {
        if (companyId == null || companyId.isBlank()) {
            throw new InvalidCompanyIdException("Please provide valid company ID");
        }
        if (newName == null || newName.isBlank()) {
            throw new InvalidNameException("Please provide a valid name to update.");
        }
        if (newContact == null || newContact.isBlank()) {
            throw new InvalidContactException("Please provide valid contact details.");
        }

        if (newLocation == null || newLocation.isBlank()) {
            throw new InvalidLocationException("Please provide valid location details");
        }

        Company existingCompany = companyRepository.getCompanyById(companyId);
        if (existingCompany == null) {
            throw new CompanyNotFoundException("Company with ID " + companyId + " not found to update.");
        }

        existingCompany.setCompanyName(newName);
        existingCompany.setCompanyContact(newContact);
        existingCompany.setCompanyLocation(newLocation);
    }

    public Company getCompanyByID(String companyId) throws InvalidCompanyIdException, CompanyNotFoundException {
        if (companyId == null || companyId.isBlank()) {
            throw new InvalidCompanyIdException("Please provide a valid company ID");
        }
        Company company = companyRepository.getCompanyById(companyId);
        if(company == null) {
            throw new CompanyNotFoundException("Compnay with ID " + companyId + " not found.");
        }
        return company;
    }

}
