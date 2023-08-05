/**	ShowManager v0.1	Dh	29.7.2023
 * 
 * 	logic.showentities.wrappers
 * 	BasicWrapper
 * 	  ShowListElementWrapper
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

import org.showmanager.logic.manager.LogManager;
import org.showmanager.logic.showentities.ShowList;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Table;

@Entity
@Table
public class ShowListElementWrapper extends BasicWrapper {
	@Id
	private int showListID;
	private int[] showIDs;
	
	/**	Dh	29.07.2023
	 * 
	 */
	public ShowListElementWrapper() {
		showListID = -1;
		showIDs = null;
	}
	/**	Dh	29.07.2023
	 * 
	 * @param pShowList
	 */
	public ShowListElementWrapper(ShowList pShowList) {
		if (pShowList != null) {
			showListID = pShowList.getId();
			try{setIDsPerList(pShowList.getShowList());}
			catch(Exception ex) {LogManager.handleException(ex);}	
		} else {
			showListID = -1;
			showIDs = new int[] {};
			
			LogManager.handleException(new Exception("04; SLEW"));
		}
	}

//----------------------------------------------------------------------------------------------------
	
	/**	Dh	29.07.2023
	 * 
	 * @return
	 */
	public int getShowListID() {
		return showListID;
	}
	/**	Dh	29.07.2023
	 * 
	 * @return
	 */
	public int[] getShowIDs() {
		return showIDs;
	}
	
	/**	Dh	29.07.2023
	 * 
	 */
	public ArrayList<Integer> getIDList() {
		return convertArrayToList(showIDs);
	}
	
	//------------------------------------------------------------------------------------------------
	
	/**	Dh	29.07.2023
	 * 
	 * @param pShowListID
	 * @throws Exception
	 */
	public void setShowListID(int pShowListID) throws Exception {
		if (pShowListID >= 0) showListID = pShowListID;
		else throw new Exception("02; sSLID,SLEW");
	}
	/**	Dh	29.07.2023
	 * 
	 * @param pShowIDs
	 */
	public void setShowIDs(int[] pShowIDs){
		showIDs = pShowIDs;
	}
	
	/**	Dh	29.07.2023
	 * 
	 */
	public void setIDsPerList(ArrayList<Integer> pIDList) throws Exception {
		if (pIDList != null) showIDs = convertListToArray(pIDList);
		else throw new Exception("04; sIDpL,SLEW");
	}
	
}
