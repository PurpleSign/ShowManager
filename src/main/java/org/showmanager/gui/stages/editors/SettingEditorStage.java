/**	ShowManager v0.1	Dh	01.08.2023
 * 
 * 	gui.stages
 * 	BasicStage
 * 	  SettingEditorStage
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

package org.showmanager.gui.stages.editors;

import org.showmanager.gui.controller.AbstractParentController;
import org.showmanager.gui.controller.editors.SettingEditorController;
import org.showmanager.gui.stages.BasicStage;
import org.showmanager.logic.manager.LogManager;
import org.showmanager.logic.manager.SettingManager;
import org.showmanager.logic.manager.ShowManager;

public class SettingEditorStage extends BasicStage {

	/**	Dh	01.08.2023
	 * 
	 * @param pParentController
	 * @param pSettingManager
	 * @param pShowManager
	 */
	public SettingEditorStage(AbstractParentController pParentController, SettingManager pSettingManager, ShowManager pShowManager) {
		super("org/showmanager/gui/editors/SettingEditorScene.fxml", "Einstellungen bearbeiten");
		
		try { ((SettingEditorController)controller).setUp(pParentController, pSettingManager, pShowManager); }
		catch(Exception ex) {LogManager.handleException(ex);}
	}

}
