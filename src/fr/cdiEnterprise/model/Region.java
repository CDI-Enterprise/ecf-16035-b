package fr.cdiEnterprise.model;

/**
 * Class for region creation for company and user
 * 
 * @author Ana�s
 * @version 23-10-2016
 *
 */

public class Region {

	public static final String[] REGIONS = { "Auvergne-Rh�ne-Alpes", "Bourgogne Franche Comt�", "Bretagne",
			"Centre-Val de Loire", "Corse", "Grand Est", "Hauts-de-France", "�le-de-France", "Normandie",
			"Nouvelle-Aquitaine", "Occitanie", "Pays de la Loire", "Provence-Alpes-C�te d'Azur", "Guadeloupe",
			"Martinique", "Guyane", "La R�union", "Mayotte" };

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
