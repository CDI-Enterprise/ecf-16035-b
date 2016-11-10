package fr.cdiEnterprise.model;
/**
 * 
 * En test le 24/10/2016
 */
import java.util.List;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

public class ModelSearch extends AbstractTableModel{

	private static final long serialVersionUID = 1L;
	private String [] entete={ "ID","NOM ENTREPRISE", "ADRESSE", "CODE POSTAL","VILLE", "TAILLE", "DOMAINE", "PROJET", "SITE WEB"};
	private Vector<String[]> datas= new Vector<String[]>();
	protected String size;


	@Override
	public int getRowCount() {
		return datas.size();
		
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return entete.length;
	}
	@Override
	public String getColumnName(int column) {
		return entete[column];
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return datas.get(rowIndex)[columnIndex];
	}
	public void loadDatas(List<Company> listCompanies){
		datas=new Vector<String[]>();
		for(Company c : listCompanies){
			datas.add(new String[]{String.valueOf(c.getCompanyId()),c.getCompanyName(),c.getAdress(),String.valueOf(c.getPostalCode()),
					c.getCity(),c.getSize(),c.getSector(),c.getProjets(),c.getWebSite()});
			 size =String.valueOf(datas.size());
			}
		 this.size =String.valueOf(datas.size());
		
	
		fireTableChanged(null);
	}

	/**
	 * @return the size
	 */
	public String getSize() {
		return size;
	}
}