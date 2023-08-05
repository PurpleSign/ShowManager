/**	ShowManager v0.1	Dh	30.7.2023
 * 
 * 	gui.stages
 * 	BasicStage
 * 	  InfoMessageStage
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

package org.showmanager.gui.stages;

import org.showmanager.MainManager;
import org.showmanager.gui.controller.InfoMessageController;
import org.showmanager.logic.manager.LogManager;

public class InfoMessageStage extends BasicStage {
	private MainManager mainManager;

	/**	Dh	30.07.2023
	 * 
	 * @param pParentController
	 * @param pTitle
	 * @param pMessage
	 */
	public InfoMessageStage(MainManager pMainManager, String pTitle, String pMessage) {
		super("org/showmanager/gui/InfoMessageScene.fxml", pTitle);
		
		mainManager = pMainManager;
		
		try { ((InfoMessageController)controller).setUp(this, pTitle, pMessage); }
		catch(Exception ex) {LogManager.handleException(ex);}
	}

//--------------------------------------------------------------------------------------------------------
	
	/**	Dh	30.07.2023
	 * 
	 */
	public void closeInfoStage() {
		mainManager.removeInfoMessageStage(this);
	}
	
}
