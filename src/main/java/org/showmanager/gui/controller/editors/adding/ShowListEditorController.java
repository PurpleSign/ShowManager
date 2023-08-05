/**	ShowManager v0.1	Dh	01.08.2023
 * 
 * 	gui.controller.editors
 * 	BasicController
 * 	  AbstractParentController
 * 		BasicEditorController
 * 		  BasicAddingEditorController
 * 		    ShowListEditorController
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
import org.showmanager.gui.tableElements.NameElement;
import org.showmanager.logic.manager.LogManager;
import org.showmanager.logic.manager.ShowManager;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class ShowListEditorController extends BasicAddingEditorController {
	@FXML
	private TextField tfName;
	@FXML
	private Button btAddShow, btRemoveShow;
	
	@FXML
	private ListView<NameElement> lvChoosenShows, lvPotentialShows;
	
	private ObservableList<NameElement> liChoosenShows, liPotentialShows;
	
	
	/**	Dh	31.07.2023
	 * 
	 */
	public ShowListEditorController() {
		liChoosenShows = null;
		liPotentialShows = null;
	}

	//------------------------------------------------------------------------------------------------
	
	/**	Dh	01.08.2023
	 * 
	 */
	public void setUp(AbstractParentController pParentController, ShowManager pShowManager, int pEditObjectID) throws Exception{
		super.setUp(pParentController, pShowManager, pEditObjectID);
		
		if (pEditObjectID != -1) tfName.setText( pShowManager.getShowListName(pEditObjectID) );
		
		liChoosenShows = FXCollections.observableArrayList();
		liPotentialShows = FXCollections.observableArrayList();
		
		liPotentialShows.setAll( convertShowIDsToNameElements( showManager.getShowIDs() ));
		if (editObjectID != -1) {
			liChoosenShows.setAll( convertShowIDsToNameElements( showManager.getShowListShowIDs(pEditObjectID) ));
			
			for (NameElement vChoosenElement : liChoosenShows) {
				if (liPotentialShows.contains(vChoosenElement)) liPotentialShows.remove(vChoosenElement);
			}
		}
		
		lvChoosenShows.getSelectionModel().selectedItemProperty().addListener((pObs, pOldSel, pNewSel) -> {
			if ((pNewSel != null) && (lvPotentialShows.getSelectionModel().getSelectedItem() != null)) lvPotentialShows.getSelectionModel().clearSelection();
			
			setEnabledButtons(true);
		});
		lvPotentialShows.getSelectionModel().selectedItemProperty().addListener((pObs, pOldSel, pNewSel) -> {
			if ((pNewSel != null) && (lvChoosenShows.getSelectionModel().getSelectedItem() != null)) lvChoosenShows.getSelectionModel().clearSelection();
			
			setEnabledButtons(true);
		});
		
		lvChoosenShows.setItems(liChoosenShows);
		lvPotentialShows.setItems(liPotentialShows);
		
		setEnabled();
	}
	
//--------------------------------------------------------------------------------------------------------
	
	/**	Dh	30.07.2023
	 * 
	 * @param pEnable
	 */
	private void setEnabledButtons(boolean pEnable) {
		btAddShow.setDisable( (lvPotentialShows.getSelectionModel().getSelectedItem() == null ? true : !pEnable ) );
		btRemoveShow.setDisable( (lvChoosenShows.getSelectionModel().getSelectedItem() == null ? true : !pEnable ) );
	}
	/**	Dh	31.07.2023
	 * 
	 * @param pEnable
	 */
	private void setEnabledLists(boolean pEnable) {
		lvChoosenShows.setDisable(!pEnable);
		lvPotentialShows.setDisable(!pEnable);
	}
	
	/**	Dh	31.07.2023
	 * 
	 */
	protected void setEnabledObjectInformations(boolean pEnable) {
		setEnabledButtons(pEnable);
		setEnabledLists(pEnable);
	}
	
//--------------------------------------------------------------------------------------------------------
	
	/**	Dh	31.07.2023
	 * 
	 */
	@FXML
	private void addShow() {
		NameElement vPotSel = lvPotentialShows.getSelectionModel().getSelectedItem(); 
		
		if (vPotSel != null) {
			liChoosenShows.add(vPotSel);
			liPotentialShows.remove(vPotSel);
		}
	}
	/**	Dh	31.07.2023
	 * 
	 */
	@FXML
	private void removeShow() {
		NameElement vPotSel = lvChoosenShows.getSelectionModel().getSelectedItem(); 
		
		if (vPotSel != null) {
			liPotentialShows.add(vPotSel);
			liChoosenShows.remove(vPotSel);
		}
	}
	
	/**	Dh	31.07.2023
	 * 
	 */
	@FXML
	public void progress() {
		if (isInputValid()) {
			try {
				if (editObjectID == -1) showManager.addShowList(tfName.getText(), convertNameElementListToIDList(liChoosenShows));
				else {
					showManager.setShowListName(editObjectID, tfName.getText());
					showManager.setShowListShowIDs(editObjectID,  convertNameElementListToIDList(liChoosenShows) );
				}
				
				parentController.closeChildStage();
			}catch(Exception ex) {LogManager.handleException(ex);}
		}else LogManager.handleMessage("Ungültige Eingabe!");
	}

//--------------------------------------------------------------------------------------------------------
	
	/**	Dh	31.07.2023
	 * 
	 */
	protected boolean isInputValid() {
		boolean vRet = false;
		
		if (tfName.getText() != "") vRet = true;
		
		return vRet;
	}
	
//--------------------------------------------------------------------------------------------------------

	/**	Dh	31.07.2023
	 * 
	 * @param pShowIDs
	 * @return
	 * @throws Exception
	 */
	private ArrayList<NameElement> convertShowIDsToNameElements(ArrayList<Integer> pShowIDs) throws Exception{
		ArrayList<NameElement> vRet = new ArrayList<NameElement>();
		
		if ((pShowIDs != null && (!pShowIDs.isEmpty()))) {
			for (Integer vShowID : pShowIDs) {
				vRet.add(new NameElement(vShowID, showManager.getShowName(vShowID)));
			}
		}
		
		return vRet;
	}
	
}
