/**	ShowManager v0.1	Dh	31.7.2023
 * 
 * 	gui.stages.editors
 * 	BasicStage
 * 	  BasicAddingEditorStage
 * 		ShowListEditorsStage
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

public class ShowListEditorStage extends BasicAddingEditorStage {

	public ShowListEditorStage(AbstractParentController pParentController,
			ShowManager pShowManager, int pGenreID) {
		super("org/showmanager/gui/editors/adding/ShowListEditorScene.fxml", (pGenreID != -1 ? "Listen Bearbeitung" : "Liste Hinzufügen"),
				pParentController, pShowManager, pGenreID);
	}

}
