/**
 * 
 */
package fr.cdiEnterprise.view.message;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

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
 * @version 15-10-2016
 * @author nicolas Tarral
 *
 */
public class MessagingReadPanel extends JPanel {
	
	private JButton btnRep;
	private JButton btnDel;
	private JButton btnRet;
	
	private Border border;
	private Border borderMessage;
	private Border borderCadre;
	
	private String from;
	private JLabel lblInfo;
	private JLabel receiver;
	private JLabel object;
	private JLabel Message;
	
	private JLabel lblSender;
	private JLabel lblObject;
	private JLabel lblDate;
	private JLabel lblMessage;
	
	private JLabel letterCount;
	private JLabel lblCounter;
	
	private JComboBox cboReceiver;
	private JTextField txtReceiver;
	private JTextField txtObject;
	private JTextArea  txtMessage;
	
	private Users usersList;
	private Item itm;
	
	private static final String CONSIGNE_ONE 	= "Avant de cliquer sur repondre, vous devez rediger une reponse...";
	private static int MAX_CHARACTERS 		= 450;
	private static final String CONSIGNE_TWO 	= "Le texte est limité a 450 caractères.";

	
	public MessagingReadPanel(Item item) {
		
		itm = item;
		MessageListener listener = new MessageListener((JPanel) this);
		borderCadre 	= BorderFactory.createLineBorder(Color.RED);
		borderMessage 	= BorderFactory.createTitledBorder(" Message ");
		border 			= BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
	
		
		receiver= new JLabel(itm.getSender());
		object 	= new JLabel(itm.getObject());
		Message = new JLabel(itm.getBody());
		txtMessage = new JTextArea(10, 50);
		/*if(itm != null) {
			
			txtMessage.setText("From :/n" +itm.getSender() +"/n");
			txtMessage.setText("When :/n" +itm.getTimeStamp() +"/n");
			txtMessage.setText("Objet :/n" +itm.getObject() +"/n");
			txtMessage.setText("Message :/n" +itm.getBody() +"/n");
		}*/
		
		
		usersList = OldDatas.getUsersList();
		from = MessageListener.alias;
		
		
		JPanel panMess 		= new JPanel();
		JPanel panNorth 	= new JPanel();
		JPanel panCenter 	= new JPanel();
		JPanel panSouth 	= new JPanel();
		panMess.setLayout(new BorderLayout());
		add(panMess);
		panMess.add(panNorth,BorderLayout.NORTH);
		panMess.add(panCenter,BorderLayout.CENTER);
		panMess.add(panSouth,BorderLayout.SOUTH);	
		Font boldFont 	= new Font("Arial", Font.PLAIN, 18);
		JLabel lblTitle = new JLabel(" Votre Message ");
		JLabel lblInfo  = new JLabel(CONSIGNE_ONE);
		//lblTitle.setFont(boldFont);
		
		btnRep = new JButton("Repondre");
		btnDel = new JButton("Effacer");
		btnRet = new JButton("Retour");
		
	/*	receiver = new JLabel("Destinataire");
		object = new JLabel("Objet");
		Message = new JLabel("Texte");
		letterCount = new JLabel("compteur");
		lblCounter =   new JLabel();
		*/
		
		lblSender = new JLabel("Message De :");
		lblObject = new JLabel("Sujet :");
		lblDate   = new JLabel("reçu le :");
		lblMessage = new JLabel("Message :");


		//cboReceiver = new JComboBox();
		//cboReceiver.setEditable(true);
		//cboReceiver.setMaximumRowCount(3);
		//txtReceiver = new JTextField();
		txtObject = new JTextField(20);
		txtObject.setText(itm.getObject());
		txtMessage = new JTextArea(10,50);
		

		
		
		txtMessage.setBorder(border);
		txtMessage.setText(itm.getBody());
		txtMessage.setLineWrap(true);
		txtMessage.setWrapStyleWord(true);

		AbstractDocument doc = (AbstractDocument) txtMessage.getDocument();
		MAX_CHARACTERS = MAX_CHARACTERS - itm.getBody().length();
	    doc.setDocumentFilter(new DocumentSizeFilter(MAX_CHARACTERS));
	
		
		/*if(usersList != null) {
			for(User current : usersList) {
				if(current != null) {
					cboReceiver.addItem(current.getAlias());
					System.out.println("les alias des utilisateurs :"+current.getAlias());
				}	
			}
		}else {
			System.out.println("usersList is null ");
		}*/
		panNorth.setLayout(new FlowLayout());
		panNorth.setBorder(borderCadre);
		panNorth.add(lblInfo);
		panCenter.setLayout(new MigLayout());
		panCenter.setBorder(borderMessage);
		
		
		panCenter.add(lblSender, "w 200!");
		panCenter.add(receiver, "wrap");
		
		panCenter.add(lblObject, "w 200!");
		panCenter.add(txtObject, "wrap");
		
		panCenter.add(lblMessage, "w 200!");
		panCenter.add(txtMessage, "wrap");
		
		panSouth.setLayout(new FlowLayout());
		panSouth.add(btnRep);
		panSouth.add(btnDel);
		panSouth.add(btnRet);
		
		//panCenter.add(lblMessage, "w 200!");
		//panCenter.add(txtMessage, "wrap");

		//panCenter.add(letterCount, "w 200!");
		//panCenter.add(lblCounter, "wrap");
		
		
		
		
		
		btnRep.addActionListener(listener);
		btnDel.addActionListener(listener);
		btnRet.addActionListener(listener);
		
//		txtMessage.addKeyListener(listener);
		
		
		
		
	}



	public MessagingReadPanel() {
	
	}



	public JLabel getLblCounter() {
		return lblCounter;
	}



	public void setLblCounter(JLabel lblCounter) {
		this.lblCounter = lblCounter;
	}



	public JButton getBtnRep() {
		return btnRep;
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





}
