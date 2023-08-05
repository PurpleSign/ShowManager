/**	ShowManager v0.1	Dh	31.7.2023
 * 
 * 	gui.tableElements
 * 	BasicTableElement
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

package org.showmanager.gui.tableElements;

import org.showmanager.logic.manager.LogManager;

public abstract class BasicTableElement {
	protected int id;

	/**	Dh 27.07.2023
	 * 
	 * @param pID
	 */
	public BasicTableElement(int pID) {
		try {setId(pID);}
		catch(Exception ex) {LogManager.handleException(ex);}
	}

//--------------------------------------------------------------------------------------------------------

	/**	Dh	27.07.2023
	 * 
	 * @return
	 */
	public int getId() {
		return id;
	}
	
	/**	Dh	31.07.2023
	 * 
	 * @param pID
	 * @throws Exception
	 */
	public void setId(int pID) throws Exception {
		if (pID >= -1) id = pID;
		else throw new Exception("02; sId,BTE");
	}
	
	//----------------------------------------------------------------------------------------------------
	
	/**	Dh	27.07.2023
	 * 
	 */
	@Override
	public abstract String toString();
	
}
