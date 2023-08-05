/**	ShowManager v0.1	Dh	31.7.2023
 * 
 * 	gui.controller.editors
 * 	BasicController
 * 	  AbstractParentController
 * 		BasicEditorController
 * 		  AbstractSettingController
 * 			DatabaseConnectionEditorController
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

import org.showmanager.gui.controller.AbstractParentController;
import org.showmanager.gui.controller.MainStageController;
import org.showmanager.logic.manager.LogManager;
import org.showmanager.logic.manager.SettingManager;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;

public class DatabaseConnectionEditorController extends AbstractSettingEditorController{	
	@FXML
	private TextField tfDBName, tfUser;
	@FXML
	private PasswordField tfPassword;
	@FXML
	private Button btConnect, btBack;
	
	/**	Dh	27.07.2023
	 * 
	 */
	public DatabaseConnectionEditorController() {
		super();
	}

	//----------------------------------------------------------------------------------------------------
	
	/**	Dh	30.07.2023
	 * 
	 * @param pParentController
	 * @param pSettingManager
	 * @throws Exception
	 */
	public void setUp(AbstractParentController pParentController, SettingManager pSettingManager) throws Exception{
		super.setUp(pParentController, pSettingManager);
		
		tfDBName.setText(settingManager.getDatabaseName());
		tfUser.setText(settingManager.getUserName());
		
		tfPassword.setOnKeyReleased(event -> {
			if (event.getCode() == KeyCode.ENTER) {
				this.progress();
				event.consume();
			}
		});
		
		setEnabled();
	}
	
//--------------------------------------------------------------------------------------------------------
	
	/**	Dh	27.07.2023
	 * 
	 * @param pEnable
	 */
	private void setEnabledTextFields(boolean pEnable) {
		tfDBName.setDisable(!pEnable);
		tfUser.setDisable(!pEnable);
		tfPassword.setDisable(!pEnable);
	}
	/**	Dh	27.07.2023
	 * 
	 * @param pEnable
	 */
	private void setEnabledButtons(boolean pEnable) {
		btConnect.setDisable(!pEnable);
		btBack.setDisable(!pEnable);
	}
	
	/**	Dh	27.07.2023
	 * 
	 */
	protected void setEnabled(boolean pEnable) {
		setEnabledTextFields(pEnable);
		setEnabledButtons(pEnable);
	}
	
//--------------------------------------------------------------------------------------------------------
	
	/**	Dh	31.07.2023
	 * 
	 */
	@FXML
	public void progress() {
		if (ping() == true) {
			try {
				super.settingManager.setDatabaseName(tfDBName.getText());
				super.settingManager.setUseName(tfUser.getText());
				super.settingManager.setPassword(tfPassword.getText());
				
				super.settingManager.connectToDatabase();
				
				if (parentController instanceof MainStageController) ((MainStageController)parentController).loadFromDatabase();
				super.parentController.closeChildStage();
			}catch(Exception ex) {LogManager.handleException(ex);}
		}
			
		tfPassword.clear();
	}
		
//--------------------------------------------------------------------------------------------------------

	/**	Dh	30.07.2023
	 * 
	 */
	private boolean ping() {
		boolean vRet = false;
		String vDatabase, vUser, vPassword;
		
		vDatabase = tfDBName.getText();
		vUser = tfUser.getText();
		vPassword = tfPassword.getText();
		
		if ((!vDatabase.equals("")) && (!vUser.equals("")) && (!vPassword.equals(""))) {
			try {
				vRet = super.settingManager.ping(vDatabase, vUser, vPassword);
				if (vRet == false) LogManager.handleMessage("Verbindung fehlgeschlagen!");//System.out.println("Verbdindung fehlgeschlagen!");//JOptionPane.showMessageDialog(null, "Verbdindung fehlgeschlagen!", "Verbdindung fehlgeschlagen!", JOptionPane.INFORMATION_MESSAGE);
			}catch(Exception ex) {LogManager.handleException(ex);}
		}
		
		return vRet;
	}
	
}
