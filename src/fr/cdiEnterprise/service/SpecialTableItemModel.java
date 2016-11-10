package fr.cdiEnterprise.service;

import java.util.ArrayList;
import java.util.List;


import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

public class SpecialTableItemModel<Item> extends AbstractTableModel {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Items users;
    private String[] columnIdentifiers;
	private String[] columnNames;
    
    public SpecialTableItemModel(Items users) {

        this.users = users;

    }




    /**
     * default constructor
     */
    public SpecialTableItemModel() {
		
	}





	public String getColumnName(int col) {
        return columnNames[col];
    }
    
    
    @Override
    public int getRowCount() {
        return users.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }
    
 

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
    	
    	Object value = (Item) "??";
        
    	fr.cdiEnterprise.model.Item user =  users.get(rowIndex);
        
        switch (columnIndex) {
            case 0:
                value = user.getSender();
                
                break;
            case 1:
                value = user.getObject();
                break;
            case 2:
                value = user.getTimeStamp();
                break;
        }

        return value;

    }

//    @Override
//    public Class<?> getColumnClass(int columnIndex) {
//        return // Return the class that best represents the column...
//    }

    /* Override this if you want the values to be editable...
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        //....
    }
    */

    /**
     * This will return the user at the specified row...
     * @param row
     * @return 
     */
    public fr.cdiEnterprise.model.Item getUserAt(int row) {
    	if(row >= 0) {
    		return users.get(row);
    	}else {
    		return null;
    	}
        
    }








	public Items getUsers() {
		return users;
	}





	public void setUsers(Items users) {
		this.users = users;
	}





}