package fr.cdiEnterprise.model;

public class Recherche {
	
	
	private int idRech;
	private String idUser;
	private String nomRech;
	private String compRech;
	private String sectorRech;
	private Region regionRech;
	private String cityRech;
	
	public Recherche() {	
	}

	public Recherche(int idRech, String idUser, String nomRech, String compRech, String sectorRech, Region regionRech, String cityRech){
		this.idRech = idRech;
		this.idUser = idUser;
		this.nomRech = nomRech;
		this.compRech = compRech;
		this.sectorRech = sectorRech;
		this.regionRech = regionRech;
		this.cityRech = cityRech;
	}

	public int getIdRech() {
		return idRech;
	}

	public String getIdUser() {
		return idUser;
	}

	public String getNomRech() {
		return nomRech;
	}

	public String getCompRech() {
		return compRech;
	}

	public String getSectorRech() {
		return sectorRech;
	}

	public Region getRegionRech() {
		return regionRech;
	}

	public String getCityRech() {
		return cityRech;
	}

	public void setIdRech(int idRech) {
		this.idRech = idRech;
	}

	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}

	public void setNomRech(String nomRech) {
		this.nomRech = nomRech;
	}

	public void setCompRech(String compRech) {
		this.compRech = compRech;
	}

	public void setSectorRech(String sectorRech) {
		this.sectorRech = sectorRech;
	}

	public void setRegionRech(Region regionRech) {
		this.regionRech = regionRech;
	}

	public void setCityRech(String cityRech) {
		this.cityRech = cityRech;
	}
	
	@Override
	public String toString() {
		String msg= this.getNomRech()+" "+this.getIdUser()+" "+this.getCompRech()+this.getCityRech()+" ";
		return msg;
		
	}



}
