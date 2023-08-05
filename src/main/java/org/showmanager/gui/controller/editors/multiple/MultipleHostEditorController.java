/**	ShowManager v0.1	Dh	31.7.2023
 * 
 * 	gui.controller.editors
 * 	BasicController
 * 	  BasicParentController
 * 		BasicAddingEditorController
 * 		  BasicMultipleEditorController
 * 			MultipleHostEditorController
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

import org.showmanager.gui.stages.editors.adding.HostEditorStage;
import org.showmanager.gui.tableElements.NameElement;
import org.showmanager.logic.manager.LogManager;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class MultipleHostEditorController extends BasicMultipleEditorController {
	@FXML
	private TextField tfName, tfURL;
	
	/**	Dh	31.07.2023
	 * 
	 */
	public MultipleHostEditorController() {
		super();
		
		newObjectButtonText = "Neuer Host";
	}
	
//--------------------------------------------------------------------------------------------------------
	
	/**	Dh	31.07.2023
	 * 
	 */
	protected void setEnabledObjectInformations(boolean pEnable) {
		NameElement vCurSel = lvObjects.getSelectionModel().getSelectedItem();
		
		tfName.setDisable( ((vCurSel == null) ? true : !pEnable) );
		tfURL.setDisable( ((vCurSel == null) ? true : !pEnable) );
	}
	
	//------------------------------------------------------------------------------------------------
	
	/**	Dh	31.07.2023
	 * 
	 */
	protected void updateObjectInformation() {
		NameElement vSelObject = lvObjects.getSelectionModel().getSelectedItem();
		
		if (vSelObject != null) {
			try {
				tfName.setText( vSelObject.getName() );
				tfURL.setText( showManager.getHostURL( editObjectID ) );
			}catch(Exception ex) {LogManager.handleException(ex);}
		}
		else {
			tfName.setText( "" );
			tfURL.setText( "" );
		}
	}
	/**	Dh	31.07.2023
	 * 
	 */
	protected void updateList() {
		NameElement vCurNameElement, vCurSel;
		ArrayList<NameElement> vNewList = new ArrayList<NameElement>();
		vCurSel = lvObjects.getSelectionModel().getSelectedItem();
		
		for (Integer vHostID : showManager.getHostIDList()) {
			try {
				vCurNameElement = new NameElement(vHostID, showManager.getHostName(vHostID)); 
				
				vNewList.add(vCurNameElement);
				
				if ((vCurSel != null) &&  (vCurSel.getId() == vCurNameElement.getId())) vCurSel = vCurNameElement;
			}catch(Exception ex) {LogManager.handleException(ex);}
		}
		
		liObjects.setAll(vNewList);
		
		if (vCurSel != null) lvObjects.getSelectionModel().select(vCurSel);
	}
	
//--------------------------------------------------------------------------------------------------------
	
	/**	Dh	31.07.2023
	 * 
	 */
	@FXML
	public void progress() {
		NameElement vCurSel = lvObjects.getSelectionModel().getSelectedItem();
		String vHostName, vHostURL;
		
		if (vCurSel == null) {
			childStage = new HostEditorStage(this, showManager, -1);
			setDisabled();
		}else if (isInputValid()) {
			try { 
				vHostName = tfName.getText();
				vHostURL  = tfURL.getText();
				
				showManager.setHostName(editObjectID, vHostName);
				showManager.setHostURL(editObjectID, vHostURL);
				vCurSel.setName( vHostName );
				
				LogManager.handleMessage("Host erfolgreich geändert!");
			} catch(Exception ex) {LogManager.handleException(ex);}
		} else LogManager.handleMessage("Eingaben nicht gültig!\nHost nicht geändert.");
	}
	
	/**	Dh	31.07.2023
	 * 
	 */
	@FXML
	protected void removeObject() {
		NameElement vCurSel = lvObjects.getSelectionModel().getSelectedItem();
		
		if (vCurSel != null) {
			try {
				liObjects.remove(vCurSel);
				lvObjects.getSelectionModel().clearSelection();
				
				showManager.removeShow( vCurSel.getId() );
			}catch(Exception ex) {LogManager.handleException(ex);}
		}else LogManager.handleMessage("Kein Host entfernt!\nEs ist kein Host ausgewählt.");
	}
	
//--------------------------------------------------------------------------------------------------------

	/**	Dh	31.07.2023
	 * 
	 */
	protected boolean isInputValid() {
		boolean vRet = false;
		
		if ((tfName.getText() != "") && (tfURL.getText() != "")) {
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
