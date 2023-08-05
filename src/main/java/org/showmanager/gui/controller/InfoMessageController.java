/**	ShowManager v0.1	Dh	30.7.2023
 * 
 * 	gui.controller
 * 	BasicController
 * 	  InfoController
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

import org.showmanager.gui.stages.InfoMessageStage;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class InfoMessageController extends AbstractParentController {
	private InfoMessageStage controlledStage;
	
	@FXML
	private Label lTitle;
	@FXML
	private TextArea taMessage;
	@FXML
	private Button btBack;
	
	/**	Dh	30.07.2023
	 * 
	 */
	public InfoMessageController() {
		super();
		
		controlledStage = null;
	}
	
	//----------------------------------------------------------------------------------------------------

	/**	Dh	30.07.2023
	 * 
	 */
	public void setUp(InfoMessageStage pControlledStage, String pTitle, String pMessage) throws Exception{
		super.setUp(null);
		
		controlledStage = pControlledStage;
		
		lTitle.setText(pTitle);
		taMessage.setText(pMessage);
	}
	
//--------------------------------------------------------------------------------------------------------	

	/**	Dh	30.07.2023
	 * 
	 */
	protected void setEnabled(boolean pEnable) {
		btBack.setDisable(!pEnable);
	}
	
//--------------------------------------------------------------------------------------------------------

	/**	Dh	30.07.2023
	 * 
	 */
	@FXML
	public void back() {
		controlledStage.closeInfoStage();
	}
	
}
