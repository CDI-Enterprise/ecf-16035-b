package fr.cdiEnterprise.application;

import java.sql.SQLException;

//import fr.cdiEnterprise.dao.OldDatas;
import fr.cdiEnterprise.view.MainFrame;

public class Application {

	public static void main(String[] args) throws SQLException {
		

//	OldDatas.init();

		
		MainFrame mainFrame = new MainFrame();
		mainFrame.setVisible(true);
		mainFrame.pack();
		

	}
}