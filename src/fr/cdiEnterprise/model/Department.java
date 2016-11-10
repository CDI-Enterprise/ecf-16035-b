package fr.cdiEnterprise.model;

/*
 * Class for department creation company and user 
 * @ author: Anaïs
 * @version 23-10-2016
 * 
 */

public class Department {

	private String departmentName;
	private int departmentNumber;
	static int ind;

	public static final String[] DEPARTMENTS = { "Ain", "Aisne", "Allier", "Alpes de Hautes-Provence", "Hautes-Alpes",
			"Alpes-Maritimes", "Ardèche", "Ardennes", "Ariège", "Aube", "Aude", "Aveyron", " Bouches-du-Rhône",
			"Calvados", "Cantal", "Charente", "Charente-Maritime", "Cher", "Corrèze", "Corse-du-Sud-Haute-Corse",
			"Côte-d'Or", "Côtes d'Armor", "Creuse", "Dordogne", "Doubs", "Drôme", "Eure", "Eure-et-Loir", "Finistère",
			"Gard", " Haute-Garonne", "Gers", "Gironde", "Hérault", "Ille-et-Vilaine", "Indre", "Indre-et-Loire",
			"Isère", "Jura", "Landes", "Loir-et-Cher", "Loire", "Haute-Loire", "Loire-Atlantique", "Loiret", "Lot",
			"Lot-et-Garonne", "Lozère", "Maine-et-Loire", "Manche", "Marne", "Haute-Marne", "Mayenne",
			"Meurthe-et-Moselle", "Meuse", "Morbihan", "Moselle", "Nièvre", "Nord", "Oise", "Orne", "Pas-de-Calais",
			"Puy-de-Dôme", "Pyrénées-Atlantiques", "Hautes-Pyrénées", "Pyrénées-Orientales", "Bas-Rhin", "Haut-Rhin",
			"Rhône", "Haute-Saône", "Saône-et-Loire", "Sarthe", "Savoie", "Haute-Savoie", "Paris", "Seine-Maritim",
			"Seine-et-Marne", "Yvelines", "Deux-Sèvres", "Somme", "Tarn", "Tarn-et-Garonne", "Var", "Vaucluse",
			"Vendée", "Vienne", "Haute-Vienne", "Vosges", "Yonne", "Territoire-de-Belfort", "Essonne", "Hauts-de-Seine",
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
