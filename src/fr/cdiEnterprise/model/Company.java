package fr.cdiEnterprise.model;

import fr.cdiEnterprise.exceptions.CompanyCreationException;
import fr.cdiEnterprise.service.Languages;

/**
 * Class for company creation
 * 
 * @author Anaïs
 * @version 23-10-2016
 *
 */
public class Company {

	// private static int id;
	private int companyId;
	private String companyName;
	private String adress;
	private String postalCode;
	private String city;
	private Department department;
	private Region region;
	private String sector;
	private String size;
	// private Languages languages;
	private Language language;
	private String projets;
	private String webSite;
	private Contact contact;

	/**
	 * Constructeur par défaut, ne prend pas de paramètres
	 */
	public Company() {
	}

	public Company(int idCompany, String companyName, String adress, String postalCode, String city,
			Department department, Region region) {

		this.companyName = companyName;
		this.adress = adress;
		this.postalCode = postalCode;
		this.city = city;
		this.department = department;
		this.region = region;
		this.companyId = idCompany;
	}

	// /**
	// * Constructeur avec attributs disponible dans la prochaine version
	// *
	// * @param companyId
	// * @param companyName
	// * @param adress
	// * @param postalCode
	// * @param city
	// * @param department
	// * @param region
	// * @param sector
	// * @param languages
	// * @param webSite
	// * @param contact
	// */
	// public Company(int companyId, String companyName, String adress, String
	// postalCode, String city,
	// Department department, Region region, String size, String sector,
	// Languages languages, String projets,
	// String webSite, Contact contact) {
	//
	// this.companyName = companyName;
	// this.adress = adress;
	// this.postalCode = postalCode;
	// this.city = city;
	// this.department = department;
	// this.region = region;
	// this.sector = sector;
	// this.size = size;
	// this.languages = languages;
	// this.projets = projets;
	// this.webSite = webSite;
	// this.contact = contact;
	// this.companyId = companyId;
	// }

	public Company(int companyId, String companyName, String adress, String postalCode, String city,
			Department department, Region region, String size, String sector, Language language, String projets,
			String webSite, Contact contact) {

		this.companyName = companyName;
		this.adress = adress;
		this.postalCode = postalCode;
		this.city = city;
		this.department = department;
		this.region = region;
		this.sector = sector;
		this.size = size;
		this.setLanguage(language);
		this.projets = projets;
		this.webSite = webSite;
		this.contact = contact;
		this.companyId = companyId;
	}
	
	 public Company(int idEntreprise,String companyName, String adress, String postalCode, 
			 String city, Department department, Region region, String size, String sector, String webSite) {
	
	 this.companyName = companyName;
	 this.adress = adress;
	 this.postalCode = postalCode;
	 this.city = city;
	 this.department = department;
	 this.region = region;
	 this.sector = sector;
	 this.size = size;
	 this.webSite = webSite;
	 this.companyId = idEntreprise;
	 }
	 
	 /**
	  * Constructeur sans Contact et avec uniquement un langage informatique
	  * @param idEntreprise
	  * @param companyName
	  * @param adress
	  * @param postalCode
	  * @param city
	  * @param department
	  * @param region
	  * @param language
	  * @param size
	  * @param sector
	  * @param webSite
	  */
	 public Company(int idEntreprise,String companyName, String adress, String postalCode, 
			 String city, Department department, Region region, Language language, String size, String sector, String webSite) throws 
	 CompanyCreationException {
	
	 this.companyName = companyName;
	 this.adress = adress;
	 this.postalCode = postalCode;
	 this.city = city;
	 this.department = department;
	 this.region = region;
	 this.sector = sector;
	 this.size = size;
	 this.webSite = webSite;
	 this.companyId = idEntreprise;
	 this.language=language;
	 }
	 
	/**
	 * Ensemble des getters de la clase Company
	 */

	/**
	 * @return the companyName
	 */
	public String getCompanyName() {
		return companyName;
	}

	/**
	 * @return the adress
	 */
	public String getAdress() {
		return adress;
	}

	/**
	 * @return the postalCode
	 */
	public String getPostalCode() {
		return postalCode;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @return the department
	 */
	public Department getDepartment() {
		return department;
	}

	/**
	 * @return the region
	 */
	public Region getRegion() {
		return region;
	}

	/**
	 * @return the sector
	 */
	public String getSector() {
		return sector;
	}

	public String getSize() {
		return size;
	}

	/**
	 * @return the languages
	 */
	public Languages getLanguages() {
		return getLanguages();
	}

	public String getProjets() {
		return projets;
	}

	/**
	 * @return the webSite
	 */
	public String getWebSite() {
		return webSite;
	}

	/**
	 * @return the contact
	 */
	public Contact getContact() {
		return contact;
	}

	/**
	 * @return the idEnterprise
	 */
	public int getCompanyId() {
		return companyId;
	}

	/**
	 * Ensemble des setteurs de la clase Company
	 */

	/**
	 * @param size
	 *            the size to set
	 */
	public void setSize(String size) {
		this.size = size;
	}

	/**
	 * @param companyName
	 *            the companyName to set
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	/**
	 * @param adress
	 *            the adress to set
	 */
	public void setAdress(String adress) {
		this.adress = adress;
	}

	/**
	 * @param postalCode
	 *            the postalCode to set
	 */
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	/**
	 * @param city
	 *            the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @param department
	 *            the department to set
	 */
	public void setDepartment(Department department) {
		this.department = department;
	}

	/**
	 * @param region
	 *            the region to set
	 */
	public void setRegion(Region region) {
		this.region = region;
	}

	/**
	 * @param sector
	 *            the sector to set
	 */
	public void setSector(String sector) {
		this.sector = sector;
	}

	/**
	 * @param languages
	 *            the languages to set
	 */
	// public void setLanguages(Languages languages) {
	// this.languages = languages;
	// }

	/**
	 * @param projets
	 *            the projets to set
	 */
	public void setProjets(String projets) {
		this.projets = projets;
	}

	/**
	 * @param webSite
	 *            the webSite to set
	 */
	public void setWebSite(String webSite) {
		this.webSite = webSite;
	}

	/**
	 * @param contact
	 *            the contact to set
	 */
	public void setContact(Contact contact) {
		this.contact = contact;
	}

	/**
	 * @param idEnterprise
	 *            the idEnterprise to set
	 */
	public void setIdEnterprise(int idEnterprise) {
		this.companyId = idEnterprise;
	}

	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {

		this.language = language;

//		String languageName;
//		languageName= language.getLanguageName();
//		if(languageName == null || languageName.length()==0){
//			throw new CompanyCreationException(" Veuillez renseigner un langage informatique");
//		}else{
//		this.language = language;
//		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Entreprise [companyName=" + companyName + ", adress=" + adress + ", postalCode=" + postalCode
				+ ", city=" + city + ", department=" + department + ", region=" + region + ", langage=" + language
				+ ", size=" + size + ", sector= " + sector + ", webSite=" + webSite + ", contact=" + contact + "]";
	}

	@Override
	public void finalize() {
		System.gc();
		System.out.println("Game Over, try again");
	}
}
