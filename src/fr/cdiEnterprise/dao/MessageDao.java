package fr.cdiEnterprise.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import fr.cdiEnterprise.control.MessageListener;
import fr.cdiEnterprise.model.Item;
import fr.cdiEnterprise.service.Items;

/**
 * class utilisee pour gerer les donnees dans la base de donnee. L'utilisateur
 * qui qua instancie cette class aura alors access a 2 ArrayList d'item. un
 * ArrayList pour les message recus. un ArrayList pour les messages brouillon
 * (draft)
 * 
 * Cette class ne devra etre instancie qu'une seule fois.
 * 
 * create table mailbox(identity VARCHAR2(80), sender VARCHAR2(40), receiver
 * VARCHAR2(40), subject VARCHAR2(144), messBody VARCHAR2(1500), timeStamp
 * VARCHAR2(20), draft number);
 * 
 * @author Nicolas Tarral
 * @version 19-10-2016
 *
 */
public class MessageDao {

	private static String TABLE_NAME = "mailbox";
	private static final String IDENTITY = "IDENTITY";
	private static final String SENDER = "SENDER";
	private static final String RECEIVER = "RECEIVER";
	private static final String SUBJECT  = "SUBJECT";
	private static final String MESSBODY = "MESSBODY";
	private static final String TIMESTAMP  = "TIMESTAMP";
	private static final String DRAFT  = "DRAFT";
	
	/**
	 * This class is going to insert new message in the table.
	 * @param item represent the item to insert.
	 * @throws SQLException 
	 */
	public static void insertItem(Item item) throws SQLException {

		Connection connection = null;
		PreparedStatement statement = null;
	
		try {
			connection = DBConnection.getConnect();
					
			

			int ref = 0;
			String sender = null;
			String receiver = null;
			String object = null;
			String body = null;
			String date = null;
			int draft = 0; // 0 for draft

			ref = item.getId();
			sender = item.getSender();
			receiver = item.getReceiver();
			object = item.getObject();
			body = item.getBody();
			draft = booleanToInt(item.isDraftEmail());
			//System.out.println(item.isDraftEmail()+ " "+ draft);
			if (draft == 0) {
				date = localDateToString(item.getTimeStamp());
			}
			
			statement = connection
					.prepareStatement("insert into mailbox (IDENTITY, SENDER, RECEIVER, SUBJECT, MESSBODY, TIMESTAMP, DRAFT) values (?, ?, ?, ?, ?, ?, ?)");
			
			
			statement.setInt(1, ref);
			statement.setString(2, sender);
			statement.setString(3, receiver);
			statement.setString(4, object);
			statement.setString(5, body);
			statement.setString(6, date);
			statement.setInt(7, draft);
			statement.executeUpdate();
			connection.commit();

		} catch (SQLException e) {
			throw new SQLException("[Write] Erreur SQL suivante : " + e.getMessage());
			
			//e.printStackTrace();
		

		}

	}


	/**
	 * This method is going to return the email for a particular user mailbox or
	 * a draft Mailbox. box will indicate the mailbox whether draft is false ,
	 * or draft message if that is true
	 * 
	 * @param box indicate the user messagebox.
	 * @param draft indicate if it is a draft email (true) 
	 * @return list of items found for this user.
	 * @throws SQLException to specify the type of issue
	 */
	public static Items getAllItems(String box, boolean draft){

		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		Items items = new Items();

		connection = DBConnection.getConnect();
		try {
			statement = connection.createStatement();


		// String ident = null;
		int ident = 0;
		String sender = null;
		String receiver = null;
		String object = null;
		String body = null;
		String date = null;
		int draftMess = 0;

		String createStatement = null;
		if (draft) {
			createStatement = String.format(
					"select %s, %s  , %s ,%s , %s ,%s, %s from %s WHERE %s = '%s' AND %s = '%s'",
					//
					"identity", "sender", "receiver", "subject", "messBody", "timeStamp", "draft", TABLE_NAME, "SENDER",
					box, "DRAFT", 1);
		} else {
			createStatement = String.format(
					"select %s, %s  , %s ,%s , %s ,%s, %s from %s WHERE %s = '%s' AND %s = '%s'",
					//
					"identity", "sender", "receiver", "subject", "messBody", "timeStamp", "draft", TABLE_NAME,
					"RECEIVER", box, "DRAFT", 0);
		}




			resultSet = statement.executeQuery(createStatement);
			connection.commit();

			while (resultSet.next()) {

				ident 	= resultSet.getInt("identity");
				sender 	= resultSet.getString("sender");
				receiver= resultSet.getString("receiver");
				object 	= resultSet.getString("subject");
				body 	= resultSet.getString("messBody");

				draftMess = resultSet.getInt("draft");
				if (draftMess == 0) {
					date = resultSet.getString("timeStamp");
				}

				Item item = new Item(ident, sender, receiver, object, body, StringToLocalDate(date),
						intToBoolean(draftMess));
				items.add(item);
			}
		} catch (SQLException e1) {
			System.out.println("[GET] an issue happen with the SQL to getAll items");
			e1.printStackTrace();
		}
		return items;

	}

	//TODO (nicolas) a revoir
	/**
	 * This method is going to return the email for a particular user mailbox or
	 * a draft Mailbox. box will indicate the mailbox whether draft is false ,
	 * or draft message if that is true
	 * 
	 * @box indicqte the user messagebox.
	 * @return a list of all the message for the user.
	 * @throws SQLException throw if SAL issue.
	 */

	// TODO (nicolas) methode a revoir
	public static Items getAllItems(String box) throws SQLException {

		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		Items items = new Items();

		connection = DBConnection.getConnect();
		try {
			statement = connection.createStatement();
		} catch (SQLException e1) {
			// TODO (nicolas) need to fix this excp
			e1.printStackTrace();
		}

		// String ident = null;
		int ident = 0;
		String sender = null;
		String receiver = null;
		String object = null;
		String body = null;
		String date = null;
		int draftMess = 0;

		String createStatement = null;

		createStatement = String.format("select %s, %s  , %s ,%s , %s ,%s, %s from %s",
				//
				"identity", "sender", "receiver", "subject", "messBody", "timeStamp", "draft", TABLE_NAME);

		try {
			resultSet = statement.executeQuery(createStatement);
			connection.commit();

			while (resultSet.next()) {

				ident = resultSet.getInt("identity");
				sender = resultSet.getString("sender");
				receiver = resultSet.getString("receiver");
				object = resultSet.getString("subject");
				body = resultSet.getString("messBody");

				draftMess = resultSet.getInt("draft");
				if (draftMess == 0) {
					date = resultSet.getString("timeStamp");
				}

				Item item = new Item(ident, sender, receiver, object, body, StringToLocalDate(date),
						intToBoolean(draftMess));
				items.add(item);
			}
		} catch (SQLException e) {
			throw new SQLException("[READ] Erreur SQL suivante : " + e.getMessage());
			
		}

		return items;

	}

	// }
	
	
	/**
	 * Cette methode effectue une recherche par mot sur le sujet du message, il retournera une liste dItems si necessaire.
	 * @param word est le mot a trouver dans le sujet d'un message
	 * @return une liste de message dont le sujet correspond au critere de recherche.
	 */
	public static Items searchMessage(String word) {
		
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;

		Items items = new Items();
		try {
	
			connection = DBConnection.getConnect();
			statement = connection.createStatement();
			
			int ident = 0;
			String sender = null;
			String receiver = null;
			String object = null;
			String body = null;
			String date = null;
			int draftMess = 0;

			String query =  String.format("SELECT  %s, %s  , %s ,%s , %s ,%s, %s FROM %s WHERE RECEIVER = '%s' AND DRAFT = '%s' AND UPPER (SUBJECT) LIKE UPPER('%%%s%%')",
					IDENTITY, SENDER, RECEIVER, SUBJECT, MESSBODY, TIMESTAMP, DRAFT, TABLE_NAME,MessageListener.alias, "0" ,word);
			System.out.println(query);
			resultSet = statement.executeQuery(query);
			connection.commit();

			while (resultSet.next()) {

				ident = resultSet.getInt("identity");
				sender = resultSet.getString("sender");
				receiver = resultSet.getString("receiver");
				object = resultSet.getString("subject");
				body = resultSet.getString("messBody");

				draftMess = resultSet.getInt("draft");
				if (draftMess == 0) {
					date = resultSet.getString("timeStamp");
				}

				Item item = new Item(ident, sender, receiver, object, body, StringToLocalDate(date),
						intToBoolean(draftMess));
				items.add(item);
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return items;
		
	}


	/**
	 * This method is going to delete a message with a particular Identifier, receipient user, and a message type(draft) 
	 * @param receipient represent the user logged on the system
	 * @param identifier an integer for identify unique message	 
	 * @param draft indicate if it is a draft email (true)
 
	 * @throws SQLException a SQL exception error.
	 */
	public static void removeMessage(String receipient, int identifier, boolean draft) throws SQLException {

		Connection connection = null;
		Statement statement = null;
		

		connection = DBConnection.getConnect();
		try {
			statement = connection.createStatement();
			String query = "delete from mailbox where identity = '" + identifier + "'";

			statement.executeUpdate(query);
			connection.commit();
		} catch (SQLException e) {
			throw new SQLException("[DEL] Erreur SQL suivante : " + e.getMessage());
			
		}



	}




	/**
	 * This method is going to modifie a draft message for the sender's user.
	 * @param updatedItem is the new item thqt need to be inserted
	 * @throws SQLException in case of SQL error
	 */
	public static void updateMessage(Item updatedItem) throws SQLException {

		Connection connection = null;
		Statement statement = null;
		

		connection = DBConnection.getConnect();
		try {
			statement = connection.createStatement();
			int ident = 0;
			String sender = null;
			String receiver = null;
			String object = null;
			String body = null;


			ident = updatedItem.getId();
			sender = updatedItem.getSender();
			receiver = updatedItem.getReceiver();
			object = updatedItem.getObject();
			body = updatedItem.getBody();

			String createStatement = String.format(
					"UPDATE mailbox SET SENDER=" + "'" + sender + "',RECEIVER= " + "'" + receiver + "', SUBJECT = "
							+ "'" + object + "', MESSBODY = " + "'" + body + "' WHERE receiver = 'receiver' AND identity= " + "'" + ident + "'");
			System.out.println(createStatement);
			statement.executeUpdate(createStatement);

			connection.commit();

		} catch (SQLException e) {
			throw new SQLException("[UPDATE] Erreur SQL suivante : " + e.getMessage());
		} 

	}
	


	/**
	 * This static method is going to be used to convert LocalDateTime in a
	 * String format this way dd MM yyyy HH:MM:SS
	 * 
	 * @param input
	 *            the date to convert in LocalDateTime
	 * @return the output date in String format.
	 */
	public static String localDateToString(LocalDateTime input) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MM uuuu HH:mm:ss");
		String strDate = null;
		if(input != null) {
			strDate  = input.format(formatter);
			return strDate;
		}else {
			System.err.println(" -- parametre non-conforme heure local produite -- ");
			LocalDateTime dateNow = LocalDateTime.now();
			strDate  = dateNow.format(formatter);
			return strDate;
		}
	}

	/**
	 * Cette methode statique va convertir une date de type String de format
	 * "dd MM uuuu HH:mm:ss" en type LocalDateTime
	 * 
	 * @param input
	 *            represente la date en String
	 * @return un objet de type LocalDateTime.
	 */
	public static LocalDateTime StringToLocalDate(String input) {
		LocalDateTime localTime = null;
		if (input != null) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MM uuuu HH:mm:ss");
			localTime = LocalDateTime.parse(input, formatter);

		}
		return localTime;
	}

	/**
	 * This method is going to convert boolean true/false to its corresponding
	 * integer value 1/0. The given value is a primitive type int.
	 * 
	 * @param input
	 *            will be a boolean type
	 * @return is going to be its corresponding integer value
	 */
	public static int booleanToInt(boolean input) {
		int output = 0;
		if (input == true) {
			output = 1;
		}
		return output;

	}

	public static boolean intToBoolean(int input) {
		boolean output = false;
		if (input == 1) {
			output = true;
		}
		return output;

	}

}
