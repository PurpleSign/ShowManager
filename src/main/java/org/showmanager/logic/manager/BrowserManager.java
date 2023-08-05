/**	ShowManager v0.1	Dh	29.7.2023
 * 
 * 	logic.manager
 * 	BrowserManager
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

import org.showmanager.logic.showentities.Host;
import org.showmanager.logic.showentities.ShowElement;

public class BrowserManager {
	private File browserFile;
	private ProcessBuilder processBuilder;
	private Process process;
	private ArrayList<Host> hostList;
	
	/**	Dh	27.07.2023
	 * 
	 * @param pBrowserFile
	 */
	public BrowserManager(File pBrowserFile) {
		try {setBrowserFile(pBrowserFile);}
		catch(Exception ex) {
			browserFile = null;
			LogManager.handleException(ex);
		}
		
		hostList = new ArrayList<Host>();
	}
	/**	Dh	27.07.2023
	 * 
	 * @param pBrowserFile
	 * @param pHostList
	 */
	public BrowserManager(File pBrowserFile, ArrayList<Host> pHostList) {
		try {setBrowserFile(pBrowserFile);}
		catch(Exception ex) {
			browserFile = null;
			LogManager.handleException(ex);
		}
		
		try {setHostList(pHostList);}
		catch(Exception ex) {
			hostList = new ArrayList<Host>();
			LogManager.handleException(ex);
		}
	}

//----------------------------------------------------------------------------------------------------	
	
	/**	Dh	26.07.2023
	 * 
	 * @return
	 */
	public File getBrowserFile() {
		return browserFile;
	}
	/**	Dh	26.07.2023
	 * 
	 * @return
	 */
	public ArrayList<Host> getHostList() {
		return hostList;
	}
	
	/**	Dh	26.07.2023
	 * 
	 * @param pID
	 * @return
	 * @throws Exception
	 */
	public Host getHost(int pID) throws Exception{
		Host vRet = null;
		
		if (pID >= 0) {
			if (!hostList.isEmpty()) {
				for (int i=0; ((i<hostList.size()) && (vRet == null)); i++) {
					vRet = hostList.get(i);
					
					if (vRet.getId() != pID) vRet = null;
				}
			}
		} else throw new Exception("04; gHo,BrM");
		
		return vRet;
	}
	//-----
	/**	Dh	29.07.2023
	 * 
	 * @param pID
	 * @return
	 * @throws Exception
	 */
	public String getHostName(int pID) throws Exception{
		String vRet = null;
		Host vCur;
		
		if (pID >= 0) {
			if (!hostList.isEmpty()) {
				for (int i=0; ((i<hostList.size()) && (vRet == null)); i++) {
					vCur = hostList.get(i);
					
					if (vCur.getId() == pID) vRet = vCur.getName();
				}
			}
		} else vRet = "";
		
		return vRet;
	}
	/**	Dh	29.07.2023
	 * 
	 * @param pID
	 * @return
	 * @throws Exception
	 */
	public String getHostURL(int pID) throws Exception{
		String vRet = null;
		Host vCur;
		
		if (pID >= 0) {
			if (!hostList.isEmpty()) {
				for (int i=0; ((i<hostList.size()) && (vRet == null)); i++) {
					vCur = hostList.get(i);
					
					if (vCur.getId() == pID) vRet = vCur.getUrl();
				}
			}
		} else throw new Exception("04; gHURL,BrM");
		
		return vRet;
	}
	
	//------------------------------------------------------------------------------------------------
	
	/**	Dh	26.07.2023
	 * 
	 * @param pBrowserFile
	 * @throws Exception
	 */
	public void setBrowserFile(File pBrowserFile) throws Exception {
		if (pBrowserFile != null) browserFile = pBrowserFile;
		else throw new Exception("04; sBF,BrM");
	}
	/**	Dh	26.07.2023
	 * 
	 * @param pBrowserList
	 * @throws Exception
	 */
	public void setHostList(ArrayList<Host> pHostList) throws Exception {
		if (pHostList != null) hostList = (ArrayList<Host>)pHostList.clone();
		else throw new Exception("04; sHL,BrM");
	}
	
	/**	Dh	26.07.2023
	 * 
	 * @param pID
	 * @param pName
	 * @throws Exception
	 */
	public void setHostName(int pID, String pName) throws Exception{
		if (pID >= 0) {
			if (!hostList.isEmpty()) {
				for (Host vHost : hostList) {
					if (vHost.getId() == pID) vHost.setName(pName);
				}
			}
		} else throw new Exception("04; sHN,BrM");
	}
	/**	Dh	26.07.2023
	 * 
	 * @param pID
	 * @param pURL
	 * @throws Exception
	 */
	public void setHostURL(int pID, String pURL) throws Exception{
		if (pID >= 0) {
			if (!hostList.isEmpty()) {
				for (Host vHost : hostList) {
					if (vHost.getId() == pID) vHost.setUrl(pURL);
				}
			}
		} else throw new Exception("04; sHURL,BrM");
	}
	
//----------------------------------------------------------------------------------------------------

	/**	Dh	26.07.2023
	 * 
	 * @param pID
	 * @return
	 * @throws Exception
	 */
	public boolean haveHost(int pID) throws Exception{
		boolean vRet = false;
		Host vCur;
		
		if (pID >= 0) {
			if (!hostList.isEmpty()) {
				for (int i=0; ((i<hostList.size()) && (vRet == false)); i++) {
					vCur = hostList.get(i);
					
					if (vCur.getId() == pID) vRet = true;
				}
			}
		} else throw new Exception("04; hHo,BrM");
		
		return vRet;
	}
	
	//------------------------------------------------------------------------------------------------
	
	/**	Dh	26.07.2023
	 * 
	 * @param pHoster
	 * @throws Exception
	 */
	public void addHost(Host pHost) throws Exception{
		if (pHost != null) hostList.add(pHost);
		else throw new Exception("04; aHo,BrM");
	}
	/**	Dh	26.07.2023
	 * 
	 * @param pID
	 * @throws Exception
	 */
	public void removeHost(int pID) throws Exception{
		Host vCur;
		
		if (pID >= 0) {
			if (!hostList.isEmpty()) {
				for (int i=0; i<hostList.size(); i++) {
					vCur = hostList.get(i);
					
					if (vCur.getId() == pID) {
						hostList.remove(i);
						i--;
					}
				}
			}
		} else throw new Exception("04; rHo,BrM");
	}
	
	//------------------------------------------------------------------------------------------------
	
	/**	Dh	26.07.2023
	 * 
	 * @param pShow
	 * @throws Exception
	 */
	public void openShow(ShowElement pShow) throws Exception{
		int vHostID;
		String vURL = pShow.getUrl();
		
		vHostID = pShow.getHostID();
		if (vHostID != -1) vURL = getHostURL(vHostID)+vURL;
		
		if (vURL != null) {
			if (!vURL.equals("")) {
				if (browserFile.exists() && browserFile.canExecute()) {
			    	processBuilder = new ProcessBuilder();
			    	processBuilder.command(browserFile.toString(), vURL);
					process = processBuilder.start();
				} else throw new Exception("10; opS,BrM");
			} else throw new Exception("02; opS,BrM");
		} else throw new Exception("04; opS,BrM");
		
	}
}
