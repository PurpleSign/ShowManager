/**	ShowManager v0.1	Dh	29.7.2023
 * 
 * 	logic.showentities
 * 	BasicShowentity
 * 	  ShowElement
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

package org.showmanager.logic.showentities;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.persistence.Entity;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@JsonPropertyOrder({
	"id", "name", "rating", "hostID", "url"
})
@Entity
@Table
public class ShowElement extends BasicShowentity {
	@JsonProperty("rating")
	protected int rating;
	@JsonProperty("hostID")
	protected int hostID;
	@JsonProperty("url")
	protected String url;
	@Transient
	protected ArrayList<Integer> genreList;
	
	/**	Dh	29.07.2023
	 * 
	 */
	public ShowElement() {
		super();
		
		rating = 0;
		hostID = -1;
		
		url = "";
		
		genreList = new ArrayList<Integer>();
	}

	/**	Dh	26.07.2023
	 * 
	 * @param pID
	 * @param pName
	 * @param pHostID
	 * @param pURL
	 */
	public ShowElement(int pID, String pName, int pHostID, String pURL) {
		this(pID, pName, pHostID, pURL, 0, new ArrayList<Integer>());
	}
	/**	Dh	26.07.2023
	 * 
	 * @param pID
	 * @param pName
	 * @param pHostID
	 * @param pURL
	 * @param pRating
	 */
	public ShowElement(int pID, String pName, int pHostID, String pURL, int pRating) {
		this(pID, pName, pHostID, pURL, pRating, new ArrayList<Integer>());
	}
	/**	Dh	26.07.2023
	 * 
	 * @param pID
	 * @param pName
	 * @param pHostID
	 * @param pURL
	 * @param pGenreList
	 */
	public ShowElement(int pID, String pName, int pHostID, String pURL, ArrayList<Integer> pGenreList) {
		this(pID, pName, pHostID, pURL, 0, new ArrayList<Integer>());
	}
	/**	Dh	29.07.2023
	 * 
	 * @param pID
	 * @param pName
	 * @param pHostID
	 * @param pURL
	 * @param pRating
	 * @param pGenreList
	 */
	public ShowElement(int pID, String pName, int pHostID, String pURL, int pRating, ArrayList<Integer> pGenreList) {
		super(pID, pName);
		
		rating = pRating;
		hostID = pHostID;
		
		url = pURL;
		
		genreList = pGenreList;
	}

//----------------------------------------------------------------------------------------------------

	/**	Dh	26.07.2023
	 * 
	 * @return
	 */
	public int getRating() {
		return rating;
	}
	/**	Dh	26.07.2023
	 * 
	 * @return
	 */
	public int getHostID() {
		return hostID;
	}
	
	/**	Dh	29.07.2023
	 * 
	 * @return
	 */
	public String getUrl() {
		return url;
	}
	
	/**	Dh	26.07.2023
	 * 
	 * @return
	 */
	public ArrayList<Integer> getGenreList(){
		return genreList;
	}
	
	//------------------------------------------------------------------------------------------------
	
	/**	Dh	26.07.2023
	 * 
	 * @param pRating
	 * @throws Exception
	 */
	public void setRating(int pRating) throws Exception{
		if ((0 <= pRating) && (pRating <= 10)) rating = pRating;
		else throw new Exception("02; sRa,ShE");
	}
	/**	Dh	26.07.2023
	 * 
	 * @param pHostID
	 * @throws Exception
	 */
	public void setHostID(int pHostID) throws Exception{
		if (pHostID >= -1) hostID = pHostID;
		else throw new Exception("02; sHID,ShE");
	}
	
	/**	Dh	29.07.2023
	 * 
	 * @param pURL
	 * @throws Exception
	 */
	public void setUrl(String pURL) throws Exception{
		if (pURL != null) url = pURL;
		else throw new Exception("04; sURL,Hos");
	}
	
	/**	Dh	26.07.2023
	 * 
	 * @param pGenreList
	 * @throws Exception
	 */
	public void setGenreList(ArrayList<Integer> pGenreList) throws Exception{
		if (pGenreList != null) {
			for (Integer vGenreID : pGenreList) {
				if (vGenreID != null) {
					if (vGenreID <= -1) throw new Exception("02; sGL,ShE");
				}else throw new Exception("04b, sGL,ShE");
			}
			
			genreList = (ArrayList<Integer>)pGenreList.clone();
		}else throw new Exception("04; sGL,ShE");
	}
	
//----------------------------------------------------------------------------------------------------
	
	/**	Dh	26.07.2023
	 * 
	 * @param pID
	 * @return
	 * @throws Exception
	 */
	public boolean haveGenre(int pID) throws Exception{
		boolean vRet = false;
		
		if (pID >= 0) {
			if (!genreList.isEmpty()) {
				vRet = genreList.contains(Integer.valueOf(pID));
			}
		} else throw new Exception("02; hGe,ShE");
		
		return vRet;
	}
	
	//------------------------------------------------------------------------------------------------
	
	/**	Dh	26.07.2023
	 * 
	 * @param pGenreID
	 * @throws Exception
	 */
	public void addGenre(int pGenreID) throws Exception{
		if (pGenreID >= 0) genreList.add(Integer.valueOf(pGenreID));
		else throw new Exception("04; adG,ShE");
	}
	/**	Dh	26.07.2023
	 * 
	 * @param pID
	 * @throws Exception
	 */
	public void removeGenre(int pID) throws Exception{
		if (pID >= 0) {
			if (!genreList.isEmpty()) {
				genreList.remove(Integer.valueOf(pID));
			}
		}else throw new Exception("02; reG,ShE");
	}
	
}
