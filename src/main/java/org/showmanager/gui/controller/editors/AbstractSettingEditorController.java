/**	ShowManager v0.1	Dh	30.7.2023
 * 
 * 	gui.controller.editors
 * 	BasicController
 * 	  AbstractParentController
 * 		BasicEditorController
 * 		  AbstractSettingEditorController
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
import org.showmanager.logic.manager.SettingManager;

public abstract class AbstractSettingEditorController extends BasicEditorController {
	protected SettingManager settingManager;
	
	/**	Dh	30.07.2023
	 * 
	 */
	public AbstractSettingEditorController() {
		settingManager = null;
	}

	//----------------------------------------------------------------------------------------------------
	
	/**	Dh	30.07.2023
	 * 
	 * @param pParentController
	 * @param pSettingManager
	 * @throws Exception
	 */
	public void setUp(AbstractParentController pParentController, SettingManager pSettingManager) throws Exception{
		super.setUp(pParentController);
		
		settingManager = pSettingManager;
		
	}
	
}
