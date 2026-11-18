package repository;

import model.Company;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class CompanyRepository {
    private final Map<String, Company> companies = new HashMap<>();

    public void addCompany(Company company) {
        companies.put(company.getCompanyId(), company);
    }

    public void deleteCompany(String companyId) {
        companies.remove(companyId);
    }

    public Company getCompanyById(String companyId) {
        return companies.get(companyId);
    }

    public Map<String, Company> getAllCompanies() {
        return Collections.unmodifiableMap(companies);
    }


}
