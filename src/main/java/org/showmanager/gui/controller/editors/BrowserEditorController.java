/**	ShowManager v0.1	Dh	30.7.2023
 * 
 * 	gui.controller.editors
 * 	BasicController
 * 	  AbstractParentController
 * 		BasicEditorController
 * 		  AbstractSettingController
 * 			BrowserEditorController
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

import org.showmanager.MainManager;
import org.showmanager.gui.controller.AbstractParentController;
import org.showmanager.logic.manager.LogManager;
import org.showmanager.logic.manager.SettingManager;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

public class BrowserEditorController extends AbstractSettingEditorController {	
	@FXML
	private TextField tfBrowserName, tfBrowserPath;
	@FXML
	private Button btBrowserPath, btProgress, btBack;
	
	/**	Dh	30.07.2023
	 * 
	 */
	public BrowserEditorController() {
		super();
	}

	//----------------------------------------------------------------------------------------------------
	
	/**	Dh	30.07.2023
	 * 
	 */
	public void setUp(AbstractParentController pParentController, SettingManager pSettingManager) throws Exception{
		super.setUp(pParentController, pSettingManager);
		
		tfBrowserName.setText( super.settingManager.getBrowserName() );
		tfBrowserPath.setText( super.settingManager.getBrowserPath() );
	}
	
//--------------------------------------------------------------------------------------------------------
	
	/**	Dh	30.07.2023
	 * 
	 * @param pEnable
	 */
	private void setEnabledTextFields(boolean pEnable) {
		tfBrowserName.setDisable(!pEnable);
		tfBrowserPath.setDisable(!pEnable);
	}
	/**	Dh	30.07.2023
	 * 
	 * @param pEnable
	 */
	private void setEnabledButtons(boolean pEnable) {
		btBrowserPath.setDisable(!pEnable);
		
		btProgress.setDisable(!pEnable);
		btBack.setDisable(!pEnable);
	}
	
	/**	Dh	30.07.2023
	 * 
	 */
	protected void setEnabled(boolean pEnable) {
		setEnabledTextFields(pEnable);
		setEnabledButtons(pEnable);
	}
	
//--------------------------------------------------------------------------------------------------------
	
	/**	Dh	30.07.2023
	 * 
	 */
	@FXML
	public void progress() {
		if (isInputValid()) {
			try {
				super.settingManager.setBrowserName( tfBrowserName.getText() );
				super.settingManager.setBrowserPath( tfBrowserPath.getText() );
				
				super.parentController.closeChildStage();
			}catch(Exception ex) {LogManager.handleException(ex);}
		} else {
			LogManager.handleMessage("Eingaben sind nicht korrekt!");
		}
	}

	/**	Dh	30.07.2023
	 * 
	 */
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
	
//--------------------------------------------------------------------------------------------------------

	/**	Dh	30.07.2023
	 * 
	 * @return
	 */
	private boolean isBrowserNameValid() {
		return (tfBrowserName.getText() != "");
	}
	/**	Dh	30.07.2023
	 * 
	 * @return
	 */
	private boolean isBrowsPathValid() {
		return isFileValid(tfBrowserPath.getText());
	}
	
	/**	Dh	30.07.2023
	 * 
	 * @return
	 */
	private boolean isInputValid() {
		return (isBrowserNameValid() && isBrowsPathValid());
	}
	
	//----------------------------------------------------------------------------------------------------
	
	/**	Dh	30.07.2023
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
