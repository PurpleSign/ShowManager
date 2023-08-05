/**	ShowManager v0.1	Dh	31.7.2023
 * 
 * 	gui.controller.editors
 * 	BasicController
 * 	  BasicParentController
 * 		BasicAddingEditorController
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

import java.util.ArrayList;

import org.showmanager.gui.controller.AbstractParentController;
import org.showmanager.gui.controller.editors.BasicEditorController;
import org.showmanager.gui.tableElements.NameElement;
import org.showmanager.logic.manager.ShowManager;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public abstract class BasicAddingEditorController extends BasicEditorController {
	protected int editObjectID;
	protected ShowManager showManager;
	
	@FXML
	protected Button btProgress, btBack;
	
	/**	Dh	30.07.2023
	 * 
	 */
	public BasicAddingEditorController() {
		editObjectID = -1;
		
		showManager = null;
	}

	//------------------------------------------------------------------------------------------------
	
	/**	Dh	30.07.2023
	 * 
	 * @param pParentController
	 * @param pEditorManager
	 * @param pEditObjectID
	 * @throws Exception
	 */
	public void setUp(AbstractParentController pParentController, ShowManager pShowManager, int pEditObjectID) throws Exception{
		super.setUp(pParentController);
		
		editObjectID = pEditObjectID;
		
		showManager = pShowManager;
	}
	
//--------------------------------------------------------------------------------------------------------

	/**	Dh	31.07.2023
	 * 
	 * @param pEnable
	 */
	protected abstract void setEnabledObjectInformations(boolean pEnable); 
	/**	Dh	31.07.2023
	 * 
	 * @param pEnable
	 */
	protected void setEnabledProcessButtons(boolean pEnable) {
		btProgress.setDisable(!pEnable);
		btBack.setDisable(!pEnable);
	}
	
	/**	Dh	31.07.2023
	 * 
	 */
	protected void setEnabled(boolean pEnable) {
		setEnabledObjectInformations(pEnable);
		setEnabledProcessButtons(pEnable);
	}
	
//--------------------------------------------------------------------------------------------------------
	
	protected abstract boolean isInputValid();
	
//--------------------------------------------------------------------------------------------------------

	/**	Dh	31.07.2023
	 * 
	 * @param pNameElementList
	 * @return
	 * @throws Exception
	 */
	protected ArrayList<Integer> convertNameElementListToIDList(ObservableList<NameElement> pNameElementList) throws Exception{
		ArrayList<Integer> vRet = new ArrayList<Integer>();
		
		if ((pNameElementList != null) && (!pNameElementList.isEmpty())) {
			for (NameElement vCur : pNameElementList) {
				vRet.add(Integer.valueOf( vCur.getId() ));
			}
		}
		
		return vRet;
	}
	
}
