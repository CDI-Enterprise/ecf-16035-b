/**
 * 
 */
package fr.cdiEnterprise.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.swing.JOptionPane;

import fr.cdiEnterprise.exceptions.CustomMessagingException;
import fr.cdiEnterprise.view.MainFrame;

/**
 * this properties class will give the ability to one user to customize its program and provide its Alias.
 * Further informations coumd be loaded like first, last name, phone number, address, email...
 * 
 * @author Nicolas Tarral
 * @version 13-10-2016
 *
 */
public class ReadProperties {
	
	private static final String ALIAS = "alias";
	static final String FILENAME = "MonIdentite.properties";

	public  ReadProperties() {
	}

	public static String getMyAlias() throws CustomMessagingException {
		Properties properties = new Properties();
		try {
			FileInputStream in = new FileInputStream(FILENAME);
			properties.load(in);
			in.close();
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(MainFrame.getPanHome(),
	                "cannot find MonIdentite.properties", "Error", JOptionPane.ERROR_MESSAGE);
			System.exit(-1);
		} catch (IOException e) {
			System.exit(-1);
		}
		
		String myAlias = properties.getProperty(ALIAS);
		if(myAlias.isEmpty()) {
			throw new CustomMessagingException("[MonIdentite] Alias manquant dans le fichier properties");
		}

		return myAlias;
	}
}
