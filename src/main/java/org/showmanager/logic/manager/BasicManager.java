/**	ShowManager v0.1	Dh	31.7.2023
 * 
 * 	logic.manager
 * 	BasicManager
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

package org.showmanager.logic.manager;

import java.util.ArrayList;

import org.showmanager.logic.showentities.BasicShowentity;

public abstract class BasicManager {
	protected DatabaseManager databaseManager;
	protected BrowserManager browserManager;
	
	/**	Dh	27.07.2023
	 * 
	 * @param pDatabaseManager
	 */
	public BasicManager(DatabaseManager pDatabaseManager) {
		if (pDatabaseManager != null) databaseManager = pDatabaseManager;
		else LogManager.handleException(new Exception("04; BMa"));
	}

//----------------------------------------------------------------------------------------------------
	
	public abstract void saveAll() throws Exception;
	
//----------------------------------------------------------------------------------------------------

	/**	Dh	31.07.2023
	 * 
	 * @param pTotalElementList
	 * @return
	 */
	protected int dertermineID(ArrayList<? extends BasicShowentity> pTotalElementList) {
		int vRet = -1;
		
		if (!pTotalElementList.isEmpty()) {
			for (int i=0; (i < pTotalElementList.size()); i++) {
				if (pTotalElementList.get(i).getId() >= vRet) vRet = pTotalElementList.get(i).getId();
			}
			
			vRet ++;
		} else vRet = 1;
		
		return vRet;
	}
	/**	Dh	27.07.2023
	 * 
	 * @param pList
	 */
	protected <T extends BasicShowentity> void sortListByID(ArrayList<T> pList) {
		int vPreID, vCurID;
		BasicShowentity vCur, vTemp;
		
		if (!pList.isEmpty()) {			
			vPreID = -1;
			
			for (int i=0; i<pList.size(); i++) {
				vCur = (BasicShowentity)pList.get(i);
				vCurID = vCur.getId();
				
				if (vPreID > vCurID) {
					vTemp = vCur;
					vPreID = vCurID;
					
					for (int u=i; u >= 0 ; u--) {
						vCur = (BasicShowentity)pList.get(u);
						vCurID = vCur.getId();
						
						if (vPreID > vCurID) {
							pList.set(u+1, (T)pList.get(u));
							pList.set(u, (T)vTemp);
						}else u = -1;
					}
				}
				
				vPreID = ((BasicShowentity)pList.get(i)).getId();
			}
		}
	}

	
}
