package fr.cdiEnterprise.model;

/**
 * Class for region creation for company and user
 * 
 * @author Anaïs
 * @version 23-10-2016
 *
 */

public class Region {

	public static final String[] REGIONS = { "Auvergne-Rhône-Alpes", "Bourgogne Franche Comté", "Bretagne",
			"Centre-Val de Loire", "Corse", "Grand Est", "Hauts-de-France", "Île-de-France", "Normandie",
			"Nouvelle-Aquitaine", "Occitanie", "Pays de la Loire", "Provence-Alpes-Côte d'Azur", "Guadeloupe",
			"Martinique", "Guyane", "La Réunion", "Mayotte" };

	private String regionName;
	private int codeRegion;
	static int ind;

	public Region() {

	}

	public Region(String name) {
		ind++;
		this.regionName = name;
		this.codeRegion = ind;

	}

	public Region(String name, int regionId) {
		this.regionName = name;
		this.codeRegion = regionId;
	}

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	public int getCodeRegion() {
		return codeRegion;
	}

	public void setCodeRegion(int codeRegion) {
		this.codeRegion = codeRegion;
	}

	@Override
	public String toString() {
		return this.regionName + " " + " (" + this.codeRegion + ")";
	}
}
