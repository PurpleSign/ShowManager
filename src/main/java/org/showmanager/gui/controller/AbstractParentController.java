/**	ShowManager v0.1	Dh	30.7.2023
 * 
 * 	gui.controller
 * 	BasicController
 * 	  AbstractParentController
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

package org.showmanager.gui.controller;

import org.showmanager.gui.stages.BasicStage;

import javafx.fxml.FXML;

public abstract class AbstractParentController extends BasicController {
	protected BasicStage childStage;
	
	/**	Dh	29.07.2023
	 * 
	 */
	public AbstractParentController() {
		childStage = null;
	}
		
//--------------------------------------------------------------------------------------------------------

	/**	Dh	30.07.2023
	 * 
	 */
	public void closeChildStage() {
		childStage.hide();
		setEnabled();
	}
	
	/**	Dh	29.07.2023
	 * 
	 */
	@FXML
	public abstract void back();
	
}
