package fr.cdiEnterprise.control;

import fr.cdiEnterprise.exceptions.CompanyCreationException;


/**
 * M�thode pour les controles de champs
 * 
 * @version 23-11-2016
 * @author Ana�s
 */
public class MethodsForControl {

	/**
	 * M�thode pour controler si les champs obligatoires ont bien �t� renseign�s
	 * 
	 * @author Ana�s
	 * @param String (champ � tester)
	 * @return String (valeur du champ si ok)
	 * @version 23-11-2016
	 */

	public static String nullField(String txtField) {
		String field = txtField;
		int fieldLength = field.length();
		String fieldReturn;

		if (fieldLength != 0) {
			fieldReturn = field;
		} else {
			throw new CompanyCreationException("Veuillez remplir les champs obligatoires");
		}
		return fieldReturn;
	}


	/**
	 * M�thode permettant de controler la saisie du code postal
	 * 
	 * @author Ana�s
	 * @param postalCode
	 * @version 23/11/2016
	 * 
	 */
	public static void controlPostalCode(String postalCode) {
		int length = postalCode.length();
		char[] tabChaineCarac = postalCode.toCharArray();
		if (length == 5) {
			for (char carac : tabChaineCarac) {
				if (Character.isDigit(carac)) {
				} else {
					throw new CompanyCreationException("Le code postal doit �tre uniquement compos� de chiffres");
				}
			}
		} else {
			throw new CompanyCreationException("Code postal non valide, 5 chiffres attendus");
		}
	}

}
