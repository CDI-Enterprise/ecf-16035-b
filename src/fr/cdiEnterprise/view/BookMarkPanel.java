/**
 * 
 */
package fr.cdiEnterprise.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.sql.SQLException;

import javax.swing.BorderFactory;
//import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import fr.cdiEnterprise.control.BookMarkListener;
import fr.cdiEnterprise.dao.FavoriteDao;
import fr.cdiEnterprise.model.FavoriteModelTable;
import fr.cdiEnterprise.service.Favorites;
import fr.cdiEnterprise.model.Favorite;

import net.miginfocom.swing.MigLayout;

/**
 *12 oct. 2016
 * @author Ismael
 *ecf-16035-a
 *21:48:53
 */

public class BookMarkPanel extends JPanel 
{

	/**
	 * 
	 */

	private static final long serialVersionUID = 1L;

	//Panel
	private JPanel contentBookMarkPan;				//Main panel
	private JPanel panelMarkLeft;					//left's panel
	private JPanel listCompanyResultPan;			//bookMarkResult's panel			

	//Label
	private JLabel lblMyNote;						//Title of txtNoteUser part

	private Favorites favorites;
	private JComboBox <String> lstMyFavorites;		//List of enterprises recorded

	private JTextArea txtNoteUser ;					//User's mark
	private JTextField txtSearchBookMark;			//Search a company
	private JTable bookMarkResult;					//Company's information
	private DefaultTableModel tabFieldInfo;	

	private JScrollPane noteUserPane;				//Add scrollbar to the txtNoteUser
	private JScrollPane favoriteScrollTable;		//Add scrollbar to the bookMarkResult

	//Button	
	private JButton btnBookMarkSee;					//Button to show company's information
	private JButton btnBookMarkDelete;				//Button to delete a favorite
	private JButton btnSaveNote;					//Bouton to save a user's mark
	private JButton btnSearchBookMark;				//Button to search a company in my favorites
	private JButton btnGoCompanySheet;				//Button to go to company's presentation
	private JButton btnContactMail;

	private BookMarkListener listener = new BookMarkListener(this);					//Creation of actionListener 

	/**
	 * 
	 */

	public BookMarkPanel()
	{
		//		//Main
		Container markPan = this;
		markPan.setLayout(new BorderLayout(20,20));


		//Proprieté Containeur
		//
		contentBookMarkPan = new JPanel();
		this.add(contentBookMarkPan);
		contentBookMarkPan.setPreferredSize(new Dimension (850,450));						//Taille fenetre;
		contentBookMarkPan.setBorder(BorderFactory.createTitledBorder("  Mes Favoris  "));
		contentBookMarkPan.setBackground(Color.LIGHT_GRAY);									//Couleur Fond
		contentBookMarkPan.setBorder(BorderFactory.createLineBorder(Color.BLUE));
		contentBookMarkPan.setLayout(new BorderLayout(0, 0));								//agencement des composants (ici bordure)

		panelMarkLeft = new JPanel();
		panelMarkLeft.setLayout(new MigLayout("", "[grow][]", "[][][][][][][][grow][][][][]"));
		panelMarkLeft.setBorder(BorderFactory.createTitledBorder("  Mes Favoris  "));
		contentBookMarkPan.add(panelMarkLeft, BorderLayout.WEST);

		lstMyFavorites = new JComboBox<String>();
		try {
			favorites = FavoriteDao.getMyFavorite();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("coucou" + favorites);
		for (Favorite favorite : favorites)
		{
			lstMyFavorites.addItem(favorite.getCompanyName());
			System.out.println(favorite);
		}
		
		lstMyFavorites.setEditable(false);
		lstMyFavorites.setMaximumRowCount(3);
		panelMarkLeft.add(lstMyFavorites, "cell 0 1 2 1,growx");

		btnBookMarkSee = new JButton("Voir");
		panelMarkLeft.add(btnBookMarkSee, "cell 0 2");

		btnBookMarkDelete = new JButton("Supprimer");
		panelMarkLeft.add(btnBookMarkDelete, "cell 1 2");

		lblMyNote = new JLabel("Note");
		panelMarkLeft.add(lblMyNote, "cell 0 5");

		txtNoteUser = new JTextArea();
		txtNoteUser.setFont(new Font("Arial", Font.PLAIN, 16));
		txtNoteUser.setRows(10);
		txtNoteUser.setTabSize(160);
		txtNoteUser.setColumns(20);
		txtNoteUser.setLineWrap(true);
		txtNoteUser.setWrapStyleWord(false);
		noteUserPane = new JScrollPane(txtNoteUser);
		panelMarkLeft.add(noteUserPane, "flowx,cell 0 6 2 2, grow");

		btnSaveNote = new JButton("Sauver note");
		panelMarkLeft.add(btnSaveNote, "cell 1 8");

		txtSearchBookMark = new JTextField();
		txtSearchBookMark.setColumns(10);
		panelMarkLeft.add(txtSearchBookMark, "cell 0 9,growx");

		btnSearchBookMark = new JButton("Rechercher");
		panelMarkLeft.add(btnSearchBookMark, "cell 0 10");


		listCompanyResultPan = new JPanel();
		listCompanyResultPan.setLayout(new MigLayout("", "[][][grow]", "[][grow][]"));
		listCompanyResultPan.setBorder(BorderFactory.createTitledBorder("  Mon Entreprise  "));
		contentBookMarkPan.add(listCompanyResultPan, BorderLayout.CENTER);

		//Load list's table

		bookMarkResult = new JTable();
		tabFieldInfo = (DefaultTableModel) bookMarkResult.getModel();
		bookMarkResult = new JTable(new FavoriteModelTable());
		bookMarkResult.setPreferredScrollableViewportSize(new Dimension(460,200));
		favoriteScrollTable =new JScrollPane(bookMarkResult);
		listCompanyResultPan.add(favoriteScrollTable, "cell 2 1,grow");

		btnGoCompanySheet = new JButton("Fiche complete de l'entreprise");
		listCompanyResultPan.add(btnGoCompanySheet, "cell 2 2");

		btnContactMail = new JButton ("Contacter par mail");
		btnContactMail.setEnabled(false);
		listCompanyResultPan.add(btnContactMail, " cell 2 4");

		//LISTENER

		btnBookMarkSee.addActionListener(listener);
		btnBookMarkDelete.addActionListener(listener);
		btnSaveNote.addActionListener(listener);
		btnSearchBookMark.addActionListener(listener);
		btnGoCompanySheet.addActionListener(listener);
	}

	//Getters

	/**
	 * @return the lstMyFavorites
	 */
	public JComboBox<String> getLstMyFavorites() {
		return lstMyFavorites;
	}

	/**
	 * @return the txtNoteUser
	 */
	public JTextArea getTxtNoteUser() {
		return txtNoteUser;
	}

	/**
	 * @return the txtSearchBookMark
	 */
	public JTextField getTxtSearchBookMark() {
		return txtSearchBookMark;
	}

	/**
	 * @return the btnBookMarkSee
	 */
	public JButton getBtnBookMarkSee() {
		return btnBookMarkSee;
	}

	/**
	 * @return the btnBookMarkDelete
	 */
	public JButton getBtnBookMarkDelete() {
		return btnBookMarkDelete;
	}

	/**
	 * @return the btnSaveNote
	 */
	public JButton getBtnSaveNote() {
		return btnSaveNote;
	}

	/**
	 * @return the btnSearchBookMark
	 */
	public JButton getBtnSearchBookMark() {
		return btnSearchBookMark;
	}

	/**
	 * @return the btnGoCompanySheet
	 */
	public JButton getBtnGoCompanySheet() {
		return btnGoCompanySheet;
	}

	/**
	 * @return the tabFieldInfo
	 */
	public DefaultTableModel getTabFieldInfo() {
		return tabFieldInfo;
	}
}