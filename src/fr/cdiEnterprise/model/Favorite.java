/**
 * 
 */
package fr.cdiEnterprise.model;

/**

 *This class describe different parameters in a bookmark's user.
 * 
 *14 oct. 2016
 * @author Ismael
 *ecf-16035-a
 *22:06:18
 */

public class Favorite 
{
	private static int id;
	private int idEnterprise;
	private int idFavorite;
	private String companyName;
	private String noteUser;
	private String city;
	private String size;
	private String sector;
	private String webSite;
	private String contactMail;

	
	/**
	 * Default constructor
	 */
	public Favorite ()
	{
	}
	
	/**
	 * Default constructor
	 * @param noteUser
	 */

	public Favorite(int idFavorite,String noteUser) 
	{
		id++;
		this.noteUser	= noteUser;
		this.idFavorite		= idFavorite;

	}


	/**
	 * Constructor with parameters
	 * 
	 * @param idFavorite
	 * @param companyName
	 * @param city
	 * @param size
	 * @param sector
	 * @param website
	 * @param contactMail
	 * @param noteCompany
	 * 
	 */

	public Favorite( int idFavorite,String companyName, String city, String size, String sector, String webSite, String contactMail, String noteUser) 
	{
		super();
		id++;
		this.idFavorite 	= id;
		this.companyName 	= companyName;
		this.city			= city;
		this.size			= size;
		this.sector			= sector;
		this.webSite		= webSite;
		this.contactMail	= contactMail;
		this.noteUser		= noteUser;
	}

	/**
	 * Constructor with parameters
	 * 
	 * @param idFavorite 
	 * @param city
	 * @param size
	 * @param sector
	 * @param website
	 * @param contactMail
	 * @param note
	 * 
	 */
	
	public Favorite(String city, String size, String sector, String webSite, String contactMail, String noteUser) 
	{
		super();
		id++;
		this.idFavorite 	= id;
		this.city			= city;
		this.size			= size;
		this.sector			= sector;
		this.webSite		= webSite;
		this.contactMail	= contactMail;
		this.noteUser		= noteUser; 
	}

	/**
	 * Getters
	 */

	public Favorite(String companyName) 
	{
		id++;
		this.companyName = companyName;
	}

	/**
	 * @return the idFavorite
	 */
	public int getIdFavorite() {
		return idFavorite;
	}
	/**
	 * @return the idEnterprise
	 */
	public int getIdEnterprise() {
		return idEnterprise;
	}

	/**
	 * @return the companyName
	 */
	public String getCompanyName() {
		return companyName;
	}

	/**
	 * @return the noteCompany
	 */
	public String getNoteUser() {
		return noteUser;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @return the size
	 */
	public String getSize() {
		return size;
	}

	/**
	 * @return the sector
	 */
	public String getSector() {
		return sector;
	}

	/**
	 * @return the webSite
	 */
	public String getWebSite() {
		return webSite;
	}

	/**
	 * @return the contactMail
	 */
	public String getContactMail() {
		return contactMail;
	}


	/**
	 * Setters
	 */

	/**
	 * @param companyName
	 * the companyName to set
	 */
	public void setCompanyName(String companyName) 
	{
		this.companyName = companyName;
	}

	/**
	 * @param idFavorite
	 * the idFavorite to set
	 */
	public void setIdFavorite(int idFavorite) {
		this.idFavorite = idFavorite;
	}
	
	/**
	 * @param idEnterprise
	 *            the idEnterprise to set
	 */
	public void setIdEnterprise(int idEnterprise) 
	{
		this.idEnterprise = idEnterprise;
	}

	/**
	 * @param noteUser the noteCompany to set
	 */
	public void setNoteCompany(String noteUser) {
		this.noteUser = noteUser;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @param size the size to set
	 */
	public void setSize(String size) {
		this.size = size;
	}

	/**
	 * @param sector the sector to set
	 */
	public void setSector(String sector) {
		this.sector = sector;
	}

	/**
	 * @param webSite the webSite to set
	 */
	public void setWebSite(String webSite) {
		this.webSite = webSite;
	}

	/**
	 * @param contactMail the contactMail to set
	 */
	public void setContactMail(String contactMail) {
		this.contactMail = contactMail;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Favorite [idEnterprise=" + idEnterprise + ", companyName=" + companyName + ", city=" + city + ", size="
				+ size + ", sector=" + sector + ", webSite=" + webSite + ", contactMail=" + contactMail + "]";
	}

	@Override
	public void finalize() {
		System.gc();
		System.out.println("This is end");
	}
}
