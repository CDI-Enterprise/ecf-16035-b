package fr.cdiEnterprise.control;

import fr.cdiEnterprise.exceptions.CompanyCreationException;


/**
 * Méthode pour les controles de champs
 * 
 * @version 23-11-2016
 * @author Anaïs
 */
public class MethodsForControl {

	/**
	 * Méthode pour controler si les champs obligatoires ont bien été renseignés
	 * 
	 * @author Anaïs
	 * @param String (champ à tester)
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
	 * Méthode permettant de controler la saisie du code postal
	 * 
	 * @author Anaïs
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
					throw new CompanyCreationException("Le code postal doit être uniquement composé de chiffres");
				}
			}
		} else {
			throw new CompanyCreationException("Code postal non valide, 5 chiffres attendus");
		}
	}

}
