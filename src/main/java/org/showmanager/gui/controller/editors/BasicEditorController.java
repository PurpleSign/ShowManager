/**	ShowManager v0.1	Dh	30.7.2023
 * 
 * 	gui.controller.editors
 * 	BasicController
 * 	  AbstractParentController
 * 		BasicEditorController
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

package org.showmanager.gui.controller.editors;

import org.showmanager.gui.controller.AbstractParentController;

import javafx.fxml.FXML;

public abstract class BasicEditorController extends AbstractParentController {

	/**	Dh	30.07.2023
	 * 
	 */
	public BasicEditorController() {
		
	}

//--------------------------------------------------------------------------------------------------------	

	/**	Dh	30.07.2023
	 * 
	 */
	public abstract void progress();
	
	/**	Dh	30.07.2023
	 * 
	 */
	@FXML
	public void back() {
		super.parentController.closeChildStage();
	}
	
}
