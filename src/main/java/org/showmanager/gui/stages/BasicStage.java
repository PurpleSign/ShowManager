/**	ShowManager v0.1	Dh	29.7.2023
 * 
 * 	gui.stages
 * 	BasicStage
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

package org.showmanager.gui.stages;

import org.showmanager.gui.controller.AbstractParentController;
import org.showmanager.logic.manager.LogManager;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public abstract class BasicStage extends Stage {
	protected String zSceneFileName, zTitle;
	
	protected Parent root;
	protected Scene scene;
	protected AbstractParentController controller;
	
	/**	Dh	29.07.2023
	 * 
	 * @param pSceneFileName
	 * @param pTitle
	 */
	public BasicStage(String pSceneFileName, String pTitle) {
		super();
		
		zSceneFileName = pSceneFileName;
		zTitle = pTitle;
		
		this.setOnCloseRequest(event -> {controller.back();});

		init();
	}
	
//--------------------------------------------------------------------------------------------------------

	/**	Dh	27.07.2023
	 * 
	 */
	private void init() {
		FXMLLoader fxmlloader;
		try {
			fxmlloader = new FXMLLoader(getClass().getResource("/"+zSceneFileName));
			root = fxmlloader.load();
			scene = new Scene(root);
			controller = fxmlloader.getController();
			
			this.setScene(scene);
			this.sizeToScene();
			this.setResizable(false);
			
			this.setTitle(zTitle);
			this.initModality(Modality.WINDOW_MODAL);
			this.setIconified(false);
			
			this.show();
		} catch(Exception ex) {LogManager.handleException(ex);}
	}
	
//--------------------------------------------------------------------------------------------------------

	/**	Dh	29.07.2023
	 * 
	 * @return
	 */
	public AbstractParentController getController() {
		return controller;
	}
	
}
