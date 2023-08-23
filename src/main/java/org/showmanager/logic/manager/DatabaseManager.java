/**	ShowManager v0.1	Dh	05.08.2023
 * 
 * 	logic.manager
 * 	DatabaseManager
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
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.showmanager.logic.settings.DatabaseSetting;
import org.showmanager.logic.settings.GUISettings;
import org.showmanager.logic.settings.LogicSettings;
import org.showmanager.logic.showentities.Genre;
import org.showmanager.logic.showentities.Host;
import org.showmanager.logic.showentities.ShowElement;
import org.showmanager.logic.showentities.ShowList;
import org.showmanager.logic.showentities.wrappers.DatabaseWrapper;
import org.showmanager.logic.showentities.wrappers.GenreListElementWrapper;
import org.showmanager.logic.showentities.wrappers.ShowListElementWrapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

public class DatabaseManager {
	private File systemFile;
	private File showsFile, genreFile, settingFile, showListFile;
	
	private EntityManagerFactory rEMF;
	private EntityManager rEM;
	
	private DatabaseSetting databaseSettings;
	
	/**	Dh	28.07.2023
	 * 
	 */
	public DatabaseManager() {
		String vOS, vArch, vHome;
		
		vOS = System.getProperty("os.name");
		vArch = System.getProperty("os.arch");
		vHome = System.getProperty("user.home");
		
		if (vOS.contains("Windows")) vHome = vHome+ "/AppData/Roaming/ShowManager";
		else if (vOS.contains("Linux")) {
			if (!vArch.contains("aarch64")) vHome = vHome+"/.local/ShowManager";
		}
		else LogManager.handleException(new Exception("20; DaM"));
		
		systemFile = new File(vHome);
		if (!systemFile.exists()) systemFile.mkdir();
		
		showsFile = new File(vHome+"");
		genreFile = new File(vHome+"");
		settingFile = new File(vHome+"/settings");
		if (!settingFile.exists()) settingFile.mkdir();
		
		showListFile = new File(vHome+"");
		
		try {
			databaseSettings = loadDatabaseSettings();
			//initDatabaseConnection();
		}catch(Exception ex) {
			if (!ex.getMessage().equals("21; lOfXML,DaM")) LogManager.handleException(ex);
			else databaseSettings = new DatabaseSetting();
		}
	}

	/**	Dh	27.07.2023
	 * 
	 */
	public void closeDatabaseInterface() {
		if (rEM != null) {
			rEM.close();
			rEMF.close();
		}
	}

	public String getOSName() {
		return systemFile.getAbsolutePath();
	}
	
	//------------------------------------------------------------------------------------------------
	
	/**	Dh	27.07.2023
	 * 
	 * @throws Exception
	 */
	public void initDatabaseConnection() throws Exception{
		initDatabaseConnection(databaseSettings.getDatabaseName(), databaseSettings.getUserName(), databaseSettings.getPassword());
	}
	/**	Dh	27.07.2023
	 * 
	 * @param pDatabase
	 * @param pUser
	 * @param pPassword
	 * @throws Exception
	 */
	public void initDatabaseConnection(String pDatabase, String pUser, String pPassword) throws Exception{
		try {
			Map<String, String> vProps = new HashMap<String, String>();
			
			vProps.put("javax.persistence.jdbc.url", "jdbc:mysql://localhost:3306/"+pDatabase+"?serverTimezone=UTC");
			vProps.put("javax.persistence.jdbc.user", pUser);
			vProps.put("javax.persistence.jdbc.password", pPassword);
			
			if (isConnectionOn(pDatabase, pUser, pPassword) == true) {
				
				rEMF = Persistence.createEntityManagerFactory("ShowManager", vProps);
				rEM = rEMF.createEntityManager();
				
				if ((rEM == null) || (rEM.isOpen() == false)) {
					rEMF.close();
					
					rEM = null;
					rEMF = null;
				}
			}
			else {
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
	
	/**	Dh	27.07.2023
	 * 
	 * @return
	 */
	public DatabaseSetting getDatabaseSettings() {
		return databaseSettings;
	}
	
	/**	Dh	27.07.2023
	 * 
	 * @return
	 */
	public boolean haveConnection() {
		return (rEM != null);
	}
	
	/**	DH	27.07.2023
	 * 
	 * @param pDatabase
	 * @param pUser
	 * @param pPassword
	 * @return
	 * @throws Exception
	 */
	public boolean ping(String pDatabase, String pUser, String pPassword) throws Exception{
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
	
	/**	Dh	27.07.2023
	 * 
	 * @return
	 */
	public ArrayList<Genre> loadGenres() {
		ArrayList<Genre> vRet = new ArrayList<Genre>();
		
		if (rEM != null) {
			Query vQuery = rEM.createQuery("Select g.id from Genre g");
			
			java.util.List<Integer> vList = vQuery.getResultList();
			
			if (vList.size() > 0) {
				for (int i=0; i < vList.size(); i++) {
					vRet.add(rEM.find(Genre.class, vList.get(i)));
				}	
			}
		}
		
		return vRet;
	}
	/**	Dh	27.07.2023
	 * 
	 * @return
	 * @throws Exception
	 */
	public ArrayList<ShowElement> loadShows() throws Exception {
		ShowElement vCurShow;
		GenreListElementWrapper vCurWrapper;
		ArrayList<ShowElement> vRet = new ArrayList<ShowElement>();
		
		if (rEM != null) {
			Query vQuery = rEM.createQuery("Select s.id from ShowElement s");
			
			java.util.List<Integer> vList = vQuery.getResultList();
			
			if (vList.size() > 0) {
				for (int i=0; i < vList.size(); i++) {
					vCurShow = rEM.find(ShowElement.class, vList.get(i));
					vCurWrapper = rEM.find(GenreListElementWrapper.class, vList.get(i));
					
					vCurShow.setGenreList(vCurWrapper.getIDList());
					
					vRet.add(vCurShow);
				}	
			}
		}
		
		return vRet;
	}
	/**	Dh	27.07.2023
	 * 
	 * @return
	 */
	public ArrayList<Host> loadHosts() {
		ArrayList<Host> vRet = new ArrayList<Host>();
		
		if (rEM != null) {
			Query vQuery = rEM.createQuery("Select h.id from Host h");
			
			java.util.List<Integer> vList = vQuery.getResultList();
			
			if (vList.size() > 0) {
				for (int i=0; i < vList.size(); i++) {
					vRet.add(rEM.find(Host.class, vList.get(i)));
				}	
			}
		}
		
		return vRet;
	}
	//-----
	/**	Dh	27.07.2023
	 * 
	 * @param pID
	 * @return
	 * @throws Exception
	 */
	public ShowList loadShowList(int pID) throws Exception{
		return null;
	}
	/**	Dh	27.07.2023
	 * 
	 * @return
	 * @throws Exception
	 */
	public ArrayList<ShowList> loadShowLists() throws Exception {
		ShowList vCurShowList;
		ShowListElementWrapper vCurWrapper;
		ArrayList<ShowList> vRet = new ArrayList<ShowList>();
		
		if (rEM != null) {
			Query vQuery = rEM.createQuery("Select sl.id from ShowList sl");
			
			java.util.List<Integer> vList = vQuery.getResultList();
			
			if (vList.size() > 0) {
				for (int i=0; i < vList.size(); i++) {
					vCurShowList = rEM.find(ShowList.class, vList.get(i));
					vCurWrapper = rEM.find(ShowListElementWrapper.class, vList.get(i));
					
					vCurShowList.setShowList(vCurWrapper.getIDList());
					
					vRet.add(vCurShowList);
				}	
			}
		}
		
		return vRet;
	}
	
	/**	Dh	27.07.2023
	 * 
	 * @return
	 * @throws Exception
	 */
	public GUISettings loadGUISettings() throws Exception{
		GUISettings vRet = null;
		String vPath = settingFile+"/guiSettings"+".xml";
		
		vRet = (GUISettings) loadObjectFromXML(vPath, GUISettings.class);
		
		return vRet;
	}
	/**	Dh	27.07.2023
	 * 
	 * @return
	 * @throws Exception
	 */
	public LogicSettings loadLogicSettings() throws Exception{
		LogicSettings vRet = null;
		String vPath = settingFile+"/logicSettings"+".xml";
		
		vRet = (LogicSettings) loadObjectFromXML(vPath, LogicSettings.class);
		
		return vRet;
	}
	/**	Dh	27.07.2023
	 * 
	 * @return
	 * @throws Exception
	 */
	public DatabaseSetting loadDatabaseSettings() throws Exception{
		DatabaseSetting vRet = null;
		String vPath = settingFile+"/databaseSettings"+".xml";
		
		vRet = (DatabaseSetting) loadObjectFromXML(vPath, DatabaseSetting.class);
		
		return vRet;
	}
	
	/**	Dh	05.08.2023
	 * 	
	 * @param pImportPath
	 * @return
	 * @throws Exception
	 */
	public DatabaseWrapper loadDatabaseXML(String pImportPath) throws Exception{
		DatabaseWrapper vRet = null;
		
		vRet = (DatabaseWrapper) loadObjectFromXML(pImportPath, DatabaseWrapper.class);
		
		return vRet;
	}
	
	//------------------------------------------------------------------------------------------------
	
	/**	Dh	27.07.2023
	 * 
	 * @param pGenereList
	 * @throws Exception
	 */
	public void saveGenres(ArrayList<Genre> pGenereList) throws Exception{
		ArrayList<Genre> vCurList = (ArrayList<Genre>) pGenereList.clone();
		ArrayList<Genre> vExisList = loadGenres();
		
		if (rEM != null) {
			rEM.getTransaction().begin();
			if (!vExisList.isEmpty()) {
				for (Genre vGenre : vExisList) {
					if ((!vCurList.isEmpty()) && (vCurList.contains(vGenre))) vCurList.remove(vGenre);
					else rEM.remove(vGenre);
				}
			}
			
			rEM.getTransaction().commit();
			rEM.getTransaction().begin();
			
			if (!vCurList.isEmpty()) {
				for (Genre vGenre : vCurList) {
					rEM.persist(vGenre);
				}
			}
			
			rEM.getTransaction().commit();
		}
	}
	/**	Dh	27.07.2023
	 * 
	 * @param pShowList
	 * @throws Exception
	 */
	public void saveShows(ArrayList<ShowElement> pShowList) throws Exception {
		GenreListElementWrapper vCurWrap;
		ArrayList<ShowElement> vCurList = (ArrayList<ShowElement>)pShowList.clone();
		HashMap<Integer, ArrayList<Integer>> vGenreHashMap = new HashMap<Integer, ArrayList<Integer>>();
		
		if (rEM != null) {
			if (!pShowList.isEmpty()) {
				for (ShowElement vShowElement : pShowList) {
					vGenreHashMap.put(vShowElement.getId(), vShowElement.getGenreList());
				}
			}
			
			ArrayList<ShowElement> vExisList = loadShows();
			
			rEM.getTransaction().begin();
			if (!vExisList.isEmpty()) {
				for (ShowElement vShowElement : vExisList) {
					if ((!vCurList.isEmpty()) && (vCurList.contains(vShowElement))) {
						vCurWrap = rEM.find(GenreListElementWrapper.class, vShowElement.getId());
						
						if (vCurWrap != null) vCurWrap.setIDsPerList(vGenreHashMap.get(vShowElement.getId()));
						else throw new Exception("04; sSh,DaM");
						
						vCurList.remove(vShowElement);
					}else {
						rEM.remove(vShowElement);
						rEM.remove(rEM.find(GenreListElementWrapper.class, vShowElement.getId()));
					}
				}
			}
			
			rEM.getTransaction().commit();
			rEM.getTransaction().begin();
			
			if (!vCurList.isEmpty()) {
				for (ShowElement vShowElement : vCurList) {
					vCurWrap = new GenreListElementWrapper(vShowElement);
					
					rEM.persist(vShowElement);
					rEM.persist(vCurWrap);
				}
			}
			
			rEM.getTransaction().commit();
		}
	}
	/**	Dh	27.07.2023
	 * 
	 * @param pHostList
	 * @throws Exception
	 */
	public void saveHosts(ArrayList<Host> pHostList) throws Exception{
		Object vCur;
		ArrayList<Host> vCurList = (ArrayList<Host>)pHostList.clone();
		ArrayList<Host> vExisList = loadHosts();
		
		if (rEM != null) {
			rEM.getTransaction().begin();
			if (!vExisList.isEmpty()) {
				for (Host vHost : vExisList) {
					if ((!vCurList.isEmpty()) && (vCurList.contains(vHost))) vCurList.remove(vHost);
					else rEM.remove(vHost);
				}
			}
		
			rEM.getTransaction().commit();
			rEM.getTransaction().begin();
			
			if (!vCurList.isEmpty()) {
				for (Host vHost : vCurList) {
					rEM.persist(vHost);
				}
			}
			
			rEM.getTransaction().commit();
		}
	}
	//-----
	/**	Dh	27.07.2023
	 * 
	 * @param pShowList
	 * @throws Exception
	 */
	public void saveShowList(ShowList pShowList) throws Exception{
		ShowListElementWrapper vCur;
		
		if (pShowList != null) {
			vCur = new ShowListElementWrapper(pShowList);
			
			rEM.getTransaction().begin();
			
			rEM.persist(pShowList);
			rEM.persist(vCur);
			
			rEM.getTransaction().commit();
		}
	}
	/**	Dh	27.07.2023
	 * 
	 * @param pShowListList
	 * @throws Exception
	 */
	public void saveShowLists(ArrayList<ShowList> pShowListList) throws Exception{
		ShowListElementWrapper vCurWrap;
		ArrayList<ShowList> vCurList = (ArrayList<ShowList>)pShowListList.clone();
		HashMap<Integer, ArrayList<Integer>> vShowListIDHashMap = new HashMap<Integer, ArrayList<Integer>>();
		
		if (rEM != null) {
			if (!pShowListList.isEmpty()) {
				for (ShowList vShowList : pShowListList) {
					vShowListIDHashMap.put(vShowList.getId(), vShowList.getShowList());
				}
			}
			
			ArrayList<ShowList> vExisList = loadShowLists();
			
			rEM.getTransaction().begin();
			if (!vExisList.isEmpty()) {
				for (ShowList vShowList : vExisList) {
					if ((!vCurList.isEmpty()) && (vCurList.contains(vShowList))) {
						vCurWrap = rEM.find(ShowListElementWrapper.class, vShowList.getId());
						
						if (vCurWrap != null) vCurWrap.setIDsPerList(vShowListIDHashMap.get(vShowList.getId()));
						else throw new Exception("04; sSL,DaM");
						
						vCurList.remove(vShowList);
					}else {
						rEM.remove(vShowList);
						rEM.remove(rEM.find(ShowListElementWrapper.class, vShowList.getId()));
					}
				}
			}
			
			rEM.getTransaction().commit();
			rEM.getTransaction().begin();
			
			if (!vCurList.isEmpty()) {
				for (ShowList vShowList : vCurList) {
					vCurWrap = new ShowListElementWrapper(vShowList);
					
					rEM.persist(vShowList);
					rEM.persist(vCurWrap);
				}
			}
			
			rEM.getTransaction().commit();
		}
	}
		
	/**	Dh	27.07.2023
	 * 
	 * @param pGUISettings
	 * @throws Exception
	 */
	public void saveGUISettings(GUISettings pGUISettings) throws Exception{
		saveObjectAsXML(settingFile+"/guiSettings"+".xml", pGUISettings);
	}
	/**	Dh	27.07.2023
	 * 
	 * @param pLogicSettings
	 * @throws Exception
	 */
	public void saveLogicSettings(LogicSettings pLogicSettings) throws Exception{
		saveObjectAsXML(settingFile+"/logicSettings"+".xml", pLogicSettings);
	}
	/**	Dh	27.07.2023
	 * 
	 * @param pDatabaseSettings
	 * @throws Exception
	 */
	public void saveDatabaseSettings(DatabaseSetting pDatabaseSettings) throws Exception{
		saveObjectAsXML(settingFile+"/databaseSettings"+".xml", pDatabaseSettings);
	}
	
	/**	Dh	05.08.2023
	 * 
	 * @param pDatabaseWrapper
	 * @param pExportFile
	 * @throws Exception
	 */
	public void saveDatabaseXML(DatabaseWrapper pDatabaseWrapper, String pExportPath) throws Exception{
		if ((pDatabaseWrapper != null) && (pExportPath != null)) {
			saveObjectAsXML(pExportPath, pDatabaseWrapper);
		}else throw new Exception("04; sDbXML,DaM");
	}
	
//----------------------------------------------------------------------------------------------------
	
	/**	Dh	28.07.2023
	 * 
	 * @param pDatabase
	 * @param pUser
	 * @param pPassword
	 * @return
	 * @throws Exception
	 */
	private boolean isConnectionOn(String pDatabase, String pUser, String pPassword) throws Exception {
		boolean vRet = false;
		
		InetAddress addr = InetAddress.getByName("127.0.0.1");
		int port = 3306;
		
		SocketAddress sockaddr = new InetSocketAddress(addr, port);
		Socket sock = new Socket();
		try {
			sock.connect(sockaddr, 2000);
			if (sock.isConnected() == true) {
				vRet = true;
				sock.close();
			}

		}
		catch(Exception ex) {LogManager.handleException(ex);}
		
		return vRet;
	}
	
	/**	Dh	27.07.2023
	 * 
	 * @param pFilePath
	 * @param pClass
	 * @return
	 * @throws Exception
	 */
	private Object loadObjectFromXML(String pFilePath, Class<?> pClass) throws Exception {
		Object vRet = null;
		String vXMLText;
		File vFile;
		ObjectMapper vObjectMapper;
		
		vFile = new File(pFilePath);
				
		if (vFile.exists()) {
			vXMLText = Files.readString(vFile.toPath(), Charset.forName("UTF-8"));  // ISO-8859-1 UTF-8
			vObjectMapper = new XmlMapper();
			
			vRet = vObjectMapper.readValue(vXMLText, pClass);
		} else throw new Exception("21; lOfXML,DaM");
		
		return vRet;
	}
	/**	Dh	27.07.2023
	 * 
	 * @param pFilePath
	 * @param pObject
	 * @throws Exception
	 */
	private void saveObjectAsXML(String pFilePath, Object pObject) throws Exception {
		String vXMLText;
		File vFile;
		ObjectMapper vObjectMapper;
		
		if (pObject != null) {
			if (!pFilePath.equals("")) {
				vFile = new File(pFilePath);
				
				vObjectMapper = new XmlMapper();
				
				vXMLText = vObjectMapper.writeValueAsString(pObject);
				
				if (!vFile.exists()) vFile.createNewFile();
				
				Files.write(vFile.toPath(), vXMLText.getBytes("UTF-8"));
			} else throw new Exception("02; sOaXML,DaM");
		} else throw new Exception("04; sOaXML,DaM");
	}
	
}
