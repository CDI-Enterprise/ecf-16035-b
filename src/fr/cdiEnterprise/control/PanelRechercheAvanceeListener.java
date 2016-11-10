package fr.cdiEnterprise.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import fr.cdiEnterprise.model.Company;
import fr.cdiEnterprise.service.MetierAdvancedSearch;
import fr.cdiEnterprise.view.RechercheAvanceePanel;


public class PanelRechercheAvanceeListener implements ActionListener{

	//private RechercheAvanceeFrame 
	private MetierAdvancedSearch metier;
	private String mcRaisonSociale;  
	private String mcDomaine;
	private String mcVille;
	private String mcRegion;
	private RechercheAvanceePanel panelRecherche; // 26/10/2016 A RECTIFIER
	//private AdvancedSearchPanel panelRecherche;

	//	private String rqtSqlRecherche;

	/**
	 * 
	 * @author  Olivier
	 */
//	public PanelRechercheAvanceeListener(RechercheAvanceePanel panelRecherche) {  // 26/10/2016 A RECTIFIER
//		this.panelRecherche = panelRecherche;
//		mcRaisonSociale="";
//		mcDomaine="";
//		mcVille="";
//		mcRegion="";
//		metier = new MetierAdvancedSearch();
//	}
	
	public PanelRechercheAvanceeListener(RechercheAvanceePanel panelRecherche) {
		this.panelRecherche = panelRecherche;
		mcRaisonSociale="";
		mcDomaine="";
		mcVille="";
		mcRegion="";
		metier = new MetierAdvancedSearch();
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if(e.getSource()==panelRecherche.getBtnEffacer()){
			if(panelRecherche.getChkRaisonSocial().isSelected()){
				panelRecherche.getTxtRaisonSocial().setText("");
				panelRecherche.getChkRaisonSocial().setSelected(false);
			}else if(panelRecherche.getChkDomaine().isSelected()){
				panelRecherche.getTxtDomaine().setText("");
				panelRecherche.getChkDomaine().setSelected(false);
			}else if (panelRecherche.getChkVille().isSelected()){
				panelRecherche.getTxtVille().setText("");
				panelRecherche.getChkVille().setSelected(false);
			}else if (panelRecherche.getChkRegion().isSelected()){
				panelRecherche.getTxtRegion().setText("");
				panelRecherche.getChkRegion().setSelected(false);
			}


		}

		if(e.getSource()==panelRecherche.getBtnDelete()){
			panelRecherche.clear();

		}
		if(e.getSource()==panelRecherche.getBtnRechercher()){
			if(panelRecherche.getChkRaisonSocial().isSelected()){
				mcRaisonSociale = panelRecherche.getTxtRaisonSocial().getText();
				List<Company> listCompnies =  metier.getCompagnyByMCNom(mcRaisonSociale);
				panelRecherche.getModelTable().loadDatas(listCompnies);
				System.out.println(listCompnies.isEmpty());
				System.out.println(listCompnies);
				//String nbrSearc =panelRecherche.getModelTable().getSize();
				panelRecherche.getTxtResult().setText(panelRecherche.getModelTable().getSize());
				//panelRecherche.getModelTable().fireTableDataChanged();
			}
			if(panelRecherche.getChkDomaine().isSelected()){
				mcDomaine = panelRecherche.getTxtDomaine().getText();
				List<Company> listCompnies =  metier.getCompagnyByMCDomaine(mcDomaine);
				panelRecherche.getModelTable().loadDatas(listCompnies);
				panelRecherche.getTxtResult().setText(panelRecherche.getModelTable().getSize());
				System.out.println(listCompnies.isEmpty());
				System.out.println(listCompnies);
			}
			if(panelRecherche.getChkVille().isSelected()){
				mcVille = panelRecherche.getTxtVille().getText();
				List<Company> listCompnies =  metier.getCompagnyByMCVille(mcVille);
				panelRecherche.getModelTable().loadDatas(listCompnies);
				panelRecherche.getTxtResult().setText(panelRecherche.getModelTable().getSize());
				System.out.println(listCompnies.isEmpty());
				System.out.println(listCompnies);
			}
			if(panelRecherche.getChkRegion().isSelected()){
				mcRegion = panelRecherche.getTxtRegion().getText();
				List<Company> listCompnies =  metier.getCompagnyByMCRegion(mcRegion);
				panelRecherche.getModelTable().loadDatas(listCompnies);
				panelRecherche.getTxtResult().setText(panelRecherche.getModelTable().getSize());
				System.out.println(listCompnies.isEmpty());
				System.out.println(listCompnies);
			}
//			if(panelRecherche.getChkRaisonSocial().isSelected() && panelRecherche.getChkDomaine().isSelected()){
//				mcRaisonSociale = panelRecherche.getTxtRaisonSocial().getText();
//				mcDomaine = panelRecherche.getTxtDomaine().getText();
//				List<Company> listCompnies =  metier.getCompagnyByMC(mcRaisonSociale, mcDomaine );
//				panelRecherche.getModelTable().loadDatas(listCompnies);
//				panelRecherche.getTxtResult().setText(panelRecherche.getModelTable().getSize());
//				System.out.println(listCompnies.isEmpty());
//				System.out.println(listCompnies);
//			}


			//System.out.println(mcRaisonSociale + "," + mcDomaine + "," + mcVille + "," + mcRegion );
			System.out.println(mcRaisonSociale);

		}


	}
}
