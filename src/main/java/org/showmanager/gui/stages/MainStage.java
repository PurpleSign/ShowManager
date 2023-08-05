/**	ShowManager v0.1	Dh	27.7.2023
 * 
 * 	gui.stages
 * 	BasicStage
 * 	  MainStage
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

import org.showmanager.gui.controller.MainStageController;
import org.showmanager.logic.manager.LogManager;
import org.showmanager.logic.manager.SettingManager;
import org.showmanager.logic.manager.ShowManager;

public class MainStage extends BasicStage {

	/**	Dh	30.07.2023
	 * 
	 * @param pSettingManager
	 * @param pShowManager
	 */
	public MainStage(SettingManager pSettingManager, ShowManager pShowManager) {
		super("org/showmanager/gui/MainStageScene.fxml", "Serien Manager v" + LogManager.getVersion());
		
		try { ((MainStageController)controller).setUp(pSettingManager, pShowManager); }
		catch(Exception ex) {LogManager.handleException(ex);}
	}

}
