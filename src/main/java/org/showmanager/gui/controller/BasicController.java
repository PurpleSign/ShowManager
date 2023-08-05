/**	ShowManager v0.1	Dh	30.7.2023
 * 
 * 	gui.controller
 * 	BasicController
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

package org.showmanager.gui.controller;

public abstract class BasicController {
	protected AbstractParentController parentController;
	
	/**	Dh	29.07.2023
	 * 
	 */
	public BasicController() {
		super();
		
		parentController = null;
	}

	//----------------------------------------------------------------------------------------------------
	
	/**	Dh	29.07.2023
	 * 
	 * @param pParentController
	 * @throws Exception
	 */
	public void setUp(AbstractParentController pParentController) throws Exception{
		parentController = pParentController;
	}
	
//--------------------------------------------------------------------------------------------------------
	
	/**	Dh	27.07.2023
	 * 
	 */
	public void setDisabled() {
		setEnabled(false);
	}
	/**	Dh	27.07.2023
	 * 
	 */
	public void setEnabled() {
		setEnabled(true);
	}
	//-----
	/**	Dh	27.07.2023
	 * 
	 * @param pEnable
	 */
	protected abstract void setEnabled(boolean pEnable); 
		
}
