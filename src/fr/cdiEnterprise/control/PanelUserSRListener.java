/**
 * 
 */
package fr.cdiEnterprise.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import fr.cdiEnterprise.dao.UserDAO;
import fr.cdiEnterprise.service.Users;
import fr.cdiEnterprise.view.profile.PanelUserSR;

/**
 * Listeners for users Search.
 *
 * @author Claire
 * @version 23-10-2016
 */
public class PanelUserSRListener implements ActionListener {

	// Given attribute
	private PanelUserSR panel;
	// Attributes for DB access
	private Users users;
	// Frame for error message
	private JFrame popUpFrame;

	
	/**
	 * Constructs a listener taking a panel for attribute.
	 */
	public PanelUserSRListener(PanelUserSR panel) {
		this.panel = panel;
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == panel.getCmdAll()) {

			try {			
				users = UserDAO.getUsersList();
				panel.refresh(users);

				if (users.isEmpty()) {
					JOptionPane.showMessageDialog(popUpFrame, "Oups, la liste est vide !");
				}

			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}

		if (e.getSource() == panel.getCmdTrainee()) {

			try {
				users = UserDAO.getUsersByStatusList(panel.getCmdTrainee().getText());
				panel.refresh(users);

				if (users.isEmpty()) {
					JOptionPane.showMessageDialog(popUpFrame, "Désolé, il n'y a aucun "
							+ panel.getCmdTrainee().getText() + " !");
				}

			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}

		if (e.getSource() == panel.getCmdFormerTrainee()) {

			try {
				users = UserDAO.getUsersByStatusList(panel.getCmdFormerTrainee().getText());
				panel.refresh(users);

				if (users.isEmpty()) {
					JOptionPane.showMessageDialog(popUpFrame, "Désolé, il n'y a aucun "
							+ panel.getCmdFormerTrainee().getText() + " !");
				}

			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}

		if (e.getSource() == panel.getCmdTrainer()) {

			try {
				users = UserDAO.getUsersByStatusList(panel.getCmdTrainer().getText());
				panel.refresh(users);

				if (users.isEmpty()) {
					JOptionPane.showMessageDialog(popUpFrame, "Désolé, il n'y a aucun "
							+ panel.getCmdTrainer().getText() + " !");
				}

			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
}