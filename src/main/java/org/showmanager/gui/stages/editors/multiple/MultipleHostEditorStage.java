/**	ShowManager v0.1	Dh	31.7.2023
 * 
 * 	gui.stages
 * 	BasicStage
 * 	  BasicMultipleEditorStage
 * 		MultiHostEditorStage
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
import org.showmanager.gui.controller.editors.multiple.MultipleHostEditorController;
import org.showmanager.gui.stages.BasicStage;
import org.showmanager.logic.manager.LogManager;
import org.showmanager.logic.manager.ShowManager;

public class MultipleHostEditorStage extends BasicMultipleEditorStage {

	/**	Dh	31.07.2023
	 * 
	 * @param pParentController
	 * @param pShowManager
	 */
	public MultipleHostEditorStage(AbstractParentController pParentController, ShowManager pShowManager) {
		super("org/showmanager/gui/editors/multiple/MultipleHostEditorScene.fxml", "Genres bearbeiten", pParentController, pShowManager);
	}

}
