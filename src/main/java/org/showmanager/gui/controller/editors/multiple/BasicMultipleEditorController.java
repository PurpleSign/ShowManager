/**	ShowManager v0.1	Dh	30.7.2023
 * 
 * 	gui.controller.editors
 * 	BasicController
 * 	  BasicParentController
 * 		BasicAddingEditorController
 * 		  BasicMultipleEditorController
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

package org.showmanager.gui.controller.editors.multiple;

import org.showmanager.gui.controller.AbstractParentController;
import org.showmanager.gui.controller.editors.adding.BasicAddingEditorController;
import org.showmanager.gui.tableElements.NameElement;
import org.showmanager.logic.manager.ShowManager;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;

public abstract class BasicMultipleEditorController extends BasicAddingEditorController {
	protected String newObjectButtonText;
	
	@FXML
	protected Button btProgress, btRemove, btBack;
	@FXML
	protected ListView<NameElement> lvObjects;
	
	protected ObservableList<NameElement> liObjects;
	
	/**	Dh	30.07.2023
	 * 
	 */
	public BasicMultipleEditorController() {
		super();
		
		liObjects = null;
		newObjectButtonText = "";
	}
	
	//------------------------------------------------------------------------------------------------

	/**	Dh	30.07.2023
	 * 
	 * @param pParentController
	 * @param pShowManager
	 * @throws Exception
	 */
	public void setUp(AbstractParentController pParentController, ShowManager pShowManager) throws Exception{
		super.setUp(pParentController, pShowManager, -1);

		liObjects = FXCollections.observableArrayList();
		
		lvObjects.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		lvObjects.getSelectionModel().selectedItemProperty().addListener((pObs, pOldSel, pNewSel) -> {
			if (pNewSel == null) super.editObjectID = -1;
			else super.editObjectID = pNewSel.getId();
			
			setEnabled();
			updateObjectInformation();
			updateButtons();
		});
		
		updateAll();
		lvObjects.setItems(liObjects);
		
		setEnabled();
	}
	
//--------------------------------------------------------------------------------------------------------
	
	/**	Dh	30.07.2023
	 * 
	 * @param pEnable
	 */
	protected abstract void setEnabledObjectInformations(boolean pEnable);
	/**	Dh	30.07.2023
	 * 
	 * @param pEnable
	 */
	private void setEnabledButtons(boolean pEnable) {
		btProgress.setDisable(!pEnable);
		btBack.setDisable(!pEnable);
		
		btRemove.setDisable( ((lvObjects.getSelectionModel().getSelectedItem() == null) ? true : !pEnable));
	}
	
	/**	Dh	30.07.2023
	 * 
	 */
	protected void setEnabled(boolean pEnable) {
		setEnabledObjectInformations(pEnable);
		setEnabledButtons(pEnable);
	}
	
	//------------------------------------------------------------------------------------------------
	
	/**	Dh	30.07.2023
	 * 
	 */
	protected abstract void updateObjectInformation();
	/**	Dh	30.07.2023
	 * 
	 */
	protected abstract void updateList();
	/**	Dh	30.07.2023
	 * 
	 */
	protected void updateButtons() {
		if (lvObjects.getSelectionModel().getSelectedItem() == null) btProgress.setText(newObjectButtonText);
		else btProgress.setText("Anwenden");
	}
	
	/**	Dh	30.07.2023
	 * 
	 */
	protected void updateAll() {
		updateObjectInformation();
		updateList();
		updateButtons();
	}
	
	//------------------------------------------------------------------------------------------------
	
	/**	Dh	30.07.2023
	 * 
	 */
	public void closeChildStage() {
		super.closeChildStage();
		
		updateAll();
	}
	
//--------------------------------------------------------------------------------------------------------
	
	/**	Dh	30.07.2023
	 * 
	 */
	protected abstract void removeObject();
	
}
