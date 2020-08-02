/**	ShowManager v0.0	Dh	27.7.2020
 * 
 * 	pLogic
 * 	  ShowManagerElemet
 * 		Show
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
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

import pDataStructures.List;

@XmlRootElement
//@XmlSeeAlso({Genre.class})
//@XmlType(propOrder = {"rating", "showURL", "genereList", "hostID", "url"})
@Entity
@Table
public class ShowElement extends ShowManagerElement{
	private int rating, hostID;
	private String url;
	@Transient
	private List genreList;
	
	/**	Dh	27.7.2020
	 * 
	 */
	public ShowElement() {
		super();
		
		rating = 0;
		genreList = new List();
		
		hostID = -1;
		url = "";
	}
	/**	Dh	27.7.2020
	 * 
	 * @param pID
	 * @param pName
	 * @param pHostID
	 * @param pURL
	 */
	public ShowElement(int pID, String pName, int pHostID, String pURL) {
		super(pID, pName);
		
		try {
			setHostID(pHostID);
			setUrl(pURL);
		}catch (Exception ex) {
			hostID = -1;
			url = "";
			MainManager.handleException(ex);
		}
		
		rating = 0;
		genreList = new List();
	}
	/**	Dh	27.7.2020
	 * 
	 * @param pID
	 * @param pName
	 * @param pHostID
	 * @param pURL
	 * @param pRating
	 */
	public ShowElement(int pID, String pName, int pHostID, String pURL, int pRating) {
		super(pID, pName);
		
		try {setRating(pRating);}
		catch (Exception ex) {
			rating = 0;
			MainManager.handleException(ex);
		}
		try {
			setHostID(pHostID);
			setUrl(pURL);
		}catch (Exception ex) {
			hostID = -1;
			url = "";
			MainManager.handleException(ex);
		}
		
		genreList = new List();
	}
	/**	Dh	27.7.2020
	 * 
	 * @param pID
	 * @param pName
	 * @param pHostID
	 * @param pURL
	 * @param pGenreList
	 */
	public ShowElement(int pID, String pName, int pHostID, String pURL, List pGenreList) {
		super(pID, pName);
		
		try {
			setHostID(pHostID);
			setUrl(pURL);
		}catch (Exception ex) {
			hostID = -1;
			url = "";
			MainManager.handleException(ex);
		}
		try {setGenreList(pGenreList);}
		catch (Exception ex) {
			genreList = new List();
			MainManager.handleException(ex);
		}
		
		rating = 0;
	}
	/**	Dh	27.7.2020
	 * 
	 * @param pID
	 * @param pName
	 * @param pHostID
	 * @param pURL
	 * @param pRating
	 * @param pGenreList
	 */
	public ShowElement(int pID, String pName, int pHostID, String pURL, int pRating, List pGenreList) {
		super(pID, pName);
		
		try {setRating(pRating);}
		catch (Exception ex) {
			rating = 0;
			MainManager.handleException(ex);
		}
		try {setGenreList(pGenreList);}
		catch (Exception ex) {
			genreList = new List();
			MainManager.handleException(ex);
		}
		try {
			setHostID(pHostID);
			setUrl(pURL);
		}catch (Exception ex) {
			hostID = -1;
			url = "";
			MainManager.handleException(ex);
		}
	}

//----------------------------------------------------------------------------------------------------
	
	/**	Dh	18.7.2020
	 * 	
	 * @return
	 */
	@XmlElement(name = "Rating")
	public int getRating() {
		return rating;
	}
	/**	Dh	18.7.2020
	 * 
	 * @return
	 */
	@XmlElement(name = "GenreList")
	public List getGenreList() {
		return genreList;
	}
	
	/**	Dh	27.7.2020
	 * 
	 * @return
	 */
	@XmlElement(name = "HostID")
	public int getHostID() {
		return hostID;
	}
	/**	Dh	27.7.2020
	 * 
	 * @return
	 */
	@XmlElement(name = "URL")
	public String getUrl() {
		return url;
	}
	
	//------------------------------------------------------------------------------------------------
	
	/**	Dh	18.7.2020
	 * 
	 * @param pRating
	 * @throws Exception
	 */
	public void setRating(int pRating) throws Exception {
		if ((pRating >= 0) && (pRating <= 10)) rating = pRating;
		else throw new Exception("02; Sho,sR");
	}
	/**	Dh	19.7.2020
	 * 
	 * @param pGenereList
	 * @throws Exception
	 */
	public void setGenreList(List pGenreList) throws Exception {
		Object vCur;
		
		if (pGenreList != null) {
			if (!pGenreList.isEmpty()) {
				pGenreList.toFirst();
				
				while(!pGenreList.isEnd()) {
					vCur = pGenreList.getCurrent();
					if (vCur != null) {
						if (!(vCur instanceof Integer)) throw new Exception("06; Sho,sGL");
					} else throw new Exception("04; Sho,sGL");
					
					pGenreList.next();
				}
			}
			
			genreList = pGenreList.copyList();
		} else throw new Exception("04; Sho,sGL");
	}
		
	/**	Dh	27.7.2020
	 * 
	 * @param pHostID
	 * @throws Exception
	 */
	protected void setHostID(int pHostID) throws Exception{
		if (pHostID >= -1) hostID = pHostID;
		else throw new Exception("02; Sho,sHID");
	}
	/**	Dh	28.7.2020
	 * 
	 * @param pURL
	 * @throws Exception
	 */
	protected void setUrl(String pURL) throws Exception{
		if (pURL != null) url = pURL;
		else throw new Exception("04; Sho,sURL");
	}
	
//----------------------------------------------------------------------------------------------------
	
	/**	Dh	19.7.2020
	 * 
	 * @param pID
	 * @return
	 * @throws Exception
	 */
	protected boolean haveGenre(int pID) throws Exception{
		boolean vRet = false;
		
		if (pID >= 0) {
			if (!genreList.isEmpty()) {
				genreList.toFirst();
				
				while(!genreList.isEnd() && (vRet == false)) {
					if ( ((int)genreList.getCurrent()) == pID ) vRet = true;
					
					genreList.next();
				}
			}
		} else throw new Exception("02; Sho,hG");
		
		return vRet;
	}
	
	//----------------------------------------------------------------------------------------------------
	
	/**	Dh	19.7.2020
	 * 
	 * @param pGenere
	 * @throws Exception
	 */
	protected void addGenre(int pGenreID) throws Exception{
		if (pGenreID >= 0) genreList.append(pGenreID);
		else throw new Exception("04; Sho,aG");
	}
	
	/**	Dh	19.7.2020
	 * 
	 * @param pID
	 * @throws Exception
	 */
	protected void removeGenre(int pID) throws Exception{
		if (pID >= 0) {
			if (!genreList.isEmpty()) {
				genreList.toFirst();
				
				while(!genreList.isEnd() && (pID != -1)) {
					if (((Integer)genreList.getCurrent()) == pID) {
						genreList.remove();
						pID = -1;
					}
					
					genreList.next();
				}
			}
		}else throw new Exception("02; Sho,rG");
	}
}
