/**	ShowManager v0.0	Dh	30.7.2020
 * 
 * 	pGUI
 * 	  ListPanel
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

import java.awt.Font;
import java.awt.LayoutManager;

import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;

import pLogic.MainManager;

import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class ListPanel extends JPanel {
	private int showListID;
	
	private JScrollPane scScrollPane;
	private JLabel lTitle, lListCount;
	private JTable tTable;
	
	private JTableModel rTableModel;
	
	/**	Dh	21.7.2020
	 * 
	 * @param pShowListID
	 * @param pShowListName
	 */
	public ListPanel(int pShowListID, String pShowListName) {
		if (pShowListID >= -1) showListID = pShowListID;
		else MainManager.handleException(new Exception("02; LiPan"));
		
		rTableModel = new JTableModel();
		
		initPanel();
		
		try {setShowListName(pShowListName);}
		catch(Exception ex) {MainManager.handleException(ex);}
	}
	
	/**	Dh	30.7.2020
	 * 
	 */
	private void initPanel() {
		lTitle = new JLabel("");
		lTitle.setFont(new Font("Liberation Serif", Font.BOLD, 18));
		lTitle.setHorizontalAlignment(SwingConstants.CENTER);
		
		lListCount = new JLabel("Anzahl: ");
		lListCount.setFont(new Font("Liberation Serif", Font.PLAIN, 16));
		lListCount.setHorizontalAlignment(SwingConstants.CENTER);
		
		scScrollPane = new JScrollPane();
		
		tTable = new JTable();
		tTable.setModel(rTableModel);
		tTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tTable.getTableHeader().setReorderingAllowed(false);
		scScrollPane.setViewportView(tTable);
		
		GroupLayout gl_pPanle = new GroupLayout(this);
		gl_pPanle.setHorizontalGroup(
			gl_pPanle.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_pPanle.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_pPanle.createParallelGroup(Alignment.TRAILING)
						.addComponent(scScrollPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 767, Short.MAX_VALUE)
						.addComponent(lTitle, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 767, Short.MAX_VALUE)
						.addComponent(lListCount, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 767, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_pPanle.setVerticalGroup(
			gl_pPanle.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pPanle.createSequentialGroup()
					.addComponent(lTitle)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lListCount)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scScrollPane, GroupLayout.DEFAULT_SIZE, 589, Short.MAX_VALUE)
					.addContainerGap())
		);
		setLayout(gl_pPanle);
	}

//----------------------------------------------------------------------------------------------------
	
	/**	Dh	21.7.2020
	 * 
	 * @return
	 */
	protected int getShowListID() {
		return showListID;
	}
	
	/**	Dh	23.7.2020
	 * 
	 * @return
	 */
	protected JTable getTable() {
		return tTable;
	}
	/**	Dh	21.7.2020
	 * 
	 * @return
	 */
	protected JTableModel getTableModel() {
		return rTableModel;
	}
	
	/**	Dh	21.7.2020
	 * 
	 * @param pShowListName
	 * @throws Exception
	 */
	protected void setShowListName(String pShowListName)  throws Exception{
		if (pShowListName != null) {
			if (!pShowListName.equals("")) lTitle.setText(pShowListName);
			else throw new Exception("02; LiPan,sSLN");
		} else throw new Exception("04; LiPan,sSLN");
	}
	
//----------------------------------------------------------------------------------------------------

	/**	Dh	29.7.2020
	 * 
	 */
	protected void updateListCount() {
		lListCount.setText("Anzahl: "+rTableModel.getRowCount());
	}
}
