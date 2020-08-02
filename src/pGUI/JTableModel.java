/**	ShowManager v0.0	Dh	23.7.2020
 * 
 * 	pGUI
 * 	  JTableModel
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

import pDataStructures.List;
import pLogic.MainManager;

import javax.swing.table.*;

public class JTableModel extends AbstractTableModel {
	Object[][] tableData;
	boolean[][] editableCells;;
	int[] ids;
	String[] titles;
	 
	/**	Dh	21.7.2020
	 * 
	 */
	public JTableModel() {
		titles = new String[] {};
		ids = new int[] {};
		tableData = new Object[][] {{}};
		editableCells = new boolean[][] {{}};
	}
	/**	Dh	21.7.2020
	 * 
	 * @param pColumnTitles
	 * @param pRowCount
	 */
	public JTableModel(String[] pColumnTitles, int pRowCount) {
		if (pColumnTitles != null) {
			if (pColumnTitles.length >= 1) {
				titles = pColumnTitles.clone();
				ids = new int[pRowCount];
				tableData = new Object[pRowCount][titles.length];
				editableCells = new boolean[pRowCount][titles.length];
				
				try{setEditableTable(false);}
				catch(Exception ex) {MainManager.handleException(ex);}
			} else MainManager.handleException( new Exception("01; JTaMod_a"));
		} else MainManager.handleException( new Exception("04; JTaMod_a"));
	}
	/**	Dh	21.7.2020
	 * 
	 * @param pColumnTitles
	 * @param pIDs
	 * @param pData
	 */
	public JTableModel(String[] pColumnTitles, int[] pIDs, Object[][] pData) {
		try {
			setNewTable(pColumnTitles, pIDs, pData);
			editableCells = new boolean[ids.length][titles.length];
			setEditableTable(false);
		}catch(Exception ex) {MainManager.handleException(ex);}
	}
	/**	Dh	21.7.2020
	 * 
	 * @param pColumnTitles
	 * @param pIDList
	 * @param pDataDoubleList
	 */
	public JTableModel(String[] pColumnTitles, List pIDList, List pDataDoubleList) {
		try {
			setNewTable(pColumnTitles, pIDList, pDataDoubleList);
			editableCells = new boolean[ids.length][titles.length];
			setEditableTable(false);
		} catch(Exception ex) {MainManager.handleException(ex);}
	}
	/**	Dh	21.7.2020
	 * 
	 * @param pColumnTitles
	 * @param pIDs
	 * @param pData
	 * @param pEditableCells
	 */
	public JTableModel(String[] pColumnTitles, int[] pIDs, Object[][] pData, boolean[][] pEditableCells) {
		try {
			setNewTable(pColumnTitles, pIDs, pData);
			editableCells = new boolean[ids.length][titles.length];
			setEditableTable(pEditableCells);
		}catch(Exception ex) {MainManager.handleException(ex);}
	}
	/**	Dh	21.7.2020
	 * 
	 * @param pColumnTitles
	 * @param pIDList
	 * @param pDataDoubleList
	 * @param pEditableCells
	 */
	public JTableModel(String[] pColumnTitles, List pIDList, List pDataDoubleList, boolean[][] pEditableCells) {
		try {
			setNewTable(pColumnTitles, pIDList, pDataDoubleList);
			editableCells = new boolean[ids.length][titles.length];
			setEditableTable(pEditableCells);
		} catch(Exception ex) {MainManager.handleException(ex);}
	}
	
//--------------------------------------------------------------------------------------------------------
	
	/**	Dh	8.7.2020
	 * 
	 */
	public boolean isCellEditable(int pRowInd, int pColInd) {
        return editableCells[pRowInd][pColInd];
    }
	
	//----------------------------------------------------------------------------------------------------
	
	/**	Dh	3.7.2020
	 * 
	 */
	public int getColumnCount() {
		return titles.length;
	}
	/**	Dh	3.7.2020
	 * 
	 */
	public int getRowCount() {
		return ids.length;
	}
	
	/**	Dh	3.7.2020
	 * 
	 * @param pRowInd
	 * @return
	 */
	public int getID(int pRowInd){
		return ids[pRowInd];
	}
	/**	Dh	3.7.2020
	 * 
	 */
	public String getColumnName(int pCol){
		return titles[pCol];
	}
	
	/**	Dh	3.7.2020
	 * 
	 */
	public Class getColumnClass(int pColInd) {
		return getValueAt(0, pColInd).getClass();
	}
	
	/**	Dh	3.7.2020
	 * 
	 */
	public Object getValueAt(int pRowInd, int pColInd){
		return tableData[pRowInd][pColInd];
	}
	
	//----------------------------------------------------------------------------------------------------
	
	/**	Dh	3.7.2020
	 * 
	 * @param pID
	 * @param pRowInd
	 */
	public void setID(int pID, int pRowInd) {
		ids[pRowInd] = pID;
	}
	/**	Dh	3.7.2020
	 * 
	 * @param pTitle
	 * @param pColInd
	 */
	public void setColumnName(String pTitle, int pColInd) {
		titles[pColInd] = pTitle;
	}
	
	/**	Dh	8.7.2020
	 * 
	 * @param pIsEditable
	 * @param pRowInd
	 * @param pColInd
	 * @throws Exception
	 */
	public void setEditableCell(boolean pIsEditable, int pRowInd, int pColInd) throws Exception{
		if ((pRowInd >= 0) && (pRowInd < editableCells.length) && (pColInd >= 0) && (pColInd < editableCells[0].length)) {
			editableCells[pRowInd][pColInd] = pIsEditable;
		}else throw new Exception("02; JTaMod,sEC");
	}
	//-----
	/**	Dh	8.7.2020
	 * 
	 * @param pIsEditbale
	 * @param pRowInd
	 * @throws Exception
	 */
	public void setEditableRow(boolean pIsEditbale, int pRowInd) throws Exception{
		if ((pRowInd >= 0) && (pRowInd < editableCells.length)) {
			for (int i=0; i < editableCells[pRowInd].length; i++) {
				editableCells[pRowInd][i] = pIsEditbale;
			}
		}else throw new Exception("02; JTaMod,sER");
	}
	/**	Dh	8.7.2020
	 * 
	 * @param pIsEditbale
	 * @param pColInd
	 * @throws Exception
	 */
	public void setEditableColumn(boolean pIsEditbale, int pColInd) throws Exception{
		for (int i=0; i < editableCells.length; i++) {
			if ((pColInd >= 0) && (pColInd < editableCells.length)) {
				editableCells[i][pColInd] = pIsEditbale;
			}else throw new Exception("02; JTaMod,sEC");
		}
	}
	/**	Dh	8.7.2020
	 * 
	 * @param pIsEditbale
	 * @param pRowInd
	 * @throws Exception
	 */
	public void setEditableRow(boolean[] pIsEditbale, int pRowInd) throws Exception{
		if ((pRowInd >= 0) && (pRowInd < editableCells.length)) {
			if (pIsEditbale.length == editableCells[pRowInd].length) {
				for (int i=0; i < editableCells[pRowInd].length; i++) {
					editableCells[pRowInd][i] = pIsEditbale[i];
				}
			} else throw new Exception("01; JTaMod,sER");
		} else throw new Exception("02; JTaMod,sER");
	}
	/**	Dh	8.7.2020
	 * 
	 * @param pIsEditbale
	 * @param pColInd
	 * @throws Exception
	 */
	public void setEditableColumn(boolean[] pIsEditbale, int pColInd) throws Exception{
		if (pIsEditbale.length == editableCells.length) {
			for (int i=0; i < editableCells.length; i++) {
				if ((pColInd >= 0) && (pColInd < editableCells.length)) {
					editableCells[i][pColInd] = pIsEditbale[i];
				}else throw new Exception("02; JTaMod,sEC");
			}
		}else throw new Exception("01; JTaMod,sEC");
	}
	//-----
	/**	Dh	8.7.2020
	 * 
	 * @param pIsEditbale
	 * @throws Exception
	 */
	public void setEditableTable(boolean pIsEditbale) throws Exception{
		for (int i=0; i < editableCells.length; i++) {
			for (int u=0; u < editableCells[i].length; u++) {
				editableCells[i][u] = pIsEditbale;
			}
		}
	}
	/**	Dh	8.7.2020
	 * 
	 * @param pIsEditbale
	 * @throws Exception
	 */
	public void setEditableTable(boolean[][] pIsEditbale) throws Exception{
		if (pIsEditbale.length == editableCells.length) {
			for (int i=0; i < editableCells.length; i++) {
				if (pIsEditbale[i].length == editableCells[i].length) {
					for (int u=0; u < editableCells[i].length; u++) {
						editableCells[i][u] = pIsEditbale[i][u];
					}
				} else throw new Exception("01; JTaMod,sET");
			}
		} else throw new Exception("01; JTaMod,sET");
	}
	
	/**	Dh	8.7.2020
	 * 
	 * @param pObj
	 * @param pRowInd
	 * @param pColInd
	 */
	public void setValueAt(Object pObj, int pRowInd, int pColInd) {
		tableData[pRowInd][pColInd] = pObj;
		fireTableCellUpdated(pRowInd, pColInd);
	}
	
	/**	Dh	23.7.2020
	 * 
	 * @param pColumnTitles
	 * @param pIDs
	 * @param pData
	 * @throws Exception
	 */
	public void setNewTable(String[] pColumnTitles, int[] pIDs, Object[][] pData) throws Exception {
		boolean[][] vOldEditable;
		
		if (pColumnTitles != null) {
			if (pColumnTitles.length >= 1) titles = pColumnTitles.clone();
			else throw new Exception("01; JTaMod,sNT");
		} else throw new Exception("04; JTaMod,sNT");
		
		if (pIDs != null) {
			ids = pIDs.clone();
		} else throw new Exception("04; JTaMod,sNT");
		
		if (pData != null) {
			if (pData.length == ids.length) {
				if (pData.length >= 1) {
					tableData = new Object[ids.length][titles.length];
					for (int i=0; i < pData.length; i++) {
						if (pData[i].length == titles.length) tableData[i] = pData[i].clone();
						else throw new Exception("01; JTaMod,sNT");
					}
				} else tableData = new Object[][] {{}};
			} else throw new Exception("01; JTaMod,sNT");
		} else throw new Exception("04; JTaMod,sNT");
		
		if ((tableData.length != editableCells.length) || ((tableData.length >= 1) && (tableData[0].length != editableCells[0].length))) {
			vOldEditable = new boolean[tableData.length][titles.length];
			
			for (int i=0; i < ids.length; i++) {
				for (int u=0; u < titles.length; u++) {
					if ((editableCells.length >= 1) && (i < editableCells.length) && (u < editableCells[i].length)) vOldEditable[i][u] = editableCells[i][u];
					else vOldEditable[i][u] = false;
				}
			}
			
			editableCells = vOldEditable;
		}
		
		fireTableStructureChanged();
	}
	/**	Dh	23.7.2020
	 * 
	 * @param pColumnTitles
	 * @param pIDList
	 * @param pDataDoubleList
	 * @throws Exception
	 */
	public void setNewTable(String[] pColumnTitles, List pIDList, List pDataDoubleList) throws Exception{
		Object vCur;
		boolean[][] vOldEditable;
		
		if (pColumnTitles != null) {
			if (pColumnTitles.length >= 1) {
				titles = pColumnTitles.clone();
			} else throw new Exception("01; JTaMod,sNT");
		} else throw new Exception("04; JTaMod,sNT");
		
		if (pIDList != null) {
			if (pIDList.getContentNumber() >= 1) {
				ids = new int[pIDList.getContentNumber()];
				pIDList.toFirst();
				
				for (int i=0; !pIDList.isEnd(); i++) {
					vCur = pIDList.getCurrent();
					
					if (vCur instanceof Integer) {
						if ((int)vCur >= 0) ids[i] = (int)vCur;
						else throw new Exception("02; JTaMod,sNT");
					} else throw new Exception("06; JTaMod,sNT");
					
					pIDList.next();
				}
			} else ids = new int[] {};
		} else throw new Exception("04; JTaMod,sNT");
		
		if (pDataDoubleList != null) {
			if (pDataDoubleList.getContentNumber() == ids.length) {
				if (pDataDoubleList.getContentNumber() >= 1) {
					tableData = new Object[ids.length][titles.length];
					pDataDoubleList.toFirst();
					
					for (int i=0; !pDataDoubleList.isEnd(); i++) {
						vCur = pDataDoubleList.getCurrent();
						
						if (vCur instanceof List) {
							if (((List)vCur).getContentNumber() == titles.length) {
								((List)vCur).toFirst();
								
								for (int u=0; !((List)vCur).isEnd(); u++) {
									tableData[i][u] = ((List)vCur).getCurrent();
									
									((List)vCur).next();
								}
							} else throw new Exception("01; JTaMod,sNT");
						} else throw new Exception("06; JTaMod,sNT");
						
						pDataDoubleList.next();
					}
				} else tableData = new Object[][] {{}};
			} else throw new Exception("01; JTaMod,sNT");
		} else throw new Exception("04; JTaMod,sNT");
		
		if ((tableData.length != editableCells.length) || ((tableData.length >= 1) && (tableData[0].length != editableCells[0].length))) {
			vOldEditable = new boolean[tableData.length][titles.length];
			
			for (int i=0; i < ids.length; i++) {
				for (int u=0; u < titles.length; u++) {
					if ((editableCells.length >= 1) && (i < editableCells.length) && (u < editableCells[i].length)) vOldEditable[i][u] = editableCells[i][u];
					else vOldEditable[i][u] = false;
				}
			}
			
			editableCells = vOldEditable;
		}
		
		fireTableStructureChanged();
	}
	
//--------------------------------------------------------------------------------------------------------
	
	/**	Dh	3.7.2020
	 * 	
	 * 	Fuegt eine Spalte am Ende der Tabelle hinzu.
	 * 
	 * @param pTitle
	 * @param pColData
	 * @throws Exception
	 */
	public void addColumn(String pTitle, Object[] pColData) throws Exception {
		addColumnAtPosition(pTitle, pColData, titles.length);
	}
	/**	Dh	3.7.2020
	 * 
	 * 	Fuegt eine Spalte am Ende der Tabelle hinzu.
	 * 
	 * @param pTitle
	 * @param pColData
	 * @throws Exception
	 */
	public void addColumn(String pTitle, List pColDataList) throws Exception {
		addColumnAtPosition(pTitle, pColDataList, titles.length);
	}
	//-----
	/**	Dh	3.7.2020
	 * 
	 * 	Fuegt eine Zeile am Ende der Tabelle hinzu.
	 * 
	 * @param pRowData
	 * @throws Exception
	 */
	public void addRow(int pID, Object[] pRowData) throws Exception{
		addRowAtPosition(pID, pRowData, titles.length);
	}
	/**	Dh	3.7.2020
	 * 
	 * 	Fuegt eine Zeile am Ende der Tabelle hinzu.
	 * 
	 * @param pRowDataList
	 * @throws Exception
	 */
	public void addRow(int pID, List pRowDataList) throws Exception{
		addRowAtPosition(pID, pRowDataList, titles.length);
	}
	
	/**	Dh	3.7.2020
	 * 
	 * @param pTitle
	 * @param pColData
	 * @param pColPos
	 * @throws Exception
	 */
	public void addColumnAtPosition(String pTitle, Object[] pColData, int pColPos) throws Exception {
		String[] vNewTitles;
		Object[] vNewColumn;
		
		if (pTitle != null && pColData != null) {
			if (!pTitle.equals("") && (pColPos >= 0) && (pColPos <= titles.length)) {
				if (pColData.length == ids.length) {
					vNewTitles = new String[titles.length+1];
					
					for (int i=0; i < (titles.length+1); i++) {
						if (i < pColPos) vNewTitles[i] = titles[i];
						else if (i > pColPos) vNewTitles[i] = titles[i-1];
						else vNewTitles[i] = pTitle;
					}
					
					for (int i=0; i < pColData.length; i++) {
						vNewColumn = new Object[titles.length+1];
						
						for (int u=0; u < (titles.length+1); u++) {
							if (u < pColPos) vNewColumn[u] = tableData[i][u];
							else if (u > pColPos) vNewColumn[u] = tableData[i][u-1];
							else vNewColumn[u] = pColData[i];
						}
						
						tableData[i] = vNewColumn.clone();
					}
					
					titles = vNewTitles;
					fireTableStructureChanged();
				} throw new Exception("01; JTaMod,aCaP");
			} throw new Exception("02; JTaMod,aCaP");
		} throw new Exception("04; JTaMod,aCaP");
	}
	/**	Dh	3.7.2020
	 * 
	 * @param pTitle
	 * @param pColData
	 * @param pColPos
	 * @throws Exception
	 */
	public void addColumnAtPosition(String pTitle, List pColDataList, int pColPos) throws Exception {
		String[] vNewTitles;
		Object[] vNewColumn;
		
		if (pTitle != null && pColDataList != null) {
			if (!pTitle.equals("") && (pColPos >= 0) && (pColPos <= titles.length)) {
				if (pColDataList.getContentNumber() == ids.length) {
					vNewTitles = new String[titles.length+1];
					pColDataList.toFirst();
					
					for (int i=0; i < (titles.length+1); i++) {
						if (i < pColPos) vNewTitles[i] = titles[i];
						else if (i > pColPos) vNewTitles[i] = titles[i-1];
						else vNewTitles[i] = pTitle;
					}
					
					for (int i=0; !pColDataList.isEnd(); i++) {
						vNewColumn = new Object[titles.length+1];
						
						for (int u=0; u < (titles.length+1); u++) {
							if (u < pColPos) vNewColumn[u] = tableData[i][u];
							else if (u > pColPos) vNewColumn[u] = tableData[i][u-1];
							else vNewColumn[u] = pColDataList.getCurrent();
						}
						
						tableData[i] = vNewColumn.clone();
						pColDataList.next();
					}
					
					titles = vNewTitles;
					fireTableStructureChanged();
				} throw new Exception("01; JTaMod,aCaP");
			} throw new Exception("02; JTaMod,aCaP");
		} throw new Exception("04; JTaMod,aCaP");
	}
	//-----
	/**	Dh	3.7.2020
	 * 
	 * @param pID
	 * @param pRowData
	 * @param pRowPos
	 * @throws Exception
	 */
	public void addRowAtPosition(int pID, Object[] pRowData, int pRowPos) throws Exception{
		Object[][] vNewData;
		
		if (pRowData != null) {
			if (pRowData.length == titles.length) {
				if ((pRowPos >= 0) && (pRowPos <= titles.length) && (pID >= 0)) {
				vNewData = new Object[ids.length+1][titles.length];
				
				for (int i=0; i < (ids.length+1); i++) {
					if (i < pRowPos) vNewData[i] = tableData[i];
					else if (i > pRowPos) vNewData[i] = tableData[i-1];
					else vNewData[i] = pRowData;
				}
				
				tableData = vNewData.clone();
				fireTableRowsInserted(pRowPos, pRowPos);
				} else throw new Exception("02; JTaMod,aRaP");
			} else throw new Exception("01; JTaMod,aRaP");
		} else throw new Exception("04; JTaMod,aRaP");
	}
	/**	Dh	3.7.2020
	 * 
	 * @param pID
	 * @param pRowDataList
	 * @param pRowPos
	 * @throws Exception
	 */
	public void addRowAtPosition(int pID, List pRowDataList, int pRowPos) throws Exception{
		Object[][] vNewData;
		Object[] vNewRow;
		
		if (pRowDataList != null) {
			if (pRowDataList.getContentNumber() == titles.length) {
				if ((pRowPos >= 0) && (pRowPos <= titles.length) && (pID >= 0)) {
				vNewData = new Object[ids.length+1][titles.length];
				vNewRow = new Object[titles.length];
				
				pRowDataList.toFirst();
				for (int i=0; !pRowDataList.isEnd(); i++) {
					vNewRow[i] = pRowDataList.getCurrent();
					
					pRowDataList.next();
				}
				
				for (int i=0; i < (ids.length+1); i++) {
					if (i < pRowPos) vNewData[i] = tableData[i];
					else if (i > pRowPos) vNewData[i] = tableData[i-1];
					else vNewData[i] = vNewRow;
				}
				
				tableData = vNewData.clone();
				fireTableRowsInserted(pRowPos, pRowPos);
				} else throw new Exception("02; JTaMod,aRaP");
			} else throw new Exception("01; JTaMod,aRaP");
		} else throw new Exception("04; JTaMod,aRaP");
	}

	//----------------------------------------------------------------------------------------------------
	
	/**	Dh	21.7.2020
	 * 
	 * 	Entfernt die letzte Spalte der Tabelle.
	 */
	public void removeColumn() {
		try {removeColumnAtPosition(titles.length-1);}
		catch(Exception ex) {MainManager.handleException(ex);}
	}
	/**	Dh	3.7.2020
	 * 
	 * 	Entfernt die letzte Zeile der Tabelle.
	 */
	public void removeRow() {
		try {removeRowAtPosition(ids.length-1);}
		catch(Exception ex) {MainManager.handleException(ex);}
	}
	
	/**	Dh	3.7.2020
	 * 
	 * @param pColPos
	 * @throws Exception
	 */
	public void removeColumnAtPosition(int pColPos) throws Exception{
		String[] vNewTitles;
		Object[] vNewRow;
		
		if ((pColPos >= 0) && (pColPos < titles.length)) {
			vNewTitles = new String[titles.length-1];
			
			for (int i=0; i < vNewTitles.length; i++) {
				if (i < pColPos) vNewTitles[i] = titles[i];
				else if (i >= pColPos) vNewTitles[i] = titles[i+1];
			}
			
			for (int i=0; i < ids.length; i++) {
				vNewRow = new Object[vNewTitles.length];
				
				for (int u=0; u < vNewRow.length; u++) {
					if (u < pColPos) vNewRow[u] = tableData[i][u];
					else if (u >= pColPos) vNewRow[u] = tableData[i][u+1];
				}
				
				tableData[i] = vNewRow.clone();
			}
			
			titles = vNewTitles;
			fireTableStructureChanged();
		} else throw new Exception("02; JTaMod,rCaP");
	}
	/**	Dh	3.7.2020
	 * 
	 * @param pRowPos
	 * @throws Exception
	 */
	public void removeRowAtPosition(int pRowPos) throws Exception{
		int[] vNewIDs;
		Object[][] vNewData;
		
		if ((pRowPos >= 0) && (pRowPos < ids.length)) {
			vNewIDs = new int[ids.length-1];
			vNewData = new Object[ids.length-1][titles.length];
			
			for (int i=0; i < vNewIDs.length; i++) {
				if (i < pRowPos) {
					vNewIDs[i] = ids[i];
					vNewData[i] = tableData[i];
				}
				else if (i >= pRowPos) {
					vNewIDs[i] = ids[i+1];
					vNewData[i] = tableData[i+1];
				}
			}
			
			tableData = vNewData;
			ids = vNewIDs;
			fireTableRowsDeleted(pRowPos, pRowPos);
		} else throw new Exception("02; JTaMod,rCaP");
	}
	
//--------------------------------------------------------------------------------------------------------

	/**	Dh	3.7.2020
	 * 
	 */
	public void clearTable() {
		titles = new String[] {};
		ids = new int[] {};
		tableData = new Object[][] {{}};
		fireTableStructureChanged();
	}
	
}
