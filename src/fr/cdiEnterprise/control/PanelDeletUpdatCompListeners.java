package fr.cdiEnterprise.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import fr.cdiEnterprise.dao.DataBaseCompany;
import fr.cdiEnterprise.model.Company;
import fr.cdiEnterprise.view.MainFrame;
import fr.cdiEnterprise.view.company.CompanyDeletUpdatPanel;

/**
 * Listeners for panel "Company Delete and update"
 *
 * @version 21-10-2016
 * @author Anaïs
 * 
 *
 */

public class PanelDeletUpdatCompListeners implements ActionListener, ListSelectionListener, MouseListener {

	/* Given attributes */
	private CompanyDeletUpdatPanel panCompDeletUpdat;

	/* Attributes for delete and update company */
	private Company selecCompany;
	private static int selecIndSelec;

	private JFrame popupError;

	public PanelDeletUpdatCompListeners(CompanyDeletUpdatPanel panCompDeletUpdat) {
		this.panCompDeletUpdat = panCompDeletUpdat;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		/* */

		if (e.getSource() == panCompDeletUpdat.getBtnCancel()) {
			MainFrame.getPanMain().removeAll();
			MainFrame.getPanMain().add(MainFrame.getPanHome());
			MainFrame.getPanMain().repaint();
			MainFrame.getPanMain().revalidate();
		}

		if (e.getSource() == panCompDeletUpdat.getBtnDelete()) {
			// System.out.println("*******Suppression*****");
			// System.out.println(selecIndDelet);
			JOptionPane.showConfirmDialog(popupError, "Vous allez supprimer une fiche entreprise");
			try {
				DataBaseCompany.deleteCompanyData(selecIndSelec);
				MethodsForListeners.refreshListCompanies();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}

		if (e.getSource() == panCompDeletUpdat.getBtnUpdate()) {
			System.out.println("Modification d'une entreprise");
			String newAdress = panCompDeletUpdat.getTxtCompanyAdress().getText();
			try {
				DataBaseCompany.updateCompanyData(selecIndSelec, newAdress);
				MethodsForListeners.refreshListCompanies();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

		}
	}

	@Override
	public void valueChanged(ListSelectionEvent ev) {
		// if (ev.getSource() == panCompDeletUpdat.getLstCompanies()) {
		//// System.out.println("Affiche"); (test)
		// selecCompanie = (Company)
		// panCompDeletUpdat.getLstCompanies().getSelectedValue();
		// selecIndex = panCompDeletUpdat.getLstCompanies().getSelectedIndex();
		//// System.out.println(selecCompanie); (test)
		//// System.out.println(selecIndex); (test)
		// }

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// JList list = (JList)e.getSource();
		//
		// if (e.getClickCount() == 2) {
		// System.out.println("double clicked");
		// }

		if (e.getSource() == panCompDeletUpdat.getLstCompanies()) {
			selecCompany = (Company) panCompDeletUpdat.getLstCompanies().getSelectedValue();
			selecIndSelec = selecCompany.getCompanyId();
			System.out.println(selecIndSelec);
			panCompDeletUpdat.getTxtCompanyName().setText(selecCompany.getCompanyName());
			panCompDeletUpdat.getTxtCompanyAdress().setText(selecCompany.getAdress());
			panCompDeletUpdat.getTxtCompanyCity().setText(selecCompany.getCity());
			panCompDeletUpdat.getTxtPostalCode().setText(selecCompany.getPostalCode());
			panCompDeletUpdat.getTxtSector().setText(selecCompany.getSector());
			panCompDeletUpdat.getTxtProjets().setText(selecCompany.getProjets());
			panCompDeletUpdat.getTxtWebSite().setText(selecCompany.getWebSite());

			try {
				panCompDeletUpdat.getTxtContactName().setText(selecCompany.getContact().getName());
				panCompDeletUpdat.getTxtContactPhone().setText(selecCompany.getContact().getPhoneNumber());
				panCompDeletUpdat.getTxtContactMail().setText(selecCompany.getContact().getEmail());
			} catch (NullPointerException excpt) {

			}

		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {

	}

	@Override
	public void mouseExited(MouseEvent arg0) {

	}

	@Override
	public void mousePressed(MouseEvent arg0) {

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {

	}

}
