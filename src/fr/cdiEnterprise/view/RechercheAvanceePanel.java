package fr.cdiEnterprise.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import net.miginfocom.swing.MigLayout;
import fr.cdiEnterprise.control.PanelRechercheAvanceeListener;
import fr.cdiEnterprise.model.ModelSearch;;

/**
 * 
 * @author Olivier
 * Création du panel rechercheAvancee
 *
 */

public class RechercheAvanceePanel extends JPanel{
	
		static int id;

		private static final long serialVersionUID = 1L;
		// declaration des composants
		private JPanel panelNorth;
		private JPanel panelCenter;
		private JPanel panelSouth;
		private JPanel panelEast;
		private JPanel panelWest;
		private JTable tableResultats;
		private JScrollPane scrollTable;
		private JLabel lblRaisonSocial;
		private JLabel lblDomaine;
		private JLabel lblVille;
		private JLabel lblRegion;
		private JLabel lblError;
		private JLabel lblResult;
		private JLabel lblSearchName;
		private JTextField txtRaisonSocial;
		private JTextField txtDomaine;
		private JTextField txtVille;
		private JTextField txtRegion;
		private JTextField txtResult;
		private JTextField txtSearchName;
		private JButton btnRechercher;
		private JButton btnEffacer;
		private JButton btnSave;
		private JButton btnDelete;
		private JButton btnModifier;
		private JCheckBox chkRaisonSocial;
		private JCheckBox chkDomaine;
		private JCheckBox chkVille;
		private JCheckBox chkRegion;
		private ModelSearch modelTable;
		private DefaultTableModel model;
		private PanelRechercheAvanceeListener listened;

		public RechercheAvanceePanel()  {
			initCompnent();
			//DefaultTableModel 
		}
		
		//methode pour effacer touts les critere
		public void clear(){
			txtRaisonSocial.setText("");
			txtDomaine.setText("");
			txtVille.setText("");
			txtRegion.setText("");
			chkRaisonSocial.setSelected(false);
			chkDomaine.setSelected(false);
			chkVille.setSelected(false);
			chkRegion.setSelected(false);
		}
		// methode de chargement de la liste des entreprises
//		void load(){
//			model.setRowCount(0);
//		}

		//Execution du constructeur
		private void initCompnent(){


			panelNorth = new JPanel();
			panelCenter = new JPanel();
			panelSouth = new JPanel();
			panelWest = new JPanel();
			panelEast = new JPanel();
			tableResultats = new JTable();
			lblRaisonSocial = new JLabel("Raison Sociale :");
			lblDomaine = new JLabel("Domaine :");
			lblVille = new JLabel("Ville :");
			lblRegion = new JLabel("Region :");
			lblError = new JLabel("");
			lblResult = new JLabel("Nombre de fiches trouvees :");
			lblSearchName = new JLabel("Donnez un nom à votre recherche avant de la sauvgrader");
			txtRaisonSocial = new JTextField(20);
			txtDomaine = new JTextField(20);
			txtVille = new JTextField(20);
			txtRegion = new JTextField(20);
			txtResult = new JTextField(5);
			txtSearchName = new JTextField(20);
			btnRechercher = new JButton("Rechercher");
			btnEffacer = new JButton("Effacer");
			btnSave = new JButton("Save");
			btnDelete = new JButton("Delete");
			btnModifier = new JButton("Modifier");
			chkRaisonSocial= new JCheckBox() ;
			chkDomaine = new JCheckBox();
			chkVille = new JCheckBox();
			chkRegion = new JCheckBox();
			

			//model = (DefaultTableModel) tableResultats.getModel();

			this.setBorder(new LineBorder(Color.BLUE));
			this.setLayout(new BorderLayout(5, 5));

			//ajout du panneau Nord  
			this.add(panelNorth, BorderLayout.NORTH);
			panelNorth.setLayout(new BorderLayout());
		

			modelTable = new ModelSearch();
			
			tableResultats=new JTable(modelTable);
			tableResultats.setPreferredScrollableViewportSize(new Dimension(780, 250));
			scrollTable=new JScrollPane(tableResultats);
			panelNorth.add(scrollTable);
			
			// ajout du panneau Sud
			this.add(panelSouth,BorderLayout.SOUTH);
			panelSouth.setBorder(new LineBorder(Color.BLUE));
			panelSouth.setLayout(new FlowLayout());
			panelSouth.add(btnSave);
			panelSouth.add(btnDelete);
			panelSouth.add(btnModifier);

			
			

			//ajout panneau West
			panelWest.setBorder(new TitledBorder(null, "Critère de recherches", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLUE));
			this.add(panelWest, BorderLayout.WEST);
//			panelWest.setLayout(new MigLayout());
			panelWest.setLayout(new MigLayout("","[] [] []","[]30[]"));
			
			panelWest.add(lblRaisonSocial);
			panelWest.add(txtRaisonSocial);
			panelWest.add(chkRaisonSocial, "wrap");
			panelWest.add(lblDomaine);
			panelWest.add(txtDomaine);
			panelWest.add(chkDomaine, "wrap");
			panelWest.add(lblVille);
			panelWest.add(txtVille);
			panelWest.add(chkVille, "wrap");
			panelWest.add(lblRegion);
			panelWest.add(txtRegion);
			panelWest.add(chkRegion);
			panelWest.add(lblError);

			//ajout panneau Center
			panelCenter.setBorder(new TitledBorder(null, "Fonction recherche", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLUE));
			this.add(panelCenter, BorderLayout.CENTER);
			//panelCenter.setLayout(new MigLayout("","[] [] []","[]30[]"));
			panelCenter.setLayout(new MigLayout("","[] 30[] []","[]20[]"));
			panelCenter.add(btnRechercher,"cell 0 0");
			panelCenter.add(btnEffacer, "cell 0 1");
			panelCenter.add(lblResult, "cell 1 0");
			panelCenter.add(txtResult,"cell 2 0");
			panelCenter.add(lblSearchName, "cell 0 2");
			panelCenter.add(txtSearchName, "cell 0 3" );
			
			
			// Liason des composants au listener.
			listened = new PanelRechercheAvanceeListener(this);
			btnSave.addActionListener(listened);
			btnDelete.addActionListener(listened);
			btnModifier.addActionListener(listened);
			btnRechercher.addActionListener(listened);
			btnEffacer.addActionListener(listened);
			

		}
		/**
		 * @return the id
		 */
		public static int getId() {
			return id;
		}
		/**
		 * @return the serialversionuid
		 */
		public static long getSerialversionuid() {
			return serialVersionUID;
		}
		/**
		 * @return the panelNorth
		 */
		public JPanel getPanelNorth() {
			return panelNorth;
		}
		/**
		 * @return the panelCenter
		 */
		public JPanel getPanelCenter() {
			return panelCenter;
		}
		/**
		 * @return the panelSouth
		 */
		public JPanel getPanelSouth() {
			return panelSouth;
		}
		/**
		 * @return the panelEast
		 */
		public JPanel getPanelEast() {
			return panelEast;
		}
		/**
		 * @return the panelWest
		 */
		public JPanel getPanelWest() {
			return panelWest;
		}
		/**
		 * @return the tableResultats
		 */
		public JTable getTableResultats() {
			return tableResultats;
		}
		/**
		 * @return the scrollTable
		 */
		public JScrollPane getScrollTable() {
			return scrollTable;
		}
		/**
		 * @return the lblRaisonSocial
		 */
		public JLabel getLblRaisonSocial() {
			return lblRaisonSocial;
		}
		/**
		 * @return the lblDomaine
		 */
		public JLabel getLblDomaine() {
			return lblDomaine;
		}
		/**
		 * @return the lblVille
		 */
		public JLabel getLblVille() {
			return lblVille;
		}
		/**
		 * @return the lblRegion
		 */
		public JLabel getLblRegion() {
			return lblRegion;
		}
		/**
		 * @return the lblError
		 */
		public JLabel getLblError() {
			return lblError;
		}
		/**
		 * @return the lblResult
		 */
		public JLabel getLblResult() {
			return lblResult;
		}
		/**
		 * @return the txtRaisonSocial
		 */
		public JTextField getTxtRaisonSocial() {
			return txtRaisonSocial;
		}
		/**
		 * @return the txtDomaine
		 */
		public JTextField getTxtDomaine() {
			return txtDomaine;
		}
		/**
		 * @return the txtVille
		 */
		public JTextField getTxtVille() {
			return txtVille;
		}
		/**
		 * @return the txtRegion
		 */
		public JTextField getTxtRegion() {
			return txtRegion;
		}
		/**
		 * @return the txtResult
		 */
		public JTextField getTxtResult() {
			return txtResult;
		}
		/**
		 * @return the btnRechercher
		 */
		public JButton getBtnRechercher() {
			return btnRechercher;
		}
		/**
		 * @return the btnEffacer
		 */
		public JButton getBtnEffacer() {
			return btnEffacer;
		}
		/**
		 * @return the btnSave
		 */
		public JButton getBtnSave() {
			return btnSave;
		}
		/**
		 * @return the btnDelete
		 */
		public JButton getBtnDelete() {
			return btnDelete;
		}
		/**
		 * @return the btnModifier
		 */
		public JButton getBtnModifier() {
			return btnModifier;
		}
		/**
		 * @return the chkRaisonSocial
		 */
		public JCheckBox getChkRaisonSocial() {
			return chkRaisonSocial;
		}
		/**
		 * @return the chkDomaine
		 */
		public JCheckBox getChkDomaine() {
			return chkDomaine;
		}
		/**
		 * @return the chkVille
		 */
		public JCheckBox getChkVille() {
			return chkVille;
		}
		/**
		 * @return the chkRegion
		 */
		public JCheckBox getChkRegion() {
			return chkRegion;
		}
		/**
		 * @return the model
		 */
		public DefaultTableModel getModel() {
			return model;
		}
		/**
		 * @return the modelTable
		 */
		public ModelSearch getModelTable() {
			return modelTable;
		}

		/**
		 * @param modelTable the modelTable to set
		 */
		public void setModelTable(ModelSearch modelTable) {
			this.modelTable = modelTable;
		}

		/**
		 * @return the listened
		 */
		public PanelRechercheAvanceeListener getListened() {
			return listened;
		}



}
