/**	ShowManager v0.1	Dh	01.08.2023
 * 
 * 	gui.controller.editors
 * 	BasicController
 * 	  AbstractParentController
 * 		BasicEditorController
 * 		  BasicAddingEditorController
 * 		    ShowEditorController
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
import org.showmanager.gui.stages.editors.adding.GenreEditorStage;
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

public class ShowEditorController extends BasicAddingEditorController {
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
	public ShowEditorController() {
		liGenres = null;
		liHosts = null;
	}

	//------------------------------------------------------------------------------------------------
	
	/**	Dh	01.08.2023
	 * 
	 */
	public void setUp(AbstractParentController pParentController, ShowManager pShowManager, int pEditObjectID) throws Exception{		
		int vHostID;
		String vHostURL = "";
		ArrayList<Integer> vGenreIDs;
		super.setUp(pParentController, pShowManager, pEditObjectID);
		
		liGenres = FXCollections.observableArrayList();
		liHosts = FXCollections.observableArrayList();
		
		lvGenres.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		
		spRating.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10, 0));
		
		updateHosts();
		updateGenres();
		
		lvGenres.setItems(liGenres);
		cbHosts.setItems(liHosts);
		
		if (editObjectID != -1) {
			tfName.setText( showManager.getShowName(pEditObjectID) );
			
			spRating.getValueFactory().setValue( showManager.getShowRating(pEditObjectID) );
			
			vHostID = showManager.getShowHostID(editObjectID);
			for (NameElement vHostElement : liHosts) {
				if (vHostElement.getId() == vHostID) cbHosts.getSelectionModel().select(vHostElement);
			}
			if (vHostID != -1) vHostURL = showManager.getHostURL( vHostID );
			tfURL.setText( showManager.getShowURL(pEditObjectID).replace(vHostURL, "") );
			
			vGenreIDs = showManager.getShowGenreIDs(editObjectID);
			lvGenres.getSelectionModel().clearSelection();
			for (NameElement vGenreElement : liGenres) {
				if (vGenreIDs.contains(Integer.valueOf( vGenreElement.getId() ))) lvGenres.getSelectionModel().select(vGenreElement);
			}
		}
	}
		
//--------------------------------------------------------------------------------------------------------
	
	/**	Dh	31.07.2023
	 * 
	 */
	protected void setEnabledObjectInformations(boolean pEnable) {
		tfName.setDisable( !pEnable );
		tfURL.setDisable( !pEnable );
		
		cbHosts.setDisable( !pEnable );
		spRating.setDisable( !pEnable );
		
		btNewGenre.setDisable( !pEnable );
		lvGenres.setDisable( !pEnable );
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
		ArrayList<Integer> vCurSels = null;
		
		try { vCurSels = convertNameElementListToIDList( lvGenres.getSelectionModel().getSelectedItems() ); }
		catch (Exception ex) {LogManager.handleException(ex);}
		
		for (Integer vGenreID : showManager.getGenreIDs()) {
			try {
				vCurNameElement = new NameElement(vGenreID, showManager.getGenreName(vGenreID)); 
				
				vNewList.add(vCurNameElement);
			}catch(Exception ex) {LogManager.handleException(ex);}
		}
		
		liGenres.setAll(vNewList);
		
		lvGenres.getSelectionModel().clearSelection();
		for (NameElement vTempGenre : vNewList) {
			if (vCurSels.contains( vTempGenre.getId() )) lvGenres.getSelectionModel().select(vTempGenre);
		}
	}
	
	//------------------------------------------------------------------------------------------------
	
	/**	Dh	31.07.2023
	 * 
	 */
	public void closeChildStage() {
		super.closeChildStage();
		updateGenres();
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
		NameElement vCurHostSel;
		ObservableList<NameElement> vCurGenreSel;
		String vShowName;
		
		if (isInputValid()) {
			try {
				vShowName = tfName.getText();
				vCurHostSel = cbHosts.getValue();
				vCurGenreSel = lvGenres.getSelectionModel().getSelectedItems();
				
				if (editObjectID != -1) {
					showManager.setShowName(editObjectID, vShowName);
					showManager.setShowURL(editObjectID, tfURL.getText());
					showManager.setShowRating(editObjectID, spRating.getValue());
					
					if (vCurHostSel != null) showManager.setShowHostID(editObjectID, vCurHostSel.getId());
					else 					 showManager.setShowHostID(editObjectID, -1);
					
					showManager.setShowGenreIDs(editObjectID,  convertNameElementListToIDList(vCurGenreSel) );
				}else {
					showManager.addShow(vShowName, ( vCurHostSel != null ? vCurHostSel.getId() : -1), tfURL.getText(),
											spRating.getValue(), convertNameElementListToIDList(vCurGenreSel));
				}
				
				parentController.closeChildStage();
			} catch(Exception ex) {LogManager.handleException(ex);}
		} else LogManager.handleMessage("Eingaben nicht gültig!");
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
