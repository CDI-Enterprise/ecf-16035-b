/**
 * 
 */
package fr.cdiEnterprise.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import fr.cdiEnterprise.view.MainFrame;
import fr.cdiEnterprise.view.Menu;

/**
 * Listeners for menu of the main frame
 * @version 07-10-2016
 * @author Claire, Anais, Nicolas, Ismael, Olivier
 */

// TODO (Groupe) is this the best listener for a JMenuBar?
public class MainMenuListener implements ActionListener {

	private Menu menu;

	/**
	 * @param menu
	 */
	public MainMenuListener(Menu menu) {
		this.menu = menu;
	}

	// ACTION LISTENER
	@Override
	public void actionPerformed(ActionEvent e) {

		// Display the panel for User management
		if(e.getSource() == menu.getSubProfileCRUD()) {

			MainFrame.getPanMain().removeAll();
			MainFrame.getPanMain().add(MainFrame.getScrollPanelUserCRUD());
			MainFrame.getPanMain().repaint();
			MainFrame.getPanMain().validate();
		}

		// Display the panel with a list of existing members
		if(e.getSource() == menu.getSubProfileSR()) {

			MainFrame.getPanMain().removeAll();
			MainFrame.getPanMain().add(MainFrame.getScrollPanelUserSR());
			MainFrame.getPanMain().repaint();
			MainFrame.getPanMain().validate();
		}

		if(e.getSource() == menu.getSubCompanyCreate()){
			MainFrame.getPanMain().removeAll();
			MainFrame.getPanMain().add(MainFrame.getScrollCreateCompany());
			MainFrame.getPanMain().repaint();
			MainFrame.getPanMain().revalidate();
		}

		if(e.getSource() == menu.getSubCompanyUpdateDelete()){
			MainFrame.getPanMain().removeAll();
			MainFrame.getPanMain().add(MainFrame.getScrollUpdateDeleteCompany());
			MainFrame.getPanMain().repaint();
			MainFrame.getPanMain().revalidate();
		}

		if(e.getSource() == menu.getSubCompanyRead()){
			MainFrame.getPanMain().removeAll();
			MainFrame.getPanMain().add(MainFrame.getScrollSRCompanies());
			MainFrame.getPanMain().repaint();
			MainFrame.getPanMain().revalidate();
		}

		if(e.getSource() == menu.getSubMessageDisplay()){
			MainFrame.getPanMain().removeAll();
			MainFrame.getPanMain().add(MainFrame.getPanelMessaging());
			MainFrame.getPanMain().validate();
			MainFrame.getPanMain().repaint();

		}
		if(e.getSource() == menu.getSubBookMarkRead())
		{
			MainFrame.getPanMain().removeAll();
			MainFrame.getPanMain().add(MainFrame.getPanelBookMark());
			MainFrame.getPanMain().validate();
			MainFrame.getPanMain().repaint();
		}

		if(e.getSource() == menu.getSubSearchRechercher())
		{
			MainFrame.getPanMain().removeAll();
			MainFrame.getPanMain().add(MainFrame.getPanelRecherche());
			MainFrame.getPanMain().validate();
			MainFrame.getPanMain().repaint();
		}

		// Display a JOptionPan update
		if(e.getSource() == menu.getSubHelpUpdate()) {

			String update = "Aucune mise à jour disponible.";

			JOptionPane.showMessageDialog(MainFrame.getPanMain(), update);
		}

		// Display a JOptionPane about
		if(e.getSource() == menu.getSubHelpAbout()) {

			String newline = System.getProperty("line.separator");

			String about = "CDI Enterprise for Desktop"
					+ newline + newline
					+ "Version Alpha"
					+ newline + newline
					+ "CDI Enterprise est une application de gestion"
					+ newline + "pour une base de données de fiches informatives"
					+ newline + "d'entreprises."
					+ newline + newline
					+ "Cette version est fournie par ACINO Company"
					+ newline + "2016 GNU GENERAL PUBLIC LICENSE"
					+ newline + newline;

			//JOptionPane.showMessageDialog(MainFrame.getPanMain(), about);
			Icon icon = new ImageIcon("spock-24.png");
			JOptionPane.showMessageDialog(MainFrame.getPanMain(), about, "A propos de CDI Enterprise",
					JOptionPane.INFORMATION_MESSAGE, icon);
			
		}
	}
}
