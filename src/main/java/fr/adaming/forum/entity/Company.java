package fr.adaming.forum.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
/*
 * @author Alonzo.M, Beauvironnois.F, Bonnecaze.K, Roblin.M
 * Classe de cr�ation des objets Company permettant de lier un user � une entreprise au sein de laquelle il travaille. 
 * Un historique des companies au sein desquelles l'utilisateur a travaill� est � envisager.
 * Ajout de contraintes d'unicit� pour ne pas avoir deux companies � la m�me adresse ni deux branches identiques
 * de la m�me companie.
 */

@Entity
@Table(
		uniqueConstraints={
		@UniqueConstraint(name="company", columnNames={"companyName", "companyBranch"}),
		@UniqueConstraint(name="company_address", columnNames={"companyName", "streetName", "zipCode", "city", "country"})
		//TODO a tester
		})
public class Company {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idCompany;

	@NotNull
	private String companyName;

	/*
	 * Utilis�e si il y a plusieurs antennes de la companie:
	 * Exemple 1 : Paris - Toulouse - Lyon ...
	 * Exemple 2 : Toulouse 1 - Toulouse 2 ...
	 */
	
	@NotNull
	private String companyBranch;

	@Embedded
	@AttributeOverrides({ 
		@AttributeOverride(name = "streetName", column = @Column(nullable = false)),
		@AttributeOverride(name = "zipCode", column = @Column(nullable = false)),
		@AttributeOverride(name = "city", column = @Column(nullable = false)),
		@AttributeOverride(name = "country", column = @Column(nullable = false))
		})
	@NotNull
	private Address companyAddress;

	public Company() {
		super();
	}

	public Company(String companyName, String companyBranch, Address companyAddress) {
		super();
		this.companyName = companyName;
		this.companyBranch = companyBranch;
		this.companyAddress = companyAddress;

	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyBranch() {
		return companyBranch;
	}

	public void setCompanyBranch(String companyBranch) {
		this.companyBranch = companyBranch;
	}

	public Address getCompanyAddress() {
		return companyAddress;
	}

	public void setCompanyAddress(Address companyAddress) {
		this.companyAddress = companyAddress;
	}

	public Long getIdCompany() {
		return idCompany;
	}

}
