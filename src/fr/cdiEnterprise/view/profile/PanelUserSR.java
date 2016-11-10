/**
 * 
 */
package fr.cdiEnterprise.view.profile;

import java.awt.BorderLayout;
import java.awt.Font;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

import fr.cdiEnterprise.control.PanelUserSRListener;
import fr.cdiEnterprise.service.Users;
import fr.cdiEnterprise.service.UsersTableModel;
import net.miginfocom.swing.MigLayout;

/**
 * Panel test to display a list of all users.
 * 
 * @author Claire
 * @version 23-10-2016
 */
public class PanelUserSR extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Components for panNorth
	private JPanel panNorth;
	private JLabel lblPanelTitle;
	private JLabel lblDisplay;
	private JButton cmdAll;
	private JButton cmdTrainee;
	private JButton cmdFormerTrainee;
	private JButton cmdTrainer;

	// Attributes for a JTable
	private UsersTableModel tblMdlUser;
	private JTable jtUser;

	// JScrollPane for BorderLayout Center
	private JScrollPane panScrollList;

	// Listener
	private PanelUserSRListener listener;

	/**
	 * Constructs a panel which display a list of Users.
	 * 
	 * @throws SQLException 
	 */
	public PanelUserSR() throws SQLException {

		// Main layout for users display panel
		this.setLayout(new BorderLayout(10, 20));

		// NORTH - Title
		panNorth = new JPanel();
		panNorth.setLayout(new MigLayout());
		this.add(panNorth, BorderLayout.NORTH);

		lblPanelTitle = new JLabel("Membres");
		lblPanelTitle.setFont(new Font(getName(), Font.BOLD, 14));
		panNorth.add(lblPanelTitle, "wrap 20");

		lblDisplay = new JLabel("Voir : ");
		panNorth.add(lblDisplay);
		cmdAll = new JButton("Tout");
		panNorth.add(cmdAll);
		cmdTrainee = new JButton("Stagiaire");
		panNorth.add(cmdTrainee);
		cmdFormerTrainee = new JButton("Ancien");
		panNorth.add(cmdFormerTrainee);
		cmdTrainer = new JButton("Formateur");
		panNorth.add(cmdTrainer);

		// CENTER - List
		panScrollList = new JScrollPane();
		panScrollList.setBorder(new EmptyBorder(0, 20, 0, 20));
		this.add(panScrollList, BorderLayout.CENTER);		

		// Dynamic JTable
		tblMdlUser = new UsersTableModel();
		jtUser = new JTable(tblMdlUser);
		// Only one line at a time can be selected
		jtUser.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		panScrollList.setViewportView(jtUser);


		// LISTENER
		listener = new PanelUserSRListener(this);
		cmdAll.addActionListener(listener);
		cmdTrainee.addActionListener(listener);
		cmdFormerTrainee.addActionListener(listener);
		cmdTrainer.addActionListener(listener);
	}


	/**
	 * This method remove all elements from the UsersTableModel and add the new one from parameters.
	 * 
	 * @author Claire
	 * @param users
	 * @version 24-10-2016
	 */
	public void refresh(Users users) {	
		tblMdlUser.refresh(users);
	}	

	/**
	 * @return the tblMdlUser
	 */
	public UsersTableModel getTblMdlUser() {
		return tblMdlUser;
	}

	/**
	 * @return the cmdFormerTrainee
	 */
	public JButton getCmdFormerTrainee() {
		return cmdFormerTrainee;
	}

	/**
	 * @return the cmdAll
	 */
	public JButton getCmdAll() {
		return cmdAll;
	}

	/**
	 * @return the cmdTrainee
	 */
	public JButton getCmdTrainee() {
		return cmdTrainee;
	}

	/**
	 * @return the cmdTrainer
	 */
	public JButton getCmdTrainer() {
		return cmdTrainer;
	}		
}