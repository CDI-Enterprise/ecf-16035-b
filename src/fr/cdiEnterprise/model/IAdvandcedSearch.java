package fr.cdiEnterprise.model;

import java.util.List;

public interface IAdvandcedSearch {
	
	
	
	// sauvegarder une recherche en bdd
//	public void addAdvancedSearch(AdvandcedSearch s);
	
	//recherche de fiche(s) entreprise par mot clé
	public List<Company> getCompagnyByMCNom(String mcA);
	public List<Company> getCompagnyByMCDomaine(String mcA);
	public List<Company> getCompagnyByMCVille(String mcA);
	public List<Company> getCompagnyByMCRegion(String mcA);
	public List<Company> getCompagnyByMC(String mcA, String mcB);
	public List<Company> getCompagnyByMC(String mcA, String mcB, String mcC);
	public List<Company> getCompagnyByMC(String mcA, String mcB, String mcC, String mcD);
	
	//lister toutes les recherches
	//public List<AdvandcedSearch> getAllAdandcedSearch();
	
//	public void addCategorie( Categorie c);
//	public void addProduit(Produit p, int idCat);
//	public List<Produit> getProduitsParMC(String mc);
//	public List<Produit> getProduitsParCat(int idCat);
//	public List<Categorie> getAllCategorie();
//	public Categorie getCategorie(int idCat);

}
