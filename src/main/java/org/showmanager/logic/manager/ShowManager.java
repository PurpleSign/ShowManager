/**	ShowManager v0.1	Dh	31.7.2023
 * 
 * 	logic.manager
 * 	BasicManager
 * 	  ShowManager
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

import java.io.File;
import java.util.ArrayList;

import org.showmanager.logic.showentities.Genre;
import org.showmanager.logic.showentities.Host;
import org.showmanager.logic.showentities.ShowElement;
import org.showmanager.logic.showentities.ShowList;
import org.showmanager.logic.showentities.wrappers.DatabaseWrapper;

public class ShowManager extends BasicManager {
	private ArrayList<Genre> genres;
	private ArrayList<ShowElement> shows;
	private ArrayList<ShowList> showLists;

	/**	Dh	27.07.2023
	 * 
	 * @param pDatabaseManager
	 * @param pBrowserManager
	 */
	public ShowManager(DatabaseManager pDatabaseManager, BrowserManager pBrowserManager) {
		super(pDatabaseManager);
		
		if (pBrowserManager != null) super.browserManager = pBrowserManager;
		else LogManager.handleException(new Exception("04; ShM"));
		
		try {
			loadAll();
		}catch(Exception ex) {LogManager.handleException(ex);}
	}

//----------------------------------------------------------------------------------------------------
	
	/**	Dh	27.07.2023
	 * 
	 * @return
	 */
	public ArrayList<Integer> getGenreIDs() {
		ArrayList<Integer> vRet = new ArrayList<Integer>();
		
		if (!genres.isEmpty()) {
			for (Genre vGenre : genres) {
				vRet.add(vGenre.getId());
			}
		}
		
		return vRet;
	}
	/**	Dh	27.07.2023
	 * 
	 * @return
	 */
	public ArrayList<Integer> getShowIDs() {
		ArrayList<Integer> vRet = new ArrayList<Integer>();
		
		if (!shows.isEmpty()) {
			for (ShowElement vShow : shows) {
				vRet.add(vShow.getId());
			}
		}
		
		return vRet;
	}
	/**	Dh	27.07.2023
	 * 
	 * @return
	 */
	public ArrayList<Integer> getShowListIDs() {
		ArrayList<Integer> vRet = new ArrayList<Integer>();
		
		if (!showLists.isEmpty()) {
			for (ShowList vShowList : showLists) {
				vRet.add(vShowList.getId());
			}
		}
		
		return vRet;
	}
	/**	Dh	27.07.2023
	 * 
	 * @return
	 */
	public ArrayList<Integer> getHostIDList() {
		ArrayList<Integer> vRet = new ArrayList<Integer>();
		ArrayList<Host> vTempList = super.browserManager.getHostList();
		
		if (!vTempList.isEmpty()) {
			for (Host vHost : vTempList) {
				vRet.add(vHost.getId());
			}
		}
		
		return vRet;
	}
	
	
	/**	Dh	27.07.2023
	 * 
	 * @param pID
	 * @return
	 * @throws Exception
	 */
	public String getGenreName(int pID) throws Exception{
		String vRet = null;
		
		if ((pID >= 0) && !genres.isEmpty()) {
			for (Genre vGenre : genres) {
				if (vGenre.getId() == pID) vRet = vGenre.getName();
			}
		} else throw new Exception("02; gGN,ShM");
		
		return vRet;
	}
	//-----
	/**	Dh	27.07.2023
	 * 
	 * @param pID
	 * @return
	 * @throws Exception
	 */
	public String getShowName(int pID) throws Exception{
		String vRet = null;
		
		if ((pID >= 0) && !shows.isEmpty()) {
			for (ShowElement vShow : shows) {
				if (vShow.getId() == pID) vRet = vShow.getName();
			}
		} else throw new Exception("02; gSN,ShM");
		
		return vRet;
	}
	/**	Dh	27.07.2023
	 * 
	 * @param pID
	 * @return
	 * @throws Exception
	 */
	public int getShowRating(int pID) throws Exception{
		int vRet = -1;
		
		if ((pID >= 0) && !shows.isEmpty()) {
			for (ShowElement vShow : shows) {
				if (vShow.getId() == pID) vRet = vShow.getRating();
			}
		} else throw new Exception("02; gSR,ShM");
		
		return vRet;
	}
	/**	Dh	27.07.2023
	 * 
	 * @param pID
	 * @return
	 * @throws Exception
	 */
	public int getShowHostID(int pID) throws Exception{
		int vRet = -1;
		
		if ((pID >= 0) && !shows.isEmpty()) {
			for (ShowElement vShow : shows) {
				if (vShow.getId() == pID) vRet = vShow.getHostID();
			}
		} else throw new Exception("02; gSHID,ShM");
		
		return vRet;
	}
	/**	Dh	27.07.2023
	 * 
	 * @param pID
	 * @return
	 * @throws Exception
	 */
	public String getShowURL(int pID) throws Exception{
		int vHostID;
		String vRet = null;
		
		if ((pID >= 0) && !shows.isEmpty()) {
			for (ShowElement vShow : shows) {
				if (vShow.getId() == pID) {
					vHostID = vShow.getHostID();
					
					if (vHostID != -1) vRet = super.browserManager.getHostURL(vHostID);
					else vRet = "";
					
					vRet += vShow.getUrl();
				}
			}
		} else throw new Exception("02; gSURL,ShM");
		
		return vRet;
	}
	/**	Dh	27.07.2023
	 * 
	 * @param pID
	 * @return
	 * @throws Exception
	 */
	public ArrayList<Integer> getShowGenreIDs(int pID) throws Exception{
		ArrayList<Integer> vRet = null;
		
		if ((pID >= 0) && !shows.isEmpty()) {
			for (ShowElement vShow : shows) {
				if (vShow.getId() == pID) vRet = vShow.getGenreList();
			}
		} else throw new Exception("02; gSGID,ShM");
		
		return vRet;
	}
	//-----
	/**	Dh	27.07.2023
	 * 
	 * @param pID
	 * @return
	 * @throws Exception
	 */
	public String getShowListName(int pID) throws Exception{
		String vRet = null;
		
		if ((pID >= 0) && !showLists.isEmpty()) {
			for (ShowList vShowList : showLists) {
				if (vShowList.getId() == pID) vRet = vShowList.getName();
			}
		} else throw new Exception("02; gSLN,ShM");
		
		return vRet;
	}
	/**	Dh	27.07.2023
	 * 
	 * @param pID
	 * @return
	 * @throws Exception
	 */
	public ArrayList<Integer> getShowListShowIDs(int pID) throws Exception{
		ArrayList<Integer> vRet = null;
		
		if ((pID >= 0) && !showLists.isEmpty()) {
			for (ShowList vShowList : showLists) {
				if (vShowList.getId() == pID) vRet = vShowList.getShowList();
			}
		} else throw new Exception("02; gSLSID,ShM");
		
		return vRet;
	}
	//-----
	/**	Dh	31.07.2023
	 * 
	 * @param pID
	 * @return
	 * @throws Exception
	 */
	public String getHostName(int pID) throws Exception{
		return super.browserManager.getHostName(pID);
	}
	/**	Dh	31.07.2023
	 * 
	 * @param pID
	 * @return
	 * @throws Exception
	 */
	public String getHostURL(int pID) throws Exception{
		return super.browserManager.getHostURL(pID);
	}
	
	//------------------------------------------------------------------------------------------------
	
	/**	Dh	27.07.2023
	 * 
	 * @param pID
	 * @param pName
	 * @throws Exception
	 */
	public void setGenreName(int pID, String pName) throws Exception{
		if ((pID >= 0) && !genres.isEmpty()) {
			for (Genre vGenre : genres) {
				if (vGenre.getId() == pID) vGenre.setName(pName);
			}
		} else throw new Exception("02; sGN,ShM");
	}
	//-----
	/**	Dh	27.07.2023
	 * 
	 * @param pID
	 * @param pName
	 * @throws Exception
	 */
	public void setShowName(int pID, String pName) throws Exception{
		if ((pID >= 0) && !shows.isEmpty()) {
			for (ShowElement vShow : shows) {
				if (vShow.getId() == pID) vShow.setName(pName);
			}
		} else throw new Exception("02; sSN,ShM;");
	}
	/**	Dh	27.07.2023
	 * 
	 * @param pID
	 * @param pRating
	 * @throws Exception
	 */
	public void setShowRating(int pID, int pRating) throws Exception{
		
		if ((pID >= 0) && !shows.isEmpty()) {
			for (ShowElement vShow : shows) {
				if (vShow.getId() == pID) vShow.setRating(pRating);
			}
		} else throw new Exception("02; sSR,ShM");
	}
	/**	Dh	27.07.2023
	 * 
	 * @param pShowID
	 * @param pHostID
	 * @throws Exception
	 */
	public void setShowHostID(int pShowID, int pHostID) throws Exception{
		if ((pShowID >= 0)) {
			for (ShowElement vShow : shows) {
				if (vShow.getId() == pShowID) vShow.setHostID(pHostID);
			}
		} else throw new Exception("02; sSHID,ShM");
	}
	/**	Dh	27.07.2023
	 * 
	 * @param pID
	 * @param pURL
	 * @throws Exception
	 */
	public void setShowURL(int pID, String pURL) throws Exception{
		if ((pID >= 0) && !shows.isEmpty()) {
			for (ShowElement vShow : shows) {
				if (vShow.getId() == pID) vShow.setUrl(pURL);
			}
		} else throw new Exception("02; sSURL,ShM");
	}
	/**	Dh	31.07.2023
	 * 
	 * @param pID
	 * @param pGenreIDList
	 * @throws Exception
	 */
	public void setShowGenreIDs(int pID, ArrayList<Integer> pGenreIDList) throws Exception{
		if ((pID >= 0) && !shows.isEmpty()) {
			for (ShowElement vShow : shows) {
				if (vShow.getId() == pID) vShow.setGenreList(pGenreIDList);
			}
		} else throw new Exception("02; sSGID,ShM");
	}
	//-----
	/**	Dh	27.07.2023
	 * 
	 * @param pID
	 * @param pName
	 * @throws Exception
	 */
	public void setShowListName(int pID, String pName) throws Exception{
		if ((pID >= 0) && !showLists.isEmpty()) {
			for (ShowList vShowList : showLists) {
				if (vShowList.getId() == pID) vShowList.setName(pName);
			}
		} else throw new Exception("02; sSLN,ShM");
	}
	/**	Dh	27.07.2023
	 * 
	 * @param pID
	 * @param pShowIDList
	 * @throws Exception
	 */
	public void setShowListShowIDs(int pID, ArrayList<Integer> pShowIDList) throws Exception{
		if ((pID >= 0) && !showLists.isEmpty()) {
			for (ShowList vShowList : showLists) {
				if (vShowList.getId() == pID) vShowList.setShowList(getShowListIDs());
			}
		} else throw new Exception("02; gSLSID,ShM");
	}
	//-----
	/**	Dh	31.07.2023
	 * 
	 * @param pID
	 * @param pName
	 * @throws Exception
	 */
	public void setHostName(int pID, String pName) throws Exception{
		super.browserManager.setHostName(pID, pName);
	}
	/**	Dh	31.07.2023
	 * 
	 * @param pID
	 * @param pURL
	 * @throws Exception
	 */
	public void setHostURL(int pID, String pURL) throws Exception{
		super.browserManager.setHostURL(pID, pURL);
	}
	
//----------------------------------------------------------------------------------------------------
	
	/**	Dh	27.07.2023
	 * 
	 * @param pID
	 * @return
	 * @throws Exception
	 */
	public boolean haveGenre(int pID) throws Exception{
		boolean vRet = false;
		
		if ((pID >= 0) && !genres.isEmpty()) {
			for (Genre vGenre : genres) {
				if (vGenre.getId() == pID) vRet = true;
			}
		} else throw new Exception("02; hGe,ShM");
		
		return vRet;
	}
	/**	Dh	27.07.2023
	 * 
	 * @param pID
	 * @return
	 * @throws Exception
	 */
	public boolean haveShow(int pID) throws Exception{
		boolean vRet = false;
		ShowElement vCur;
		
		if ((pID >= 0) && !shows.isEmpty()) {
			for (ShowElement vShow : shows) {
				if (vShow.getId() == pID) vRet = true;
			}
		} else throw new Exception("02; hSh,ShM");
		
		return vRet;
	}
	/**	Dh	27.07.2023
	 * 
	 * @param pID
	 * @return
	 * @throws Exception
	 */
	public boolean haveShowList(int pID) throws Exception{
		boolean vRet = false;
		
		if ((pID >= 0) && !showLists.isEmpty()) {
			for (ShowList vShowList : showLists) {
				if (vShowList.getId() == pID) vRet = true;
			}
		} else throw new Exception("02; gSL,ShM");
		
		return vRet;
	}
	/**	Dh	31.07.2023
	 * 
	 * @param pID
	 * @return
	 * @throws Exception
	 */
	public boolean haveHost(int pID) throws Exception{
		return super.browserManager.haveHost(pID);
	}
	
	/**	Dh	27.07.2023
	 * 
	 * @param pShowID
	 * @param pGenreID
	 * @return
	 * @throws Exception
	 */
	public boolean hasShowGenre(int pShowID, int pGenreID) throws Exception{
		boolean vRet = false;
		
		if ((pShowID >= 0) && !shows.isEmpty()) {
			for (ShowElement vShow : shows) {
				if (vShow.getId() == pShowID) vRet = vShow.haveGenre(pGenreID);
			}
		} else throw new Exception("02; hSG,ShM");
		
		return vRet;
	}
	/**	Dh	27.07.2023
	 * 
	 * @param pShowListID
	 * @param pShowID
	 * @return
	 * @throws Exception
	 */
	public boolean hasShowListShow(int pShowListID, int pShowID) throws Exception{
		boolean vRet = false;
		
		if ((pShowListID >= 0) && !showLists.isEmpty()) {
			for (ShowList vShowList : showLists) {
				if (vShowList.getId() == pShowListID) vRet = vShowList.haveShow(pShowID);
			}
		} else throw new Exception("02; gSLN,ShM");
		
		return vRet;
	}
	
	//------------------------------------------------------------------------------------------------
	
	/**	Dh	27.07.2023
	 * 	
	 * @param pName
	 * @throws Exception
	 */
	public void addGenre(String pName) throws Exception{
		genres.add(new Genre(dertermineID(genres), pName));
		sortListByID(genres);
	}
	//-----
	/**	Dh	27.07.2023
	 * 
	 * @param pName
	 * @param pHostID
	 * @param pURL
	 * @throws Exception
	 */
	public void addShow(String pName, int pHostID, String pURL) throws Exception {
		shows.add(new ShowElement(dertermineID(shows), pName, pHostID, prepareURL(pURL, pHostID)));
		sortListByID(shows);
	}
	/**	Dh	27.07.2023
	 * 
	 * @param pName
	 * @param pHostID
	 * @param pURL
	 * @param pRating
	 * @throws Exception
	 */
	public void addShow(String pName, int pHostID, String pURL, int pRating) throws Exception {
		shows.add(new ShowElement(dertermineID(shows), pName, pHostID, prepareURL(pURL, pHostID), pRating));
		sortListByID(shows);
	}
	/**	Dh	27.07.2023
	 * 
	 * @param pName
	 * @param pHostID
	 * @param pURL
	 * @param pGenereIDList
	 * @throws Exception
	 */
	public void addShow(String pName, int pHostID, String pURL, ArrayList<Integer> pGenereIDList) throws Exception {
		shows.add(new ShowElement(dertermineID(shows), pName, pHostID, prepareURL(pURL, pHostID), pGenereIDList));
		sortListByID(shows);
	}
	/**	Dh	27.07.2023
	 * 
	 * @param pName
	 * @param pHostID
	 * @param pURL
	 * @param pRating
	 * @param pGenereIDList
	 * @throws Exception
	 */
	public void addShow(String pName, int pHostID, String pURL, int pRating, ArrayList<Integer> pGenereIDList) throws Exception {
		shows.add(new ShowElement(dertermineID(shows), pName, pHostID, prepareURL(pURL, pHostID), pRating, pGenereIDList));
		sortListByID(shows);
	}
	//-----
	/**	Dh	27.07.2023
	 * 
	 * @param pName
	 * @throws Exception
	 */
	public void addShowList(String pName) throws Exception{
		showLists.add(new ShowList(dertermineID(showLists), pName));
		sortListByID(showLists);
	}
	/**	Dh	27.07.2023
	 * 
	 * @param pName
	 * @param pShowsIDList
	 * @throws Exception
	 */
	public void addShowList(String pName, ArrayList<Integer> pShowsIDList) throws Exception{
		showLists.add(new ShowList(dertermineID(showLists), pName, pShowsIDList));
		sortListByID(showLists);
	}
	//-----
	/**	Dh	31.07.2023
	 * 
	 * @param pName
	 * @param pPath
	 * @throws Exception
	 */
	public void addHost(String pName, String pPath) throws Exception{
		super.browserManager.addHost(new Host(this.dertermineID(super.browserManager.getHostList()), pName, pPath));
		sortListByID(super.browserManager.getHostList());
	}
	
	/**	Dh	27.07.2023
	 * 
	 * @param pShowID
	 * @param pGenreID
	 * @throws Exception
	 */
	public void addGenreToShow(int pShowID, int pGenreID) throws Exception{
		if ((pShowID >= 0) && !shows.isEmpty()) {
			for (ShowElement vShow : shows) {
				if (vShow.getId() == pShowID) vShow.addGenre(pGenreID);
			}
		} else throw new Exception("02; aGtS,ShM");
	}
	
	/**	Dh	27.07.2023
	 * 
	 * @param pShowListID
	 * @param pShowID
	 * @throws Exception
	 */
	public void addShowToShowList(int pShowListID, int pShowID) throws Exception {
		if ((pShowListID >= 0) && !showLists.isEmpty()) {
			for (ShowList vShowList : showLists) {
				if (vShowList.getId() == pShowListID) vShowList.addShow(pShowID);
			}
		} else throw new Exception("02; aStSL,ShM");
	}
	
	//------------------------------------------------------------------------------------------------
	
	/**	Dh	01.08.2023
	 * 
	 * @param pID
	 * @throws Exception
	 */
	public void removeGenre(int pID) throws Exception{
		if (!genres.isEmpty()) {			
			if (!shows.isEmpty()) {
				for (ShowElement vShow : shows) {
					vShow.removeGenre(pID);
				}
			}
			
			for (int i=0; i < genres.size(); i++) {
				if (genres.get(i).getId() == pID) genres.remove(i);
			}
		}
	}
	/**	Dh	01.08.2023
	 * 
	 * @param pID
	 * @throws Exception
	 */
	public void removeShow(int pID) throws Exception{
		if (!shows.isEmpty()) {
			if (!showLists.isEmpty()) {
				for (ShowList vShowList : showLists) {
					vShowList.removeShow(pID);
				}
			}
			
			for (int i=0; i < shows.size(); i++) {
				if (shows.get(i).getId() == pID) shows.remove(i);
			}
		}
	}
	/**	Dh	01.08.2023
	 * 
	 * @param pID
	 * @throws Exception
	 */
	public void removeShowList(int pID) throws Exception{
		if (!showLists.isEmpty()) {
			for (int i=0; i < showLists.size(); i++) {
				if (showLists.get(i).getId() == pID) showLists.remove(i);
			}
		}
	}
	/**	Dh	31.07.2023
	 * 
	 * @param pID
	 * @throws Exception
	 */
	public void removeHost(int pID) throws Exception{
		removeHostFromShows(pID);
		super.browserManager.removeHost(pID);
	}
	
	/**	Dh	31.07.2023
	 * 
	 * @param pID
	 * @throws Exception
	 */
	public void removeHostFromShows(int pID) throws Exception{
		if (pID >= 0) {
			if (!shows.isEmpty()) {
				for (ShowElement vShow : shows) {
					if (vShow.getHostID() == pID) vShow.setHostID(-1);
				}
			}
		} else throw new Exception("02; rHo,ShM");
	}
	
	/**	Dh	27.07.2023
	 * 
	 * @param pShowID
	 * @param pGenreID
	 * @throws Exception
	 */
	public void removeGenreOfShow(int pShowID, int pGenreID) throws Exception{
		if ((pShowID >= 0) && !shows.isEmpty()) {
			for (ShowElement vShow : shows) {
				if (vShow.getId() == pShowID) vShow.removeGenre(pGenreID);
			}
		} else throw new Exception("02; rGoS,ShM");
	}
	/**	Dh	27.07.2023
	 * 
	 * @param pShowListID
	 * @param pShowID
	 * @throws Exception
	 */
	public void removeShowOfShowList(int pShowListID, int pShowID) throws Exception{
		if ((pShowListID >= 0) && !showLists.isEmpty()) {
			for (ShowList vShowList : showLists) {
				if (vShowList.getId() == pShowListID) vShowList.removeShow(pShowID);
			}
		} else throw new Exception("02; rSoSL,ShM");
	}
	
//----------------------------------------------------------------------------------------------------
	
	/**	Dh	27.07.2023
	 * 
	 * @param pShowID
	 * @throws Exception
	 */
	public void openShow(int pShowID) throws Exception{
		if ((pShowID >= 0) && !shows.isEmpty()) {
			for (ShowElement vShow : shows) {
				if (vShow.getId() == pShowID) super.browserManager.openShow(vShow);
			}
		} else throw new Exception("02; oSh,ShM");
	}
	
	//------------------------------------------------------------------------------------------------
	
	/**	Dh	27.07.2023
	 * 
	 * @throws Exception
	 */
	public void saveGenres() throws Exception {
		databaseManager.saveGenres(genres);
	}
	//-----
	/**	Dh	27.07.2023
	 * 
	 * @throws Exception
	 */
	public void saveShows() throws Exception{
		databaseManager.saveShows(shows);
	}
	//-----
	/**	Dh	27.07.2023
	 * 
	 * @param pID
	 * @throws Exception
	 */
	public void saveShowList(int pID) throws Exception{
		if ((pID >= 0) && (!showLists.isEmpty())) {
			for (ShowList vShowList : showLists) {
				if (vShowList.getId() == pID) databaseManager.saveShowList(vShowList);
			}
		} else throw new Exception("02; sSL,ShM");
	}
	/**	Dh	27.07.2023
	 * 
	 * @throws Exception
	 */
	public void saveShowLists() throws Exception{
		databaseManager.saveShowLists(showLists);
	}
	//-----
	/**	Dh	31.07.2023
	 * 
	 * @throws Exception
	 */
	public void saveHosts() throws Exception{
		databaseManager.saveHosts(super.browserManager.getHostList());
	}
	//-----
	/**	Dh	31.07.2023
	 * 
	 */
	public void saveAll() throws Exception{
		saveGenres();
		saveShows();
		saveShowLists();
		saveHosts();
	}
	
	/**	Dh	27.07.2023
	 * 
	 * @param pID
	 * @throws Exception
	 */
	public void loadShowList(int pID) throws Exception{
		ShowList vCur, vTemp;
		
		if (pID >= 0) {
			vCur = databaseManager.loadShowList(pID);
			
			showLists.add(vCur);
			sortListByID(showLists);
		}else throw new Exception("02; lSL,ShM");
	}
	//-----
	/**	Dh	31.07.2023
	 * 
	 * @throws Exception
	 */
	public void loadHosts() throws Exception{
		ArrayList<Host> vHost = databaseManager.loadHosts();
		
		if (vHost == null) vHost = new ArrayList<Host>();
		
		super.browserManager.setHostList(vHost);
	}
	
	/**	Dh	31.07.2023
	 * 
	 * @throws Exception
	 */
	public void loadAll() throws Exception {
		try {
			genres = databaseManager.loadGenres();
			shows = databaseManager.loadShows();
			
			showLists = databaseManager.loadShowLists();
			loadHosts();
		}catch(Exception ex) {throw ex;}
		
		if (genres == null) genres = new ArrayList<Genre>();
		if (shows == null) shows = new ArrayList<ShowElement>();
		
		if (showLists == null) showLists = new ArrayList<ShowList>();
	}
	
	//------------------------------------------------------------------------------------------------
	
	/**	Dh	05.08.2023
	 * 
	 * @param pExportPath
	 * @throws Exception
	 */
	public void exportDatabase(String pExportPath) throws Exception {
		DatabaseWrapper vDatabaseWrapper = new DatabaseWrapper();
		
		vDatabaseWrapper.loadFromDatabase(databaseManager);
		
		databaseManager.saveDatabaseXML(vDatabaseWrapper, pExportPath);
	}
	/**	Dh	05.08.2023
	 * 	
	 * @param pImportPath
	 * @throws Exception
	 */
	public void importDatabase(String pImportPath) throws Exception{
		DatabaseWrapper vDatabaseWrapper = databaseManager.loadDatabaseXML(pImportPath);
		
		if (vDatabaseWrapper != null) {
			genres = vDatabaseWrapper.getGenres();
			shows = vDatabaseWrapper.getShows();
			showLists = vDatabaseWrapper.getShowLists();
			
			browserManager.setHostList( vDatabaseWrapper.getHosts() );
		}else throw new Exception("04; iDa,ShM");
	}
	
//----------------------------------------------------------------------------------------------------
	
	/**	Dh	27.07.2023
	 * 
	 * @param pURL
	 * @param vHostID
	 * @return
	 * @throws Exception
	 */
	private String prepareURL(String pURL, int vHostID) throws Exception{
		String vHostURL;
		String vRet = "";
		
		if (vHostID != -1) vHostURL = super.browserManager.getHostURL(vHostID);
		else vHostURL = "";
		
		if (pURL != null) {
			if ( !pURL.equals("") && !vHostURL.equals("")) vRet = pURL.replace(vHostURL, "");
			else vRet = pURL;
		} else throw new Exception("04; pURL,ShM");
		
		return vRet;
	}
	
}
