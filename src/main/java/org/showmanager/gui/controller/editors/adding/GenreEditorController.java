/**	ShowManager v0.1	Dh	30.7.2023
 * 
 * 	gui.controller.editors
 * 	BasicController
 * 	  AbstractParentController
 * 		BasicEditorController
 * 		  BasicAddingEditorController
 * 		    GenreEditorController
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

package org.showmanager.gui.controller.editors.adding;

import org.showmanager.gui.controller.AbstractParentController;
import org.showmanager.logic.manager.LogManager;
import org.showmanager.logic.manager.ShowManager;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class GenreEditorController extends BasicAddingEditorController {
	@FXML
	private TextField tfGenreName;	
	
	/**	Dh	30.07.2023
	 * 
	 */
	public GenreEditorController() {
		super();
	}

	//------------------------------------------------------------------------------------------------
	
	/**	Dh	30.07.2023
	 * 
	 */
	public void setUp(AbstractParentController pParentController, ShowManager pShowManager, int pEditObjectID) throws Exception{
		super.setUp(pParentController, pShowManager, pEditObjectID);
	}
	
//--------------------------------------------------------------------------------------------------------

	/**	Dh	31.07.2023
	 * 
	 * @param pEnable
	 */
	protected void setEnabledObjectInformations(boolean pEnable) {
		tfGenreName.setDisable(!pEnable);
	}
	
//--------------------------------------------------------------------------------------------------------
	
	/**	Dh	30.07.2023
	 * 
	 */
	@FXML
	public void progress() {
		if (isInputValid()) {
			try {
				if (editObjectID == -1) showManager.addGenre( tfGenreName.getText() );
				else 					showManager.setGenreName(editObjectID, tfGenreName.getText());
			
				super.parentController.closeChildStage();
			} catch(Exception ex) {LogManager.handleException(ex);}
		}else LogManager.handleMessage("Eingabe ungültig!");
	}
	
//--------------------------------------------------------------------------------------------------------

	/**	Dh	30.07.2023
	 * 
	 */
	protected boolean isInputValid() {
		boolean vRet = false;
		
		if (tfGenreName.getText() != "") {
			vRet = true;
			for (Integer vGenreID : showManager.getGenreIDs()) {
				try {	if (showManager.getGenreName(vGenreID).equals(tfGenreName.getText()) 
						&& (vGenreID != editObjectID)) vRet = false; }
				catch(Exception ex) {LogManager.handleException(ex);}
			}
		}
		
		return vRet;
	}
	
}
