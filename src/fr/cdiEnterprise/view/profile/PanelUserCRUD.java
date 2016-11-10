package fr.cdiEnterprise.view.profile;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

import fr.cdiEnterprise.control.PanelUserCRUDListener;
import fr.cdiEnterprise.dao.UserDAO;
import fr.cdiEnterprise.model.User;
import fr.cdiEnterprise.service.Users;
import net.miginfocom.swing.MigLayout;

/**
 * Panel for a user's profile CRUD.
 * 
 * @author Claire
 * @version 16-10-2016
 */
public class PanelUserCRUD extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//TODO (Claire) fake companies list in database!
	private String [] company = {"Aucune", "9e compagnie", "Cie", "Comme Pagny", "Autre..."};

	// Attributes for DB access
	private Users users;

	// NORTH - panel and label
	private JPanel panNorth;
	private JLabel lblFieldInfo;

	// WEST
	private JPanel panWest;
	private JScrollPane panScrollWest;
	// First panel for first log-in informations
	private JPanel panRegister;
	// Labels
	private JLabel lblStatus;
	private JLabel lblAlias;
	private JLabel lblInfoAlias;
	private JLabel lblMail;
	private JLabel lblInfoMail;
	private JLabel lblAfpa;
	private JLabel lblTrainer;
	// Others components
	private ButtonGroup statusGrp;
	private JRadioButton optTrainee;
	private JRadioButton optFormerTrainee;
	private JRadioButton optTrainer;
	private JTextField txtAlias;
	private JTextField txtMail;
	private JTextField txtAfpa;
	private JTextField txtTrainer;

	// Second panel for public profile informations
	private JPanel panPublic;
	// Labels
	private JLabel lblSession;
	private JLabel lblMainInfoLang;
	private JLabel lblApproachedCie;
	private JLabel lblJob;
	private JLabel lblCurrentCie;
	private JLabel lblFormerCie;
	//Others components
	private JTextField txtSession;
	private JRadioButton optCsharp;
	private JRadioButton optJava;
	private JTextField txtJob;

	// Third panel for restricted profile informations
	private JPanel panRestricted;
	// Labels
	private JLabel lblSurname;
	private JLabel lblGivenName;
	// Others components
	private JTextField txtSurname;
	private JTextField txtGivenName;

	// Fourth panel for optional profile informations
	private JPanel panOptional;
	// Labels
	private JLabel lblInfoLang;
	private JLabel lblApi;
	private JLabel lblWebsite;
	private JLabel lblLinkedIn;
	// Others components
	private JTextField txtWebsite;
	private JTextField txtLinkedIn;

	// CENTER
	private JPanel panCenter;
	// Attributes for JList of users
	private JPanel panLstUsersDB;
	private DefaultListModel<User> lstMdlUsersDB;
	private JList<User> lstUsersDB;
	private JScrollPane panScrollLstUsersDB;

	// SOUTH
	private JPanel panSouth;
	// JButton
	private JButton cmdCancel;
	private JButton cmdCreate;
	private JButton cmdUpdate;
	private JButton cmdDelete;

	// LISTENER
	private PanelUserCRUDListener listener;

	// ArrayList of components
	private ArrayList<JTextField> allJTextFields;


	/**
	 * Constructor for PanelUserCRUD.
	 * 
	 * @throws SQLException
	 */
	public PanelUserCRUD() throws SQLException {

		// Main layout for user CRUD panel
		this.setLayout(new BorderLayout(10, 20));


		// NORTH - main information on compulsory fields
		panNorth = new JPanel();
		panNorth.setLayout(new FlowLayout());
		this.add(panNorth, BorderLayout.NORTH);

		lblFieldInfo = new JLabel("Les champs marqués d'étoiles sont obligatoires pour : "
				+ "* tous les utilisateurs / "
				+ "** les statuts Stagiaire et Ancien / "
				+ "*** le statut Ancien");
		lblFieldInfo.setFont(new Font(getName(), Font.BOLD, 14));
		panNorth.add(lblFieldInfo);
		panNorth.setBorder(BorderFactory.createLineBorder(Color.RED));


		// WEST - For create / update with four horizontal parts
		panWest = new JPanel();
		panWest.setLayout(new MigLayout());
		panScrollWest = new JScrollPane(panWest);
		panScrollWest.setBorder(new EmptyBorder(0, 5,0, 0));
		this.add(panScrollWest, BorderLayout.WEST);

		// FIRST horizontal part: first log-in informations
		panRegister = new JPanel();	
		panRegister.setLayout(new MigLayout());
		panRegister.setBorder(BorderFactory.createTitledBorder("ENREGISTREMENT"));
		panWest.add(panRegister, "wrap, w 475!");

		// User status
		lblStatus = new JLabel("Statut* : ");
		panRegister.add(lblStatus, "w 200!");
		optTrainee = new JRadioButton("Stagiaire");
		optFormerTrainee = new JRadioButton("Ancien");
		optTrainer = new JRadioButton("Formateur");
		// RadioButton group creation
		statusGrp = new ButtonGroup();
		statusGrp.add(optTrainee);
		statusGrp.add(optFormerTrainee);
		statusGrp.add(optTrainer);

		panRegister.add(optTrainee, "split 3");
		panRegister.add(optFormerTrainee);
		panRegister.add(optTrainer, "wrap");

		// User alias
		lblAlias = new JLabel("Nom d'utilisateur* : ");
		panRegister.add(lblAlias);
		txtAlias = new JTextField(20);
		panRegister.add(txtAlias, "wrap");
		lblInfoAlias = new JLabel("Maximum 20 caractères");
		lblInfoAlias.setFont(new Font(getName(), Font.ITALIC, 13));
		lblInfoAlias.setForeground(Color.GRAY);
		panRegister.add(lblInfoAlias, "wrap");

		// User email
		lblMail = new JLabel("Mail* : ");
		panRegister.add(lblMail);
		txtMail = new JTextField(20);
		panRegister.add(txtMail, "wrap");
		lblInfoMail = new JLabel("Ne sera pas rendu public");
		lblInfoMail.setFont(new Font(getName(), Font.ITALIC, 13));
		lblInfoMail.setForeground(Color.GRAY);
		panRegister.add(lblInfoMail, "wrap");

		// Name of AFPA where the user did his training
		lblAfpa = new JLabel("AFPA* : ");
		panRegister.add(lblAfpa);
		txtAfpa = new JTextField(20);
		panRegister.add(txtAfpa, "wrap");

		// User trainer
		lblTrainer = new JLabel("Nom du formateur** : ");
		panRegister.add(lblTrainer);
		txtTrainer = new JTextField(20);
		panRegister.add(txtTrainer);

		// SECOND horizontal part: public profile informations
		panPublic = new JPanel();
		panPublic.setLayout(new MigLayout());
		panPublic.setBorder(BorderFactory.createTitledBorder("PROFIL PUBLIC"));
		panWest.add(panPublic, "wrap, w 475!");

		// Code of training session, usually 5 numbers
		lblSession = new JLabel("Numéro de session** : ");
		panPublic.add(lblSession, "w 200!");
		txtSession = new JTextField(20);
		panPublic.add(txtSession, "wrap");

		// Main programming language of the training
		lblMainInfoLang = new JLabel("Langage principal** : ");
		panPublic.add(lblMainInfoLang);
		optCsharp = new JRadioButton("C#");
		optJava = new JRadioButton("Java");
		panPublic.add(optCsharp, "split 2");
		panPublic.add(optJava, "wrap");

		// List of approached companies
		// TODO (Claire) put arraylist in JList?
		lblApproachedCie = new JLabel("Entreprise(s) démarchée(s)*** : ");
		panPublic.add(lblApproachedCie);
		JComboBox<String> cboApproachedCie = new JComboBox<String> ();
		for (int i = 0; i < company.length; i++) {
			cboApproachedCie.addItem(company[i]);
		}
		cboApproachedCie.setMaximumRowCount(5);
		panPublic.add(cboApproachedCie, "wrap");

		// Current job
		lblJob = new JLabel("Profession actuelle*** : ");
		panPublic.add(lblJob);
		txtJob = new JTextField(20);
		panPublic.add(txtJob, "wrap");

		// Current company
		// TODO (Claire) cie arraylist
		lblCurrentCie = new JLabel("Entreprise actuelle*** : ");
		panPublic.add(lblCurrentCie);
		JComboBox<String> cboCurrentCie = new JComboBox<String> ();
		for (int i = 0; i < company.length; i++) {
			cboCurrentCie.addItem(company[i]);
		}
		cboCurrentCie.setMaximumRowCount(5);
		panPublic.add(cboCurrentCie, "wrap");

		// Former companies
		lblFormerCie = new JLabel("Ancienne(s) entreprise(s)*** : "); 
		panPublic.add(lblFormerCie);
		JComboBox<String> cboFormerCie = new JComboBox<String> ();
		for (int i = 0; i<company.length; i++) {
			cboFormerCie.addItem(company[i]);
		}
		cboFormerCie.setMaximumRowCount(5);
		panPublic.add(cboFormerCie, "wrap");

		// THIRD horizontal part: protected profile informations
		panRestricted = new JPanel();
		panRestricted.setLayout(new MigLayout());
		panRestricted.setBorder(BorderFactory.createTitledBorder("PROFIL RESTREINT"));
		panWest.add(panRestricted, "wrap, w 475!");

		// Trainee surname for trainer (monitoring)
		lblSurname = new JLabel("Nom** : ");
		panRestricted.add(lblSurname, "w 200!");
		txtSurname = new JTextField(20);
		panRestricted.add(txtSurname, "wrap");

		// Trainee given name for trainer (monitoring)
		lblGivenName = new JLabel("Prénom** : ");
		panRestricted.add(lblGivenName);
		txtGivenName = new JTextField(20);
		panRestricted.add(txtGivenName, "wrap");

		// FOURTH horizontal part: optional profile informations
		panOptional = new JPanel();
		panOptional.setLayout(new MigLayout());
		panOptional.setBorder(BorderFactory.createTitledBorder("VISIBLE PUBLIQUEMENT SI RENSEIGNÉ"));
		panWest.add(panOptional, "w 475!");

		// Other known programming languages
		lblInfoLang = new JLabel("Autre(s) langage(s) : ");
		panOptional.add(lblInfoLang, "w 200!");
		// TODO (Claire) create language list in dao
		String [] progLanguage = {"C", "PHP"};
		JComboBox<String> cboProgLanguage = new JComboBox<String> ();
		for (int i = 0; i < progLanguage.length; i++) {
			cboProgLanguage.addItem(progLanguage[i]);
		}
		cboProgLanguage.setMaximumRowCount(2);
		panOptional.add(cboProgLanguage, "wrap");

		// Known graphic API
		lblApi = new JLabel("API graphique : ");
		panOptional.add(lblApi);
		// TODO (Claire) create api list in dao
		String [] graphicApi = {"AWT", "JFace", "Swing", "SWT"};
		JComboBox<String> cboGraphicApi = new JComboBox<String> ();
		for (int i = 0; i < graphicApi.length; i++) {
			cboGraphicApi.addItem(graphicApi[i]);
		}
		cboGraphicApi.setMaximumRowCount(4);
		panOptional.add(cboGraphicApi, "wrap");

		// User web site
		lblWebsite = new JLabel("Site Internet : ");
		panOptional.add(lblWebsite);
		txtWebsite = new JTextField(20);
		panOptional.add(txtWebsite, "wrap");

		// User LinkedIn
		lblLinkedIn = new JLabel("Profil LinkedIn");
		panOptional.add(lblLinkedIn);
		txtLinkedIn = new JTextField(20);
		panOptional.add(txtLinkedIn, "wrap");


		// CENTER - for list of users
		panCenter = new JPanel();
		panCenter.setLayout(new MigLayout());
		panCenter.setBorder(new EmptyBorder(0, 0, 0, 5));
		this.add(panCenter, BorderLayout.CENTER);

		// TEST JList
		panLstUsersDB = new JPanel();
		panLstUsersDB.setLayout(new MigLayout());
		panLstUsersDB.setBorder(BorderFactory.createTitledBorder("TEST DB - Liste des utilisateurs"));
		panCenter.add(panLstUsersDB);

		lstMdlUsersDB = new DefaultListModel<User>();
		lstUsersDB = new JList<User>(lstMdlUsersDB);
		lstUsersDB.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		users = UserDAO.getUsersList();
		System.out.println(users);
		if(users != null) {
			for (User user : users) {
				if(user != null) {
					lstMdlUsersDB.addElement(user);
				}
			}
		}
		panScrollLstUsersDB = new JScrollPane(lstUsersDB);
		panLstUsersDB.add(panScrollLstUsersDB);


		// SOUTH - Footer for JButton
		panSouth = new JPanel();
		panSouth.setLayout(new FlowLayout());
		this.add(panSouth, BorderLayout.SOUTH);
		cmdCancel = new JButton("Annuler");
		panSouth.add(cmdCancel);
		cmdCreate = new JButton("S'enregistrer");
		panSouth.add(cmdCreate);
		cmdUpdate = new JButton("Modifier");
		panSouth.add(cmdUpdate);
		cmdDelete = new JButton("Supprimer");
		panSouth.add(cmdDelete);

		// ArrayList of components
		allJTextFields = new ArrayList<JTextField>();
		allJTextFields.add(txtAlias);
		allJTextFields.add(txtMail);
		allJTextFields.add(txtAfpa);
		allJTextFields.add(txtTrainer);

		// LISTENERS
		listener = new PanelUserCRUDListener(this);
		cmdCancel.addActionListener(listener);
		cmdCreate.addActionListener(listener);
		cmdUpdate.addActionListener(listener);
		cmdDelete.addActionListener(listener);

		lstUsersDB.addMouseListener(listener);
	}


	/**
	 * This method remove all elements from the DefaultListModel and add the new one from parameters.
	 * 
	 * @author Claire
	 * @param users
	 * @version 24-10-2016
	 */
	public void refresh(Users users) {	
		lstMdlUsersDB.removeAllElements();
		for (User user : users) {
			lstMdlUsersDB.addElement(user);
		}	
	}

	
	
	/**
	 * @return the lblStatus
	 */
	public JLabel getLblStatus() {
		return lblStatus;
	}

	/**
	 * @return the statusGrp
	 */
	public ButtonGroup getStatusGrp() {
		return statusGrp;
	}
	
	/**
	 * @return the optTrainee
	 */
	public JRadioButton getOptTrainee() {
		return optTrainee;
	}

	/**
	 * @param optTrainee the optTrainee to set
	 */
	public void setOptTrainee(JRadioButton optTrainee) {
		this.optTrainee = optTrainee;
	}

	/**
	 * @return the optFormerTrainee
	 */
	public JRadioButton getOptFormerTrainee() {
		return optFormerTrainee;
	}

	/**
	 * @param optFormerTrainee the optFormerTrainee to set
	 */
	public void setOptFormerTrainee(JRadioButton optFormerTrainee) {
		this.optFormerTrainee = optFormerTrainee;
	}

	/**
	 * @return the optTrainer
	 */
	public JRadioButton getOptTrainer() {
		return optTrainer;
	}

	/**
	 * @param optTrainer the optTrainer to set
	 */
	public void setOptTrainer(JRadioButton optTrainer) {
		this.optTrainer = optTrainer;
	}

	/**
	 * @return the lblAlias
	 */
	public JLabel getLblAlias() {
		return lblAlias;
	}

	/**
	 * @param lblAlias the lblAlias to set
	 */
	public void setLblAlias(JLabel lblAlias) {
		this.lblAlias = lblAlias;
	}

	/**
	 * @return the txtAlias
	 */
	public JTextField getTxtAlias() {
		return txtAlias;
	}

	/**
	 * @return the txtMail
	 */
	public JTextField getTxtMail() {
		return txtMail;
	}

	/**
	 * @return the txtAfpa
	 */
	public JTextField getTxtAfpa() {
		return txtAfpa;
	}

	/**
	 * @return the txtTrainer
	 */
	public JTextField getTxtTrainer() {
		return txtTrainer;
	}

	/**
	 * @return getLstUsers
	 */
	public JList<User> getLstUsersDB() {
		return lstUsersDB;
	}

	/**
	 * @return getLstMdlUsers
	 */
	public DefaultListModel<User> getLstMdlUsersDB() {
		return lstMdlUsersDB;
	}

	/**
	 * @return the cmdCancel
	 */
	public JButton getCmdCancel() {
		return cmdCancel;
	}

	/**
	 * @return the cmdCreate
	 */
	public JButton getCmdCreate() {
		return cmdCreate;
	}

	/**
	 * @return the cmdUpdate
	 */
	public JButton getCmdUpdate() {
		return cmdUpdate;
	}

	/**
	 * @return the cmdDelete
	 */
	public JButton getCmdDelete() {
		return cmdDelete;
	}

	/**
	 * @return the allJTextFields
	 */
	public ArrayList<JTextField> getAllJTextFields() {
		return allJTextFields;
	}
}