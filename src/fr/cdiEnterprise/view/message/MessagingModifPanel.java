/**
 * 
 */
package fr.cdiEnterprise.view.message;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.text.AbstractDocument;

import fr.cdiEnterprise.control.MessageListener;
import fr.cdiEnterprise.dao.OldDatas;
import fr.cdiEnterprise.model.Item;
import fr.cdiEnterprise.model.User;
import fr.cdiEnterprise.service.Users;
import fr.cdiEnterprise.util.ReadProperties;
import fr.cdiEnterprise.view.DocumentSizeFilter;
import javafx.scene.control.ComboBox;
import net.miginfocom.swing.MigLayout;

/**
 * Cette class represente la vue d'un message que l'utlidsqteu consulte.
 * l'utilisateur a la possibilité d'y repondre ou de le supprimer.
 * 
 * @version 22-10-2016
 * @author nicolas Tarral
 *
 */
public class MessagingModifPanel extends JPanel {
	
	private JButton btnEnv;
	private JButton btnDel;
	private JButton btnRet;
	private JButton btnSav;
	
	private Border border;
	private Border borderMessage;
	
	private String from;
	private JLabel receiver;
	private JLabel object;
	private JLabel Message;
	
	private JLabel lblReceiver;
	private JLabel lblObject;
	private JLabel lblMessage;
	
	private JLabel letterCount;
	private JLabel lblCounter;
	
	private JComboBox cboReceiver;
	private JTextField txtReceiver;
	private JTextField txtObject;
	private JTextArea  txtMessage;
	
	private Users usersList;
	private Item itm;
	private static final int MAX_CHARACTERS = 450;

	
	public MessagingModifPanel(Item item, Users list) {
		
		itm = item;
		MessageListener listener = new MessageListener((JPanel) this);
		borderMessage = BorderFactory.createTitledBorder(" Message Brouillon ");
		border = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
		
		
		receiver = new JLabel(itm.getSender());
		object = new JLabel(itm.getObject());
		Message = new JLabel(itm.getBody());
		
		txtMessage = new JTextArea(10, 50);
		/*if(itm != null) {
			
			txtMessage.setText("From :/n" +itm.getSender() +"/n");
			txtMessage.setText("When :/n" +itm.getTimeStamp() +"/n");
			txtMessage.setText("Objet :/n" +itm.getObject() +"/n");
			txtMessage.setText("Message :/n" +itm.getBody() +"/n");
		}*/
		
		// TODO (nicolas) needs to be removed
		usersList = list;
		from = MessageListener.alias;
		
		
		JPanel panMess = new JPanel();
		JPanel panNorth = new JPanel();
		JPanel panCenter = new JPanel();
		panMess.setLayout(new BorderLayout());
		add(panMess);
		panMess.add(panNorth,BorderLayout.NORTH);
		panMess.add(panCenter,BorderLayout.CENTER);
		
		
		JLabel lblTitle = new JLabel("- Modifier Votre Brouillon -");
		
		btnSav = new JButton("Sauvegarde");
		btnEnv = new JButton("Envoie");
		btnDel = new JButton("Effacer");
		btnRet = new JButton("Retour");
		
	/*	receiver = new JLabel("Destinataire");
		object = new JLabel("Objet");
		Message = new JLabel("Texte");
		letterCount = new JLabel("compteur");
		lblCounter =   new JLabel();*/
		
		
		lblReceiver = new JLabel("Destinataire");
		lblObject = new JLabel("Sujet");
		lblMessage = new JLabel("Message");


		cboReceiver = new JComboBox();
		cboReceiver.setEditable(true);
		cboReceiver.setMaximumRowCount(3);
	
		//txtReceiver = new JTextField();
		txtObject = new JTextField(20);
		txtObject.setText(itm.getObject());
		txtMessage = new JTextArea(10,50);
		txtMessage.setBorder(border);
		
		txtMessage.setText(itm.getBody());
		txtMessage.setLineWrap(true);
		txtMessage.setWrapStyleWord(true);
		
		AbstractDocument doc = (AbstractDocument) txtMessage.getDocument();
		
	    doc.setDocumentFilter(new DocumentSizeFilter(MAX_CHARACTERS));
		
		if(usersList != null) {
			for(User current : usersList) {
				if(current != null) {
					cboReceiver.addItem(current.getAlias());
					}	
			}
		}else {
			//System.out.println("usersList is null ");
		}
		
		cboReceiver.setSelectedItem(itm.getReceiver());
        
		
		panNorth.add(lblTitle);
		panCenter.setLayout(new MigLayout());
		panCenter.setBorder(borderMessage);
		
		panCenter.add(lblReceiver, "w 200!");
		panCenter.add(cboReceiver, "wrap");
		//panCenter.add(receiver, "wrap");
		//panCenter.add(lblReceiver, "wrap");
		panCenter.add(lblObject, "w 200!");
		panCenter.add(txtObject, "wrap");
	
		panCenter.add(Message, "w 200!");
		panCenter.add(txtMessage, "wrap");
		
		
		
		//panCenter.add(lblMessage, "w 200!");
		//panCenter.add(txtMessage, "wrap");

		//panCenter.add(letterCount, "w 200!");
		//panCenter.add(lblCounter, "wrap");
		
		
		panCenter.add(btnEnv, "w 200!");
		panCenter.add(btnSav, "w 200!");
		panCenter.add(btnDel, "w 200!");
		panCenter.add(btnRet, "w 200!");
		
		
		btnEnv.addActionListener(listener);
		btnSav.addActionListener(listener);
		btnDel.addActionListener(listener);
		btnRet.addActionListener(listener);
		
//		txtMessage.addKeyListener(listener);
		
		
		
		
	}



	public MessagingModifPanel() {
	
	}



	public JLabel getLblCounter() {
		return lblCounter;
	}



	public void setLblCounter(JLabel lblCounter) {
		this.lblCounter = lblCounter;
	}






	public JButton getBtnDel() {
		return btnDel;
	}



	public JButton getBtnRet() {
		return btnRet;
	}



	public String getFrom() {
		return from;
	}



	public JComboBox getCboReceiver() {
		return cboReceiver;
	}



	public JTextField getTxtObject() {
		return txtObject;
	}



	public JTextArea getTxtMessage() {
		return txtMessage;
	}



	public Item getItm() {
		return itm;
	}



	public void setItm(Item itm) {
		this.itm = itm;
	}



	public JButton getBtnEnv() {
		return btnEnv;
	}



	public JButton getBtnSav() {
		return btnSav;
	}

	





}
