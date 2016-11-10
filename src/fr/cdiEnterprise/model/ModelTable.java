package fr.cdiEnterprise.model;

import javax.swing.table.AbstractTableModel;

public class ModelTable extends AbstractTableModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final Object [][] datas;
	private final String[] entetes={ "ID", "Raison Sociale", "Domaine", "Ville", "Region"};
	

	/**
	 * 
	 */
	public ModelTable() {
		super();
		 datas = new Object[][]{
//             {"Johnathan", "Sykes", Color.red, true, Sport.TENNIS},
//             {"Nicolas", "Van de Kampf", Color.black, true, Sport.FOOTBALL},
//             {"Damien", "Cuthbert", Color.cyan, true, Sport.RIEN},
//             {"Corinne", "Valance", Color.blue, false, Sport.NATATION},
//             {"Emilie", "Schrödinger", Color.magenta, false, Sport.FOOTBALL},
//             {"Corinne", "Valance", Color.blue, false, Sport.NATATION},
//             {"Emilie", "Schrödinger", Color.magenta, false, Sport.FOOTBALL},
//             {"Corinne", "Valance", Color.blue, false, Sport.NATATION},
//             {"Emilie", "Schrödinger", Color.magenta, false, Sport.FOOTBALL},
     };
		
	}

	@Override
	public int getRowCount() {
        return datas.length;
    }
 
    public int getColumnCount() {
        return entetes.length;
    }
 
    public String getColumnName(int columnIndex) {
        return entetes[columnIndex];
    }
 
    public Object getValueAt(int rowIndex, int columnIndex) {
        return datas[rowIndex][columnIndex];
    }
}