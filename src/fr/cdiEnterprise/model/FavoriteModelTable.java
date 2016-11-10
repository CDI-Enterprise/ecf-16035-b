/**
 * 
 */
package fr.cdiEnterprise.model;

import java.util.Arrays;

import javax.swing.table.AbstractTableModel;

/**
 *22 oct. 2016
 * @author Ismael
 *ecf-16035-a
 *23:12:33
 */
public class FavoriteModelTable  extends AbstractTableModel 
{

	/**
	 * Création d'un modele de table pour la JTable 
	 * affichant les details d'une entreprise se trouvant
	 * dans les favoris. 
	 * 
	 */

	private static final long serialVersionUID = 1L;
	private final Object [][] datas;
	private final String[] HEADER_LIST ={ "Ville", "Taille", "Secteur", "WebSite", "ContactMail"};



	public FavoriteModelTable() 
	{
		super();
		datas = new Object[][]
				{
				};
	}
	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#getRowCount()
	 */
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return datas.length;
	}

	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#getColumnCount()
	 */
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return HEADER_LIST.length;
	}

	public String getColumnName(int columnIndex) {
		return HEADER_LIST [columnIndex];
	}


	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#getValueAt(int, int)
	 */
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return datas[rowIndex][columnIndex];
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "FavoriteModelTable [datas=" + Arrays.toString(datas) + ", HEADER_LIST=" + Arrays.toString(HEADER_LIST)
		+ "]";
	}

}
