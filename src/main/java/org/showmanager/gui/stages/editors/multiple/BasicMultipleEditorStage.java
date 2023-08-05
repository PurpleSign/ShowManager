/**	ShowManager v0.1	Dh	31.7.2023
 * 
 * 	gui.stages
 * 	BasicStage
 * 	  BasicMultiEditorStage
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

package org.showmanager.gui.stages.editors.multiple;

import org.showmanager.gui.controller.AbstractParentController;
import org.showmanager.gui.controller.editors.multiple.BasicMultipleEditorController;
import org.showmanager.gui.stages.BasicStage;
import org.showmanager.logic.manager.LogManager;
import org.showmanager.logic.manager.ShowManager;

public class BasicMultipleEditorStage extends BasicStage {

	/**	Dh	31.07.2023
	 * 
	 * @param pSceneFileName
	 * @param pTitle
	 * @param pParentController
	 * @param pShowManager
	 */
	public BasicMultipleEditorStage(String pSceneFileName, String pTitle, AbstractParentController pParentController, ShowManager pShowManager) {
		super(pSceneFileName, pTitle);
		
		try { ((BasicMultipleEditorController)controller).setUp(pParentController, pShowManager); }
		catch(Exception ex) {LogManager.handleException(ex);}
	}

}
