/**	ShowManager v0.1	Dh	05.08.2023
 * 
 * 	logic.showentities.wrappers
 * 	BasicWrapper
 * 	  DatabaseWrapper
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

import org.showmanager.logic.manager.DatabaseManager;
import org.showmanager.logic.showentities.Genre;
import org.showmanager.logic.showentities.Host;
import org.showmanager.logic.showentities.ShowElement;
import org.showmanager.logic.showentities.ShowList;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonRootName(value = "database")
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "_type")
@JsonSubTypes({
	  @JsonSubTypes.Type(value = Genre.class, name = "_type"),
	  @JsonSubTypes.Type(value = ShowElement.class, name = "_type"),
	  @JsonSubTypes.Type(value = Host.class, name = "_type"),
	  @JsonSubTypes.Type(value = ShowList.class, name = "_type"),
	})
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({
	"genres", "shows", "hosts", "showlists"
})
public class DatabaseWrapper extends BasicWrapper {
	@JsonProperty("genres")
	private ArrayList<Genre> genres;
	@JsonProperty("shows")
	private ArrayList<ShowElement> shows;
	@JsonProperty("hosts")
	private ArrayList<Host> hosts;
	@JsonProperty("showLists")
	private ArrayList<ShowList> showLists;
	
	/**	Dh	05.08.2023
	 * 
	 */
	public DatabaseWrapper() {
		genres = 	new ArrayList<Genre>();
		shows = 	new ArrayList<ShowElement>();
		showLists = new ArrayList<ShowList>();
		hosts = 	new ArrayList<Host>();
	}

//----------------------------------------------------------------------------------------------------

	/**	Dh	05.08.2023
	 * 
	 * @return
	 */
	public ArrayList<Genre> getGenres(){
		return genres;
	}
	/**	Dh	05.08.2023
	 * 
	 * @return
	 */
	public ArrayList<ShowElement> getShows(){
		return shows;
	}
	/**	Dh	05.08.2023
	 * 
	 * @return
	 */
	public ArrayList<Host> getHosts(){
		return hosts;
	}
	
	/**	Dh	05.08.2023
	 * 
	 * @return
	 */
	public ArrayList<ShowList> getShowLists(){
		return showLists;
	}
	
	//------------------------------------------------------------------------------------------------
	
	/**	Dh	05.08.2023
	 * 
	 * @param pGenres
	 * @throws Exception
	 */
	public void setGenres(ArrayList<Genre> pGenres) throws Exception {
		if (pGenres != null) genres = pGenres;
		else throw new Exception("04; sGe,DaW");
	}
	/**	Dh	05.08.2023
	 * 
	 * @param pShows
	 * @throws Exception
	 */
	public void setShows(ArrayList<ShowElement> pShows) throws Exception {
		if (pShows != null) shows = pShows;
		else throw new Exception("04; sSh,DaW");
	}
	/**	Dh	05.08.2023
	 * 
	 * @param pHosts
	 * @throws Exception
	 */
	public void setHosts(ArrayList<Host> pHosts) throws Exception {
		if (pHosts != null) hosts = pHosts;
		else throw new Exception("04; sHo,DaW");
	}
	
	/**	Dh	05.08.2023
	 * 
	 * @param pShowLists
	 * @throws Exception
	 */
	public void setShowLists(ArrayList<ShowList> pShowLists) throws Exception {
		if (pShowLists != null) showLists = pShowLists;
		else throw new Exception("04; sSL,DaW");
	}
	
//----------------------------------------------------------------------------------------------------
	
	/**	Dh	05.08.2023
	 * 
	 * @param pDatabaseManager
	 * @throws Exception
	 */
	public void loadFromDatabase(DatabaseManager pDatabaseManager) throws Exception {
		try {
			genres = pDatabaseManager.loadGenres();
			shows = pDatabaseManager.loadShows();
			hosts = pDatabaseManager.loadHosts();
			
			showLists = pDatabaseManager.loadShowLists();
		}catch(Exception ex) {throw ex;}
	}
	
}
