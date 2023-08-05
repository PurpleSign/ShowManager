/**	ShowManager v0.1	Dh	31.7.2023
 * 
 * 	gui.stages.editors
 * 	BasicStage
 * 	  BasicAddingEditorStage
 * 		ShowEditorsStage
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
import org.showmanager.logic.manager.ShowManager;

public class ShowEditorStage extends BasicAddingEditorStage {

	/**	Dh	31.07.2023
	 * 
	 * @param pSceneFileName
	 * @param pTitle
	 * @param pParentController
	 * @param pShowManager
	 * @param pGenreID
	 */
	public ShowEditorStage(AbstractParentController pParentController, ShowManager pShowManager, int pGenreID) {
		super("org/showmanager/gui/editors/adding/ShowEditorScene.fxml", (pGenreID != -1 ? "Serie Bearbeitung" : "Serie Hinzufügen"),
					pParentController, pShowManager, pGenreID);
		
	}

}
