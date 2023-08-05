/**	ShowManager v0.1	Dh	27.7.2023
 * 
 * 	gui.tableElements
 * 	BasicTableElement
 * 	  StringElement
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

public class NameElement extends BasicTableElement {
	protected String name;

	/**	Dh	27.07.2023
	 * 
	 * @param pID
	 * @param pText
	 */
	public NameElement(int pID, String pName) {
		super(pID);
			
		try {
			setName(pName);
		} catch(Exception ex) {LogManager.handleException(ex);}
	}

//--------------------------------------------------------------------------------------------------------

	/**	Dh	27.07.2023
	 * 
	 * @return
	 */
	public String getName() {
		return name;
	}
	
	/**	Dh	27.07.2023
	 * 
	 * @param pText
	 * @throws Exception
	 */
	public void setName(String pName) throws Exception{
		if (pName != null) name = pName;
		else throw new Exception("02, sNa,StE");
	}
	
	//----------------------------------------------------------------------------------------------------

	/**	Dh	27.07.2023
	 * 
	 */
	@Override
	public String toString() {
		return name;
	}
	
}
