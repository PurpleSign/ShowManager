/**	ShowManager v0.0	Dh	28.7.2020
 * 
 * 	pLogic
 * 	  ShowManagerElemet
 * 		ShowListElementWrapper
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
package pLogic;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import pDataStructures.List;

@Entity
@Table
public class ShowListElementWrapper {
	@Id
	private int showListID;
	private int[] showIDs;
	
	/**	Dh	27.7.2020
	 * 
	 */
	public ShowListElementWrapper() {
		showListID = -1;
		showIDs = new int[] {};
	}
	/**	Dh	27.7.2020
	 * 
	 * @param pShowList
	 */
	public ShowListElementWrapper(ShowList pShowList) {
		if (pShowList != null) {
			showListID = pShowList.getId();
			showIDs = convertListToArray(pShowList.getShowList());
		} else {
			showListID = -1;
			showIDs = new int[] {};
			
			MainManager.handleException(new Exception("04; ShLiElWra"));
		}
	}
		
//----------------------------------------------------------------------------------------------------
	
	/**	Dh	27.7.2020
	 * 
	 * @return
	 */
	public int getShowListID() {
		return showListID;
	}
	
	/**	Dh	27.7.2020
	 * 
	 * @return
	 */
	public int[] getShowIDs() {
		return showIDs;
	}
	
	/**	Dh	28.7.2020
	 * 
	 * @return
	 */
	protected List getShowIDList() {
		List vRet = new List();
		
		if (showIDs != null) {
			if (showIDs.length >= 1) {
				for (int i=0; i < showIDs.length; i++) {
					vRet.append(showIDs[i]);
				}
			}
		}
		
		return vRet;
	}
	
	//------------------------------------------------------------------------------------------------
	
	/**	Dh	27.7.2020
	 * 
	 * @param pShowList
	 * @throws Exception
	 */
	public void setShowList(int pShowListID) throws Exception{
		if (pShowListID >= 0) showListID = pShowListID;
		else throw new Exception("02; ShLiElWra,sSL");
	}
	
	/**	Dh	27.7.2020
	 * 
	 * @param pShowIDs
	 */
	public void setShowIDs(int[] pShowIDs){
		showIDs = pShowIDs;
	}
	/**	Dh	28.7.2020
	 * 
	 * @param pShowIDList
	 * @throws Exception
	 */
	protected void setShowIDsPerList(List pShowIDList) throws Exception{
		if (pShowIDList != null) showIDs = convertListToArray(pShowIDList);
		else throw new Exception("04; ShoLiElWra,sSIDpL");
	}
	
//----------------------------------------------------------------------------------------------------
	
	/**	Dh	27.7.2020
	 * 
	 * @param pList
	 * @return
	 */
	private int[] convertListToArray(List pList) {
		int[] vRet = null;
		
		if (pList != null) {
			if (!pList.isEmpty()) {
				vRet = new int[pList.getContentNumber()];
				pList.toFirst();
				
				for (int i=0; i < vRet.length; i++) {
					vRet[i] = (int)pList.getCurrent();
					
					pList.next();
				}
			} else vRet = new int[] {};
		}
		
		return vRet;
	}
}
