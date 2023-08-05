/**	ShowManager v0.1	Dh	02.08.2023
 * 
 * 	gui.controller.editors
 * 	BasicController
 * 	  AbstractParentController
 * 		BasicEditorController
 * 		  AbstractSettingController
 * 			SettingEditorController
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

import java.io.File;
import java.util.ArrayList;

import org.showmanager.MainManager;
import org.showmanager.gui.controller.AbstractParentController;
import org.showmanager.gui.stages.editors.DatabaseConnectionEditorStage;
import org.showmanager.gui.stages.editors.adding.HostEditorStage;
import org.showmanager.gui.tableElements.NameElement;
import org.showmanager.logic.manager.LogManager;
import org.showmanager.logic.manager.SettingManager;
import org.showmanager.logic.manager.ShowManager;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

public class SettingEditorController extends AbstractSettingEditorController {
	private int hostID;
	private ShowManager showManager;
	
	@FXML
	private TextField tfBrowserName, tfBrowserPath, tfHostName, tfHostURL;
	@FXML
	private Button btBrowserPath, btDatabaseConnection, btHostProgress, btRemove;
	@FXML
	private Button btProgress, btBack; 
	@FXML
	private CheckBox cbRatings, cbGenres, cbHosts, cbURLs;
	
	@FXML
	private ListView<NameElement> lvHosts;
	
	private ObservableList<NameElement> liHosts;
	
	/**	Dh	02.08.2023
	 * 
	 */
	public SettingEditorController() {
		super();
		
		hostID = -1;
		showManager = null;
		
		liHosts = null;
	}

	//----------------------------------------------------------------------------------------------------
	
	/**	Dh	02.08.2023
	 * 
	 * @param pParentController
	 * @param pSettingManager
	 * @param pShowManager
	 * @throws Exception
	 */
	public void setUp(AbstractParentController pParentController, SettingManager pSettingManager, ShowManager pShowManager) throws Exception{
		super.setUp(pParentController, pSettingManager);
		
		showManager = pShowManager;
		
		tfBrowserName.setText( settingManager.getBrowserName() );
		tfBrowserPath.setText( settingManager.getBrowserPath() );
		
		cbRatings.selectedProperty().setValue( settingManager.isRatingEnabled() );
		cbGenres.selectedProperty().setValue( settingManager.isGenreEnabled() );
		cbHosts.selectedProperty().setValue( settingManager.isHostEnabled() );
		cbURLs.selectedProperty().setValue( settingManager.isURLEnabled() );
		
		liHosts = FXCollections.observableArrayList();
		
		lvHosts.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		lvHosts.getSelectionModel().selectedItemProperty().addListener((pObs, pOldValue, pNewValue) -> {
			if (pNewValue == null) this.hostID = -1;
			else this.hostID = pNewValue.getId();
			
			setEnabled();
			updateButtons();
			updateObjectInformation();
		});
		
		updateAll();
		lvHosts.setItems(liHosts);
		
		setEnabled();
	}
	
//--------------------------------------------------------------------------------------------------------

	/**	Dh	01.08.2023
	 * 
	 * @param pEnable
	 */
	private void setEnabledTextFields(boolean pEnable) {
		tfBrowserName.setDisable(!pEnable);
		tfBrowserPath.setDisable(!pEnable);
		
		tfHostName.setDisable(!pEnable);
		tfHostURL.setDisable(!pEnable);
	}
	/**	Dh	01.08.2023
	 * 
	 * @param pEnable
	 */
	private void setEnabledButtons(boolean pEnable) {
		btBrowserPath.setDisable(!pEnable);
		
		btDatabaseConnection.setDisable(!pEnable);
		
		btHostProgress.setDisable(!pEnable);
		btRemove.setDisable( (lvHosts.getSelectionModel().getSelectedItem() == null ? true : !pEnable) );
		
		btProgress.setDisable(!pEnable);
		btBack.setDisable(!pEnable);
	}
	/**	Dh	01.08.2023
	 * 
	 * @param pEnable
	 */
	private void setEnabledCheckBoxes(boolean pEnable) {
		cbRatings.setDisable(!pEnable);
		cbGenres.setDisable(!pEnable);
		cbHosts.setDisable(!pEnable);
		cbURLs.setDisable(!pEnable);
	}
	
	/**	Dh	01.08.2023
	 * 
	 */
	protected void setEnabled(boolean pEnable) {
		setEnabledTextFields(pEnable);
		setEnabledButtons(pEnable);
		setEnabledCheckBoxes(pEnable);
	}

	//------------------------------------------------------------------------------------------------
	
	/**	Dh	02.08.2023
	 * 
	 */
	private void updateObjectInformation() {
		NameElement vSelObject = lvHosts.getSelectionModel().getSelectedItem();
		
		if (vSelObject != null) {
			try {
				tfHostName.setText( vSelObject.getName() );
				tfHostURL.setText( showManager.getHostURL( hostID ) );
			}catch(Exception ex) {LogManager.handleException(ex);}
		}
		else {
			tfHostName.setText( "" );
			tfHostURL.setText( "" );
		}
	}
	/**	Dh	02.08.2023
	 * 
	 */
	private void updateList() {
		NameElement vCurNameElement, vCurSel;
		ArrayList<NameElement> vNewList = new ArrayList<NameElement>();
		vCurSel = lvHosts.getSelectionModel().getSelectedItem();
		
		for (Integer vHostID : showManager.getHostIDList()) {
			try {
				vCurNameElement = new NameElement(vHostID, showManager.getHostName(vHostID)); 
				
				vNewList.add(vCurNameElement);
				
				if ((vCurSel != null) &&  (vCurSel.getId() == vCurNameElement.getId())) vCurSel = vCurNameElement;
			}catch(Exception ex) {LogManager.handleException(ex);}
		}
		
		liHosts.setAll(vNewList);
		
		if (vCurSel != null) lvHosts.getSelectionModel().select(vCurSel);
	}
	/**	Dh	02.08.2023
	 * 
	 */
	private void updateButtons() {
		if (lvHosts.getSelectionModel().getSelectedItem() == null) btHostProgress.setText("Neuer Host");
		else btHostProgress.setText("Anwenden");
	}
	
	/**	Dh	02.08.2023
	 * 
	 */
	private void updateAll() {
		updateObjectInformation();
		updateList();
		updateButtons();
	}
	
	//------------------------------------------------------------------------------------------------
	
	/**	Dh	02.08.2023
	 * 
	 */
	public void closeChildStage() {
		super.closeChildStage();
		
		updateAll();
	}
	
//--------------------------------------------------------------------------------------------------------
	
	/**	Dh	01.08.2023
	 * 
	 */
	@FXML
	public void chooseBrowserFile() {
		File vFile;
		FileChooser vFileChooser = new FileChooser();
		
		vFileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
		vFileChooser.setTitle("Wähle Browser");
		
		setDisabled();
		vFile = vFileChooser.showOpenDialog(MainManager.getPrimaryStage());
		setEnabled();
		
		if (vFile != null) {
			if (isFileValid(vFile.getAbsolutePath())) tfBrowserPath.setText(vFile.getAbsolutePath());
			else LogManager.handleMessage("Ungültige Datei ausgewählt!");
		}
	}
	
	/**	Dh	01.08.2023
	 * 
	 */
	@FXML
	public void openDatabaseConnection() {
		childStage = new DatabaseConnectionEditorStage(this, settingManager);
		setDisabled();
	}
	
	//-----
	
	/**	Dh	02.08.2023
	 * 
	 */
	@FXML
	public void hostProgress() {
		NameElement vCurSel = lvHosts.getSelectionModel().getSelectedItem();
		String vHostName, vHostURL;
		
		if (vCurSel == null) {
			childStage = new HostEditorStage(this, showManager, -1);
			setDisabled();
		}else if (isInputValid()) {
			try { 
				vHostName = tfHostName.getText();
				vHostURL  = tfHostURL.getText();
				
				showManager.setHostName(hostID, vHostName);
				showManager.setHostURL(hostID, vHostURL);
				vCurSel.setName( vHostName );
				
				LogManager.handleMessage("Host erfolgreich geändert!");
			} catch(Exception ex) {LogManager.handleException(ex);}
		} else LogManager.handleMessage("Eingaben nicht gültig!\nHost nicht geändert.");
	}
	/**	Dh	02.08.2023
	 * 
	 */
	@FXML
	public void removeHost() {
		NameElement vCurSel = lvHosts.getSelectionModel().getSelectedItem();
		
		if (vCurSel != null) {
			try {
				liHosts.remove(vCurSel);
				lvHosts.getSelectionModel().clearSelection();
				
				showManager.removeHost( vCurSel.getId() );
			}catch(Exception ex) {LogManager.handleException(ex);}
		}else LogManager.handleMessage("Keinen Host entfernt!\nEs ist kein Host ausgewählt.");
	}
	
	//-----
	
	/**	Dh	01.08.2023
	 * 
	 */
	@FXML
	public void progress() {
		if (isInputValid()) {
			try {
				settingManager.setBrowserName( tfBrowserName.getText() );
				settingManager.setBrowserPath( tfBrowserPath.getText() );
				
				settingManager.setRatingEnabled( cbRatings.isSelected() );
				settingManager.setGenreEnabled( cbGenres.isSelected() );
				settingManager.setHostEnabled( cbHosts.isSelected() );
				settingManager.setURLEnabled( cbURLs.isSelected() );
				
				parentController.closeChildStage();
			}catch(Exception ex) {LogManager.handleException(ex);}
		} else {
			LogManager.handleMessage("Browser Eingaben sind nicht korrekt!");
		}
	}
	
//--------------------------------------------------------------------------------------------------------
	
	/**	Dh	01.08.2023
	 * 
	 * @return
	 */
	private boolean isBrowserNameValid() {
		return (tfBrowserName.getText() != "");
	}
	/**	Dh	01.08.2023
	 * 
	 * @return
	 */
	private boolean isBrowsPathValid() {
		return isFileValid(tfBrowserPath.getText());
	}
	
	/**	Dh	01.08.2023
	 * 
	 * @return
	 */
	private boolean isInputValid() {
		return (isBrowserNameValid() && isBrowsPathValid());
	}

	//----------------------------------------------------------------------------------------------------
	
	/**	Dh	01.08.2023
	 * 
	 * @param pFilePath
	 * @return
	 */
	private boolean isFileValid(String pFilePath) {
		boolean vRet = false;
		File vFile;
		
		if (pFilePath != null) {
			vFile = new File(pFilePath);
			
			vRet =  ((vFile.exists()) && (vFile.canExecute()));
		}
		
		return vRet;
	}
	
}
