package fr.cdiEnterprise.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *  Classe permettant d'effectuer une recherche
 *  selon des critères sur les Fiches Entreprise
 *  @author olivier
 *  @version 02-10-2016
 */
public class RechercheAvancee implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String comPagnyName;
	private String adress;
	private String postalCode;
	private String city;
	private Integer department;
	private String sector;
	private String webSite;
	private Contact contact;
	private List<Company> listCompanies=new ArrayList<Company>();
	private static int compt;
	/**
	 * Default constructor
	 */
	public RechercheAvancee() {
	}
	/**
	 * Constructor whith parameters
	 * @param id
	 * @param comPagnyName
	 * @param adress
	 * @param postalCode
	 * @param city
	 * @param department
	 * @param sector
	 * @param webSite
	 * @param contact
	 * @param listCompanies
	 */
	public RechercheAvancee(int id, String comPagnyName, String adress, String postalCode, String city,
			Integer department, String sector, String webSite, Contact contact, List<Company> listCompanies) {
		super();
		this.id = id;
		this.comPagnyName = comPagnyName;
		this.adress = adress;
		this.postalCode = postalCode;
		this.city = city;
		this.department = department;
		this.sector = sector;
		this.webSite = webSite;
		this.contact = contact;
		this.listCompanies = listCompanies;
	}
	/**
	 * @param id avec incrementation automatique
	 * @param comPagnyName
	 * @param adress
	 * @param postalCode
	 * @param city
	 * @param department
	 * @param sector
	 * @param webSite
	 * @param contact
	 * @param listCompanies
	 */
	public RechercheAvancee(String comPagnyName, String adress, String postalCode, String city,
			Integer department, String sector, String webSite, Contact contact, List<Company> listCompanies) {
		super();
		this.id = ++compt;
		this.comPagnyName = comPagnyName;
		this.adress = adress;
		this.postalCode = postalCode;
		this.city = city;
		this.department = department;
		this.sector = sector;
		this.webSite = webSite;
		this.contact = contact;
		this.listCompanies = listCompanies;
	}
	// Creation des assesseurs
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the comPagnyName
	 */
	public String getComPagnyName() {
		return comPagnyName;
	}
	/**
	 * @param comPagnyName the comPagnyName to set
	 */
	public void setComPagnyName(String comPagnyName) {
		this.comPagnyName = comPagnyName;
	}
	/**
	 * @return the adress
	 */
	public String getAdress() {
		return adress;
	}
	/**
	 * @param adress the adress to set
	 */
	public void setAdress(String adress) {
		this.adress = adress;
	}
	/**
	 * @return the postalCode
	 */
	public String getPostalCode() {
		return postalCode;
	}
	/**
	 * @param postalCode the postalCode to set
	 */
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}
	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}
	/**
	 * @return the department
	 */
	public Integer getDepartment() {
		return department;
	}
	/**
	 * @param department the department to set
	 */
	public void setDepartment(Integer department) {
		this.department = department;
	}
	/**
	 * @return the sector
	 */
	public String getSector() {
		return sector;
	}
	/**
	 * @param sector the sector to set
	 */
	public void setSector(String sector) {
		this.sector = sector;
	}
	/**
	 * @return the webSite
	 */
	public String getWebSite() {
		return webSite;
	}
	/**
	 * @param webSite the webSite to set
	 */
	public void setWebSite(String webSite) {
		this.webSite = webSite;
	}
	/**
	 * @return the contact
	 */
	public Contact getContact() {
		return contact;
	}
	/**
	 * @param contact the contact to set
	 */
	public void setContact(Contact contact) {
		this.contact = contact;
	}
	/**
	 * @return the listCompanies
	 */
	public List<Company> getListCompanies() {
		return listCompanies;
	}
	/**
	 * @param listCompanies the listCompanies to set
	 */
	public void setListCompanies(List<Company> listCompanies) {
		this.listCompanies = listCompanies;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "RechercheAvancee [id=" + id + ", comPagnyName=" + comPagnyName + ", adress=" + adress + ", postalCode="
				+ postalCode + ", city=" + city + ", department=" + department + ", sector=" + sector + ", webSite="
				+ webSite + ", contact=" + contact + ", listCompanies=" + listCompanies + "]";
	}
}
