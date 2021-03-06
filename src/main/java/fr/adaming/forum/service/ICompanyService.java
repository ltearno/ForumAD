package fr.adaming.forum.service;

import java.util.List;

import fr.adaming.forum.entity.Address;
import fr.adaming.forum.entity.Company;

public interface ICompanyService {
	
	public Company addCompany(Company company);
	public Company updateCompany(Company company);
	public Company deleteCompany(Long idCompany);
	
	public Company getCompanyById(Long idCompany);
	public List<Company> getAllCompany();
	public List<Company> getCompanyByMc(String keyword);
	List<Company> getCompanyByAddress(Address address);

}
