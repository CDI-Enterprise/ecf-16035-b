package fr.cdiEnterprise.model;

/*
 * Class for department creation company and user 
 * @ author: Ana�s
 * @version 23-10-2016
 * 
 */

public class Department {

	private String departmentName;
	private int departmentNumber;
	static int ind;

	public static final String[] DEPARTMENTS = { "Ain", "Aisne", "Allier", "Alpes de Hautes-Provence", "Hautes-Alpes",
			"Alpes-Maritimes", "Ard�che", "Ardennes", "Ari�ge", "Aube", "Aude", "Aveyron", " Bouches-du-Rh�ne",
			"Calvados", "Cantal", "Charente", "Charente-Maritime", "Cher", "Corr�ze", "Corse-du-Sud-Haute-Corse",
			"C�te-d'Or", "C�tes d'Armor", "Creuse", "Dordogne", "Doubs", "Dr�me", "Eure", "Eure-et-Loir", "Finist�re",
			"Gard", " Haute-Garonne", "Gers", "Gironde", "H�rault", "Ille-et-Vilaine", "Indre", "Indre-et-Loire",
			"Is�re", "Jura", "Landes", "Loir-et-Cher", "Loire", "Haute-Loire", "Loire-Atlantique", "Loiret", "Lot",
			"Lot-et-Garonne", "Loz�re", "Maine-et-Loire", "Manche", "Marne", "Haute-Marne", "Mayenne",
			"Meurthe-et-Moselle", "Meuse", "Morbihan", "Moselle", "Ni�vre", "Nord", "Oise", "Orne", "Pas-de-Calais",
			"Puy-de-D�me", "Pyr�n�es-Atlantiques", "Hautes-Pyr�n�es", "Pyr�n�es-Orientales", "Bas-Rhin", "Haut-Rhin",
			"Rh�ne", "Haute-Sa�ne", "Sa�ne-et-Loire", "Sarthe", "Savoie", "Haute-Savoie", "Paris", "Seine-Maritim",
			"Seine-et-Marne", "Yvelines", "Deux-S�vres", "Somme", "Tarn", "Tarn-et-Garonne", "Var", "Vaucluse",
			"Vend�e", "Vienne", "Haute-Vienne", "Vosges", "Yonne", "Territoire-de-Belfort", "Essonne", "Hauts-de-Seine",
			"Seine-Saint-Denis", "Val-de-Marne", "Val-d'Oise" };

	public Department(String name, int number) {

		this.departmentName = name;
		this.departmentNumber = number;

	}

	public Department(String name) {
		ind++;
		this.departmentName = name;
		this.departmentNumber = ind;

	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String name) {
		this.departmentName = name;
	}

	public int getDepartmentNumber() {
		return departmentNumber;
	}

	@Override
	public String toString() {
		return departmentName + " " + " (" + this.departmentNumber + ")";
	}

}
