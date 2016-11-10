package fr.cdiEnterprise.control;

import java.awt.Component;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import fr.cdiEnterprise.dao.DataBaseCompany;
import fr.cdiEnterprise.exceptions.CompanyCreationException;
import fr.cdiEnterprise.model.Company;
import fr.cdiEnterprise.view.company.CompanyCreationPanel;

/**
 * Methods for the control package
 * 
 * @version 16-10-2016
 * @author Claire
 */
public class MethodsForListeners {

	/**
	 * Takes a group of JRadioButton and searches which one is selected to
	 * return it.
	 * 
	 * @author Claire
	 * @param jrButtonGrp
	 * @return jrButtonSelected
	 * @since 16-10-2016
	 */
	public static JRadioButton getSelectedJRadioButton(ButtonGroup jrButtonGrp) {

		JRadioButton jrButtonSelected = null;

		for (Enumeration<AbstractButton> e = jrButtonGrp.getElements(); e.hasMoreElements();) {

			JRadioButton jrButton = (JRadioButton) e.nextElement();

			if (jrButton.getModel() == jrButtonGrp.getSelection()) {

				jrButtonSelected = jrButton;
				break;
			}
		}

		return jrButtonSelected;
	}

	/**
	 * Takes an ArrayList of JTextField to clear and re-enabled all of them.
	 * 
	 * @author Claire
	 * @param allJTextFields
	 * @since 16-10-2016
	 */
	public static void resetJTextField(ArrayList<JTextField> allJTextFields) {

		for (Component c : allJTextFields) {

			if (c instanceof JTextField) {

				JTextField textfield = (JTextField) c;
				textfield.setText(null);
				textfield.setEnabled(true);
			}
		}
	}

	/**
	 * M�thode pour controler si les champs obligatoires ont bien �t� renseign�s
	 * 
	 * @author Ana�s
	 * @param String
	 *            (txtField)
	 * @return
	 * @return
	 * @since 21-10-2016
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
	 * M�thode permettant de rafraichir la liste des fiches entreprises
	 * 
	 * @author Ana�s
	 * 
	 * @throws SQLException
	 */
	public static void refreshListCompanies() throws SQLException {
		CompanyCreationPanel.getDlmCompanies().clear();
		for (Company company : DataBaseCompany.getCompaniesData()) {
			CompanyCreationPanel.getDlmCompanies().addElement(company);
		}
	}

	/**
	 * M�thode permettant de controler la saisie du code postal
	 * 
	 * @author Ana�s
	 * @param postalCode
	 * @version 27/10/2016
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
