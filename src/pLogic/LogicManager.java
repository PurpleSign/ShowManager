/**	ShowManager v0.0	Dh	22.7.2020
 * 
 * 	pLogic
 * 	  LogicManager
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

import pDataStructures.List;

public abstract class LogicManager {
	protected DatabaseInterface databaseInterface;
	protected BrowserOpener opener;
	
	/**	Dh	21.7.2020
	 * 
	 * @param pDatabaseInterface
	 */
	public LogicManager(DatabaseInterface pDatabaseInterface) {
		if (pDatabaseInterface != null) databaseInterface = pDatabaseInterface;
		else MainManager.handleException(new Exception("04; Man"));
	}
	
//----------------------------------------------------------------------------------------------------

	public abstract void saveAll() throws Exception;
	
//----------------------------------------------------------------------------------------------------
	
	/**	Dh	22.7.2020
	 * 
	 * @param pTotalElementList
	 * @return
	 */
	protected int dertermineID(List pTotalElementList) {
		int vRet = -1;
		
		if (!pTotalElementList.isEmpty()) {
			pTotalElementList.toFirst();
			
			for (int i=1; (i <= pTotalElementList.getContentNumber()) && (vRet == -1); i++) {
				if (((ShowManagerElement)pTotalElementList.getCurrent()).getId() != i) vRet = i;
				
				pTotalElementList.next();
			}
			
			if (vRet == -1) vRet = pTotalElementList.getContentNumber()+1;
		} else vRet = 1;
		
		return vRet;
	}
	
	//------------------------------------------------------------------------------------------------
	
	/**	Dh	30.7.2020
	 * 
	 * @param pList
	 */
	protected void sortListByID(List pList) {
		int vPreID, vCurID;
		ShowManagerElement vCur, vTemp;
		
		if (!pList.isEmpty()) {
			pList.toFirst();
			vPreID = -1;
			
			while(!pList.isEnd()) {
				vCur = (ShowManagerElement) pList.getCurrent();
				vCurID = vCur.getId();
				
				if (vPreID > vCurID) {
					vTemp = vCur;
					vPreID = vCurID;
					
					pList.toFirst();
					while(!pList.getCurrent().equals(vTemp)) {
						vCur = (ShowManagerElement) pList.getCurrent();
						vCurID = vCur.getId();
						
						if ((vPreID != -1) && (vCurID > vPreID)) {
							pList.insert(vTemp);
							vPreID = -1;
						}
						
						pList.next();
					}
					
					if (!pList.isLast()) {
						pList.remove();
						pList.prior();
					} else pList.remove();
				}
				
				vPreID = ((ShowManagerElement)pList.getCurrent()).getId();
				pList.next();
			}
		}
	}
	
}
