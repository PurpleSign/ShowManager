/**	ShowManager v0.0	Dh	28.7.2020
 * 
 * 	pLogic
 * 	  ShowManagerElemet
 * 		GenreListElementWrapper
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
public class GenreListElementWrapper {
	@Id
	private int genreListID;
	private int[] genreIDs;
	
	/**	Dh	27.7.2002
	 * 
	 */
	public GenreListElementWrapper() {
		genreListID = -1;
		genreIDs = new int[] {};
	}
	/**	Dh	27.7.2020
	 * 
	 * @param pShow
	 */
	public GenreListElementWrapper(ShowElement pShow) {
		if (pShow != null) {
			genreListID = pShow.getId();
			genreIDs = convertListToArray(pShow.getGenreList());
		} else {
			genreListID = -1;
			genreIDs = new int[] {};
			
			MainManager.handleException(new Exception("04; GeLiElWra"));
		}
	}

//----------------------------------------------------------------------------------------------------
	
	/**	Dh	27.7.2020
	 * 
	 * @return
	 */
	public int getGenreListID() {
		return genreListID;
	}
	
	/**	Dh	27.7.2020
	 * 
	 * @return
	 */
	public int[] getGenreIDs() {
		return genreIDs;
	}
	
	/**	Dh	28.7.2020
	 * 
	 * @return
	 */
	protected List getGenreIDList() {
		List vRet = new List();
		
		if (genreIDs != null) {
			if (genreIDs.length >= 1) {
				for (int i=0; i < genreIDs.length; i++) {
					vRet.append(genreIDs[i]);
				}
			}
		}
		
		return vRet;
	}
	
	//------------------------------------------------------------------------------------------------
	
	/**	Dh	27.7.2020
	 * 
	 * @param pGenreListID
	 * @throws Exception
	 */
	public void setGenreList(int pGenreListID) throws Exception{
		if (pGenreListID >= 0) genreListID = pGenreListID;
		else throw new Exception("02; GeLiElWra,sGL");
	}
	
	/**	Dh	27.7.2020
	 * 
	 * @param pGenreIDs
	 */
	public void setGenreIDs(int[] pGenreIDs){
		genreIDs = pGenreIDs;
	}
	/**	Dh	28.7.2020
	 * 
	 * @param pGenreIDList
	 * @throws Exception
	 */
	protected void setGenreIDsPerList(List pGenreIDList) throws Exception {
		if (pGenreIDList != null) genreIDs = convertListToArray(pGenreIDList);
		else throw new Exception("04; GeLiElWra,sGIDpL");
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
