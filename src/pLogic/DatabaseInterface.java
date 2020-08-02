/**	ShowManager v0.0	Dh	31.7.2020
 * 
 * 	pLogic
 * 	  DatabaseInterface
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

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import pDataStructures.List;

public class DatabaseInterface {
	private File systemFile;
	private File showsFile, genreFile, settingFile, showListFile;
	
	private EntityManagerFactory rEMF;
	private EntityManager rEM;
	
	private DatabaseSettings databaseSettings;
	
	/**	Dh	31.7.2020
	 * 
	 */
	public DatabaseInterface() throws Exception{
		String vOS, vHome;
		
		vOS = System.getProperty("os.name");
		vHome = System.getProperty("user.home");
		
		if (vOS.contains("Windows")) vHome = vHome+ "/AppData/Roaming/ShowManager";
		else if (vOS.contains("Linux")) {
			vHome = vHome+"/.local/ShowManager";
			
		}
		else MainManager.handleException(new Exception("20; DaInt"));
		
		systemFile = new File(vHome);
		if (!systemFile.exists()) systemFile.mkdir();
		
		showsFile = new File(vHome+"");
		genreFile = new File(vHome+"");
		settingFile = new File(vHome+"/settings");
		if (!settingFile.exists()) settingFile.mkdir();
		
		showListFile = new File(vHome+"");
		
		try {
			databaseSettings = loadDatabaseSettings();
			initDatabaseConnection();
		}catch(Exception ex) {
			if (!ex.getMessage().equals("21; DaInt,lDS")) MainManager.handleException(ex);
			else databaseSettings = new DatabaseSettings();
		}
	}
	
	/**	Dh	31.7.2020
	 * 
	 */
	protected void closeDatabaseInterface() {
		if (rEM != null) {
			rEM.close();
			rEMF.close();
		}
	}

	protected String getOSName() {
		return systemFile.getAbsolutePath();
	}
	
	//------------------------------------------------------------------------------------------------
	
	/**	Dh	31.7.2020
	 * 
	 */
	protected void initDatabaseConnection() throws Exception{
		String vDatabase, vUser, vPassword;
		
		vDatabase = databaseSettings.getDatabaseName();
		vUser = databaseSettings.getUserName();
		vPassword = databaseSettings.getPassword();
		
		initDatabaseConnection(vDatabase, vUser, vPassword);
	}
	/**	Dh	31.7.2020
	 * 
	 * @param pDatabase
	 * @param pUser
	 * @param pPassword
	 * @throws Exception
	 */
	protected void initDatabaseConnection(String pDatabase, String pUser, String pPassword) throws Exception{
		try {
			Map<String, String> vProps = new HashMap<String, String>();
				 
			vProps.put("javax.persistence.jdbc.url", "jdbc:mysql://localhost:3306/"+pDatabase+"?serverTimezone=UTC");
			vProps.put("javax.persistence.jdbc.user", pUser);
			vProps.put("javax.persistence.jdbc.password", pPassword);
			
			rEMF = Persistence.createEntityManagerFactory("ShowManager", vProps);
			rEM = rEMF.createEntityManager();
			
			if ((rEM == null) || (rEM.isOpen() == false)) {
				rEMF.close();
				
				rEM = null;
				rEMF = null;
			}
		}catch(Exception ex) {
			if (ex.getMessage().contains("Error Code: 1045")) {
				rEM = null;
				rEMF = null;
			}
			else throw ex;
		}
	}
	
//----------------------------------------------------------------------------------------------------
	
	/**	Dh	31.7.2020
	 * 
	 * @return
	 */
	protected DatabaseSettings getDatabaseSettings() {
		return databaseSettings;
	}
	
	/**	Dh	31.7.2020
	 * 
	 * @return
	 */
	protected boolean haveConnection() {
		boolean vRet = false;
		
		if (rEM != null) vRet = true;
		
		return vRet;
	}
	
	/**	Dh	31.7.2020
	 * 
	 * @param pDatabase
	 * @param pUser
	 * @param pPassword
	 * @return
	 * @throws Exception
	 */
	protected boolean ping(String pDatabase, String pUser, String pPassword) throws Exception{
		boolean vRet = false;
		
		initDatabaseConnection(pDatabase, pUser, pPassword);
		
		if (haveConnection()) {
			vRet = true;
			
			rEM.close();
			rEMF.close();
			rEM = null;
			rEMF = null;
		}
		
		return vRet;
	}
	
//----------------------------------------------------------------------------------------------------
	
	/**	Dh	31.7.2020
	 * 
	 * @return
	 */
	protected List loadGenres() {
		List vRet = new List();
		
		if (rEM != null) {
			Query vQuery = rEM.createQuery("Select g.id from Genre g");
			
			java.util.List<Integer> vList = vQuery.getResultList();
			
			if (vList.size() > 0) {
				for (int i=0; i < vList.size(); i++) {
					vRet.append(rEM.find(Genre.class, vList.get(i)));
				}	
			}
		}
		
		return vRet;
	}
	/**	Dh	31.7.2020
	 * 
	 * @return
	 * @throws Exception
	 */
	protected List loadShows() throws Exception {
		ShowElement vCurShow;
		GenreListElementWrapper vCurWrapper;
		List vRet = new List();
		
		if (rEM != null) {
			Query vQuery = rEM.createQuery("Select s.id from ShowElement s");
			
			java.util.List<Integer> vList = vQuery.getResultList();
			
			if (vList.size() > 0) {
				for (int i=0; i < vList.size(); i++) {
					vCurShow = rEM.find(ShowElement.class, vList.get(i));
					vCurWrapper = rEM.find(GenreListElementWrapper.class, vList.get(i));
					
					vCurShow.setGenreList(vCurWrapper.getGenreIDList());
					
					vRet.append(vCurShow);
				}	
			}
		}
		
		return vRet;
	}
	
	protected ShowList loadShowList(int pID) throws Exception{
		return null;
	}
	/**	Dh	31.7.2020
	 * 
	 * @return
	 * @throws Exception
	 */
	protected List loadShowLists() throws Exception {
		ShowList vCurShowList;
		ShowListElementWrapper vCurWrapper;
		List vRet = new List();
		
		if (rEM != null) {
			Query vQuery = rEM.createQuery("Select sl.id from ShowList sl");
			
			java.util.List<Integer> vList = vQuery.getResultList();
			
			if (vList.size() > 0) {
				for (int i=0; i < vList.size(); i++) {
					vCurShowList = rEM.find(ShowList.class, vList.get(i));
					vCurWrapper = rEM.find(ShowListElementWrapper.class, vList.get(i));
					
					vCurShowList.setShowList(vCurWrapper.getShowIDList());
					
					vRet.append(vCurShowList);
				}	
			}
		}
		
		return vRet;
	}
	/**	Dh	31.7.2020
	 * 
	 * @return
	 */
	protected List loadHosts() {
		List vRet = new List();
		
		if (rEM != null) {
			Query vQuery = rEM.createQuery("Select h.id from Host h");
			
			java.util.List<Integer> vList = vQuery.getResultList();
			
			if (vList.size() > 0) {
				for (int i=0; i < vList.size(); i++) {
					vRet.append(rEM.find(Host.class, vList.get(i)));
				}	
			}
		}
		
		return vRet;
	}
	
	/**	Dh	28.7.2020
	 * 
	 * @return
	 * @throws Exception
	 */
	protected GUISettings loadGUISettings() throws Exception{
		GUISettings vRet = null;
		File vFile = new File(settingFile+"/guiSettings"+".xml");
		
		if (vFile.exists()) vRet = JAXB.unmarshal(vFile, GUISettings.class);
		else throw new Exception("21; DaInt,lGUIS");
		
		return vRet;
	}
	/**	Dh	28.7.2020
	 * 
	 * @return
	 * @throws Exception
	 */
	protected LogicSettings loadLogicSettings() throws Exception{
		LogicSettings vRet = null;
		File vFile = new File(settingFile+"/logicSettings"+".xml");
		
		if (vFile.exists()) vRet = JAXB.unmarshal(vFile, LogicSettings.class);
		else throw new Exception("21; DaInt,lLS");
		
		return vRet;
	}
	/**	Dh	31.7.2020
	 * 
	 * @return
	 * @throws Exception
	 */
	protected DatabaseSettings loadDatabaseSettings() throws Exception{
		DatabaseSettings vRet = null;
		File vFile = new File(settingFile+"/databaseSettings"+".xml");
		
		if (vFile.exists()) vRet = JAXB.unmarshal(vFile, DatabaseSettings.class);
		else throw new Exception("21; DaInt,lDS");
		
		return vRet;
	}
	
	//------------------------------------------------------------------------------------------------
	
	/**	Dh	31.7.2020
	 * 
	 * @param pGenereList
	 * @throws Exception
	 */
	protected void saveGenres(List pGenereList) throws Exception{
		Object vCur;
		List vCurList = pGenereList.copyList();
		List vExisList = loadGenres();
		
		if (rEM != null) {
			rEM.getTransaction().begin();
			if (!vCurList.isEmpty()) {
				if (!vExisList.isEmpty()) {
					vExisList.toFirst();
					
					while(!vExisList.isEnd()) {
						vCur = vExisList.getCurrent();
						
						if (vCur instanceof Genre) {
							if ((!vCurList.isEmpty()) && (vCurList.haveObject(vCur))) vCurList.deleteElement(vCur);
							else rEM.remove(vCur);
						} else throw new Exception("06; DaInt,sG");
						
						vExisList.next();
					}
				}
			}
			
			rEM.getTransaction().commit();
			rEM.getTransaction().begin();
			
			if (!vCurList.isEmpty()) {
				vCurList.toFirst();
				
				while(!vCurList.isEnd()) {
					vCur = vCurList.getCurrent();
					
					if (vCur instanceof Genre) {
						rEM.persist(vCur);
					} else throw new Exception("06; DaInt,sG");
					
					vCurList.next();
				}
			}
			
			rEM.getTransaction().commit();
		}
	}
	/**	Dh	31.7.2020
	 * 
	 * @param pShowList
	 * @throws Exception
	 */
	protected void saveShows(List pShowList) throws Exception {
		Object vCur;
		GenreListElementWrapper vCurWrap;
		List vCurList = pShowList.copyList();
		List vGenreListIDList = new List();
		
		if (rEM != null) {
			if (!pShowList.isEmpty()) {
				pShowList.toFirst();
				
				while(!pShowList.isEnd()) {
					vGenreListIDList.append( new Object[] {((ShowElement)pShowList.getCurrent()).getId(), ((ShowElement)pShowList.getCurrent()).getGenreList().copyList()});
					
					pShowList.next();
				}
			}
			
			List vExisList = loadShows();
			
			rEM.getTransaction().begin();
			if (!vCurList.isEmpty()) {
				if (!vExisList.isEmpty()) {
					vExisList.toFirst();
					
					while(!vExisList.isEnd()) {
						vCur = vExisList.getCurrent();
						
						if (vCur instanceof ShowElement) {
							if ((!vCurList.isEmpty()) && (vCurList.haveObject(vCur))) {
								vCurWrap = rEM.find(GenreListElementWrapper.class, ((ShowElement)vCur).getId());
								 
								 if (vCurWrap != null) vCurWrap.setGenreIDsPerList(getElementByID(vGenreListIDList, ((ShowElement)vCur).getId()));
								 else throw new Exception("04; DaInt,sS");
								 
								 vCurList.deleteElement(vCur);
							}
							else {
								rEM.remove(vExisList.getCurrent());
								rEM.remove(rEM.find(GenreListElementWrapper.class, ((ShowElement)vExisList.getCurrent()).getId()));
							}
						} else throw new Exception("06; DaInt,sS");
						
						vExisList.next();
					}
				}
			}
			
			rEM.getTransaction().commit();
			rEM.getTransaction().begin();
			
			if (!vCurList.isEmpty()) {
				vCurList.toFirst();
				
				while(!vCurList.isEnd()) {
					vCur = vCurList.getCurrent();
					
					if (vCur instanceof ShowElement) {
						vCurWrap = new GenreListElementWrapper((ShowElement)vCur);
						rEM.persist(vCur);
						rEM.persist(vCurWrap);
					} else throw new Exception("06; DaInt,sS");
					
					vCurList.next();
				}
			}
			
			rEM.getTransaction().commit();
		}
	}
	
	/**	Dh	27.7.2020
	 * 
	 * @param pShowList
	 * @throws Exception
	 */
	protected void saveShowList(ShowList pShowList) throws Exception{
		ShowListElementWrapper vCur;
		
		if (pShowList != null) {
			vCur = new ShowListElementWrapper(pShowList);
			
			rEM.getTransaction().begin();
			
			rEM.persist(pShowList);
			rEM.persist(vCur);
			
			rEM.getTransaction().commit();
		}
	}
	/**	Dh	31.7.2020
	 * 
	 * @param pShowListList
	 * @throws Exception
	 */
	protected void saveShowLists(List pShowListList) throws Exception{
		Object vCur;
		ShowListElementWrapper vCurWrap;
		List vCurList = pShowListList.copyList();
		List vShowListIDList = new List();
		
		if (rEM != null) {
			if (!pShowListList.isEmpty()) {
				pShowListList.toFirst();
				
				while(!pShowListList.isEnd()) {
					vShowListIDList.append( new Object[] {((ShowList)pShowListList.getCurrent()).getId(), ((ShowList)pShowListList.getCurrent()).getShowList().copyList()});
					
					pShowListList.next();
				}
			}
			
			List vExisList = loadShowLists();
			
			rEM.getTransaction().begin();
			if (!vCurList.isEmpty()) {
				if (!vExisList.isEmpty()) {
					vExisList.toFirst();
					
					while(!vExisList.isEnd()) {
						vCur = vExisList.getCurrent();
						
						if (vCur instanceof ShowList) {
							if ((!vCurList.isEmpty()) && (vCurList.haveObject(vCur))) {
								vCurWrap = rEM.find(ShowListElementWrapper.class, ((ShowList)vCur).getId());
								 
								 if (vCurWrap != null) vCurWrap.setShowIDsPerList(getElementByID(vShowListIDList, ((ShowList)vCur).getId()));
								 else throw new Exception("04; DaInt,sSL");
								 
								 vCurList.deleteElement(vCur);
							}
							else {
								rEM.remove(vExisList.getCurrent());
								rEM.remove(rEM.find(ShowListElementWrapper.class, ((ShowList)vExisList.getCurrent()).getId()));
							}
						} else throw new Exception("06; DaInt,sSL");
						
						vExisList.next();
					}
				}
			}
			
			rEM.getTransaction().commit();
			rEM.getTransaction().begin();
			
			if (!vCurList.isEmpty()) {
				vCurList.toFirst();
				
				while(!vCurList.isEnd()) {
					vCur = vCurList.getCurrent();
					
					if (vCur instanceof ShowList) {
						vCurWrap = new ShowListElementWrapper((ShowList)vCur);
						rEM.persist(vCur);
						rEM.persist(vCurWrap);
					} else throw new Exception("06; DaInt,sSL");
					
					vCurList.next();
				}
			}
			
			rEM.getTransaction().commit();
		}
	}
	/**	Dh	31.7.2020
	 * 
	 * @param pHostList
	 * @throws Exception
	 */
	protected void saveHosts(List pHostList) throws Exception{
		Object vCur;
		List vCurList = pHostList.copyList();
		List vExisList = loadHosts();
		
		if (rEM != null) {
			rEM.getTransaction().begin();
			if (!vCurList.isEmpty()) {
				if (!vExisList.isEmpty()) {
					vExisList.toFirst();
					
					while(!vExisList.isEnd()) {
						vCur = vExisList.getCurrent();
						
						if (vCur instanceof Host) {
							if ((!vCurList.isEmpty()) && (vCurList.haveObject(vCur))) vCurList.deleteElement(vCur);
							else rEM.remove(vCur);
						} else throw new Exception("06; DaInt,sH");
						
						vExisList.next();
					}
				}
			}
			
			rEM.getTransaction().commit();
			rEM.getTransaction().begin();
			
			if (!vCurList.isEmpty()) {
				vCurList.toFirst();
				
				while(!vCurList.isEnd()) {
					vCur = vCurList.getCurrent();
					
					if (vCur instanceof Host) {
						rEM.persist(vCur);
					} else throw new Exception("06; DaInt,sH");
					
					vCurList.next();
				}
			}
			
			rEM.getTransaction().commit();
		}
	}
	
	/**	Dh	28.7.2020
	 * 
	 * @param pGUISettings
	 * @throws Exception
	 */
	protected void saveGUISettings(GUISettings pGUISettings) throws Exception{
		File vFile;
		JAXBContext jc;
		Marshaller marschaller;
		
		if (pGUISettings != null) {
			vFile = new File(settingFile+"/guiSettings"+".xml");
			jc = JAXBContext.newInstance(GUISettings.class);
			marschaller = jc.createMarshaller();
			
			marschaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			
			if (!vFile.exists()) {
				try{ vFile.createNewFile();}
				catch(Exception ex) {throw ex;}
			}
			
			marschaller.marshal(pGUISettings, vFile);
		}
	}
	/**	Dh	28.7.2020
	 * 
	 * @param pLogicSettings
	 * @throws Exception
	 */
	protected void saveLogicSettings(LogicSettings pLogicSettings) throws Exception{
		File vFile;
		JAXBContext jc;
		Marshaller marschaller;
		
		if (pLogicSettings != null) {
			vFile = new File(settingFile+"/logicSettings"+".xml");
			jc = JAXBContext.newInstance(LogicSettings.class);
			marschaller = jc.createMarshaller();
			
			marschaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			
			if (!vFile.exists()) {
				try{ vFile.createNewFile();}
				catch(Exception ex) {throw ex;}
			}
			
			marschaller.marshal(pLogicSettings, vFile);
		}
	}
	/**	Dh	31.7.2020
	 * 
	 * @param pDatabaseSettings
	 * @throws Exception
	 */
	protected void saveDatabaseSettings(DatabaseSettings pDatabaseSettings) throws Exception{
		File vFile;
		JAXBContext jc;
		Marshaller marschaller;
		
		if (pDatabaseSettings != null) {
			vFile = new File(settingFile+"/databaseSettings"+".xml");
			jc = JAXBContext.newInstance(DatabaseSettings.class);
			marschaller = jc.createMarshaller();
			
			marschaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			
			if (!vFile.exists()) {
				try{ vFile.createNewFile();}
				catch(Exception ex) {throw ex;}
			}
			
			marschaller.marshal(pDatabaseSettings, vFile);
		}
	}
	
//----------------------------------------------------------------------------------------------------
	
	/**	Dh	30.7.2020
	 * 
	 * @param pList
	 * @param pID
	 * @return
	 */
	private List getElementByID(List pList, int pID) {
		Object[] vCur;
		List vRet = null;
		
		if (!pList.isEmpty()) {
			pList.toFirst();
			
			while(!pList.isEnd() && (vRet == null)) {
				vCur = (Object[])pList.getCurrent();
				
				if (((int)vCur[0]) == pID) vRet = (List)vCur[1];
				
				pList.next();
			}
		}
		
		return vRet;
	}
}
