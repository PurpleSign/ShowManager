/**	ShowManager v0.1	Dh	01.08.2023
 * 
 * 	gui.controller.editors
 * 	BasicController
 * 	  BasicParentController
 * 		BasicAddingEditorController
 * 		  BasicMultipleEditorController
 * 			MultipleShowEditorController
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
import org.showmanager.gui.stages.editors.adding.HostEditorStage;
import org.showmanager.gui.stages.editors.adding.ShowEditorStage;
import org.showmanager.gui.tableElements.NameElement;
import org.showmanager.logic.manager.LogManager;
import org.showmanager.logic.manager.ShowManager;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;

public class MultipleShowEditorController extends BasicMultipleEditorController {
	@FXML
	private TextField tfName, tfURL;
	@FXML
	private Button btNewGenre;
	
	@FXML
	private ChoiceBox<NameElement> cbHosts;
	@FXML
	private Spinner<Integer> spRating;
	@FXML
	private ListView<NameElement> lvGenres;
	
	private ObservableList<NameElement> liGenres, liHosts;
	
	/**	Dh	31.07.2023
	 * 
	 */
	public MultipleShowEditorController() {
		super();
		
		liGenres = null;
		liHosts = null;
		
		newObjectButtonText = "Neue Serie";
	}
	
	//------------------------------------------------------------------------------------------------

	/**	Dh	31.07.2023
	 * 
	 */
	public void setUp(AbstractParentController pParentController, ShowManager pShowManager) throws Exception{
		liGenres = FXCollections.observableArrayList();
		liHosts = FXCollections.observableArrayList();
		
		lvGenres.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		
		spRating.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10, 0));
		
		super.setUp(pParentController, pShowManager);
		
		updateHosts();
		
		lvGenres.setItems(liGenres);
		cbHosts.setItems(liHosts);
	}
	
//--------------------------------------------------------------------------------------------------------

	/**	Dh	31.07.2023
	 * 
	 */
	protected void setEnabledObjectInformations(boolean pEnable) {
		NameElement vCurSel = lvObjects.getSelectionModel().getSelectedItem();
		
		tfName.setDisable( ((vCurSel == null) ? true : !pEnable) );
		tfURL.setDisable( ((vCurSel == null) ? true : !pEnable) );
		
		cbHosts.setDisable( ((vCurSel == null) ? true : !pEnable) );
		spRating.setDisable( ((vCurSel == null) ? true : !pEnable) );
		
		btNewGenre.setDisable( ((vCurSel == null) ? true : !pEnable) );
		lvGenres.setDisable( ((vCurSel == null) ? true : !pEnable) );
	}
	
	//------------------------------------------------------------------------------------------------
	
	/**	Dh	31.07.2023
	 * 
	 */
	private void updateHosts() {
		NameElement vCurNameElement, vCurSel;
		ArrayList<NameElement> vNewList = new ArrayList<NameElement>();
		vCurSel = cbHosts.getSelectionModel().getSelectedItem();//lvObjects.getSelectionModel().getSelectedItem();
		
		vCurNameElement = new NameElement(-1, ""); 
		vNewList.add(vCurNameElement);
		if ((vCurSel != null) &&  (vCurSel.getId() == vCurNameElement.getId())) vCurSel = vCurNameElement;
		
		for (Integer vHostID : showManager.getHostIDList()) {
			try {
				vCurNameElement = new NameElement(vHostID, showManager.getHostName(vHostID)); 
				
				vNewList.add(vCurNameElement);
				
				if ((vCurSel != null) &&  (vCurSel.getId() == vCurNameElement.getId())) vCurSel = vCurNameElement;
			}catch(Exception ex) {LogManager.handleException(ex);}
		}
		
		liHosts.setAll(vNewList);
		
		if (vCurSel != null) cbHosts.getSelectionModel().select(vCurSel);
	}
	/**	Dh	31.07.2023
	 * 
	 */
	private void updateGenres() {
		NameElement vCurNameElement;
		ArrayList<NameElement> vNewList = new ArrayList<NameElement>();
		
		for (Integer vGenreID : showManager.getGenreIDs()) {
			try {
				vCurNameElement = new NameElement(vGenreID, showManager.getGenreName(vGenreID)); 
				
				vNewList.add(vCurNameElement);
			}catch(Exception ex) {LogManager.handleException(ex);}
		}
		
		liGenres.setAll(vNewList);
	}
	
	/**	Dh	01.08.2023
	 * 
	 */
	protected void updateObjectInformation() {
		int vHostID;
		String vHostURL = "";
		NameElement vSelObject = lvObjects.getSelectionModel().getSelectedItem();
		ArrayList<Integer> vGenreIDs;
		
		if (vSelObject != null) {
			try {
				tfName.setText( vSelObject.getName() );
				
				spRating.getValueFactory().setValue( showManager.getShowRating( editObjectID ) );
				
				vHostID = showManager.getShowHostID(editObjectID);
				for (NameElement vHostElement : liHosts) {
					if (vHostElement.getId() == vHostID) cbHosts.getSelectionModel().select(vHostElement);
				}
				if (vHostID != -1) vHostURL = showManager.getHostURL( vHostID );
				tfURL.setText( showManager.getShowURL(editObjectID).replace(vHostURL, "") );
				
				vGenreIDs = showManager.getShowGenreIDs(editObjectID);
				lvGenres.getSelectionModel().clearSelection();
				for (NameElement vGenreElement : liGenres) {
					if (vGenreIDs.contains(Integer.valueOf( vGenreElement.getId() ))) lvGenres.getSelectionModel().select(vGenreElement);
				}
			}catch(Exception ex) {LogManager.handleException(ex);}
		}
		else {
			tfName.setText( "" );
			tfURL.setText( "" );
			
			spRating.getValueFactory().setValue(0);
			cbHosts.getSelectionModel().select(0);
			
			lvGenres.getSelectionModel().clearSelection();
		}
	}
	
	/**	Dh	31.07.2023
	 * 
	 */
	protected void updateList() {
		NameElement vCurNameElement, vCurSel;
		ArrayList<NameElement> vNewList = new ArrayList<NameElement>();
		vCurSel = lvObjects.getSelectionModel().getSelectedItem();
		
		for (Integer vShowID : showManager.getShowIDs()) {
			try {
				vCurNameElement = new NameElement(vShowID, showManager.getShowName(vShowID)); 
				
				vNewList.add(vCurNameElement);
				
				if ((vCurSel != null) &&  (vCurSel.getId() == vCurNameElement.getId())) vCurSel = vCurNameElement;
			}catch(Exception ex) {LogManager.handleException(ex);}
		}
		
		liObjects.setAll(vNewList);
		
		if (vCurSel != null) lvObjects.getSelectionModel().select(vCurSel);
	}
	
	/**	Dh	31.07.2023
	 * 
	 */
	protected void updateAll() {
		updateGenres();
		super.updateAll();
	}
	
//--------------------------------------------------------------------------------------------------------
	
	/**	Dh	31.07.2023
	 * 
	 */
	@FXML
	public void startNewGenreEditor() {
		super.childStage = new GenreEditorStage(this, showManager, -1);
		setDisabled();
	}
	
	/**	Dh	31.07.2023
	 * 
	 */
	@FXML
	public void progress() {
		NameElement vCurSel, vCurHostSel;
		ObservableList<NameElement> vCurGenreSel;
		String vShowName;
		
		vCurSel = lvObjects.getSelectionModel().getSelectedItem();
		if (vCurSel == null) {
			childStage = new ShowEditorStage(this, showManager, -1);
			setDisabled();
		}else if (isInputValid()) {
			try { 
				vShowName = tfName.getText();
				vCurHostSel = cbHosts.getValue();
				vCurGenreSel = lvGenres.getSelectionModel().getSelectedItems();
				
				showManager.setShowName(editObjectID, vShowName);
				showManager.setShowURL(editObjectID, tfURL.getText());
				showManager.setShowRating(editObjectID, spRating.getValue());
				
				if (vCurHostSel != null) showManager.setShowHostID(editObjectID, vCurHostSel.getId());
				else 					 showManager.setShowHostID(editObjectID, -1);
				
				showManager.setShowGenreIDs(editObjectID, convertNameElementListToIDList(vCurGenreSel) );
				
				vCurSel.setName( vShowName );
				
				LogManager.handleMessage("Show erfolgreich geändert!");
			} catch(Exception ex) {LogManager.handleException(ex);}
		} else LogManager.handleMessage("Eingaben nicht gültig!\nShow nicht geändert.");
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
		}else LogManager.handleMessage("Keine Serie entfernt!\nEs ist keine Serie ausgewählt.");
	}
	
//--------------------------------------------------------------------------------------------------------

	/**	Dh	31.07.2023
	 * 
	 */
	protected boolean isInputValid() {
		boolean vRet = false;
		Integer vRating = spRating.getValue();
		
		if ((tfName.getText() != "") && (vRating != null) && (0 <= vRating) && (vRating <= 10)) {
			vRet = true;
			for (Integer vShowID : showManager.getShowIDs()) {
				try {	if (showManager.getShowName(vShowID).equals(tfName.getText()) 
						&& (vShowID != editObjectID)) vRet = false; }
				catch(Exception ex) {LogManager.handleException(ex);}
			}
		}
		
		return vRet;
	}
	
}
