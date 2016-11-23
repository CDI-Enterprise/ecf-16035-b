package fr.cdiEnterprise.dao.test;

import fr.cdiEnterprise.dao.AccesBaseInscrit;
import fr.cdiEnterprise.model.Inscription;

public class TestDaoSouleymane {

	public static void main(String[] args) {
		
//		fr.cdiEnterprise.dao.AccesBaseInscrit base = new fr.cdiEnterprise.dao.AccesBaseInscrit();
//		//Inscription inscrit = new Inscription(0, "Formateur", "afpa", "afpa", "afpa@afpa.fr", "afpa", "afpa");
//		
//		
//		
//		
//			
		Inscription inscrit = new Inscription(1,"Formateur", "afpa", "afpa", "afpa@afpa.fr", "afpa", "afpa");
//					
		AccesBaseInscrit dao =new AccesBaseInscrit();
		
		dao.desinscription(inscrit);
		
		
		
	}
}
