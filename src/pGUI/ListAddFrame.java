/**	ShowManager v0.0	Dh	23.7.2020
 * 
 * 	pGUI
 * 	  ModFrame
 * 		ListAddFrame
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

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.SwingConstants;

import pDataStructures.List;

import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import pLogic.LogicManager;
import pLogic.MainManager;
import pLogic.ShowManager;
import pLogic.ShowManagerElement;
import javax.swing.ListSelectionModel;

public class ListAddFrame extends ModFrame {
	private int showListID;
	private ShowManager rManager;
	
	private JPanel contentPane;
	private JScrollPane spListScrollPane_0, spListScrollPane_1;
	
	private JLabel lFrameTitle, lListLable_0, lListLable_1, lListLable_2;
	private JTextField tfListField_0;
	private JList liShowList_0, liShowList_1;
	
	private JListModel rListModel_0, rListModel_1;
	
	private JButton btListButton_0, btListButton_1, btFrameButton_0, btFrameButton_1;

	/**	Dh	23.7.2020
	 * 
	 * @param pGUIManager
	 * @param pManager
	 */
	public ListAddFrame(GUIManager pGUIManager, LogicManager pManager, int pShowListID, int pX, int pY) {
		super(pGUIManager, pManager, pX, pY);
		
		rManager = (ShowManager)super.rManager;
		if (pShowListID >= -1) showListID = pShowListID;
		else MainManager.handleException(new Exception("02 LiAdFra"));
		
		if (showListID != -1) {
			lFrameTitle.setText("Liste bearbeiten");
			btFrameButton_0.setText("Bearbeiten");
			try {tfListField_0.setText(rManager.getShowListName(showListID));}
			catch(Exception ex) {MainManager.handleException(ex);}
		}
		
		try{ updatedLists();}
		catch(Exception ex) {MainManager.handleException(ex);}
	}
	
	//------------------------------------------------------------------------------------------------
	
	/**	Dh	22.7.2020
	 * 
	 */
	protected void initModels() {
		rListModel_0 = new JListModel();
		rListModel_1 = new JListModel();
	}
		
	//------------------------------------------------------------------------------------------------
	
	/**	Dh	23.7.2020
	 * 
	 */
	protected void initFrame() {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(xPos-230, yPos-207, 460, 414);
		setResizable(false);
		setTitle("Liste hinzuf\u00FCgen");
		
		contentPane = new JPanel();
		getContentPane().add(contentPane, BorderLayout.CENTER);
		
		lFrameTitle = new JLabel("Liste hinzuf\u00FCgen");
		lFrameTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lFrameTitle.setFont(new Font("Liberation Serif", Font.BOLD, 18));
		
		lListLable_0 = new JLabel("Liste:");
		lListLable_0.setFont(new Font("Liberation Serif", Font.PLAIN, 12));
		
		lListLable_1 = new JLabel("Ausgew\u00E4hlte:");
		lListLable_1.setHorizontalAlignment(SwingConstants.CENTER);
		lListLable_1.setFont(new Font("Liberation Serif", Font.BOLD, 16));
		
		lListLable_2 = new JLabel("M\u00F6gliche:");
		lListLable_2.setHorizontalAlignment(SwingConstants.CENTER);
		lListLable_2.setFont(new Font("Liberation Serif", Font.BOLD, 16));
		
		tfListField_0 = new JTextField();
		tfListField_0.setColumns(10);
		tfListField_0.setFont(new Font("Liberation Serif", Font.PLAIN, 12));
		
		spListScrollPane_0 = new JScrollPane();
		
		liShowList_0 = new JList();
		liShowList_0.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		liShowList_0.setModel(rListModel_0);
		liShowList_0.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (liShowList_0.getSelectedIndex() != -1) liShowList_1.clearSelection();
				
				enableListButtons(true);
			}
		});
		spListScrollPane_0.setViewportView(liShowList_0);
		
		btListButton_0 = new JButton("Hinzuf\u00FCgen");
		btListButton_0.setFont(new Font("Liberation Serif", Font.BOLD, 12));
		btListButton_0.setEnabled(false);
		btListButton_0.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addToList();
			}
		});
		
		btListButton_1 = new JButton("Entfernen");
		btListButton_1.setFont(new Font("Liberation Serif", Font.BOLD, 12));
		btListButton_1.setEnabled(false);
		btListButton_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				removeElement();
			}
		});
		
		spListScrollPane_1 = new JScrollPane();
		
		liShowList_1 = new JList();
		liShowList_1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		liShowList_1.setModel(rListModel_1);
		liShowList_1.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (liShowList_1.getSelectedIndex() != -1)liShowList_0.clearSelection();
				
				enableListButtons(true);
			}
		});
		spListScrollPane_1.setViewportView(liShowList_1);
		
		btFrameButton_0 = new JButton("Hinzuf\u00FCgen");
		btFrameButton_0.setFont(new Font("Liberation Serif", Font.BOLD, 12));
		btFrameButton_0.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addElement();
			}
		});
		
		btFrameButton_1 = new JButton("Abbrechen");
		btFrameButton_1.setFont(new Font("Liberation Serif", Font.BOLD, 12));
		btFrameButton_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cancel();
			}
		});
					
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(btFrameButton_1, GroupLayout.DEFAULT_SIZE, 417, Short.MAX_VALUE)
						.addComponent(btFrameButton_0, GroupLayout.DEFAULT_SIZE, 417, Short.MAX_VALUE)
						.addComponent(lFrameTitle, GroupLayout.DEFAULT_SIZE, 417, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lListLable_0, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(tfListField_0, GroupLayout.DEFAULT_SIZE, 369, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(lListLable_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(spListScrollPane_0, GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(btListButton_1, GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)
								.addComponent(btListButton_0, GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addComponent(lListLable_2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(spListScrollPane_1, GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE))))
					.addGap(17))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(lFrameTitle)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lListLable_0)
						.addComponent(tfListField_0, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lListLable_1)
								.addComponent(lListLable_2))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(spListScrollPane_1, GroupLayout.PREFERRED_SIZE, 179, GroupLayout.PREFERRED_SIZE)
								.addComponent(spListScrollPane_0, GroupLayout.PREFERRED_SIZE, 179, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(86)
							.addComponent(btListButton_0, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
							.addGap(27)
							.addComponent(btListButton_1, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)))
					.addGap(18)
					.addComponent(btFrameButton_0, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btFrameButton_1, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(26, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}

//----------------------------------------------------------------------------------------------------
	
	/**	Dh	22.7.2020
	 * 
	 */
	protected void updatedLists() throws Exception{
		int vCurID;
		List vIDList;
		
		if (showListID != -1) vIDList = rManager.getShowListShowIDs(showListID);
		else vIDList = new List();
		
		addListToModel(rListModel_0, vIDList, 1);
		
		vIDList = rManager.getShowIDs();
		
		rListModel_1.clear();
		if (!vIDList.isEmpty()) {
			vIDList.toFirst();
			
			while(!vIDList.isEnd()) {
				vCurID = (int)vIDList.getCurrent();
				
				if ((showListID == -1) || (!rManager.hasShowListShow(showListID, vCurID))) rListModel_1.addElement(rManager.getShowName(vCurID), vCurID);
				
				vIDList.next();
			}
		}
	}
	
	/**	Dh	22.7.2020
	 * 
	 * @param pEnabel
	 */
	private void enableListButtons(boolean pEnabel) {
		if ((pEnabel == false) || (liShowList_0.getSelectedIndex() != -1)) btListButton_1.setEnabled(pEnabel);
		else btListButton_1.setEnabled(false);
		if ((pEnabel == false) || (liShowList_1.getSelectedIndex() != -1)) btListButton_0.setEnabled(pEnabel);
		else btListButton_0.setEnabled(false);
	}
	
//----------------------------------------------------------------------------------------------------
	
	/**	Dh	22.7.2020
	 * 
	 */
	private void addToList() {
		int vID;
		int vSelInd = liShowList_1.getSelectedIndex();
		
		if (vSelInd != -1) {
			vID = (int)rListModel_1.getObjectAt(vSelInd);
			try {
				rListModel_0.addElement(rManager.getShowName(vID), vID);
				
				rListModel_1.remove(vSelInd);
			}catch(Exception ex) {MainManager.handleException(ex);}
		}
	}
	/**	Dh	22.7.2020
	 * 
	 */
	private void removeElement() {
		int vID;
		int vSelInd = liShowList_0.getSelectedIndex();
		
		if (vSelInd != -1) {
			vID = (int)rListModel_0.getObjectAt(vSelInd);
			try {
				rListModel_1.addElement(rManager.getShowName(vID), vID);
				
				rListModel_0.remove(vSelInd);
			}catch(Exception ex) {MainManager.handleException(ex);}
		}
	}
	
	/**	Dh	22.7.2020
	 * 
	 */
	protected void addElement() {
		String vName = null;
		List vShowIDList = new List();
		
		if (!tfListField_0.getText().equals("")) vName = tfListField_0.getText();
		
		if (rListModel_0.getSize() > 0) {
			vShowIDList = new List();
			
			for (int i=0; i < rListModel_0.getSize(); i++) {
				vShowIDList.append(rListModel_0.getObjectAt(i));
			}
		}
		
		if (vName != null) {
			try {
				if (showListID == -1) {
					if (!vShowIDList.isEmpty()) rManager.addShowList(vName, vShowIDList);
					else rManager.addShowList(vName);
				} else {
					rManager.setShowListName(showListID, vName);
					rManager.setShowListShowIDs(showListID, vShowIDList);
				}
				
				cancel();
			}catch(Exception ex) {MainManager.handleException(ex);}
		}
	}
	
	//------------------------------------------------------------------------------------------------

	/**	Dh	22.7.2020
	 * 
	 */
	protected void freezeFrame(boolean pFreeze) throws Exception{
		tfListField_0.setEnabled(!pFreeze);
		
		liShowList_0.setEnabled(!pFreeze);
		liShowList_1.setEnabled(!pFreeze);
		
		enableListButtons(!pFreeze);
		
		btFrameButton_0.setEnabled(!pFreeze);
		btFrameButton_1.setEnabled(!pFreeze);
	}
}
