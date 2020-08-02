/**	ShowManager v0.0	Dh	31.7.2020
 * 
 * 	pLogic
 * 	  LogicManager
 * 	 	SettingManager
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

import pDataStructures.List;

public class SettingManager extends LogicManager {
	private LogicSettings logicSettings;
	private GUISettings guiSettings;
	private DatabaseSettings databaseSettings;
	
	/**	Dh	31.7.2020
	 * 
	 * @param pDatabaseInterface
	 */
	public SettingManager(DatabaseInterface pDatabaseInterface) {
		super(pDatabaseInterface);
		
		try {logicSettings = databaseInterface.loadLogicSettings();}
		catch(Exception ex) {if (!ex.getMessage().equals("21; DaInt,lLS")) MainManager.handleException(ex);}
		try {guiSettings = databaseInterface.loadGUISettings();}
		catch(Exception ex) {if (!ex.getMessage().equals("21; DaInt,lGUIS")) MainManager.handleException(ex);}
		try {databaseSettings = databaseInterface.getDatabaseSettings();}
		catch(Exception ex) {MainManager.handleException(ex);}
		
		if (logicSettings == null) logicSettings = new LogicSettings();
		if (guiSettings == null) guiSettings = new GUISettings();
		if (databaseSettings == null) databaseSettings = new DatabaseSettings();
		
		opener = new BrowserOpener(logicSettings.getBrowserFile());
		
		try {loadHosts();}
		catch(Exception ex) {MainManager.handleException(ex);}
	}
	
//----------------------------------------------------------------------------------------------------
	
	/**	Dh	19.7.2020
	 * 
	 * @return
	 */
	protected BrowserOpener getBrowserOpener() {
		return opener;
	}
	
	/**	Dh	19.7.2020
	 * 
	 * @return
	 */
	public List getHostIDList() {
		List vRet = new List();
		List vTempList = opener.getHosterList();
		
		if (!vTempList.isEmpty()) {
			vTempList.toFirst();
			
			while(!vTempList.isEnd()) {
				vRet.append(((Host)vTempList.getCurrent()).getId());
				
				vTempList.next();
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
	public String getHosterName(int pID) throws Exception{
		return opener.getHosterName(pID);
	}
	/**	Dh	19.7.2020
	 * 
	 * @param pID
	 * @return
	 * @throws Exception
	 */
	public String getHosterURL(int pID) throws Exception{
		return opener.getHosterURL(pID);
	}
	
	/**	Dh	19.7.2020
	 * 
	 * @return
	 */
	public String getBrowserName() {
		return logicSettings.getBrowserName();
	}
	/**	Dh	19.7.2020
	 * 
	 * @return
	 */
	public String getBrowserPath() {
		return logicSettings.getBrowserPath();
	}
	/**	Dh	19.7.2020
	 * 
	 * @return
	 */
	protected File getBrowserFile() {
		return logicSettings.getBrowserFile();
	}
	
	/**	Dh	21.7.2020
	 * 
	 * @return
	 */
	public boolean isRatingEnabled() {
		return guiSettings.isEnabledRating();
	}
	/**	Dh	21.7.2020
	 * 
	 * @return
	 */
	public boolean isGenreEnabled() {
		return guiSettings.isEnabledGenres();
	}
	/**	Dh	21.7.2020
	 * 
	 * @return
	 */
	public boolean isHostEnabled() {
		return guiSettings.isEnabledHost();
	}
	/**	Dh	21.7.2020
	 * 
	 * @return
	 */
	public boolean isURLEnabled() {
		return guiSettings.isEnabledURL();
	}
	//-----
	/**	Dh	19.7.2020
	 * 
	 * @return
	 */
	public int getFrameWidth() {
		return guiSettings.getFrameWidth();
	}
	/**	Dh	19.7.2020
	 * 
	 * @return
	 */
	public int getFrameHeight() {
		return guiSettings.getFrameHeight();
	}
	
	/**	Dh	31.7.2020
	 * 
	 * @return
	 */
	public String getDatabaseName() {
		return databaseSettings.getDatabaseName();
	}
	/**	Dh	31.7.2020
	 * 
	 * @return
	 */
	public String getUserName() {
		return databaseSettings.getUserName();
	}
	/**	Dh	31.7.2020
	 * 
	 * @return
	 */
	public String getPassword() {
		return databaseSettings.getPassword();
	}
	
	//------------------------------------------------------------------------------------------------
	
	/**	Dh	19.7.2020
	 * 
	 * @param pID
	 * @param pName
	 * @throws Exception
	 */
	public void setHosterName(int pID, String pName) throws Exception{
		opener.setHosterName(pID, pName);
	}
	/**	Dh	19.7.2020
	 * 
	 * @param pID
	 * @param pURL
	 * @throws Exception
	 */
	public void setHosterURL(int pID, String pURL) throws Exception{
		opener.setHosterURL(pID, pURL);
	}
	
	/**	Dh	19.7.2020
	 * 
	 * @param pName
	 * @throws Exception
	 */
	public void setBrowserName(String pName) throws Exception{
		logicSettings.setBrowserName(pName);
	}
	/**	Dh	19.7.2020
	 * 
	 * @param pPath
	 * @throws Exception
	 */
	public void setBrowserPath(String pPath) throws Exception{
		logicSettings.setBrowserPath(pPath);
		
		opener.setBrowserFile(logicSettings.getBrowserFile());
	}
	
	/**	Dh	21.7.2020
	 * 
	 * @param pEnabled
	 */
	public void setRatingEnabled(boolean pEnabled) {
		guiSettings.setEnabledRating(pEnabled);
	}
	/**	Dh	21.7.2020
	 * 
	 * @param pEnabled
	 */
	public void setGenreEnabled(boolean pEnabled) {
		guiSettings.setEnabledGenres(pEnabled);
	}
	/**	Dh	21.7.2020
	 * 
	 * @param pEnabled
	 */
	public void setHostEnabled(boolean pEnabled) {
		guiSettings.setEnabledHost(pEnabled);
	}
	/**	Dh	21.7.2020
	 * 
	 * @param pEnabled
	 */
	public void setURLEnabled(boolean pEnabled) {
		guiSettings.setEnabledURL(pEnabled);
	}
	//-----
	/**	Dh	19.7.2020
	 * 
	 * @param pWidth
	 * @throws Exception
	 */
	public void setFrameWidth(int pWidth) throws Exception{
		guiSettings.setFrameWidth(pWidth);
	}
	/**	Dh	19.7.2020
	 * 
	 * @param pHeight
	 * @throws Exception
	 */
	public void setFrameHeight(int pHeight) throws Exception{
		guiSettings.setFrameHeight(pHeight);
	}
	
	/**	Dh	31.7.2020
	 * 
	 * @param pDatabaseName
	 * @throws Exception
	 */
	public void setDatabaseName(String pDatabaseName) throws Exception{
		databaseSettings.setDatabaseName(pDatabaseName);
	}
	/**	Dh	31.7.2020
	 * 
	 * @param pUserName
	 * @throws Exception
	 */
	public void setUseName(String pUserName) throws Exception{
		databaseSettings.setUserName(pUserName);
	}
	/**	Dh	31.7.2020
	 * 
	 * @param pPassword
	 * @throws Exception
	 */
	public void setPassword(String pPassword) throws Exception{
		databaseSettings.setPassword(pPassword);
	}
	
//----------------------------------------------------------------------------------------------------
	
	/**	Dh	19.7.2020
	 * 
	 * @return
	 */
	public boolean haveBrowser() {
		boolean vRet = false;
		
		if (!logicSettings.getBrowserName().equals("")) vRet = true;
		
		return vRet;
	}
	
	/**	Dh	19.7.2020
	 * 
	 * @param pID
	 * @return
	 * @throws Exception
	 */
	public boolean haveHost(int pID) throws Exception{
		return opener.haveHoster(pID);
	}
	
	/**	Dh	31.7.2020
	 * 
	 * @return
	 */
	public boolean haveDatabaseConnection() {
		return databaseInterface.haveConnection();
	}
	
	//------------------------------------------------------------------------------------------------
	
	/**	Dh	30.7.2020
	 * 
	 * @param pName
	 * @param pPath
	 * @throws Exception
	 */
	public void addHost(String pName, String pPath) throws Exception{
		opener.addHoster(new Host(dertermineID(opener.getHosterList()), pName, pPath));
		sortListByID(opener.getHosterList());
	}
	
	/**	Dh	19.7.2020
	 * 
	 * @param pID
	 * @throws Exception
	 */
	public void removeHost(int pID) throws Exception{
		opener.removeHoster(pID);
		
		
	}
	
//----------------------------------------------------------------------------------------------------
	
	/**	Dh	23.7.2020
	 * 
	 */
	public void resetGUISettings() {
		guiSettings.resetSetting();
	}
	/**	Dh	23.7.2020
	 * 
	 */
	public void resetLogicSetting() {
		logicSettings.resetSetting();
	}
	
	/**	Dh	23.7.2020
	 * 
	 */
	public void resetSettings() {
		resetGUISettings();
		resetLogicSetting();
	}
	
	//------------------------------------------------------------------------------------------------
	
	/**	Dh	31.7.2020
	 * 
	 * @throws Exception
	 */
	public void connectToDatabase() throws Exception {
		databaseInterface.initDatabaseConnection();
	}
	/**	Dh	31.7.2020
	 * 
	 * @param pDatabase
	 * @param pUser
	 * @param pPassword
	 * @throws Exception
	 */
	public void connectToDatabase(String pDatabase, String pUser, String pPassword) throws Exception{
		databaseInterface.initDatabaseConnection(pDatabase, pUser, pPassword);
	}
	
	/**	Dh	31.7.2020
	 * 
	 * @param pDatabase
	 * @param pUser
	 * @param pPassword
	 * @return
	 * @throws Exception
	 */
	public boolean ping(String pDatabase, String pUser, String pPassword) throws Exception{
		return databaseInterface.ping(pDatabase, pUser, pPassword);
	}
	
	/**	Dh	21.7.2020
	 * 
	 * @throws Exception
	 */
	public void loadHosts() throws Exception{
		List vHost = databaseInterface.loadHosts();
		
		if (vHost == null) vHost = new List();
		
		opener.setHosterList(vHost);
	}
	
	/**	Dh	31.7.2020
	 * 
	 * @throws Exception
	 */
	public void saveSettings() throws Exception {
		databaseInterface.saveGUISettings(guiSettings);
		databaseInterface.saveLogicSettings(logicSettings);
		databaseInterface.saveDatabaseSettings(databaseSettings);
	}
	/**	Dh	19.7.2020
	 * 
	 * @throws Exception
	 */
	public void saveHosts() throws Exception{
		databaseInterface.saveHosts(opener.getHosterList());
	}
	//-----
	/**	Dh	19.7.2020
	 * 
	 * @throws Exception
	 */
	public void saveAll() throws Exception {
		saveSettings();
		saveHosts();
	}
	
}
