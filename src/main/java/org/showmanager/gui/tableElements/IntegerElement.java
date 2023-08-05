/**	ShowManager v0.1	Dh	27.7.2023
 * 
 * 	gui.tableElements
 * 	BasicTableElement
 * 	  IntegerElement
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

public class IntegerElement extends BasicTableElement {
	private int integer;
	
	/**	Dh	27.07.2023
	 * 
	 * @param pID
	 * @param pInteger
	 */
	public IntegerElement(int pID, int pInteger) {
		super(pID);
		
		integer = pInteger;
	}

//--------------------------------------------------------------------------------------------------------
	
	/**	Dh	27.07.2023
	 * 
	 * @return
	 */
	public int getInteger() {
		return integer;
	}
	
	/**	Dh	27.07.2023
	 * 
	 * @param pInteger
	 */
	public void setInteger(int pInteger) {
		integer = pInteger;
	}
	
	//----------------------------------------------------------------------------------------------------

	/**	Dh	27.07.2023
	 * 
	 */
	public String toString() {
		return "" + integer;
	}
	
}
