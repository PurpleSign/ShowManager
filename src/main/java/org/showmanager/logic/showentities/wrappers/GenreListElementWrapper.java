/**	ShowManager v0.1	Dh	29.7.2023
 * 
 * 	logic.showentities.wrappers
 * 	BasicWrapper
 * 	  GenreListElementWrapper
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
import org.showmanager.logic.showentities.ShowElement;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Table;

@Entity
@Table
public class GenreListElementWrapper extends BasicWrapper {
	@Id
	private int genreListID;
	private int[] genreIDs;
	
	/**	Dh	29.07.2023
	 * 
	 */
	public GenreListElementWrapper() {
		genreListID = -1;
		genreIDs = null;
	}
	/**	Dh	29.07.2023
	 * 
	 * @param pShow
	 */
	public GenreListElementWrapper(ShowElement pShow) {
		if (pShow != null) {
			genreListID = pShow.getId();
			try{setIDsPerList(pShow.getGenreList());}
			catch(Exception ex) {LogManager.handleException(ex);}
		} else {
			genreListID = -1;
			genreIDs = new int[] {};
			
			LogManager.handleException(new Exception("04; GLEW"));
		}
	}

//----------------------------------------------------------------------------------------------------

	/**	Dh	29.07.2023
	 * 
	 * @return
	 */
	public int getGenreListID() {
		return genreListID;
	}
	/**	Dh	29.07.2023
	 * 
	 * @return
	 */
	public int[] getGenreIDs() {
		return genreIDs;
	}
	
	/**	Dh	29.07.2023
	 * 
	 */
	public ArrayList<Integer> getIDList() {
		return convertArrayToList(genreIDs);
	}
	
	//------------------------------------------------------------------------------------------------
	
	/**	Dh	29.07.2023
	 * 
	 * @param pGenreListID
	 * @throws Exception
	 */
	public void setGenreListID(int pGenreListID) throws Exception {
		if (pGenreListID >= 0) genreListID = pGenreListID;
		else throw new Exception("02; sGLID,GLEW");
	}
	/**	Dh	29.07.2023
	 * 
	 * @param pGenreIDs
	 */
	public void setGenreIDs(int[] pGenreIDs){
		genreIDs = pGenreIDs;
	}
	
	/**	Dh	29.07.2023
	 * 
	 */
	public void setIDsPerList(ArrayList<Integer> pIDList) throws Exception {
		if (pIDList != null) genreIDs = convertListToArray(pIDList);
		else throw new Exception("04; sIDpL,GLEW");
	}
	
}
