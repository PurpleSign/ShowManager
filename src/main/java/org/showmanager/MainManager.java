/**	ShowManager v0.1	Dh	05.08.2023
 * 
 * 	MainManagerInterface
 * 		MainManager
 * 
 * 	Exceptions:
 * 	  01 Wrong length
 * 	  02 Wrong Value
 * 	  03 Calculation Error
 * 	  04 Nullpointer Error
 * 	  05 Empty List Error
 * 	  06 Wrong Type Error
 * 	  07 Index Error
 * 	  08 Equal Object Error
 * 	  09 Wrong Selection
 */

package org.showmanager;

import javafx.application.Application;
import javafx.stage.Stage;

import java.util.ArrayList;

import org.showmanager.gui.controller.MainStageController;
import org.showmanager.gui.stages.InfoMessageStage;
import org.showmanager.gui.stages.MainStage;
import org.showmanager.logic.manager.DatabaseManager;
import org.showmanager.logic.manager.LogManager;
import org.showmanager.logic.manager.SettingManager;
import org.showmanager.logic.manager.ShowManager;

public class MainManager extends Application implements MainManagerInterface {
	private static Stage mainStage, primaryStage;
	
	private LogManager logManager;
	private DatabaseManager databaseManager;
	private SettingManager settingManager;
	private ShowManager showManager;
	
	private ArrayList<InfoMessageStage> infoMessageStages;
	
	/**	Dh	27.07.2023
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}
	
	/**	Dh	30.07.2023
	 * 	
	 */
	public void start(Stage pPrimaryStage) {
		primaryStage = pPrimaryStage;
		
		logManager.initManager(this);
		infoMessageStages = new ArrayList<InfoMessageStage>();
		
		try {
			databaseManager = new DatabaseManager();
			settingManager = new SettingManager(databaseManager);
			showManager = new ShowManager(databaseManager, settingManager.getBrowserManager());
			
			mainStage = new MainStage(settingManager, showManager);
			((MainStageController)((MainStage)mainStage).getController()).startDatabaseConnectionEditor();
		} catch(Exception ex) {this.handleException(ex);}
	}

//--------------------------------------------------------------------------------------------------------
	
	/**	Dh	05.08.2023
	 * 
	 */
	public void closeApp() {
		try { settingManager.saveAll(); }
		catch(Exception ex) {LogManager.handleException(ex);}
		
		mainStage.close();
		primaryStage.close();
		System.exit(0);
	}
	
//--------------------------------------------------------------------------------------------------------
	
	/**	Dh	30.07.2023
	 * 
	 * @return
	 */
	public static Stage getPrimaryStage() {
		return primaryStage;
	}
	
//--------------------------------------------------------------------------------------------------------	
	
	/**	Dh	05.08.2023
	 * 
	 */
	public void handleException(Exception ex) {
		System.out.println(ex.getCause() + " : " + ex.getMessage());
		infoMessageStages.add(new InfoMessageStage(this, "Fehler", ex.getCause() + " : " + ex.getMessage()));
		//closeApp();
	}
	/**	Dh	30.07.2023
	 * 
	 */
	public void handleMessage(String pMessage) {
		System.out.println(pMessage);
		infoMessageStages.add(new InfoMessageStage(this, "Information", pMessage));
	}
	
	/**	Dh	30.07.2023
	 * 
	 * @param pInfoMessageStage
	 */
	public void removeInfoMessageStage(InfoMessageStage pInfoMessageStage) {
		infoMessageStages.remove(pInfoMessageStage);
		pInfoMessageStage.hide();
	}
	
}
