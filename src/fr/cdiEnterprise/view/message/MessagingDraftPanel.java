/**
 * 
 */
package fr.cdiEnterprise.view.message;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;

import javax.swing.JButton;
import javax.swing.JLabel;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import fr.cdiEnterprise.control.MessageListener;
import fr.cdiEnterprise.dao.OldDatas;
import fr.cdiEnterprise.model.Item;
import fr.cdiEnterprise.service.Clients;
import fr.cdiEnterprise.service.Items;
import fr.cdiEnterprise.service.SpecialTableItemModel;
import fr.cdiEnterprise.util.MpClient;
import fr.cdiEnterprise.util.ReadProperties;
import net.miginfocom.swing.MigLayout;


/**
 * This class is going to display the main messenging's page, which contains all the messages for the user.
 * from there user will be able to create new message, remove or edit draft message
 * @author Nicolas Tarral
 * @version 11-10-2016
 *
 */
public class MessagingDraftPanel extends JPanel {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Border border;
	private Border	borderTitle;



	private JButton btnMess;
	private JButton btnDisplay;
	
	private String nombreMessage;
	private Items allItems;
	private SpecialTableItemModel tiModel;
	private DefaultTableModel tableModele;
	private JScrollPane scrollPane;
	private JTable table;
	private String[][] tableauMsg;
	private Items copyUserItems;
	

	
	
	private static final String FORMAT_LIST = "%1$-25s %2$-35s %3$-10s";
	private static final String[] HEADER_LIST	= {"From", "Objet", "Date reception"};
	
	
	/**
	 * Default constructor 
	 */
	public MessagingDraftPanel() {
		
		//listModele = new DefaultListModel<>();
		MessageListener listener = new MessageListener(this);
		copyUserItems = new Items();
		
		borderTitle = BorderFactory.createTitledBorder("Brouillon");
		border = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
		
		table = new JTable(tiModel);
		table.setFillsViewportHeight(true);
		table.setEnabled(true);
		table.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 16));
		table.setPreferredScrollableViewportSize(new Dimension(780, 110));
		
		//table.setPreferredScrollableViewportSize(table.getPreferredSize());
		DefaultTableCellRenderer stringRenderer = (DefaultTableCellRenderer)table.getDefaultRenderer(String.class);
		stringRenderer.setHorizontalAlignment( SwingConstants.CENTER );
		
		
		JScrollPane scroller = new JScrollPane(table);
		fillModel();
		


		
		
		JPanel panMess 	= new JPanel();
		JPanel panNorth = new JPanel();
		JPanel panWest 	= new JPanel();
		JPanel panCenter= new JPanel();
		
		panMess.setLayout(new BorderLayout());
		add(panMess);
		panMess.add(panNorth,BorderLayout.NORTH);
		panMess.add(panCenter,BorderLayout.CENTER);
		panMess.add(panWest, BorderLayout.WEST);
		

		JLabel lblMess 		= new JLabel("Nombre de Brouillon(s) :");
		JLabel lblNombre 	= new JLabel(tableModele.getRowCount()+"");
		JLabel lblTitre 	= new JLabel("Boite de Brouillon de :"+ MessageListener.alias);
		
		String header = String.format(FORMAT_LIST, HEADER_LIST);
		JLabel headerLabel = new JLabel(header);
		
		tiModel = new SpecialTableItemModel(copyUserItems);
		
		tableModele =  new DefaultTableModel(tableauMsg, new String[] {
				"Reçu de", "Sujet", "Date Reception"
			});
		
		
		
		
		btnMess = new JButton("Messages");
		btnDisplay = new JButton("Refersh");
		
		

		btnMess.setMnemonic(KeyEvent.VK_M);
		btnDisplay.setMnemonic(KeyEvent.VK_D);
		
		panNorth.setLayout(new FlowLayout());
		panWest.setLayout(new MigLayout());
		

		panWest.add(btnMess, "wrap");
		panWest.add(btnDisplay, "wrap");
		
		panNorth.add(lblTitre);

		//JList<Item> list = new JList<Item>(listModele);
		
		scrollPane = new JScrollPane();
		panCenter.setBorder(borderTitle);
		panCenter.add(scroller, BorderLayout.CENTER);	
		//panMess.add(scrollPane, BorderLayout.CENTER);
		
		
		
		//table = new JTable();
		//table.setModel(tableModele);
		//table.setPreferredScrollableViewportSize(new Dimension(500, 70));
		

		//table.setAutoCreateRowSorter(true);
		//table.setColumnSelectionAllowed(false);
		//table.setModel(tableModele);
		
		
		//scrollPane.setViewportView(table);
		

		
		table.addMouseListener(listener);
		//table.getSelectionModel().addListSelectionListener(listener);
		//btnNew.addActionListener(listener);
		btnMess.addActionListener(listener);
		btnDisplay.addActionListener(listener);
	}

	public SpecialTableItemModel getTiModel() {
		return tiModel;
	}
	

	/**
	 * This method is going to fill up the table model with the listing copy coming from the database.
	 * it will fill up the model table and set up the table
	 * @param allItems
	 * @return
	 */
	// TODO (nicolas) Maybe put this method in util class.
	private void fillModel() {

		
		int index = 0;
		if(copyUserItems.isEmpty()) {
			
			System.out.println("--- il n'y a pas de message ---");
			tableauMsg = new String[copyUserItems.size()][3];
			for(Item current : copyUserItems) {
				
				tableauMsg[index][0] = current.getSender();
				tableauMsg[index][1] = current.getObject();
				tableauMsg[index][2] = current.getTimeStamp().toString();
		
				index++;
			}
			
	
			if(tableauMsg == null) {
			}else {
				
				tableModele =  new DefaultTableModel(tableauMsg, new String[] {
						"Sender", "Objet", "Date Reception"
					});
				
			}
			
			
		}else {
			
			tiModel.setUsers(copyUserItems);
			tableauMsg = new String[copyUserItems.size()][3];
			// recupere tous les messages d'un utilisateur de l'app.
			
			for(Item current : copyUserItems) {
				
				tableauMsg[index][0] = current.getSender();
				tableauMsg[index][1] = current.getObject();
			//	tableauMsg[index][2] = current.getTimeStamp().toString();
				
				

				index++;
			}
			
			tableModele =  new DefaultTableModel(tableauMsg, new String[] {
					"Sender", "Objet", "Date Reception"
				});
		
			
		}
		table.setModel(tableModele);

	}

	/**
	 * 
	 */
	private void readTableauModele(String[][] tableauMsg) {
		
		for(int i =0; i < tableauMsg.length; i++ ) {
			for(int j =0; j < 3; j++ ) {
				
			}
		}
		
	}



	public JButton getbtnMess() {
		return btnMess;
	}

	public JButton getBtnDisplay() {
		return btnDisplay;
	}

	public void refresh() {
		fillModel();
		

		
	}


	public Items getCopyUserItems() {
		return copyUserItems;
	}

	public void setCopyUserItems(Items copyUserItems) {
		this.copyUserItems = copyUserItems;
	}


	public JButton getBtnMess() {
		return btnMess;
	}



}
