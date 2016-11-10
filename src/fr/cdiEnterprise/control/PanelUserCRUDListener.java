/**
 * 
 */
package fr.cdiEnterprise.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import fr.cdiEnterprise.control.exception.AlreadyUsedException;
import fr.cdiEnterprise.control.exception.LengthStringException;
import fr.cdiEnterprise.control.exception.NoButtonException;
import fr.cdiEnterprise.control.util.PanelUserCRUDControl;
import fr.cdiEnterprise.control.exception.EmptyFieldException;
import fr.cdiEnterprise.dao.UserDAO;
import fr.cdiEnterprise.model.FormerTrainee;
import fr.cdiEnterprise.model.Trainee;
import fr.cdiEnterprise.model.Trainer;
import fr.cdiEnterprise.model.User;
import fr.cdiEnterprise.view.profile.PanelUserCRUD;

/**
 * Listener test for users CRUD with DB.
 *
 * @author Claire
 * @version 24-10-2016
 */
public class PanelUserCRUDListener implements ActionListener, MouseListener {

	// Given attribute
	private PanelUserCRUD panel;

	// Attributes for DB access
	// private UserDAO userDAO;

	// Frame for error message
	private JFrame popUpFrame;
	private String errorMsg;

	// Attributes for input control
	private String strLblAlias;
	private String strLblStatus;

	// Attributes do define the selected status
	private ButtonGroup jrButtonGrp;
	private JRadioButton jrButtonSelected;	

	// Attributes to handle selection
	private User selectedUser;
	// private integer indexUser;
	// private Trainee selectedTrainee;
	// private FormerTrainee selectedFormerTrainee;
	// private Trainer selectedTrainer;

	// Attributes to create-update a user
	private User user;
	private int id;
	// private String inscriptionDate;
	private String status;
	private String alias;
	private String email;
	private String afpa;

	// Attributes to reset component
	private ArrayList<JTextField> allJTextFields;

	/**
	 * Constructs a listener taking a panel for attribute.
	 * 
	 * @throws SQLException 
	 */
	public PanelUserCRUDListener(PanelUserCRUD panel) throws SQLException {
		this.panel = panel;
	}

	// ACTION
	@Override
	public void actionPerformed(ActionEvent ae) {

		// TODO (Claire) create a new class DTO instead of calling DAO

		// Gets the label if they're needed for the error pop-up frame
		strLblAlias = panel.getLblAlias().getText();
		strLblStatus = panel.getLblStatus().getText();

		// Calls the status selection method
		jrButtonGrp = panel.getStatusGrp();
		jrButtonSelected = MethodsForListeners.getSelectedJRadioButton(jrButtonGrp);

		// Gets the input data
		alias = panel.getTxtAlias().getText();
		email = panel.getTxtMail().getText();
		afpa = panel.getTxtAfpa().getText();


		// CMD CANCEL
		if (ae.getSource() == panel.getCmdCancel()) {
			// Clears User JList
			panel.getLstUsersDB().setSelectedIndices(new int[] {});

			clearInputPanel();
		}

		// CMD CREATE
		if (ae.getSource() == panel.getCmdCreate()) {
			try {
				controlButton(jrButtonSelected, strLblStatus);
				status = jrButtonSelected.getText();
				// Depending on status, instantiates a Trainee or FormerTrainee or Trainer with User reference
				switch (status) {
				case "Stagiaire" :
					user = new Trainee(status, controlAlias(alias, strLblAlias), email, afpa);
					break;

				case "Ancien" :
					user = new FormerTrainee(status, controlAlias(alias, strLblAlias), email, afpa);
					break;

				case "Formateur" :
					user = new Trainer(status, controlAlias(alias, strLblAlias), email, afpa);
					break;

				default:
					System.out.println("Aucun statut sélectionné.");
					break;
				}

				// Asks UserDAO to insert a new user in DB
				try {
					String creationDone = UserDAO.createUser(user); // With String for test code
					panel.refresh(UserDAO.getUsersList());
					System.out.println(creationDone); // Test code
					clearInputPanel();
				} catch (SQLException e) {
					e.printStackTrace();
				}

			} catch (NoButtonException | EmptyFieldException | LengthStringException | AlreadyUsedException | SQLException e) {
				errorMsg = e.getMessage();
				JOptionPane.showMessageDialog(popUpFrame, errorMsg);
			}
			
		}

		// CMD UPDATE
		if (ae.getSource() == panel.getCmdUpdate()) {

			// Get the editable fields
			status = jrButtonSelected.getText();
			alias = panel.getTxtAlias().getText();
			email = panel.getTxtMail().getText();

			selectedUser.setStatus(status);
			selectedUser.setAlias(alias);
			selectedUser.setEmail(email);		

			// Asks UserDAO to update a user in DB
			try {
				String updateDone = UserDAO.updateUser(selectedUser); // With String for test code
				panel.refresh(UserDAO.getUsersList());
				System.out.println(updateDone); // Test code
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			clearInputPanel();
		}

		// CMD DELETE
		if (ae.getSource() == panel.getCmdDelete()) {

			id = selectedUser.getId();

			// Asks UserDAO to delete a user in DB
			try {
				String deleteDone = UserDAO.deleteUser(id); // With String for test code
				panel.refresh(UserDAO.getUsersList());
				System.out.println(deleteDone); // Test code
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			clearInputPanel();
		}
	}

	// MOUSE
	@Override
	public void mouseClicked(MouseEvent me) {

		if (me.getSource() == panel.getLstUsersDB()) {

			// Gets the selected User and its index in the model list
			selectedUser = (User) panel.getLstUsersDB().getSelectedValue();
			// indexUser = panel.getLstUsersDB().getSelectedIndex();
		
			// Displays informations of the selected User
			panel.getTxtAlias().setText(selectedUser.getAlias());
			panel.getTxtMail().setText(selectedUser.getEmail());
			panel.getTxtAfpa().setText(selectedUser.getAfpa());

			// These fields can't be changed by the User
			panel.getTxtAlias().setEnabled(false);
			panel.getTxtAfpa().setEnabled(false);

			// Depending on status, displays related informations of the selected User
			switch (selectedUser.getStatus()) {
			case "Stagiaire" :  
				// Casts User to Trainee
				// selectedTrainee = (Trainee) selectedUser;
				// Status
				panel.getOptTrainee().setSelected(true);
				panel.getOptTrainee().setEnabled(true);
				panel.getOptFormerTrainee().setEnabled(true);
				panel.getOptTrainer().setEnabled(false);
				break;

			case "Ancien" :
				// Casts User to FormerTrainee
				// selectedFormerTrainee = (FormerTrainee) selectedUser;
				// Status
				panel.getOptFormerTrainee().setSelected(true);
				panel.getOptTrainee().setEnabled(false);
				panel.getOptFormerTrainee().setEnabled(false);
				panel.getOptTrainer().setEnabled(false);
				break;

			case "Formateur" :
				// Casts User to Trainer
				// selectedTrainer = (Trainer) selectedUser;
				// Status
				panel.getOptTrainer().setSelected(true);
				panel.getOptTrainee().setEnabled(false);
				panel.getOptFormerTrainee().setEnabled(false);
				panel.getOptTrainer().setEnabled(false);
				// A trainer can change his place of work
				panel.getTxtAfpa().setEnabled(true);
				break;

			default:
				System.out.println("Aucun statut sélectionné.");
				break;
			}

		}

	}

	@Override
	public void mouseEntered(MouseEvent me) {
	}

	@Override
	public void mouseExited(MouseEvent me) {
	}

	@Override
	public void mousePressed(MouseEvent me) {
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
	}


	/**
	 * This method clears all the panWest: input field, button.
	 * 
	 * @author Claire
	 * @version 26-10-2016 
	 */
	private void clearInputPanel() {
		// Clears and re-enables status JRadioButton
		jrButtonGrp.clearSelection();
		panel.getOptTrainee().setEnabled(true);
		panel.getOptFormerTrainee().setEnabled(true);
		panel.getOptTrainer().setEnabled(true);

		// Calls the method which clears and enables all JTextField 
		allJTextFields = panel.getAllJTextFields();
		MethodsForListeners.resetJTextField(allJTextFields);
	}

	/**
	 * This method controls if a JRadioButton is null.
	 * 
	 * @param jrButtonSelected
	 * @param label
	 * @throws NoButtonException
	 * @version 26-10-2016
	 */
	private void controlButton(JRadioButton jrButtonSelected, String label) throws NoButtonException {

		if (jrButtonSelected == null) {
			throw new NoButtonException("Merci de faire un choix pour " + label);
		}
	}

	/**
	 * This method controls the alias field input.
	 * 
	 * @author Claire
	 * @param attribute, label
	 * @return attribute
	 * @throws EmptyFieldException
	 * @throws LengthStringException 
	 * @throws SQLException 
	 * @throws AlreadyUsedException
	 * @version 25-10-2016
	 */
	private String controlAlias(String attribute, String label) throws EmptyFieldException, 
	LengthStringException, AlreadyUsedException, SQLException {

		PanelUserCRUDControl.isEmpty(attribute);
		PanelUserCRUDControl.isLessTwentyChar(attribute, label);
		PanelUserCRUDControl.isAliasAlreadyUsed(attribute);

		return attribute;
	}
}
