/**	ShowManager v0.0	Dh	27.7.2020
 * 
 * 	pLogic
 * 	  MainManager
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
package pLogic;

import pDataStructures.List;
import pGUI.GUIManager;

public class MainManager {
	private static DatabaseInterface databaseInterface;
	private static SettingManager settingManager;
	private static ShowManager showManager;
	private static GUIManager guiManager;
	
	/**	Dh	19.7.2020
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		new MainManager();
	}
	
	//------------------------------------------------------------------------------------------------
	
	/**	Dh	21.7.2020
	 * 
	 */
	public MainManager() {
		List vExcList = new List();
		
		try {databaseInterface = new DatabaseInterface();}
		catch(Exception ex) {vExcList.append(ex);}
		
		try {settingManager = new SettingManager(databaseInterface);}
		catch(Exception ex) {vExcList.append(ex);}
		
		try {showManager = new ShowManager(databaseInterface, settingManager.getBrowserOpener());}
		catch(Exception ex) {vExcList.append(ex);}
		
		try {guiManager = new GUIManager(showManager, settingManager);}
		catch(Exception ex) {vExcList.append(ex);}
		
		if (!vExcList.isEmpty()) {
			vExcList.toFirst();
			
			while(!vExcList.isEnd()) {
				handleException((Exception)vExcList.getCurrent());
				vExcList.next();
			}
		}
	}
	
//----------------------------------------------------------------------------------------------------

	/**	Dh	27.7.2020
	 * 
	 */
	public static void closeApp() {
		try{
			showManager.saveAll();
			showManager = null;
		}catch(Exception ex) {System.out.println(""+ex.getMessage());}
		
		try{
			settingManager.saveAll();
			settingManager = null;
		}catch(Exception ex) {System.out.println(""+ex.getMessage());}
		
		try {
			guiManager.closeAllFrames();
			guiManager = null;
		} catch(Exception ex) {System.out.println(""+ex.getMessage());}
		
		databaseInterface.closeDatabaseInterface();
		databaseInterface = null;
		
		System.exit(0);
	}
	
//----------------------------------------------------------------------------------------------------
	
	/**	Dh	21.7.2020
	 * 
	 * @param ex
	 */
	public static void handleException(Exception ex) {
		guiManager.openExceptionFrame(ex);
	}
}
