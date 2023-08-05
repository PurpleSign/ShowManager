/**	ShowManager v0.1	Dh	29.7.2023
 * 
 * 	logic.showentities.wrappers
 * 	BasicWrapper
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

package org.showmanager.logic.showentities.wrappers;

import java.util.ArrayList;

import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Transient;

public abstract class BasicWrapper {
	
	/**	Dh	29.07.2023
	 * 
	 */
	public BasicWrapper() {
		
	}
		
//----------------------------------------------------------------------------------------------------
	
	/**	Dh	29.07.2023
	 * 
	 * @param pIDArray
	 * @return
	 */
	protected ArrayList<Integer> convertArrayToList(int[] pIDArray){
		ArrayList<Integer> vRet = new ArrayList<Integer>();
		
		if (pIDArray != null) {
			if (pIDArray.length >= 1) {
				for (int i=0; i < pIDArray.length; i++) {
					vRet.add(pIDArray[i]);
				}
			}
		}
		
		return vRet;
	}
	
	/**	Dh	27.07.2023
	 * 
	 * @param pList
	 * @return
	 */
	protected int[] convertListToArray(ArrayList<Integer> pList) {
		int[] vRet = null;
		
		if (pList != null) {
			if (!pList.isEmpty()) {
				vRet = new int[pList.size()];
				
				for (int i=0; i<pList.size(); i++) {
					vRet[i] = pList.get(i).intValue();
				}
			} else vRet = new int[] {};
		}
		
		return vRet;
	}
}
