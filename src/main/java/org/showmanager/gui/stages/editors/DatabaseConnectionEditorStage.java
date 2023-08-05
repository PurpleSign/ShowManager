/**	ShowManager v0.1	Dh	30.7.2023
 * 
 * 	gui.stages.editors
 * 	BasicStage
 * 	  DatabaseConnectionEditorStage
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
import org.showmanager.gui.controller.editors.DatabaseConnectionEditorController;
import org.showmanager.gui.stages.BasicStage;
import org.showmanager.logic.manager.LogManager;
import org.showmanager.logic.manager.SettingManager;

public class DatabaseConnectionEditorStage extends BasicStage {

	/**	Dh	30.07.2023
	 * 
	 * @param pParentController
	 * @param pSettingManager
	 */
	public DatabaseConnectionEditorStage(AbstractParentController pParentController, SettingManager pSettingManager) {
		super("org/showmanager/gui/editors/DatabaseConnectionEditorScene.fxml", "Datenbankverbindung");
		
		try { ((DatabaseConnectionEditorController)controller).setUp(pParentController, pSettingManager); }
		catch(Exception ex) {LogManager.handleException(ex);}
	}

}
