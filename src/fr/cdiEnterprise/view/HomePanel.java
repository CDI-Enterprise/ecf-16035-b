package fr.cdiEnterprise.view;

import java.awt.BorderLayout;
import java.awt.Container;

import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;



public class HomePanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	private Container panneau;
//	private JPanel panNorth;
//	private JPanel panSouth;
//	private JPanel panCenter;
//	private JPanel panWest;
//	private JLabel lblTitle;
	private JLabel lblImage;
//	private JLabel lblInfo1;
//	private JLabel lblInfo2;
//	private JLabel lblInfo3;
//	private JLabel lblInfo4;
//	private JLabel lblInfo5;
//	private JLabel lblInfo6;
//	private JLabel lblInfo7;
//	private JLabel lblInfo8;
//	private JLabel lblInfo9;
//	private JPanel panInfo;
	
	
	public HomePanel() throws SQLException {
	
		panneau = this;
		panneau.setLayout(new BorderLayout(5,5));
		
		lblImage = new JLabel (new ImageIcon("homeScreen.jpg"));
//		panNorth = new JPanel();
//		panWest = new JPanel();
//		panCenter = new JPanel();
//		panSouth = new JPanel();
//		
//		panneau.add(panNorth, BorderLayout.NORTH);
//		panneau.add(panWest,BorderLayout.WEST);
//		panneau.add(panCenter, BorderLayout.CENTER);
//		panneau.add(panSouth, BorderLayout.SOUTH);
//		
//		
//		panNorth.setLayout(new FlowLayout());
//		lblTitle = new JLabel("Bienvenue sur l'application CDI Enterprise");
//		lblTitle.setAlignmentX(JPanel.CENTER_ALIGNMENT);
//		lblTitle.setVisible(true);
//		
//		panInfo = new JPanel();
//		panInfo.setLayout(new MigLayout());
//		lblInfo1 = new JLabel("Participez à la création d'une base de données commune de fiches entreprises ");
//		lblInfo2 = new JLabel("STAGIAIRES, ancien ou nouveau ");
//		lblInfo3 = new JLabel("Trouvez les entreprises qui vous conviennent avec le module de recherche avancée ");
//		lblInfo4 = new JLabel("Gérez vos compagnies et recherches favorites ");
//		lblInfo5 = new JLabel("Dialoguez avec d'autres stagiaires et des anciens de la formation ");
//		lblInfo6 = new JLabel("Maintenez un profil public et technique à jour ");
//		lblInfo7 = new JLabel("FORMATEURS ");
//		lblInfo8 = new JLabel("Gardez contact avec vos anciens stagiaires");
//		lblInfo9 = new JLabel("Suivez leur carrière ");
//		
//		panCenter.add(panInfo);
//		
//		lblImage = new JLabel (new ImageIcon("star-trek.png"));
//		
		panneau.add(lblImage);
//		panNorth.add(lblTitle);
//		panWest.add(lblImage);
//		panInfo.add(lblInfo1, "wrap");
//		panInfo.add(lblInfo2, "wrap");
//		panInfo.add(lblInfo3, "wrap");
//		panInfo.add(lblInfo4, "wrap");
//		panInfo.add(lblInfo5, "wrap");
//		panInfo.add(lblInfo6, "wrap");
//		panInfo.add(lblInfo7, "wrap");
//		panInfo.add(lblInfo8, "wrap");
//		panInfo.add(lblInfo9, "wrap");

	
	}
}
