/**	ShowManager v0.1	Dh	30.7.2023
 * 
 * 	logic.manager
 * 	LogManager
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

package org.showmanager.logic.manager;

import org.showmanager.MainManagerInterface;

public class LogManager {
	private static double version = 0.1;
	private static MainManagerInterface mainManager;
	
	/**	Dh	26.07.2023
	 * 
	 * @param pMainManager
	 */
	public static void initManager(MainManagerInterface pMainManager) {
		mainManager = pMainManager;
	}

//--------------------------------------------------------------------------------------------------------

	/**	Dh	30.07.2023
	 * 
	 * @return
	 */
	public static double getVersion() {
		return version;
	}
	
	//------------------------------------------------------------------------------------------------
	
	/**	Dh	26.07.2023
	 * 
	 */
	public static void closeApp() {
		if (mainManager != null) mainManager.closeApp();
	}
	
//--------------------------------------------------------------------------------------------------------

	/**	Dh	26.07.2023
	 * 
	 * @param ex
	 */
	public static void handleException(Exception ex) {
		if (mainManager != null) mainManager.handleException(ex);
	}
	/**	Dh	26.07.2023
	 * 
	 * @param pMessage
	 */
	public static void handleMessage(String pMessage) {
		if (mainManager != null) mainManager.handleMessage(pMessage);
	}
	
}
