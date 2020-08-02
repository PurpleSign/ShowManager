/**	ShowManager v0.0	Dh	23.7.2020
 * 
 * 	pGUI
 * 	  ModFrame
 * 		EditingFrame
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
package pGUI;

import java.awt.EventQueue;

import javax.swing.JFrame;

import pLogic.LogicManager;

public abstract class EditingFrame extends ModFrame {
	protected JListModel rEditingList;

	/**	Dh	23.7.2020
	 * 
	 * @param pGUIManager
	 * @param pManager
	 */
 	public EditingFrame(GUIManager pGUIManager, LogicManager pManager, int pX, int pY) {
		super(pGUIManager, pManager, pX, pY);
	}

	//------------------------------------------------------------------------------------------------
	
 	/**	Dh	22.7.2020
 	 * 
 	 */
	protected void initModels() {
		rEditingList = new JListModel();
	}
	
	
//----------------------------------------------------------------------------------------------------
	
	protected abstract void removeElement();
}
