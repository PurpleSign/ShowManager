/**	ShowManager v0.0	Dh	28.7.2020
 * 
 * 	pLogic
 * 	  BrowserOpener
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
 * 	  10 File Error
 */
package pLogic;

import java.io.File;

import pDataStructures.List;

public class BrowserOpener {
	private File browserFile;
	private ProcessBuilder processBuilder;
	private Process process;
	private List hosterList;
	
	/**	Dh	18.7.2020
	 * 
	 * @param pBrowserFile
	 */
	public BrowserOpener(File pBrowserFile) {
		try {setBrowserFile(pBrowserFile);}
		catch(Exception ex) {
			browserFile = null;
			MainManager.handleException(ex);
		}
		
		hosterList = new List();
	}
	/**	Dh	18.7.2020
	 * 
	 * @param pBrowserFile
	 * @param pBrowserList
	 */
	public BrowserOpener(File pBrowserFile, List pBrowserList) {
		try {setBrowserFile(pBrowserFile);}
		catch(Exception ex) {
			browserFile = null;
			MainManager.handleException(ex);
		}
		
		try {setHosterList(pBrowserList);}
		catch(Exception ex) {
			hosterList = new List();
			MainManager.handleException(ex);
		}
	}
	
//----------------------------------------------------------------------------------------------------

	/**	Dh	18.7.2020
	 * 
	 * @return
	 */
	protected File getBrowserFile() {
		return browserFile;
	}
	/**	Dh	18.7.2020
	 * 
	 * @return
	 */
	protected List getHosterList() {
		return hosterList;
	}
	
	/**	Dh	19.7.2020
	 * 
	 * @param pID
	 * @return
	 * @throws Exception
	 */
	protected Host getHoster(int pID) throws Exception{
		Host vRet = null;
		
		if (pID >= 0) {
			if (!hosterList.isEmpty()) {
				hosterList.toFirst();
				
				while(!hosterList.isEnd() && (vRet == null)) {
					vRet = (Host) hosterList.getCurrent();
					
					if (vRet.getId() != pID) vRet = null; 
					
					hosterList.next();
				}
			}
		} else throw new Exception("04; BrOpe,gH");
		
		return vRet;
	}
	//-----
	/**	Dh	19.7.2020
	 * 
	 * @param pID
	 * @return
	 * @throws Exception
	 */
	protected String getHosterName(int pID) throws Exception{
		String vRet = null;
		Host vCur;
		
		if (pID >= 0) {
			if (!hosterList.isEmpty()) {
				hosterList.toFirst();
				
				while(!hosterList.isEnd() && (vRet == null)) {
					vCur = (Host) hosterList.getCurrent();
					
					if (vCur.getId() == pID) vRet = vCur.getName(); 
					
					hosterList.next();
				}
			}
		} else throw new Exception("04; BrOpe,gHN");
		
		return vRet;
	}
	/**	Dh	19.7.2020
	 * 
	 * @param pID
	 * @return
	 * @throws Exception
	 */
	protected String getHosterURL(int pID) throws Exception{
		String vRet = null;
		Host vCur;
		
		if (pID >= 0) {
			if (!hosterList.isEmpty()) {
				hosterList.toFirst();
				
				while(!hosterList.isEnd() && (vRet == null)) {
					vCur = (Host) hosterList.getCurrent();
					
					if (vCur.getId() == pID) vRet = vCur.getUrl();
					
					hosterList.next();
				}
			}
		} else throw new Exception("04; BrOpe,gHURL");
		
		return vRet;
	}
	
	//------------------------------------------------------------------------------------------------
	
	/**	Dh	18.7.2020
	 * 
	 * @param pBrowserFile
	 * @throws Exception
	 */
	protected void setBrowserFile(File pBrowserFile) throws Exception {
		if (pBrowserFile != null) browserFile = pBrowserFile;
		else throw new Exception("04; BrOpe,sBF");
	}
	/**	Dh	18.7.2020
	 * 
	 * @param pBrowserList
	 * @throws Exception
	 */
	protected void setHosterList(List pBrowserList) throws Exception {
		Object vCur;
		
		if (pBrowserList != null) {
			if (!pBrowserList.isEmpty()) {
				pBrowserList.toFirst();
				
				while(!pBrowserList.isEnd()) {
					vCur = pBrowserList.getCurrent();
					
					if (vCur != null) {
						if (!(vCur instanceof Host)) throw new Exception("06; BrOpe,sHL");
					} else throw new Exception("04; BrOpe,sHL");
					
					pBrowserList.next();
				}
			}
			
			hosterList = pBrowserList.copyList();
		} else throw new Exception("04; BrOpe,sHL");
	}
	
	/**	Dh	19.7.2020
	 * 
	 * @param pID
	 * @param pName
	 * @throws Exception
	 */
	protected void setHosterName(int pID, String pName) throws Exception{
		Host vCur;
		
		if (pID >= 0) {
			if (!hosterList.isEmpty()) {
				hosterList.toFirst();
				
				while(!hosterList.isEnd()) {
					vCur = (Host) hosterList.getCurrent();
					
					if (vCur.getId() == pID) {
						vCur.setName(pName);
						hosterList.toLast();
					}
					
					hosterList.next();
				}
			}
		} else throw new Exception("04; BrOpe,sHN");
	}
	/**	Dh	19.7.2020
	 * 
	 * @param pID
	 * @param pURL
	 * @throws Exception
	 */
	protected void setHosterURL(int pID, String pURL) throws Exception{
		Host vCur;
		
		if (pID >= 0) {
			if (!hosterList.isEmpty()) {
				hosterList.toFirst();
				
				while(!hosterList.isEnd()) {
					vCur = (Host) hosterList.getCurrent();
					
					if (vCur.getId() == pID) {
						vCur.setUrl(pURL);
						hosterList.toLast();
					}
					
					hosterList.next();
				}
			}
		} else throw new Exception("04; BrOpe,sHURL");
	}
	
//----------------------------------------------------------------------------------------------------
	
	/**	Dh	19.7.2020
	 * 
	 * @param pID
	 * @return
	 * @throws Exception
	 */
	protected boolean haveHoster(int pID) throws Exception{
		boolean vRet = false;
		Host vCur;
		
		if (pID >= 0) {
			if (!hosterList.isEmpty()) {
				hosterList.toFirst();
				
				while(!hosterList.isEnd() && (vRet == false)) {
					vCur = (Host) hosterList.getCurrent();
					
					if (vCur.getId() == pID) vRet = true; 
					
					hosterList.next();
				}
			}
		} else throw new Exception("04; BrOpe,hH");
		
		return vRet;
	}
	
	//------------------------------------------------------------------------------------------------
	
	/**	Dh	19.7.2020
	 * 
	 * @param pHoster
	 * @throws Exception
	 */
	protected void addHoster(Host pHoster) throws Exception{
		if (pHoster != null) {
			hosterList.append(pHoster);
		} else throw new Exception("04; BrOpe,aH");
	}
	/**	Dh	19.7.2020
	 * 
	 * @param pID
	 * @throws Exception
	 */
	protected void removeHoster(int pID) throws Exception{
		Host vCur;
		
		if (pID >= 0) {
			if (!hosterList.isEmpty()) {
				hosterList.toFirst();
				
				while(!hosterList.isEnd()) {
					vCur = (Host) hosterList.getCurrent();
					
					if (vCur.getId() == pID) {
						hosterList.remove();
						hosterList.toLast();
					}
					
					hosterList.next();
				}
			}
		} else throw new Exception("04; BrOpe,hH");
	}
	
	//------------------------------------------------------------------------------------------------
	
	/**	Dh	28.7.2020
	 * 
	 * @param pShow
	 * @throws Exception
	 */
	protected void openShow(ShowElement pShow) throws Exception{
		int vInd, vHostID;
		String vBrowserPath, vBrowserFile;
		String vURL = pShow.getUrl();
		
		vHostID = pShow.getHostID();
		if (vHostID != -1) vURL = getHosterURL(vHostID)+vURL;
		
		if (vURL != null) {
			if (!vURL.equals("")) {
				if (browserFile.exists() && browserFile.canExecute()) {
					vBrowserPath = browserFile.toString();
					vInd = vBrowserPath.lastIndexOf("\\");
					
					if ((vInd > 0) && (vInd < (vBrowserPath.length()-1))) {
			    		vBrowserFile = vBrowserPath.substring(vInd+1);
			    		vBrowserPath = vBrowserPath.substring(0, vInd);
			    		
			    		processBuilder = new ProcessBuilder();
			    		processBuilder.command(browserFile.toString(), vURL);
						process = processBuilder.start();
					}
				} else throw new Exception("10; BrOpe,oS");
			} else throw new Exception("02; BrOpe,oS");
		} else throw new Exception("04; BrOpe,oS");
		
	}
	
}
