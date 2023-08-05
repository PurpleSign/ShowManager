/**	ShowManager v0.1	Dh	27.7.2023
 * 
 * 	gui.tableElements
 * 	BasicTableElement
 * 	  StringElement
 * 		ShowTableElement
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

public class ShowTableElement extends NameElement {
	protected int rating;
	protected String genres, host, url;
	
	/**	Dh	27.07.2023
	 * 
	 * @param pID
	 * @param pName
	 */
 	public ShowTableElement(int pID, String pName) {
		super(pID, pName);
		
		rating = 0;
		genres = "";
		host = "";
		url = "";
	}
 	/**	Dh	27.07.2023
 	 * 
 	 * @param pID
 	 * @param pName
 	 * @param pRating
 	 * @param pGenres
 	 * @param pHost
 	 * @param pURL
 	 */
 	public ShowTableElement(int pID, String pName, int pRating, String pGenres, String pHost, String pURL) {
		super(pID, pName);
		
		rating = pRating;
		genres = pGenres;
		host = pHost;
		url = pURL;
	}

 //--------------------------------------------------------------------------------------------------------

 	/**	Dh	27.07.2023
 	 * 
 	 * @return
 	 */
 	public int getRating() {
 		return rating;
 	}
 	
 	/**	Dh	27.07.2023
 	 * 
 	 * @return
 	 */
 	public String getGenres() {
 		return genres;
 	}
 	/**	Dh	27.07.2023
 	 * 
 	 * @return
 	 */
 	public String getHost() {
 		return host;
 	}
 	/**	Dh	27.07.2023
 	 * 
 	 * @return
 	 */
 	public String getUrl() {
 		return url;
 	}
 	
 	//----------------------------------------------------------------------------------------------------
 	
 	/**	Dh	27.07.2023
 	 * 
 	 * @param pRating
 	 * @throws Exception
 	 */
 	public void setRating(int pRating) throws Exception {
 		if ((0 <= pRating ) && (pRating <= 10)) rating = pRating;
 		else throw new Exception("02; sRa,STE");
 	}
 	
 	/**	Dh	27.07.2023
 	 * 
 	 * @param pGenres
 	 * @throws Exception
 	 */
 	public void setGenres(String pGenres) throws Exception{
 		if (pGenres != null) genres = pGenres;
 		else throw new Exception("04; sGe,STE");
 	}
 	/**	Dh	27.07.2023
 	 * 
 	 * @param pHost
 	 * @throws Exception
 	 */
 	public void setHost(String pHost) throws Exception{
 		if (pHost != null) host = pHost;
 		else throw new Exception("04; sHo,STE");
 	}
 	/**	Dh	27.07.2023
 	 * 
 	 * @param pURL
 	 * @throws Exception
 	 */
 	public void setUrl(String pURL) throws Exception {
 		if (pURL != null) url = pURL;
 		else throw new Exception("04; sURL,STE");
 	}
 	
 //--------------------------------------------------------------------------------------------------------

 	/**	Dh	27.07.2023
 	 * 
 	 */
 	public String toString() {
 		return ""+ super.id +";"+ super.name +";"+ rating +";"+ genres +";"+ host +";"+ url;
 	}
 	
}
