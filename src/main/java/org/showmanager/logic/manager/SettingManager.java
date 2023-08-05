/**	ShowManager v0.1	Dh	31.7.2023
 * 
 * 	logic.manager
 * 	BasicManager
 * 	  SettingManager
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

import org.showmanager.logic.settings.DatabaseSetting;
import org.showmanager.logic.settings.GUISettings;
import org.showmanager.logic.settings.LogicSettings;
import org.showmanager.logic.showentities.Host;

public class SettingManager extends BasicManager {
	private LogicSettings logicSettings;
	private GUISettings guiSettings;
	private DatabaseSetting databaseSettings;
	
	/**	Dh	31.07.2023
	 * 
	 * @param pDatabaseManager
	 */
	public SettingManager(DatabaseManager pDatabaseManager) {
		super(pDatabaseManager);
		
		try {logicSettings = databaseManager.loadLogicSettings();}
		catch(Exception ex) {if (!ex.getMessage().equals("21; lOfXML,DaM")) LogManager.handleException(ex);}
		try {guiSettings = databaseManager.loadGUISettings();}
		catch(Exception ex) {if (!ex.getMessage().equals("21; lOfXML,DaM")) LogManager.handleException(ex);}
		try {databaseSettings = databaseManager.getDatabaseSettings();}
		catch(Exception ex) {LogManager.handleException(ex);}
		
		if (logicSettings == null) logicSettings = new LogicSettings();
		if (guiSettings == null) guiSettings = new GUISettings();
		if (databaseSettings == null) databaseSettings = new DatabaseSetting();
		
		super.browserManager = new BrowserManager(logicSettings.getBrowserFile());
	}

	//----------------------------------------------------------------------------------------------------
	
	/**	Dh	27.07.2023
	 * 
	 * @return
	 */
	public BrowserManager getBrowserManager() {
		return super.browserManager;
	}
		
	/**	Dh	27.07.2023
	 * 
	 * @return
	 */
	public String getBrowserName() {
		return logicSettings.getBrowserName();
	}
	/**	Dh	27.07.2023
	 * 
	 * @return
	 */
	public String getBrowserPath() {
		return logicSettings.getBrowserPath();
	}
	/**	Dh	27.07.2023
	 * 
	 * @return
	 */
	public File getBrowserFile() {
		return logicSettings.getBrowserFile();
	}
	
	/**	Dh	27.07.2023
	 * 
	 * @return
	 */
	public boolean isRatingEnabled() {
		return guiSettings.isEnabledRating();
	}
	/**	Dh	27.07.2023
	 * 
	 * @return
	 */
	public boolean isGenreEnabled() {
		return guiSettings.isEnabledGenres();
	}
	/**	Dh	27.07.2023
	 * 
	 * @return
	 */
	public boolean isHostEnabled() {
		return guiSettings.isEnabledHost();
	}
	/**	Dh	27.07.2023
	 * 
	 * @return
	 */
	public boolean isURLEnabled() {
		return guiSettings.isEnabledURL();
	}
	//-----
	/**	Dh	27.07.2023
	 * 
	 * @return
	 */
	public int getFrameWidth() {
		return guiSettings.getFrameWidth();
	}
	/**	Dh	27.07.2023
	 * 
	 * @return
	 */
	public int getFrameHeight() {
		return guiSettings.getFrameHeight();
	}
	
	/**	Dh	27.07.2023
	 * 
	 * @return
	 */
	public String getDatabaseName() {
		return databaseSettings.getDatabaseName();
	}
	/**	Dh	27.07.2023
	 * 
	 * @return
	 */
	public String getUserName() {
		return databaseSettings.getUserName();
	}
	/**	Dh	27.07.2023
	 * 
	 * @return
	 */
	public String getPassword() {
		return databaseSettings.getPassword();
	}
	
	//------------------------------------------------------------------------------------------------
		
	/**	Dh	27.07.2023
	 * 
	 * @param pName
	 * @throws Exception
	 */
	public void setBrowserName(String pName) throws Exception{
		logicSettings.setBrowserName(pName);
	}
	/**	Dh	27.07.2023
	 * 
	 * @param pPath
	 * @throws Exception
	 */
	public void setBrowserPath(String pPath) throws Exception{
		logicSettings.setBrowserPath(pPath);
		
		super.browserManager.setBrowserFile(logicSettings.getBrowserFile());
	}
	
	/**	Dh	27.07.2023
	 * 
	 * @param pEnabled
	 */
	public void setRatingEnabled(boolean pEnabled) {
		guiSettings.setEnabledRating(pEnabled);
	}
	/**	Dh	27.07.2023
	 * 
	 * @param pEnabled
	 */
	public void setGenreEnabled(boolean pEnabled) {
		guiSettings.setEnabledGenres(pEnabled);
	}
	/**	Dh	27.07.2023
	 * 
	 * @param pEnabled
	 */
	public void setHostEnabled(boolean pEnabled) {
		guiSettings.setEnabledHost(pEnabled);
	}
	/**	Dh	27.07.2023
	 * 
	 * @param pEnabled
	 */
	public void setURLEnabled(boolean pEnabled) {
		guiSettings.setEnabledURL(pEnabled);
	}
	//-----
	/**	Dh	27.07.2023
	 * 
	 * @param pWidth
	 * @throws Exception
	 */
	public void setFrameWidth(int pWidth) throws Exception{
		guiSettings.setFrameWidth(pWidth);
	}
	/**	Dh	27.07.2023
	 * 
	 * @param pHeight
	 * @throws Exception
	 */
	public void setFrameHeight(int pHeight) throws Exception{
		guiSettings.setFrameHeight(pHeight);
	}
	
	/**	Dh	27.07.2023
	 * 
	 * @param pDatabaseName
	 * @throws Exception
	 */
	public void setDatabaseName(String pDatabaseName) throws Exception{
		if (!pDatabaseName.equals("")) databaseSettings.setDatabaseName(pDatabaseName);
		else throw new Exception("02; sDN,SeM");
	}
	/**	Dh	27.07.2023
	 * 
	 * @param pUserName
	 * @throws Exception
	 */
	public void setUseName(String pUserName) throws Exception{
		if (!pUserName.equals("")) databaseSettings.setUserName(pUserName);
		else throw new Exception("02; sUN,SeM");
	}
	/**	Dh	27.07.2023
	 * 
	 * @param pPassword
	 * @throws Exception
	 */
	public void setPassword(String pPassword) throws Exception{
		if (!pPassword.equals("")) databaseSettings.setPassword(pPassword);
		else throw new Exception("02; sPw,SeM");
	}
	
//----------------------------------------------------------------------------------------------------
	
	/**	Dh	27.07.2023
	 * 
	 * @return
	 */
	public boolean haveBrowser() {
		boolean vRet = false;
		
		if (!logicSettings.getBrowserName().equals("")) vRet = true;
		
		return vRet;
	}
		
	/**	Dh	27.07.2023
	 * 
	 * @return
	 */
	public boolean haveDatabaseConnection() {
		return databaseManager.haveConnection();
	}
		
//----------------------------------------------------------------------------------------------------
	
	/**	Dh	27.07.2023
	 * 
	 */
	public void resetGUISettings() {
		guiSettings.resetSetting();
	}
	/**	Dh	27.07.2023
	 * 
	 */
	public void resetLogicSetting() {
		logicSettings.resetSetting();
	}
	
	/**	Dh	27.07.2023
	 * 
	 */
	public void resetSettings() {
		resetGUISettings();
		resetLogicSetting();
	}
	
	//------------------------------------------------------------------------------------------------
	
	/**	Dh	27.07.2023
	 * 
	 * @throws Exception
	 */
	public void connectToDatabase() throws Exception {
		databaseManager.initDatabaseConnection();
	}
	/**	Dh	27.07.2023
	 * 
	 * @param pDatabase
	 * @param pUser
	 * @param pPassword
	 * @throws Exception
	 */
	public void connectToDatabase(String pDatabase, String pUser, String pPassword) throws Exception{
		databaseManager.initDatabaseConnection(pDatabase, pUser, pPassword);
	}
	
	/**	Dh	27.07.2023
	 * 
	 * @param pDatabase
	 * @param pUser
	 * @param pPassword
	 * @return
	 * @throws Exception
	 */
	public boolean ping(String pDatabase, String pUser, String pPassword) throws Exception{
		return databaseManager.ping(pDatabase, pUser, pPassword);
	}
		
	/**	Dh	27.07.2023
	 * 
	 * @throws Exception
	 */
	public void saveSettings() throws Exception {
		databaseManager.saveGUISettings(guiSettings);
		databaseManager.saveLogicSettings(logicSettings);
		databaseManager.saveDatabaseSettings(databaseSettings);
	}
	//-----
	/**	Dh	31.07.2023
	 * 
	 */
	public void saveAll() throws Exception {
		saveSettings();
	}
	
}
