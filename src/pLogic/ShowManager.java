/**	ShowManager v0.0	Dh	31.7.2020
 * 
 * 	pLogic
 * 	  LogicManager
 * 	 	ShowManager
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

public class ShowManager extends LogicManager {
	private List genres, shows, showLists;
	
	/**	Dh	31.7.2020
	 * 
	 * @param pDatabaseInterface
	 * @param pBrowserOpener
	 */
	public ShowManager(DatabaseInterface pDatabaseInterface, BrowserOpener pBrowserOpener) {
		super(pDatabaseInterface);
		
		if (pBrowserOpener != null) opener = pBrowserOpener;
		else MainManager.handleException(new Exception("04; ShMan"));
		
		try {
			loadAll();
		}catch(Exception ex) {MainManager.handleException(ex);}
	}
	
//----------------------------------------------------------------------------------------------------
	
	/**	Dh	19.7.2020
	 * 
	 * @return
	 */
	public List getGenreIDs() {
		List vRet = new List();
		
		if (!genres.isEmpty()) {
			genres.toFirst();
			
			while(!genres.isEnd()) {
				vRet.append(((Genre)genres.getCurrent()).getId());
				
				genres.next();
			}
		}
		
		return vRet;
	}
	/**	Dh	19.7.2020
	 * 
	 * @return
	 */
	public List getShowIDs() {
		List vRet = new List();
		
		if (!shows.isEmpty()) {
			shows.toFirst();
			
			while(!shows.isEnd()) {
				vRet.append(((ShowElement)shows.getCurrent()).getId());
				
				shows.next();
			}
		}
		
		return vRet;
	}
	/**	Dh	19.7.2020
	 * 
	 * @return
	 */
	public List getShowListIDs() {
		List vRet = new List();
		
		if (!showLists.isEmpty()) {
			showLists.toFirst();
			
			while(!showLists.isEnd()) {
				vRet.append(((ShowList)showLists.getCurrent()).getId());
				
				showLists.next();
			}
		}
		
		return vRet;
	}
	
	/**	Dh	19.7.2020
	 * 
	 * @param pID
	 * @return
	 * @throws Exception
	 */
	public String getGenreName(int pID) throws Exception{
		String vRet = null;
		Genre vCur;
		
		if ((pID >= 0) && !genres.isEmpty()) {
			genres.toFirst();
			
			while(!genres.isEnd() && (vRet == null)) {
				vCur = (Genre)genres.getCurrent();
				
				if (vCur.getId() == pID) vRet = vCur.getName();
				
				genres.next();
			}
		} else throw new Exception("02; ShMan,gGN");
		
		return vRet;
	}
	//-----
	/**	Dh	19.7.2020
	 * 
	 * @param pID
	 * @return
	 * @throws Exception
	 */
	public String getShowName(int pID) throws Exception{
		String vRet = null;
		ShowElement vCur;
		
		if ((pID >= 0) && !shows.isEmpty()) {
			shows.toFirst();
			
			while(!shows.isEnd() && (vRet == null)) {
				vCur = (ShowElement)shows.getCurrent();
				
				if (vCur.getId() == pID) vRet = vCur.getName();
				
				shows.next();
			}
		} else throw new Exception("02; ShMan,gSN");
		
		return vRet;
	}
	/**	Dh	19.7.2020
	 * 
	 * @param pID
	 * @return
	 * @throws Exception
	 */
	public int getShowRating(int pID) throws Exception{
		int vRet = -1;
		ShowElement vCur;
		
		if ((pID >= 0) && !shows.isEmpty()) {
			shows.toFirst();
			
			while(!shows.isEnd() && (vRet == -1)) {
				vCur = (ShowElement)shows.getCurrent();
				
				if (vCur.getId() == pID) vRet = vCur.getRating();
				
				shows.next();
			}
		} else throw new Exception("02; ShMan,gSR");
		
		return vRet;
	}
	/**	Dh	27.7.2020
	 * 
	 * @param pID
	 * @return
	 * @throws Exception
	 */
	public int getShowHostID(int pID) throws Exception{
		int vRet = -1;
		ShowElement vCur;
		
		if ((pID >= 0) && !shows.isEmpty()) {
			shows.toFirst();
			
			while(!shows.isEnd() && (vRet == -1)) {
				vCur = (ShowElement)shows.getCurrent();
				
				if (vCur.getId() == pID) vRet = vCur.getHostID();
				
				shows.next();
			}
		} else throw new Exception("02; ShMan,gSHID");
		
		return vRet;
	}
	/**	Dh	28.7.2020
	 * 
	 * @param pID
	 * @return
	 * @throws Exception
	 */
	public String getShowURL(int pID) throws Exception{
		int vHostID;
		String vRet = null;
		ShowElement vCur;
		
		if ((pID >= 0) && !shows.isEmpty()) {
			shows.toFirst();
			
			while(!shows.isEnd() && (vRet == null)) {
				vCur = (ShowElement)shows.getCurrent();
				
				if (vCur.getId() == pID) {
					vHostID = vCur.getHostID();
					
					if (vHostID != -1) vRet = opener.getHosterURL(vHostID);
					else vRet = "";
					
					vRet += vCur.getUrl();
				}
				
				shows.next();
			}
		} else throw new Exception("02; ShMan,gSURL");
		
		return vRet;
	}
	/**	Dh	19.7.2020
	 * 
	 * @param pID
	 * @return
	 * @throws Exception
	 */
	public List getShowGenreIDs(int pID) throws Exception{
		List vRet = null;
		ShowElement vCur;
		
		if ((pID >= 0) && !shows.isEmpty()) {
			shows.toFirst();
			
			while(!shows.isEnd() && (vRet == null)) {
				vCur = (ShowElement)shows.getCurrent();
				
				if (vCur.getId() == pID) vRet = vCur.getGenreList();
				
				shows.next();
			}
		} else throw new Exception("02; ShMan,gSGID");
		
		return vRet;
	}
	//-----
	/**	Dh	19.7.2020
	 * 
	 * @param pID
	 * @return
	 * @throws Exception
	 */
	public String getShowListName(int pID) throws Exception{
		String vRet = null;
		ShowList vCur;
		
		if ((pID >= 0) && !showLists.isEmpty()) {
			showLists.toFirst();
			
			while(!showLists.isEnd() && (vRet == null)) {
				vCur = (ShowList)showLists.getCurrent();
				
				if (vCur.getId() == pID) vRet = vCur.getName();
				
				showLists.next();
			}
		} else throw new Exception("02; ShMan,gSLN");
		
		return vRet;
	}
	/**	Dh	22.7.2020
	 * 
	 * @param pID
	 * @return
	 * @throws Exception
	 */
	public List getShowListShowIDs(int pID) throws Exception{
		List vRet = null;
		ShowList vCur;
		
		if ((pID >= 0) && !showLists.isEmpty()) {
			showLists.toFirst();
			
			while(!showLists.isEnd() && (vRet == null)) {
				vCur = (ShowList)showLists.getCurrent();
				
				if (vCur.getId() == pID) vRet = vCur.getShowList();
				
				showLists.next();
			}
		} else throw new Exception("02; ShMan,gSLSID");
		
		return vRet;
	}
	
	//------------------------------------------------------------------------------------------------
	
	/**	Dh	19.7.2020
	 * 
	 * @param pID
	 * @param pName
	 * @throws Exception
	 */
	public void setGenreName(int pID, String pName) throws Exception{
		Genre vCur;
		
		if ((pID >= 0) && !genres.isEmpty()) {
			genres.toFirst();
			
			while(!genres.isEnd()) {
				vCur = (Genre)genres.getCurrent();
				
				if (vCur.getId() == pID) {
					vCur.setName(pName);
					genres.toLast();
				}
				
				genres.next();
			}
		} else throw new Exception("02; ShMan,sGN");
	}
	//-----
	/**	Dh	19.7.2020
	 * 
	 * @param pID
	 * @param pName
	 * @throws Exception
	 */
	public void setShowName(int pID, String pName) throws Exception{
		ShowElement vCur;
		
		if ((pID >= 0) && !shows.isEmpty()) {
			shows.toFirst();
			
			while(!shows.isEnd()) {
				vCur = (ShowElement)shows.getCurrent();
				
				if (vCur.getId() == pID) {
					vCur.setName(pName);
					shows.toLast();
				}
				
				shows.next();
			}
		} else throw new Exception("02; ShMan,sSN");
	}
	/**	Dh	19.7.2020
	 * 
	 * @param pID
	 * @param pRating
	 * @throws Exception
	 */
	public void setShowRating(int pID, int pRating) throws Exception{
		ShowElement vCur;
		
		if ((pID >= 0) && !shows.isEmpty()) {
			shows.toFirst();
			
			while(!shows.isEnd()) {
				vCur = (ShowElement)shows.getCurrent();
				
				if (vCur.getId() == pID) {
					vCur.setRating(pRating);
					shows.toLast();
				}
				
				shows.next();
			}
		} else throw new Exception("02; ShMan,sSR");
	}
	/**	Dh	27.7.2020
	 * 
	 * @param pShowID
	 * @param pHostID
	 * @throws Exception
	 */
	public void setShowHostID(int pShowID, int pHostID) throws Exception{
		ShowElement vCur;
		
		if ((pShowID >= 0)) {
			shows.toFirst();
			
			while(!shows.isEnd()) {
				vCur = (ShowElement)shows.getCurrent();
				
				if (vCur.getId() == pShowID) {
					vCur.setHostID(pHostID);
					shows.toLast();
				}
				
				shows.next();
			}
		} else throw new Exception("02; ShMan,sSHID");
	}
	/**	Dh	28.7.2020
	 * 
	 * @param pID
	 * @param pURL
	 * @throws Exception
	 */
	public void setShowURL(int pID, String pURL) throws Exception{
		int vHostID;
		String vHostURL;
		ShowElement vCur;
		
		if ((pID >= 0) && !shows.isEmpty()) {
			shows.toFirst();
			
			while(!shows.isEnd()) {
				vCur = (ShowElement)shows.getCurrent();
				
				if (vCur.getId() == pID) {
					vCur.setUrl(prepareURL(pURL, vCur.getHostID()));
					shows.toLast();
				}
				
				shows.next();
			}
		} else throw new Exception("02; ShMan,sSURL");
	}
	/**	Dh	19.7.2020
	 * 
	 * @param pID
	 * @param pGenereIDList
	 * @throws Exception
	 */
	public void setShowGenreIDs(int pID, List pGenreIDList) throws Exception{
		ShowElement vCur;
		
		if ((pID >= 0) && !shows.isEmpty()) {
			shows.toFirst();
			
			while(!shows.isEnd()) {
				vCur = (ShowElement)shows.getCurrent();
				
				if (vCur.getId() == pID) {
					vCur.setGenreList(pGenreIDList);
					shows.toLast();
				}
				
				shows.next();
			}
		} else throw new Exception("02; ShMan,sSGID");
	}
	//-----
	/**	Dh	19.7.2020
	 * 
	 * @param pID
	 * @param pName
	 * @throws Exception
	 */
	public void setShowListName(int pID, String pName) throws Exception{
		ShowList vCur;
		
		if ((pID >= 0) && !showLists.isEmpty()) {
			showLists.toFirst();
			
			while(!showLists.isEnd()) {
				vCur = (ShowList)showLists.getCurrent();
				
				if (vCur.getId() == pID) {
					vCur.setName(pName);
					showLists.toLast();
				}
				
				showLists.next();
			}
		} else throw new Exception("02; ShMan,sSLN");
	}
	/**	Dh	19.7.2020
	 * 
	 * @param pID
	 * @param pShowIDList
	 * @throws Exception
	 */
	public void setShowListShowIDs(int pID, List pShowIDList) throws Exception{
		ShowList vCur;
		
		if ((pID >= 0) && !showLists.isEmpty()) {
			showLists.toFirst();
			
			while(!showLists.isEnd()) {
				vCur = (ShowList)showLists.getCurrent();
				
				if (vCur.getId() == pID) {
					vCur.setShowList(pShowIDList);
					showLists.toLast();
				}
				
				showLists.next();
			}
		} else throw new Exception("02; ShMan,gSLSID");
	}
	
//----------------------------------------------------------------------------------------------------
	
	/**	Dh	22.7.2020
	 * 
	 * @param pID
	 * @return
	 * @throws Exception
	 */
	public boolean haveGenre(int pID) throws Exception{
		boolean vRet = false;
		Genre vCur;
		
		if ((pID >= 0) && !genres.isEmpty()) {
			genres.toFirst();
			
			while(!genres.isEnd()) {
				vCur = (Genre)genres.getCurrent();
				
				if (vCur.getId() == pID) {
					vRet = true;
					genres.toLast();
				}
				
				genres.next();
			}
		} else throw new Exception("02; ShMan,hG");
		
		return vRet;
	}
	/**	Dh	22.7.2020
	 * 
	 * @param pID
	 * @return
	 * @throws Exception
	 */
	public boolean haveShow(int pID) throws Exception{
		boolean vRet = false;
		ShowElement vCur;
		
		if ((pID >= 0) && !shows.isEmpty()) {
			shows.toFirst();
			
			while(!shows.isEnd()) {
				vCur = (ShowElement)shows.getCurrent();
				
				if (vCur.getId() == pID) {
					vRet = true;
					shows.toLast();
				}
				
				shows.next();
			}
		} else throw new Exception("02; ShMan,hS");
		
		return vRet;
	}
	/**	Dh	22.7.2020
	 * 
	 * @param pID
	 * @return
	 * @throws Exception
	 */
	public boolean haveShowList(int pID) throws Exception{
		boolean vRet = false;
		ShowList vCur;
		
		if ((pID >= 0) && !showLists.isEmpty()) {
			showLists.toFirst();
			
			while(!showLists.isEnd()) {
				vCur = (ShowList)showLists.getCurrent();
				
				if (vCur.getId() == pID) {
					vRet = true;
					showLists.toLast();
				}
				
				showLists.next();
			}
		} else throw new Exception("02; ShMan,gSL");
		
		return vRet;
	}
	
	/**	Dh	19.7.2020
	 * 
	 * @param pShowID
	 * @param pGenreID
	 * @return
	 * @throws Exception
	 */
	public boolean hasShowGenre(int pShowID, int pGenreID) throws Exception{
		boolean vRet = false;
		ShowElement vCur;
		
		if ((pShowID >= 0) && !shows.isEmpty()) {
			shows.toFirst();
			
			while(!shows.isEnd()) {
				vCur = (ShowElement)shows.getCurrent();
				
				if (vCur.getId() == pShowID) {
					vRet = vCur.haveGenre(pGenreID);
					shows.toLast();
				}
				
				shows.next();
			}
		} else throw new Exception("02; ShMan,hSG");
		
		return vRet;
	}
	/**	Dh	19.7.2020
	 * 
	 * @param pShowListID
	 * @param pShowID
	 * @return
	 * @throws Exception
	 */
	public boolean hasShowListShow(int pShowListID, int pShowID) throws Exception{
		boolean vRet = false;
		ShowList vCur;
		
		if ((pShowListID >= 0) && !showLists.isEmpty()) {
			showLists.toFirst();
			
			while(!showLists.isEnd()) {
				vCur = (ShowList)showLists.getCurrent();
				
				if (vCur.getId() == pShowListID) {
					vRet = vCur.haveShow(pShowID);
					showLists.toLast();
				}
				
				showLists.next();
			}
		} else throw new Exception("02; ShMan,gSLN");
		
		return vRet;
	}
	
	//------------------------------------------------------------------------------------------------
	
	/**	Dh	30.7.2020
	 * 
	 * @param pName
	 * @throws Exception
	 */
	public void addGenre(String pName) throws Exception{
		genres.append(new Genre(dertermineID(genres), pName));
		sortListByID(genres);
	}
	//-----
	/**	Dh	30.7.2020
	 * 
	 * @param pName
	 * @param pHostID
	 * @param pURL
	 * @throws Exception
	 */
	public void addShow(String pName, int pHostID, String pURL) throws Exception {
		shows.append(new ShowElement(dertermineID(shows), pName, pHostID, prepareURL(pURL, pHostID)));
		sortListByID(shows);
	}
	/**	Dh	30.7.2020
	 * 
	 * @param pName
	 * @param pHostID
	 * @param pURL
	 * @param pRating
	 * @throws Exception
	 */
	public void addShow(String pName, int pHostID, String pURL, int pRating) throws Exception {
		shows.append(new ShowElement(dertermineID(shows), pName, pHostID, prepareURL(pURL, pHostID), pRating));
		sortListByID(shows);
	}
	/**	Dh	30.7.2020
	 * 
	 * @param pName
	 * @param pHostID
	 * @param pURL
	 * @param pGenereIDList
	 * @throws Exception
	 */
	public void addShow(String pName, int pHostID, String pURL, List pGenereIDList) throws Exception {
		shows.append(new ShowElement(dertermineID(shows), pName, pHostID, prepareURL(pURL, pHostID), pGenereIDList));
		sortListByID(shows);
	}
	/**	Dh	30.7.2020
	 * 
	 * @param pName
	 * @param pHostID
	 * @param pURL
	 * @param pRating
	 * @param pGenereIDList
	 * @throws Exception
	 */
	public void addShow(String pName, int pHostID, String pURL, int pRating, List pGenereIDList) throws Exception {
		shows.append(new ShowElement(dertermineID(shows), pName, pHostID, prepareURL(pURL, pHostID), pRating, pGenereIDList));
		sortListByID(shows);
	}
	//-----
	/**	Dh	30.7.2020
	 * 
	 * @param pName
	 * @throws Exception
	 */
	public void addShowList(String pName) throws Exception{
		showLists.append(new ShowList(dertermineID(showLists), pName));
		sortListByID(showLists);
	}
	/**	Dh	30.7.2020
	 * 
	 * @param pName
	 * @param pShowsIDList
	 * @throws Exception
	 */
	public void addShowList(String pName, List pShowsIDList) throws Exception{
		showLists.append(new ShowList(dertermineID(showLists), pName, pShowsIDList));
		sortListByID(showLists);
	}
	
	/**	Dh	19.7.2020
	 * 
	 * @param pShowID
	 * @param pGenereID
	 * @throws Exception
	 */
	public void addGenreToShow(int pShowID, int pGenreID) throws Exception{
		ShowElement vCur;
		
		if ((pShowID >= 0) && !shows.isEmpty()) {
			shows.toFirst();
			
			while(!shows.isEnd()) {
				vCur = (ShowElement)shows.getCurrent();
				
				if (vCur.getId() == pShowID) {
					vCur.addGenre(pGenreID);
					shows.toLast();
				}
				
				shows.next();
			}
		} else throw new Exception("02; ShMan,aGtS");
	}
	
	/**	Dh	19.7.2020
	 * 
	 * @param pShowListID
	 * @param pShowID
	 * @throws Exception
	 */
	public void addShowToShowList(int pShowListID, int pShowID) throws Exception {
		ShowList vCur;
		
		if ((pShowListID >= 0) && !showLists.isEmpty()) {
			showLists.toFirst();
			
			while(!showLists.isEnd()) {
				vCur = (ShowList)showLists.getCurrent();
				
				if (vCur.getId() == pShowListID) {
					vCur.addShow(pShowID);
					showLists.toLast();
					System.out.println("addShowToShowList 0");
				}
				
				showLists.next();
			}
		} else throw new Exception("02; ShMan,aStSL");
	}
	
	//------------------------------------------------------------------------------------------------
	
	/**	Dh	28.7.2020
	 * 
	 * @param pID
	 * @throws Exception
	 */
	public void removeGenre(int pID) throws Exception{
		if (!genres.isEmpty()) {
			genres.toFirst();
			
			if (!shows.isEmpty()) {
				shows.toFirst();
				
				while(!shows.isEnd()) {
					((ShowElement)shows.getCurrent()).removeGenre(pID);
					
					shows.next();
				}
			}
			
			while(!genres.isEnd()) {
				if ( ((Genre)genres.getCurrent()).getId() == pID ) {
					genres.remove();
					genres.toLast();
				}
				
				genres.next();
			}
		}
	}
	/**	Dh	28.7.2020
	 * 
	 * @param pID
	 * @throws Exception
	 */
	public void removeShow(int pID) throws Exception{
		if (!shows.isEmpty()) {
			shows.toFirst();
			
			if (!showLists.isEmpty()) {
				showLists.toFirst();
				
				while(!showLists.isEnd()) {
					((ShowList)showLists.getCurrent()).removeShow(pID);
					
					showLists.next();
				}
			}
			
			while(!shows.isEnd()) {
				if ( ((ShowElement)shows.getCurrent()).getId() == pID ) {
					shows.remove();
					shows.toLast();
				}
				
				shows.next();
			}
		}
	}
	/**	Dh	19.7.2020
	 * 
	 * @param pID
	 * @throws Exception
	 */
	public void removeShowList(int pID) throws Exception{
		if (!showLists.isEmpty()) {
			showLists.toFirst();
			
			while(!showLists.isEnd()) {
				if ( ((ShowList)showLists.getCurrent()).getId() == pID ) {
					showLists.remove();
					showLists.toLast();
				}
				
				showLists.next();
			}
		}
	}
	
	/**	Dh	28.7.2020
	 * 
	 * @param pID
	 * @throws Exception
	 */
	public void removeHost(int pID) throws Exception{
		ShowElement vCur;
		
		if (pID >= 0) {
			if (!shows.isEmpty()) {
				shows.toFirst();
				
				while(!shows.isEnd()) {
					vCur = (ShowElement)shows.getCurrent();
					
					if (vCur.getHostID() == pID) vCur.setHostID(-1);
				
					shows.next();
				}
			}
		} else throw new Exception("02; ShMan,rH");
	}
	
	/**	Dh	19.7.2020
	 * 
	 * @param pShowID
	 * @param pGenereID
	 * @throws Exception
	 */
	public void removeGenreOfShow(int pShowID, int pGenreID) throws Exception{
		ShowElement vCur;
		
		if ((pShowID >= 0) && !shows.isEmpty()) {
			shows.toFirst();
			
			while(!shows.isEnd()) {
				vCur = (ShowElement)shows.getCurrent();
				
				if (vCur.getId() == pShowID) {
					vCur.removeGenre(pGenreID);
					
					shows.toLast();
				}
				
				shows.next();
			}
		} else throw new Exception("02; ShMan,rGoS");
	}
	/**	Dh	19.7.2020
	 * 
	 * @param pShowListID
	 * @param pShowID
	 * @throws Exception
	 */
	public void removeShowOfShowList(int pShowListID, int pShowID) throws Exception{
		ShowList vCur;
		
		if ((pShowListID >= 0) && !showLists.isEmpty()) {
			showLists.toFirst();
			
			while(!showLists.isEnd()) {
				vCur = (ShowList)showLists.getCurrent();
				
				if (vCur.getId() == pShowListID) {
					vCur.removeShow(pShowID);
					showLists.toLast();
				}
				
				showLists.next();
			}
		} else throw new Exception("02; ShMan,rSoSL");
	}
	
//----------------------------------------------------------------------------------------------------
	
	/**	Dh	19.7.2020
	 * 
	 * @param pShowID
	 * @throws Exception
	 */
	public void openShow(int pShowID) throws Exception{
		ShowElement vCur;
		
		if ((pShowID >= 0) && !shows.isEmpty()) {
			shows.toFirst();
			
			while(!shows.isEnd()) {
				vCur = (ShowElement)shows.getCurrent();
				
				if (vCur.getId() == pShowID) {
					opener.openShow(vCur);
					shows.toLast();
				}
				
				shows.next();
			}
		} else throw new Exception("02; ShMan,oS");
	}
	
	//------------------------------------------------------------------------------------------------
	
	/**	Dh	19.7.2020
	 * 
	 * @throws Exception
	 */
	public void saveGenres() throws Exception {
		databaseInterface.saveGenres(genres);
	}
	//-----
	/**	Dh	19.7.2020
	 * 
	 * @throws Exception
	 */
	public void saveShows() throws Exception{
		databaseInterface.saveShows(shows);
	}
	//-----
	/**	Dh	19.7.2020
	 * 
	 * @param pID
	 * @throws Exception
	 */
	public void saveShowList(int pID) throws Exception{
		ShowList vCur;
		
		if ((pID >= 0) && (!showLists.isEmpty())) {
			showLists.toFirst();
			
			while(!showLists.isEnd()) {
				vCur = (ShowList)showLists.getCurrent();
				if ( vCur.getId() == pID ) {
					databaseInterface.saveShowList(vCur);
					showLists.toLast();
				}
				
				showLists.next();
			}
		} else throw new Exception("02; ShMan,sSL");
	}
	/**	Dh	19.7.2020
	 * 
	 * @throws Exception
	 */
	public void saveShowLists() throws Exception{
		databaseInterface.saveShowLists(showLists);
	}
	//-----
	/**	Dh	19.7.2020
	 * 
	 * @throws Exception
	 */
	public void saveAll() throws Exception{
		saveGenres();
		saveShows();
		saveShowLists();
	}
	
	/**	Dh	19.7.2020
	 * 
	 * @param pID
	 * @throws Exception
	 */
	public void loadShowList(int pID) throws Exception{
		ShowList vCur, vTemp;
		
		if (pID >= 0) {
			vCur = databaseInterface.loadShowList(pID);
			
			if (!showLists.isEmpty()) {
				showLists.toFirst();
				
				while(!showLists.isEnd()) {
					vTemp = (ShowList)showLists.getCurrent();
					
					if (vTemp.getId() > vCur.getId()) {
						showLists.insert(vCur);
						showLists.toLast();
						vCur = null;
					}
					
					showLists.next();;
				}
				
				if (vCur != null) showLists.append(vCur);
			} else showLists.append(vCur);
		}else throw new Exception("02; ShMan,lSL");
	}
	
	/**	Dh	31.7.2020
	 * 
	 * @throws Exception
	 */
	public void loadAll() throws Exception {
		try {
			genres = databaseInterface.loadGenres();
			shows = databaseInterface.loadShows();
			
			showLists = databaseInterface.loadShowLists();
		}catch(Exception ex) {throw ex;}
		
		if (genres == null) genres = new List();
		if (shows == null) shows = new List();
		
		if (showLists == null) showLists = new List();
	}
	
//----------------------------------------------------------------------------------------------------
	
	/**	Dh	28.7.2020
	 * 
	 * @param pURL
	 * @param vHostID
	 * @return
	 * @throws Exception
	 */
	private String prepareURL(String pURL, int vHostID) throws Exception{
		String vHostURL;
		String vRet = "";
		
		if (vHostID != -1) vHostURL = opener.getHosterURL(vHostID);
		else vHostURL = "";
		
		if (pURL != null) {
			if ( !pURL.equals("") && !vHostURL.equals("")) vRet = pURL.replace(vHostURL, "");
			else vRet = pURL;
		} else throw new Exception("04; ShMan,pURL");
		
		return vRet;
	}
	
}
