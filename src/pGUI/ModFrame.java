/**	ShowManager v0.0	Dh	23.7.2020
 * 
 * 	pGUI
 * 	  ModFrame
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

import pLogic.Genre;
import pLogic.LogicManager;
import pLogic.ShowManager;
import pLogic.SettingManager;
import pLogic.ShowManagerElement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import pDataStructures.List;

import java.awt.Font;
import javax.swing.SwingConstants;

public abstract class ModFrame extends JFrame{
	protected int xPos, yPos;
	
	protected GUIManager rGUIManager;
	protected LogicManager rManager;
	
	/**	Dh	23.7.2020
	 * 
	 * @param pGUIManager
	 * @param pManager
	 */
	public ModFrame(GUIManager pGUIManager, LogicManager pManager, int pX, int pY) {
		rGUIManager = pGUIManager;
		rManager = pManager;
		
		xPos = pX;
		yPos = pY;
		
		initModels();
		initFrame();
	}
	
	//------------------------------------------------------------------------------------------------
	
	protected void initModels() {}
	
	//------------------------------------------------------------------------------------------------
	
	protected abstract void initFrame();
	
//----------------------------------------------------------------------------------------------------
	
	/**	Dh	22.7.2020
	 * 
	 * @param pJLM
	 * @param pList
	 */
	protected void addListToModel(JListModel pJLM, List pList, int pType) throws Exception{
		int vCurID;
		
		pJLM.clear();
		if (!pList.isEmpty()) {
			pList.toFirst();
			
			while(!pList.isEnd()) {
				vCurID = (int) pList.getCurrent();
				
				if (pType == 0) pJLM.addElement(((ShowManager)rManager).getGenreName(vCurID), vCurID);
				else if (pType == 1) pJLM.addElement(((ShowManager)rManager).getShowName(vCurID), vCurID); 
				else if (pType == 2) pJLM.addElement(((ShowManager)rManager).getShowListName(vCurID), vCurID);
				else if (pType == 3) pJLM.addElement(((SettingManager)rManager).getHosterName(vCurID), vCurID); 
				else throw new Exception("02; MoFra,aLtM");
				
				pList.next();
			}
		}
	}
	
	protected void updatedLists() throws Exception{}
	
//----------------------------------------------------------------------------------------------------
	
	protected abstract void addElement();
	/**	Dh	21.7.2020
	 * 
	 */
	protected void cancel() {
		rGUIManager.closeModFrame(this);
		
		closeFrame();
	}
	
	//------------------------------------------------------------------------------------------------
	
	/**	Dh	21.7.2020
	 * 
	 */
	protected void closeFrame() {
		setVisible(false);
		dispose();
	}
	
	protected abstract void freezeFrame(boolean pFreeze) throws Exception;
	
}
