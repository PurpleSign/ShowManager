/**	ShowManager v0.0	Dh	31.7.2020
 * 
 * 	pGUI
 * 	  GUIManager
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
package pGUI;

import java.awt.Point;

import pDataStructures.List;
import pLogic.MainManager;
import pLogic.SettingManager;
import pLogic.ShowManager;

public class GUIManager {
	private ShowManager rShowManager;
	private SettingManager rSettingManager;
	
	private MainFrame rMainFrame;
	private ExceptionFrame rExceptionFrame;
	private List rModFrameList;
	
	/**	Dh	31.7.2020
	 * 
	 * @param pShowManager
	 * @param pSettingManager
	 */
	public GUIManager(ShowManager pShowManager, SettingManager pSettingManager) {
		try {rShowManager = pShowManager;}
		catch(Exception ex) {MainManager.handleException(ex);}
		try {rSettingManager = pSettingManager;}
		catch(Exception ex) {MainManager.handleException(ex);}
		
		rMainFrame = new MainFrame(this, rShowManager, rSettingManager);
		
		rModFrameList = new List();
		
		if (rSettingManager.haveDatabaseConnection() == false) {
			try{openModFrame(11);}
			catch(Exception ex) {MainManager.handleException(ex);}
		}
	}

//----------------------------------------------------------------------------------------------------

	
	
//----------------------------------------------------------------------------------------------------
	
	/**	Dh	23.7.2020
	 * 
	 * @param vExcList
	 */
	public void openExceptionFrame(List vExcList) {
		Point vPoint = rMainFrame.getFrameMiddlePoint();
		
		rExceptionFrame = new ExceptionFrame(vExcList, this, (int)vPoint.getX(), (int)vPoint.getY());
		rExceptionFrame.setVisible(true);
		
		if (!rModFrameList.isEmpty()) {
			rModFrameList.toLast();
			try {((ModFrame)rModFrameList.getCurrent()).freezeFrame(true);}
			catch(Exception ex) {MainManager.handleException(ex);}
		} else {
			try{rMainFrame.freezeFrame(true);}
			catch(Exception ex) {MainManager.handleException(ex);}
		}
	}
	/**	Dh	23.7.2020
	 * 
	 * @param vExc
	 */
	public void openExceptionFrame(Exception vExc) {
		Point vPoint = rMainFrame.getFrameMiddlePoint();
		
		rExceptionFrame = new ExceptionFrame(new List(vExc), this, (int)vPoint.getX(), (int)vPoint.getY());
		rExceptionFrame.setVisible(true);

		if (!rModFrameList.isEmpty()) {
			rModFrameList.toLast();
			try {((ModFrame)rModFrameList.getCurrent()).freezeFrame(true);}
			catch(Exception ex) {MainManager.handleException(ex);}
		} else {
			try{rMainFrame.freezeFrame(true);}
			catch(Exception ex) {MainManager.handleException(ex);}
		}
	}
	
	/**	Dh	22.7.2020
	 * 
	 * @param pType
	 * @throws Exception
	 */
	protected void openModFrame(int pType) throws Exception{
		openModFrame(pType, -1);
	}
	/**	Dh	31.7.2020
	 * 
	 * @param pType
	 * @param pID
	 * @throws Exception
	 */
	protected void openModFrame(int pType, int pID) throws Exception{
		ModFrame vCur = null;
		Point vPoint = rMainFrame.getFrameMiddlePoint();
		
		switch(pType) {
		case 0:
			vCur = new GenreAddFrame(this, rShowManager, (int)vPoint.getX(), (int)vPoint.getY());
			vCur.setVisible(true);
			break;
		case 1:
			vCur = new ShowAddFrame(this, rShowManager, rSettingManager, -1, (int)vPoint.getX(), (int)vPoint.getY());
			vCur.setVisible(true);
			break;
		case 2:
			vCur = new ShowAddFrame(this, rShowManager, rSettingManager, pID, (int)vPoint.getX(), (int)vPoint.getY());
			vCur.setVisible(true);
			break;
		case 3:
			vCur = new ListAddFrame(this, rShowManager, -1, (int)vPoint.getX(), (int)vPoint.getY());
			vCur.setVisible(true);
			break;
		case 4:
			vCur = new HostAddFrame(this, rSettingManager, (int)vPoint.getX(), (int)vPoint.getY());
			vCur.setVisible(true);
			break;
		case 5:
			vCur = new BrowserEditFrame(this, rSettingManager, (int)vPoint.getX(), (int)vPoint.getY());
			vCur.setVisible(true);
			break;	
		case 6:
			if (rShowManager.haveShowList(pID) == true) {
				vCur = new ListAddFrame(this, rShowManager, pID, (int)vPoint.getX(), (int)vPoint.getY());
				vCur.setVisible(true);
			} else throw new Exception("02; GUIMan,oMF");
			break;
		case 7:
			vCur = new GenreEditingFrame(this, rShowManager, (int)vPoint.getX(), (int)vPoint.getY());
			vCur.setVisible(true);
			break;
		case 8:
			vCur = new ShowEditingFrame(this, rShowManager, rSettingManager, (int)vPoint.getX(), (int)vPoint.getY());
			vCur.setVisible(true);
			break;
		case 9:
			vCur = new HostEditingFrame(this, rSettingManager, (int)vPoint.getX(), (int)vPoint.getY());
			vCur.setVisible(true);
			break;
		case 10:
			vCur = new SettingEditingFrame(this, rSettingManager, (int)vPoint.getX(), (int)vPoint.getY());
			vCur.setVisible(true);
			break;
		case 11:
			vCur = new DatabaseConnectionFrame(this, rSettingManager, (int)vPoint.getX(), (int)vPoint.getY());
			vCur.setVisible(true);
			break;
		default:
			throw new Exception("02; GUIMan,oMF");
		}

		
		if (!rModFrameList.isEmpty()) {
			rModFrameList.toLast();
			((ModFrame)rModFrameList.getCurrent()).freezeFrame(true);
		} else rMainFrame.freezeFrame(true);
		
		rModFrameList.append(vCur);
	}
	
	//------------------------------------------------------------------------------------------------
	
	/**	Dh	23.7.2020
	 * 
	 */
	protected void closeExceptionFrame() {
		rExceptionFrame = null;	
		
		if (!rModFrameList.isEmpty()) {
			rModFrameList.toLast();
			try {((ModFrame)rModFrameList.getCurrent()).freezeFrame(false);}
			catch(Exception ex) {MainManager.handleException(ex);}
		} else {
			try{rMainFrame.freezeFrame(false);}
			catch(Exception ex) {MainManager.handleException(ex);}
		}
	}
	/**	Dh	31.7.2020
	 * 
	 * @param pFrame
	 */
	protected void closeModFrame(ModFrame pFrame){
		ModFrame vCur;
		
		if ((pFrame instanceof DatabaseConnectionFrame) && (rSettingManager.haveDatabaseConnection())) {
			try{
				rSettingManager.loadHosts();
				
				rShowManager.loadAll();
			} catch(Exception ex) {MainManager.handleException(ex);}
		}
		
		if (!rModFrameList.isEmpty()) {
			rModFrameList.toLast();
			
			if (rModFrameList.getContentNumber() > 1) {
				while(!rModFrameList.isFirst()) {
					if (((ModFrame)rModFrameList.getCurrent()).equals(pFrame)) {
						rModFrameList.remove();
						pFrame = null;
						rModFrameList.toFirst();
					}else rModFrameList.prior();
				}
			} else {
				if (((ModFrame)rModFrameList.getCurrent()).equals(pFrame)) {
					rModFrameList.remove();
					pFrame = null;
				}
			}
			
			if (pFrame == null) {
				if (!rModFrameList.isEmpty()) {
					rModFrameList.toLast();
					try {((ModFrame)rModFrameList.getCurrent()).freezeFrame(false);}
					catch(Exception ex) {MainManager.handleException(ex);}
				}else{
					try{rMainFrame.freezeFrame(false);}
					catch(Exception ex) {MainManager.handleException(ex);}
				}
			}
		}
	}
	
	protected void closeModFrames() {
		
	}
	
	/**	Dh	21.7.2020
	 * 
	 */
	public void closeAllFrames() {
		closeModFrames();
		
		rMainFrame.setVisible(false);
		rMainFrame.dispose();
		rMainFrame = null;
	}
}
