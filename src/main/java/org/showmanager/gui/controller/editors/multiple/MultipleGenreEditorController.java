/**	ShowManager v0.1	Dh	30.7.2023
 * 
 * 	gui.controller.editors
 * 	BasicController
 * 	  BasicParentController
 * 		BasicAddingEditorController
 * 		  BasicMultipleEditorController
 * 			MultipleGenreEditorController
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

import java.util.ArrayList;

import org.showmanager.gui.controller.AbstractParentController;
import org.showmanager.gui.stages.editors.adding.GenreEditorStage;
import org.showmanager.gui.tableElements.NameElement;
import org.showmanager.logic.manager.LogManager;
import org.showmanager.logic.manager.ShowManager;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class MultipleGenreEditorController extends BasicMultipleEditorController {
	@FXML
	private TextField tfName;
	
	
	/**	Dh	30.07.2023
	 * 
	 */
	public MultipleGenreEditorController() {
		super();
		
		newObjectButtonText = "Neues Genre";
	}

	//------------------------------------------------------------------------------------------------
	
	/**	Dh	30.07.2023
	 * 
	 */
	public void setUp(AbstractParentController pParentController, ShowManager pShowManager) throws Exception{
		super.setUp(pParentController, pShowManager);

	}
	
//--------------------------------------------------------------------------------------------------------

	/**	Dh	30.07.2023
	 * 
	 */
	protected void setEnabledObjectInformations(boolean pEnable) {
		tfName.setDisable( ((lvObjects.getSelectionModel().getSelectedItem() == null) ? true : !pEnable) );
	}
	
	//------------------------------------------------------------------------------------------------
	
	/**	Dh	30.07.2023
	 * 
	 */
	protected void updateObjectInformation() {
		NameElement vSelObject = lvObjects.getSelectionModel().getSelectedItem();
		
		if (vSelObject != null) tfName.setText( vSelObject.getName() );
		else tfName.setText( "" );
	}
	/**	Dh	30.07.2023
	 * 
	 */
	protected void updateList() {
		NameElement vCurNameElement, vCurSel;
		ArrayList<NameElement> vNewList = new ArrayList<NameElement>();
		vCurSel = lvObjects.getSelectionModel().getSelectedItem();
		
		for (Integer vGenreID : showManager.getGenreIDs()) {
			try {
				vCurNameElement = new NameElement(vGenreID, showManager.getGenreName(vGenreID)); 
				
				vNewList.add(vCurNameElement);
				
				if ((vCurSel != null) &&  (vCurSel.getId() == vCurNameElement.getId())) vCurSel = vCurNameElement;
			}catch(Exception ex) {LogManager.handleException(ex);}
		}
		
		liObjects.setAll(vNewList);
		
		if (vCurSel != null) lvObjects.getSelectionModel().select(vCurSel);
	}
	
//--------------------------------------------------------------------------------------------------------
	
	/**	Dh	30.07.2023
	 * 
	 */
	@FXML
	public void progress() {
		NameElement vCurSel = lvObjects.getSelectionModel().getSelectedItem();
		String vGenreName;
		
		if (vCurSel == null) {
			childStage = new GenreEditorStage(this, showManager, -1);
			setDisabled();
		}else if (isInputValid()) {
			try { 
				vGenreName = tfName.getText();
				
				showManager.setGenreName(editObjectID, vGenreName);
				vCurSel.setName( vGenreName );
				
				LogManager.handleMessage("Genre erfolgreich geändert!");
			} catch(Exception ex) {LogManager.handleException(ex);}
		} else LogManager.handleMessage("Eingaben nicht gültig!\nGenre nicht geändert.");
	}
	
	/**	Dh	30.07.2023
	 * 
	 */
	@FXML
	protected void removeObject() {
		NameElement vCurSel = lvObjects.getSelectionModel().getSelectedItem();
		
		if (vCurSel != null) {
			try {
				liObjects.remove(vCurSel);
				lvObjects.getSelectionModel().clearSelection();
				
				showManager.removeGenre( vCurSel.getId() );
			}catch(Exception ex) {LogManager.handleException(ex);}
		}else LogManager.handleMessage("Kein Genre entfernt!\nEs ist kein Genre ausgewählt.");
	}
	
//--------------------------------------------------------------------------------------------------------

	/**	Dh	30.07.2023
	 * 
	 */
	protected boolean isInputValid() {
		boolean vRet = false;
		
		if (tfName.getText() != "") {
			vRet = true;
			for (Integer vGenreID : showManager.getGenreIDs()) {
				try {	if (showManager.getGenreName(vGenreID).equals(tfName.getText()) 
						&& (vGenreID != editObjectID)) vRet = false; }
				catch(Exception ex) {LogManager.handleException(ex);}
			}
		}
		
		return vRet;
	}
	
}
