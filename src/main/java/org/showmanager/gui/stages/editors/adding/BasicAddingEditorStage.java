/**	ShowManager v0.1	Dh	31.7.2023
 * 
 * 	gui.stages.editors
 * 	BasicStage
 * 	  BasicAddingEditorsStage
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

package org.showmanager.gui.stages.editors.adding;

import org.showmanager.gui.controller.AbstractParentController;
import org.showmanager.gui.controller.editors.adding.BasicAddingEditorController;
import org.showmanager.gui.stages.BasicStage;
import org.showmanager.logic.manager.LogManager;
import org.showmanager.logic.manager.ShowManager;

public abstract class BasicAddingEditorStage extends BasicStage {

	/**	Dh	31.07.2023
	 * 
	 * @param pSceneFileName
	 * @param pTitle
	 * @param pParentController
	 * @param pShowManager
	 * @param pGenreID
	 */
	public BasicAddingEditorStage(String pSceneFileName, String pTitle, AbstractParentController pParentController, ShowManager pShowManager, int pGenreID) {
		super(pSceneFileName, pTitle);
		
		try { ((BasicAddingEditorController)controller).setUp(pParentController, pShowManager, pGenreID); }
		catch(Exception ex) {LogManager.handleException(ex);}
	}

}
