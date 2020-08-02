/**	ShowManager v0.0	Dh	31.7.2020
 * 
 * 	pGUI
 * 	  MainFrame
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
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import pDataStructures.List;
import pLogic.MainManager;
import pLogic.SettingManager;
import pLogic.ShowManager;

import javax.swing.JToolBar;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JSeparator;
import javax.swing.JCheckBox;
import javax.swing.JSplitPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Point;

import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JList;

import java.awt.ComponentOrientation;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import java.awt.Dimension;

public class MainFrame extends JFrame {
	private GUIManager rGUIManager;
	private ShowManager rShowManager;
	private SettingManager rSettingManager;
	
	private ListPanel lpAllShows;
	private List lShowLists;
	
	private JPanel contentPane, pLeftPanle;
	private JSplitPane splitPane;
	private JTabbedPane pRightPane;
	
	private JButton btShowButton_0, btShowButton_1, btShowButton_2, btShowButton_3, btListButton_0,  btListButton_1,  btListButton_2,
		btShowButton, btGenreButton, btSettingButton, btCloseButton;
	
	//------------------------------------------------------------------------------------------------
	
	private JMenuBar mbMenuBar;
	private JMenu mMenu_0, mMenu_1, mMenu_2;
	
	private JMenuItem mFileMenuItem_0, mFileMenuItem_1, mFileMenuItem_2, mFileMenuItem_3, mFileMenuItem_4;
	private JMenuItem mEditMenuItem_0, mEditMenuItem_1, mEditMenuItem_2, mEditMenuItem_3, mEditMenuItem_4, mEditMenuItem_5;
	private JMenuItem mSettingMenuItem_0, mSettingMenuItem_1, mSettingMenuItem_3;
	
	private JCheckBox cbSettingCheckBox_0, cbSettingCheckBox_1, cbSettingCheckBox_2, cbSettingCheckBox_3;
	
	private JSeparator sFileSeparator_0, sFileSeparator_1, sEditSeparator_0, sEditSeparator_1, sSettingSeparator_0, sSettingSeparator_1;
	private JTable tTable;
	private JMenuItem mSettingMenuItem_2;

	/**	Dh	23.7.2020
	 * 
	 * @param pGUIManager
	 * @param pShowManager
	 * @param pSettingManager
	 */
	public MainFrame(GUIManager pGUIManager, ShowManager pShowManager, SettingManager pSettingManager) {
		setMinimumSize(new Dimension(1024, 728));
		rGUIManager = pGUIManager;
		rShowManager = pShowManager;
		rSettingManager = pSettingManager;
		
		setTitle("Serien Manager v0.0");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 1024, 728);
		
		initMenuBar();
		initFrame();
		
		try { 
			updatetSettingMenus();
			//updateListPanel();
			updateTable();
			
		}catch(Exception ex) {MainManager.handleException(ex);}
		
		setVisible(true);
	}
	
	//------------------------------------------------------------------------------------------------
	
	/**	Dh	31.7.2020
	 * 
	 */
	private void initMenuBar() {
		mbMenuBar = new JMenuBar();
		setJMenuBar(mbMenuBar);
		
		mMenu_0 = new JMenu("Dateien");
		mbMenuBar.add(mMenu_0);
		
		mFileMenuItem_0 = new JMenuItem("Neue Serie");
		mFileMenuItem_0.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try{rGUIManager.openModFrame(1);}
				catch(Exception ex) {MainManager.handleException(ex);}
			}
		});
		mMenu_0.add(mFileMenuItem_0);
		
		mFileMenuItem_1 = new JMenuItem("Neue Liste");
		mFileMenuItem_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try{rGUIManager.openModFrame(3);}
				catch(Exception ex) {MainManager.handleException(ex);}
			}
		});
		mMenu_0.add(mFileMenuItem_1);
		
		mFileMenuItem_2 = new JMenuItem("Neues Genre");
		mFileMenuItem_2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try{ rGUIManager.openModFrame(0); }
				catch(Exception ex) {MainManager.handleException(ex);}
			}
		});
		mMenu_0.add(mFileMenuItem_2);
		
		sFileSeparator_0 = new JSeparator();
		mMenu_0.add(sFileSeparator_0);
		
		mFileMenuItem_3 = new JMenuItem("Speicher Alles");
		mFileMenuItem_3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					rSettingManager.saveAll();
					rShowManager.saveAll();
				} catch(Exception ex) {MainManager.handleException(ex);}
			}
		});
		mMenu_0.add(mFileMenuItem_3);
		
		sFileSeparator_1 = new JSeparator();
		mMenu_0.add(sFileSeparator_1);
		
		mFileMenuItem_4 = new JMenuItem("Beenden");
		mFileMenuItem_4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				close();
			}
		});
		mMenu_0.add(mFileMenuItem_4);
		
		mMenu_1 = new JMenu("Bearbeiten");
		mbMenuBar.add(mMenu_1);
		
		mEditMenuItem_0 = new JMenuItem("Bearbeite Serie");
		mEditMenuItem_0.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				editShow();
			}
		});
		mMenu_1.add(mEditMenuItem_0);
		
		mEditMenuItem_1 = new JMenuItem("Bearbeite Liste");
		mEditMenuItem_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				editList();
			}
		});
		mMenu_1.add(mEditMenuItem_1);
		
		sEditSeparator_0 = new JSeparator();
		mMenu_1.add(sEditSeparator_0);
		
		mEditMenuItem_2 = new JMenuItem("Bearbeite Serien");
		mEditMenuItem_2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try{ rGUIManager.openModFrame(8); }
				catch(Exception ex) {MainManager.handleException(ex);}
			}
		});
		mMenu_1.add(mEditMenuItem_2);
		
		mEditMenuItem_3 = new JMenuItem("Bearbeite Genres");
		mEditMenuItem_3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try{ rGUIManager.openModFrame(7); }
				catch(Exception ex) {MainManager.handleException(ex);}
			}
		});
		mMenu_1.add(mEditMenuItem_3);
		
		sEditSeparator_1 = new JSeparator();
		mMenu_1.add(sEditSeparator_1);
		
		mEditMenuItem_4 = new JMenuItem("Entferne Serie");
		mEditMenuItem_4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				removeShow();
			}
		});
		mMenu_1.add(mEditMenuItem_4);
		
		mEditMenuItem_5 = new JMenuItem("Entferne Liste");
		mEditMenuItem_5.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				removeList();
			}
		});
		mMenu_1.add(mEditMenuItem_5);
		
		mMenu_2 = new JMenu("Einstellungen");
		mbMenuBar.add(mMenu_2);
		
		mSettingMenuItem_0 = new JMenuItem("Bearbeite Browser");
		mSettingMenuItem_0.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try{ rGUIManager.openModFrame(5); }
				catch(Exception ex) {MainManager.handleException(ex);}
			}
		});
		mMenu_2.add(mSettingMenuItem_0);
		
		mSettingMenuItem_1 = new JMenuItem("Bearbeite Hosts");
		mSettingMenuItem_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try{ rGUIManager.openModFrame(9); }
				catch(Exception ex) {MainManager.handleException(ex);}
			}
		});
		mMenu_2.add(mSettingMenuItem_1);
		
		mSettingMenuItem_2 = new JMenuItem("Datenbank Verbindung");
		mSettingMenuItem_2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try{ rGUIManager.openModFrame(11); }
				catch(Exception ex) {MainManager.handleException(ex);}
			}
		});
		mMenu_2.add(mSettingMenuItem_2);
		
		sSettingSeparator_0 = new JSeparator();
		mMenu_2.add(sSettingSeparator_0);
		
		cbSettingCheckBox_0 = new JCheckBox("Bewertung an");
		cbSettingCheckBox_0.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				rSettingManager.setRatingEnabled(cbSettingCheckBox_0.isSelected());
				try {updateTable();}
				catch(Exception ex) {MainManager.handleException(ex);}
			}
		});
		mMenu_2.add(cbSettingCheckBox_0);
		
		cbSettingCheckBox_1 = new JCheckBox("Genres an");
		cbSettingCheckBox_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				rSettingManager.setGenreEnabled(cbSettingCheckBox_1.isSelected());
				try {updateTable();}
				catch(Exception ex) {MainManager.handleException(ex);}
			}
		});
		mMenu_2.add(cbSettingCheckBox_1);
		
		cbSettingCheckBox_2 = new JCheckBox("Host an");
		cbSettingCheckBox_2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				rSettingManager.setHostEnabled(cbSettingCheckBox_2.isSelected());
				try {updateTable();}
				catch(Exception ex) {MainManager.handleException(ex);}
			}
		});
		mMenu_2.add(cbSettingCheckBox_2);
		
		cbSettingCheckBox_3 = new JCheckBox("URLs an");
		cbSettingCheckBox_3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				rSettingManager.setURLEnabled(cbSettingCheckBox_3.isSelected());
				try {updateTable();}
				catch(Exception ex) {MainManager.handleException(ex);}
			}
		});
		mMenu_2.add(cbSettingCheckBox_3);
		
		sSettingSeparator_1 = new JSeparator();
		mMenu_2.add(sSettingSeparator_1);
		
		mSettingMenuItem_3 = new JMenuItem("Default");
		mSettingMenuItem_3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				rSettingManager.resetGUISettings();
				try {
					updateTable();
					updatetSettingMenus();
				} catch(Exception ex) {MainManager.handleException(ex);} 
			}
		});
		mMenu_2.add(mSettingMenuItem_3);
	}
	/**	Dh	21.7.2020
	 * 
	 */
	private void initFrame() {
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		splitPane = new JSplitPane();
		splitPane.setDividerLocation(200);
		contentPane.add(splitPane, BorderLayout.CENTER);
		
		initLeftPanle();
		initRightPanle();
	}
	
	/**	Dh	29.7.2020
	 * 
	 */
	private void initLeftPanle() {
		pLeftPanle = new JPanel();
		splitPane.setLeftComponent(pLeftPanle);
		
		btShowButton_0 = new JButton("Bearbeite Serie");
		btShowButton_0.setFont(new Font("Liberation Serif", Font.PLAIN, 12));
		btShowButton_0.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				editShow();
			}
		});
		
		btShowButton_1 = new JButton("Neue Serie");
		btShowButton_1.setFont(new Font("Liberation Serif", Font.PLAIN, 12));
		btShowButton_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try{rGUIManager.openModFrame(1);}
				catch(Exception ex) {MainManager.handleException(ex);}
			}
		});
		
		btShowButton_2 = new JButton("Entferne Serie");
		btShowButton_2.setFont(new Font("Liberation Serif", Font.PLAIN, 12));
		btShowButton_2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				removeShow();
			}
		});
		
		btShowButton_3 = new JButton("Starte Serie");
		btShowButton_3.setFont(new Font("Liberation Serif", Font.PLAIN, 12));
		btShowButton_3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				openShow();
			}
		});
		
		btListButton_0 = new JButton("Bearbeite Liste");
		btListButton_0.setFont(new Font("Liberation Serif", Font.PLAIN, 12));
		btListButton_0.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				editList();
			}
		});
		
		btListButton_1 = new JButton("Neue Liste");
		btListButton_1.setFont(new Font("Liberation Serif", Font.PLAIN, 12));
		btListButton_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try{rGUIManager.openModFrame(3);}
				catch(Exception ex) {MainManager.handleException(ex);}
			}
		});
		
		btListButton_2 = new JButton("Entferne Liste");
		btListButton_2.setFont(new Font("Liberation Serif", Font.PLAIN, 12));
		btListButton_2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				removeList();
			}
		});
		
		btShowButton = new JButton("Serien");
		btShowButton.setFont(new Font("Liberation Serif", Font.PLAIN, 12));
		btShowButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{ rGUIManager.openModFrame(8); }
				catch(Exception ex) {MainManager.handleException(ex);}
			}
		});
		
		btGenreButton = new JButton("Genres");
		btGenreButton.setFont(new Font("Liberation Serif", Font.PLAIN, 12));
		btGenreButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try{ rGUIManager.openModFrame(7); }
				catch(Exception ex) {MainManager.handleException(ex);}
			}
		});
		
		btSettingButton = new JButton("Einstellungen");
		btSettingButton.setFont(new Font("Liberation Serif", Font.PLAIN, 12));
		btSettingButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try{ rGUIManager.openModFrame(10); }
				catch(Exception ex) {MainManager.handleException(ex);}
			}
		});
		
		btCloseButton = new JButton("Beenden");
		btCloseButton.setFont(new Font("Liberation Serif", Font.PLAIN, 12));
		btCloseButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				close();
			}
		});
		
		GroupLayout gl_pLeftPanle = new GroupLayout(pLeftPanle);
		gl_pLeftPanle.setHorizontalGroup(
			gl_pLeftPanle.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pLeftPanle.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_pLeftPanle.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pLeftPanle.createParallelGroup(Alignment.LEADING)
							.addComponent(btCloseButton, GroupLayout.PREFERRED_SIZE, 179, GroupLayout.PREFERRED_SIZE)
							.addComponent(btSettingButton, GroupLayout.PREFERRED_SIZE, 179, GroupLayout.PREFERRED_SIZE)
							.addComponent(btShowButton, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 179, GroupLayout.PREFERRED_SIZE)
							.addComponent(btGenreButton, GroupLayout.PREFERRED_SIZE, 179, GroupLayout.PREFERRED_SIZE))
						.addComponent(btShowButton_3, GroupLayout.PREFERRED_SIZE, 179, GroupLayout.PREFERRED_SIZE)
						.addComponent(btShowButton_0, GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE)
						.addComponent(btShowButton_1, GroupLayout.PREFERRED_SIZE, 179, GroupLayout.PREFERRED_SIZE)
						.addComponent(btShowButton_2, GroupLayout.PREFERRED_SIZE, 179, GroupLayout.PREFERRED_SIZE)
						.addComponent(btListButton_0, GroupLayout.PREFERRED_SIZE, 179, GroupLayout.PREFERRED_SIZE)
						.addComponent(btListButton_1, GroupLayout.PREFERRED_SIZE, 179, GroupLayout.PREFERRED_SIZE)
						.addComponent(btListButton_2, GroupLayout.PREFERRED_SIZE, 179, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		gl_pLeftPanle.setVerticalGroup(
			gl_pLeftPanle.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_pLeftPanle.createSequentialGroup()
					.addGap(23)
					.addComponent(btShowButton_3, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btShowButton_0, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btShowButton_1, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btShowButton_2, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
					.addComponent(btListButton_0, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btListButton_1, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btListButton_2, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
					.addGap(61)
					.addComponent(btShowButton, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btGenreButton, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
					.addGap(59)
					.addComponent(btSettingButton, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btCloseButton, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		pLeftPanle.setLayout(gl_pLeftPanle);
	}
	/**	Dh	28.7.2020
	 * 
	 */
	private void initRightPanle() {
		pRightPane = new JTabbedPane(JTabbedPane.TOP);
		splitPane.setRightComponent(pRightPane);
		pRightPane.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
					if ((pRightPane.getSelectedIndex() != -1) && (!pRightPane.getSelectedComponent().equals(lpAllShows))) 
						setListPanelSelectionEnabled(true);
					else setListPanelSelectionEnabled(false);
			}
		});
		
		lpAllShows = new ListPanel(-1, "Alle Serien");
		lpAllShows.getTable().getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				int vSelCol;
				
				if (pRightPane.getSelectedIndex() != -1) {
					vSelCol = ((ListPanel)pRightPane.getSelectedComponent()).getTable().getSelectedRow();
					
					if ( vSelCol != -1) setTableSelectionEnabled(true);
					else setTableSelectionEnabled(false);
				} else setListPanelSelectionEnabled(false);
			}
		});
		try {updateListPanel();}
		catch(Exception ex) {MainManager.handleException(ex);}
	}
	
	//------------------------------------------------------------------------------------------------
	
	/**	Dh	21.7.2020
	 * 
	 * @param component
	 * @param popup
	 */
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
	
//----------------------------------------------------------------------------------------------------
	
	/**	Dh	23.7.2020
	 * 
	 * @param pGenreIDList
	 * @return
	 * @throws Exception
	 */
	private String genGenreNameString(List pGenreIDList) throws Exception {
		String vRet = "";
		int vGenreID;
		
		if (!pGenreIDList.isEmpty()) {
			pGenreIDList.toFirst();
			
			while(!pGenreIDList.isEnd()) {
				vGenreID = (int)pGenreIDList.getCurrent();
				
				if (rShowManager.haveGenre(vGenreID)) {
					if (!pGenreIDList.isFirst()) vRet += ", ";
					vRet += rShowManager.getGenreName(vGenreID);
				} else throw new Exception("02; MaFra,gGNS");
				
				pGenreIDList.next();
			}
		}
		
		return vRet;
	}
	
	/**	Dh	23.7.2020
	 * 
	 * @param pShowListIDList
	 * @return
	 * @throws Exception
	 */
	private List genShowListPanel(List pShowListIDList) throws Exception {
		int vShowListID;
 		String vShowListName;
 		ListPanel vTemp;
		List vRet = new List();
		
		if (!pShowListIDList.isEmpty()) {
			pShowListIDList.toFirst();
 			
 			while(!pShowListIDList.isEnd()) {
 				vShowListID = (int) pShowListIDList.getCurrent();
 				vShowListName = rShowManager.getShowListName(vShowListID);
 				vTemp = new ListPanel(vShowListID, vShowListName);
 				
 				lShowLists.append(vTemp);
 				
 				pShowListIDList.next();
 			}
 		}
		
		return vRet;
	}
	
	//------------------------------------------------------------------------------------------------
	
	/**	Dh	27.7.2020
	 * 
	 * @param pJLM
	 * @param pList
	 * @param pRatingOn
	 * @param pGenreOn
	 * @param pHostOn
	 * @param pURLOn
	 * @throws Exception
	 */
	private void addListToTable(JTableModel pJLM, List pList, boolean pRatingOn, boolean pGenreOn, boolean pHostOn, boolean pURLOn) throws Exception {
		int u;
		List vCurList;
		int[] vIDs;
		String[] vTitles;
		boolean[][] editable;
		Object[][] vData;
		
		if (pJLM != null) {
			pJLM.clearTable();
			vCurList = new List();
			
			vCurList.append("Serie");
			if (rSettingManager.isRatingEnabled())vCurList.append("Bewertung");
			if (rSettingManager.isGenreEnabled()) vCurList.append("Genres");
			if (rSettingManager.isHostEnabled()) vCurList.append("Host");
			if (rSettingManager.isURLEnabled()) vCurList.append("URL");
			
			vTitles = new String[vCurList.getContentNumber()];
			vCurList.toFirst();
			
			for(int i=0; i < vCurList.getContentNumber(); i++) {
				vTitles[i] = (String)vCurList.getCurrent();
				
				vCurList.next();
			}
			
			if (!pList.isEmpty()) {
				vIDs = new int[pList.getContentNumber()];
				vData = new Object[pList.getContentNumber()][vTitles.length];
				pList.toFirst();
				
				for (int i=0; i < vIDs.length; i++) {
					u=1;
					vIDs[i] = (int)pList.getCurrent();
					
					vData[i][0] = rShowManager.getShowName(vIDs[i]);
					if (pRatingOn == true) {
						vData[i][u] = rShowManager.getShowRating(vIDs[i]);
						u++;
					}
					if (pGenreOn == true) {
						vData[i][u] = genGenreNameString(rShowManager.getShowGenreIDs(vIDs[i]));
						u++;
					}
					if (pHostOn == true) {
						if (rShowManager.getShowHostID(vIDs[i]) != -1) vData[i][u] = rSettingManager.getHosterName(rShowManager.getShowHostID(vIDs[i]));
						else vData[i][u] = "";
						u++;
					}
					if (pURLOn == true) vData[i][u] = rShowManager.getShowURL(vIDs[i]);
					
					pList.next();
				}
			}else {
				vIDs = new int[] {};
				vData = new Object[][] {};
			}
			pJLM.setNewTable(vTitles, vIDs, vData);
			pJLM.setEditableTable(false);
			
		} else throw new Exception("04; MaFra,aLtT");
	}
	//-----
	/**	Dh	23.7.2020
	 * 
	 * @param pJT
	 * @param pRatingOn
	 * @param pGenreOn
	 * @param pHostOn
	 * @param pURLOn
	 * @throws Exception
	 */
	private void setTableSetting(JTable pJT, boolean pRatingOn, boolean pGenreOn, boolean pHostOn, boolean pURLOn) throws Exception{
		int u = 1;
		
		//pJT.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		if (pRatingOn == true) {
			pJT.getColumnModel().getColumn(u).setMaxWidth(120);
			pJT.getColumnModel().getColumn(u).setPreferredWidth(80);
			pJT.getColumnModel().getColumn(u).setCellRenderer(new DefaultTableCellRenderer() {
				@Override
				public Component getTableCellRendererComponent(JTable pTable, Object pObj, boolean isSelected, boolean hasFocus, int pRow, int pCol) {
					setHorizontalAlignment(SwingConstants.CENTER);
					setFont(new Font("Liberation Serif", Font.PLAIN, 12));
					
					if (isSelected == true) setBackground(UIManager.getColor("TextField.selectionBackground"));
					else setBackground(Color.white);
					
					setText(""+pTable.getValueAt(pRow, pCol));
					
					return this;
				}
			});
			u++;
		}
		if (pGenreOn == true) {
			pJT.getColumnModel().getColumn(u).setMinWidth(100);
			pJT.getColumnModel().getColumn(u).setPreferredWidth(150);
			pJT.getColumnModel().getColumn(u).setCellRenderer(new DefaultTableCellRenderer() {
				@Override
				public Component getTableCellRendererComponent(JTable pTable, Object pObj, boolean isSelected, boolean hasFocus, int pRow, int pCol) {
					setHorizontalAlignment(SwingConstants.CENTER);
					setFont(new Font("Liberation Serif", Font.PLAIN, 12));
					
					if (isSelected == true) setBackground(UIManager.getColor("TextField.selectionBackground"));
					else setBackground(Color.white);
					
					setText(""+pTable.getValueAt(pRow, pCol));
					
					return this;
				}
			});
			u++;
		}
		if (pHostOn == true) {
			pJT.getColumnModel().getColumn(u).setPreferredWidth(80);
			pJT.getColumnModel().getColumn(u).setCellRenderer(new DefaultTableCellRenderer() {
				@Override
				public Component getTableCellRendererComponent(JTable pTable, Object pObj, boolean isSelected, boolean hasFocus, int pRow, int pCol) {
					setHorizontalAlignment(SwingConstants.CENTER);
					setFont(new Font("Liberation Serif", Font.PLAIN, 12));
					
					if (isSelected == true) setBackground(UIManager.getColor("TextField.selectionBackground"));
					else setBackground(Color.white);
					
					setText(""+pTable.getValueAt(pRow, pCol));
					
					return this;
				}
			});
			u++;
		}
		if (pURLOn == true) {
			pJT.getColumnModel().getColumn(u).setMinWidth(100);
			pJT.getColumnModel().getColumn(u).setPreferredWidth(150);
			pJT.getColumnModel().getColumn(u).setCellRenderer(new DefaultTableCellRenderer() {
				@Override
				public Component getTableCellRendererComponent(JTable pTable, Object pObj, boolean isSelected, boolean hasFocus, int pRow, int pCol) {
					setFont(new Font("Liberation Serif", Font.PLAIN, 12));
					
					if (isSelected == true) setBackground(UIManager.getColor("TextField.selectionBackground"));
					else setBackground(Color.white);
					
					setText(""+pTable.getValueAt(pRow, pCol));
					
					return this;
				}
			});
			u++;
		}
		pJT.getColumnModel().getColumn(0).setPreferredWidth(200);
		pJT.getColumnModel().getColumn(0).setCellRenderer(new DefaultTableCellRenderer() {
			@Override
			public Component getTableCellRendererComponent(JTable pTable, Object pObj, boolean isSelected, boolean hasFocus, int pRow, int pCol) {
				setFont(new Font("Liberation Serif", Font.PLAIN, 12));
				
				if (isSelected == true) setBackground(UIManager.getColor("TextField.selectionBackground"));
				else setBackground(Color.white);
				
				setText(""+pTable.getValueAt(pRow, pCol));
				
				return this;
			}
		});
	}
	
	//------------------------------------------------------------------------------------------------
	
	/**	Dh	28.7.2020
	 * 
	 * @param pEnable
	 */
	private void setTableSelectionEnabled(boolean pEnable) {
		int vSelInd = pRightPane.getSelectedIndex();
		
		if ((vSelInd == -1) || (((ListPanel)pRightPane.getSelectedComponent()).getTable().getSelectedColumn() == -1)) pEnable = false;
		
		btShowButton_0.setEnabled(pEnable);
		btShowButton_2.setEnabled(pEnable);
		btShowButton_3.setEnabled(pEnable);
		
		mEditMenuItem_0.setEnabled(pEnable);
		mEditMenuItem_4.setEnabled(pEnable);
	}
	
	/**	Dh	23.7.2020
	 * 
	 * @param pEnable
	 */
	private void setListPanelSelectionEnabled(boolean pEnable) {
		setTableSelectionEnabled(pEnable);
		if ((pRightPane.getSelectedIndex() == -1) || (pRightPane.getSelectedComponent().equals(lpAllShows))) pEnable = false;
		
		btListButton_0.setEnabled(pEnable);
		btListButton_2.setEnabled(pEnable);
		
		mEditMenuItem_1.setEnabled(pEnable);
		mEditMenuItem_5.setEnabled(pEnable);
	}
	
	/**	Dh	29.7.2020
	 * 
	 * @throws Exception
	 */
 	private void updateTable() throws Exception {
		boolean vRatingOn, vGenreOn, vHostOn, vURLOn;
		ListPanel vCur;
		
		vRatingOn = rSettingManager.isRatingEnabled();
		vGenreOn = rSettingManager.isGenreEnabled();
		vHostOn = rSettingManager.isHostEnabled();
		vURLOn = rSettingManager.isURLEnabled();
		
		addListToTable(lpAllShows.getTableModel(), rShowManager.getShowIDs(), vRatingOn, vGenreOn, vHostOn, vURLOn);
		setTableSetting(lpAllShows.getTable(), vRatingOn, vGenreOn, vHostOn, vURLOn);
		lpAllShows.updateListCount();
		
		if (!lShowLists.isEmpty()) {
			lShowLists.toFirst();
			
			while(!lShowLists.isEnd()) {
				vCur = (ListPanel) lShowLists.getCurrent();
				
				addListToTable(vCur.getTableModel(), rShowManager.getShowListShowIDs(vCur.getShowListID()), vRatingOn, vGenreOn, vHostOn, vURLOn);
				setTableSetting(vCur.getTable(), vRatingOn, vGenreOn, vHostOn, vURLOn);
				vCur.updateListCount();
				
				lShowLists.next();
			}
			
		}
	}
 	//-----
 	/**	Dh	23.7.2020
 	 * 
 	 * @throws Exception
 	 */
 	private void updateListPanel() throws Exception {
 		int vShowListID, vSelShowListID;
 		String vShowListName;
 		ListPanel vTemp, vNewSel;
 		List vCur = rShowManager.getShowListIDs();
 		lShowLists = new List();
 		
 		if (pRightPane.getSelectedIndex() != -1) vSelShowListID = ((ListPanel)pRightPane.getSelectedComponent()).getShowListID();
 		else vSelShowListID = -1;
 		
 		pRightPane.removeAll();
 		if (!vCur.isEmpty()) {
 			vCur.toFirst();
 			
 			while(!vCur.isEnd()) {
 				vShowListID = (int) vCur.getCurrent();
 				vShowListName = rShowManager.getShowListName(vShowListID);
 				vTemp = new ListPanel(vShowListID, vShowListName);
 				
 				vTemp.getTable().getSelectionModel().addListSelectionListener(new ListSelectionListener() {
 					@Override
 					public void valueChanged(ListSelectionEvent e) {
 						int vSelCol;
 						
 						if (pRightPane.getSelectedIndex() != -1) {
 							vSelCol = ((ListPanel)pRightPane.getSelectedComponent()).getTable().getSelectedRow();
 							
 							if ( vSelCol != -1) setTableSelectionEnabled(true);
 							else setTableSelectionEnabled(false);
 						} else setListPanelSelectionEnabled(false);
 					}
 				});
 				
 				lShowLists.append(vTemp);
 				pRightPane.addTab(vShowListName, null, vTemp, null);
 				
 				if (vShowListID == vSelShowListID) pRightPane.setSelectedComponent(vTemp);
 				
 				vCur.next();
 			}
 		}
 		
 		pRightPane.addTab("Alle Serien", null, lpAllShows, null);
 		
 		if (vSelShowListID == -1) pRightPane.setSelectedComponent(lpAllShows);
 	}
 	//-----
 	/**	Dh	23.7.2020
 	 * 
 	 * @throws Exception
 	 */
 	private void updatetSettingMenus() throws Exception{
 		cbSettingCheckBox_0.setSelected(rSettingManager.isRatingEnabled());
 		cbSettingCheckBox_1.setSelected(rSettingManager.isGenreEnabled());
 		cbSettingCheckBox_2.setSelected(rSettingManager.isHostEnabled());
 		cbSettingCheckBox_3.setSelected(rSettingManager.isURLEnabled());
 	}
 	
//----------------------------------------------------------------------------------------------------
	
 	/**	Dh	28.7.2020
 	 * 
 	 */
 	private void editShow() {
 		int vSelID;
 		ListPanel vCur;
 		
 		if (pRightPane.getSelectedIndex() != -1) {
 			vCur = (ListPanel)pRightPane.getSelectedComponent();
 			vSelID = vCur.getTableModel().getID(vCur.getTable().getSelectedRow());
 			
 			if (vSelID != -1) {
 				try{ rGUIManager.openModFrame(2, vSelID); }
 				catch(Exception ex) {MainManager.handleException(ex);}
 			}
 		}
 	}
 	/**	Dh	23.7.2020
 	 * 
 	 */
 	private void editList() {
 		ListPanel vCur;
 		
 		if (pRightPane.getSelectedIndex() != -1) {
 			vCur = (ListPanel)pRightPane.getSelectedComponent();
 			if (!vCur.equals(lpAllShows)) {
 				try{ rGUIManager.openModFrame(6, vCur.getShowListID()); }
 				catch(Exception ex) {MainManager.handleException(ex);}
 			}
 		}
 	}
 	
 	/**	Dh	28.7.2020
 	 * 
 	 */
 	private void removeShow() {
 		int vSelID;
 		ListPanel vCur;
 		
 		if (pRightPane.getSelectedIndex() != -1) {
 			vCur = (ListPanel)pRightPane.getSelectedComponent();
 			vSelID = vCur.getTableModel().getID(vCur.getTable().getSelectedRow());
 			
 			if (vSelID != -1) {
 				try{
 					rShowManager.removeShow(vSelID);
 					updateTable();
 				}catch(Exception ex) {MainManager.handleException(ex);}
 				
 			}
 		}
 	}
 	/**	Dh	23.7.2020
 	 * 
 	 */
 	private void removeList() {
 		ListPanel vCur;
 		
 		if (pRightPane.getSelectedIndex() != -1) {
 			vCur = (ListPanel)pRightPane.getSelectedComponent();
 			if (!vCur.equals(lpAllShows)) {
 				try {
 					rShowManager.removeShowList(vCur.getShowListID());
 					
 					updateListPanel();
 					updateTable();
 				}catch(Exception ex) {MainManager.handleException(ex);}
 			}
 		}
 	}
 	
 	/**	Dh	28.7.2020
 	 * 
 	 */
 	private void openShow() {
 		int vSelID;
 		ListPanel vCur;
 		
 		if (pRightPane.getSelectedIndex() != -1) {
 			vCur = (ListPanel)pRightPane.getSelectedComponent();
 			vSelID = vCur.getTableModel().getID(vCur.getTable().getSelectedRow());
 			
 			if (vSelID != -1) {
 				try{
 					rShowManager.openShow(vSelID);
 				}catch(Exception ex) {MainManager.handleException(ex);}
 				
 			}
 		}
 	}
 	
 	//------------------------------------------------------------------------------------------------
 	
	/**	Dh	21.7.2020
	 * 
	 */
	private void close() {
		MainManager.closeApp();
	}
	
	/**	Dh	23.7.2020
	 * 
	 * @param pFreeze
	 */
	protected void freezeFrame(boolean pFreeze) throws Exception {
		mbMenuBar.setEnabled(!pFreeze);
		
		mFileMenuItem_0.setEnabled(!pFreeze);
		mFileMenuItem_1.setEnabled(!pFreeze);
		mFileMenuItem_2.setEnabled(!pFreeze);
		mFileMenuItem_3.setEnabled(!pFreeze);
		mFileMenuItem_4.setEnabled(!pFreeze);
		
		btShowButton_1.setEnabled(!pFreeze);
		btListButton_1.setEnabled(!pFreeze);
		
		setListPanelSelectionEnabled(!pFreeze);
		
		mEditMenuItem_2.setEnabled(!pFreeze);
		mEditMenuItem_3.setEnabled(!pFreeze);
		
		btShowButton.setEnabled(!pFreeze);
		btGenreButton.setEnabled(!pFreeze);
		
		mSettingMenuItem_0.setEnabled(!pFreeze);
		mSettingMenuItem_1.setEnabled(!pFreeze);
		cbSettingCheckBox_0.setEnabled(!pFreeze);
		cbSettingCheckBox_1.setEnabled(!pFreeze);
		cbSettingCheckBox_2.setEnabled(!pFreeze);
		cbSettingCheckBox_3.setEnabled(!pFreeze);
		mSettingMenuItem_3.setEnabled(!pFreeze);
		
		btSettingButton.setEnabled(!pFreeze);
		
		if (!pFreeze) {
			updateListPanel();
			updateTable();
		}
	}

//----------------------------------------------------------------------------------------------------

	/**	Dh	23.7.2020
	 * 
	 * @return
	 */
	protected Point getFrameMiddlePoint() {
		return new Point(getX()+(getWidth()/2), getY()+(getHeight()/2));
	}
	
}
