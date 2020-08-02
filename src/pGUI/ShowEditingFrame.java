/**	ShowManager v0.0	Dh	31.7.2020
 * 
 * 	pGUI
 * 	  ModFrame
 * 		EditingFrame
 * 		  ShowEditingFrame
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
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import pDataStructures.List;
import pLogic.MainManager;
import pLogic.SettingManager;
import pLogic.ShowManager;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerNumberModel;

public class ShowEditingFrame extends EditingFrame {
	private String hostURL;
	
	protected ShowManager rManager;
	protected SettingManager rSettingManager;
	
	private JPanel contentPane, pShowPane;
	private JScrollPane spFrameScrollPane, spShowScrollPane;
	
	private JLabel lFrameTitle, lShowLable_0, lShowLable_1, lShowLable_2, lShowLable_3, lShowLable_4, lShowLable_5;
	private JTextField tfShowField_0, tfShowField_1;
	
	private JComboBox cbShowComboBox;
	private JSpinner spShowSpinner;
	
	private JList liFrameList, liShowList;
	
	private JButton btFrameButton_0, btFrameButton_1, btFrameButton_2, btShowButton;
	
	private JComboBoxModel rComboBoxModel;
	private JListModel rListModel;

	/**	Dh	23.7.2020
	 * 
	 * @param pGUIManager
	 * @param pManager
	 * @param pSettingManager
	 */
	public ShowEditingFrame(GUIManager pGUIManager, ShowManager pManager, SettingManager pSettingManager, int pX, int pY) {
		super(pGUIManager, pManager, pX, pY);
		
		rManager = (ShowManager)super.rManager;
		rSettingManager = pSettingManager;
		
		hostURL = "";
		try{ updatedLists();}
		catch(Exception ex) {MainManager.handleException(ex);}	
	}
	
	//------------------------------------------------------------------------------------------------
	
	/**	Dh	22.7.2020
	 * 
	 */
	protected void initModels() {
		super.initModels();
		
		rComboBoxModel = new JComboBoxModel();
		rListModel = new JListModel();
	}
	
	//------------------------------------------------------------------------------------------------
	
	/**	Dh	30.7.2020
	 * 
	 */
	protected void initFrame() {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(xPos-261, yPos-175, 522, 350);
		setResizable(false);
		setTitle("Serien bearbeiten");
		
		contentPane = new JPanel();
		getContentPane().add(contentPane, BorderLayout.CENTER);
		
		lFrameTitle = new JLabel("Serien bearbeiten");
		lFrameTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lFrameTitle.setFont(new Font("Liberation Serif", Font.BOLD, 18));
		
		lShowLable_0 = new JLabel("Alle Serien");
		lShowLable_0.setHorizontalAlignment(SwingConstants.CENTER);
		lShowLable_0.setFont(new Font("Liberation Serif", Font.BOLD, 12));
		
		lShowLable_1 = new JLabel("Serie:");
		lShowLable_1.setFont(new Font("Liberation Serif", Font.PLAIN, 12));
		
		lShowLable_2 = new JLabel("URL:");
		lShowLable_2.setFont(new Font("Liberation Serif", Font.PLAIN, 12));
		
		lShowLable_3 = new JLabel("Host:");
		lShowLable_3.setFont(new Font("Liberation Serif", Font.PLAIN, 12));
		
		lShowLable_4 = new JLabel("Rating:");
		lShowLable_4.setFont(new Font("Liberation Serif", Font.PLAIN, 12));
		
		lShowLable_5 = new JLabel("Genre:");
		lShowLable_5.setFont(new Font("Liberation Serif", Font.PLAIN, 12));
		
		spFrameScrollPane = new JScrollPane();
		
		liFrameList = new JList();
		liFrameList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		liFrameList.setModel(rEditingList);
		liFrameList.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				enableListElements(true);
			}
		});
		spFrameScrollPane.setViewportView(liFrameList);
		
		tfShowField_0 = new JTextField();
		tfShowField_0.setColumns(10);
		tfShowField_0.setFont(new Font("Liberation Serif", Font.PLAIN, 12));
		tfShowField_0.setEnabled(false);
		
		tfShowField_1 = new JTextField();
		tfShowField_1.setFont(new Font("Liberation Serif", Font.PLAIN, 12));
		tfShowField_1.setEnabled(false);
		tfShowField_1.setColumns(10);
		tfShowField_1.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				String vText = tfShowField_1.getText();
				
				if (!hostURL.equals("")) {
					if (!vText.startsWith(hostURL)) {
						for (int i=0; i<hostURL.length(); i++) {
							if (i < vText.length()) {
								if (hostURL.charAt(i) != vText.charAt(i)) {
									vText = vText.substring(i);
									
									i= hostURL.length();
								}
							} else {
								if (i != 0) vText = "";
							}
						}
						tfShowField_1.setText(hostURL+vText);
					}
				}
			}
			@Override
			public void keyReleased(KeyEvent e) {
				
			}
			@Override
			public void keyPressed(KeyEvent e) {
				
			}
		});
		
		cbShowComboBox = new JComboBox();
		cbShowComboBox.setFont(new Font("Liberation Serif", Font.BOLD, 12));
		cbShowComboBox.setModel(rComboBoxModel);
		cbShowComboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Object vTemp = rComboBoxModel.getSelectedObject();
				String vURL;
				
				vURL = tfShowField_1.getText();
				
				try {
					if ((!vURL.equals("")) && (!hostURL.equals(""))) vURL = vURL.replace(hostURL, "");
					
					if ((vTemp != null) && (((int)vTemp) != -1)) {
						hostURL = rSettingManager.getHosterURL(((int)vTemp));
						vURL = hostURL +vURL; 
					}
					
					tfShowField_1.setText(vURL);
				}catch(Exception ex) {MainManager.handleException(ex);}
			}
		});
		cbShowComboBox.setEnabled(false);
		
		spShowSpinner = new JSpinner();
		spShowSpinner.setModel(new SpinnerNumberModel(0, 0, 10, 1));
		spShowSpinner.setEnabled(false);
		
		btShowButton = new JButton("Neues Genre");
		btShowButton.setFont(new Font("Liberation Serif", Font.BOLD, 12));
		btShowButton.setEnabled(false);
		btShowButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {rGUIManager.openModFrame(0);}
				catch(Exception ex) {MainManager.handleException(ex);}
			}
		});
		
		spShowScrollPane = new JScrollPane();
		
		liShowList = new JList();
		liShowList.setModel(rListModel);
		liShowList.setEnabled(false);
		spShowScrollPane.setViewportView(liShowList);
		
		initSubpane();
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lFrameTitle, GroupLayout.DEFAULT_SIZE, 506, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(spFrameScrollPane, GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
								.addComponent(lShowLable_0, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(spShowScrollPane, GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
								.addComponent(lShowLable_2, GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
								.addComponent(lShowLable_1, GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
								.addComponent(tfShowField_0, GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
								.addComponent(tfShowField_1, GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(cbShowComboBox, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
										.addComponent(lShowLable_3, GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
										.addComponent(lShowLable_4, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(spShowSpinner, GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lShowLable_5, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btShowButton, GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)))
							.addGap(18)
							.addComponent(pShowPane, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE)
							.addGap(15))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(lFrameTitle)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lShowLable_0)
					.addGap(5)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addComponent(pShowPane, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lShowLable_1)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(tfShowField_0, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lShowLable_2)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(tfShowField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lShowLable_4)
								.addComponent(lShowLable_3))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(cbShowComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(spShowSpinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lShowLable_5)
								.addComponent(btShowButton))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(spShowScrollPane, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE))
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(spFrameScrollPane, GroupLayout.PREFERRED_SIZE, 256, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(38, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
	
	/**	Dh	22.7.2020
	 * 
	 */
	private void initSubpane() {
		pShowPane = new JPanel();
		
		btFrameButton_0 = new JButton("Neue Serie");
		btFrameButton_0.setFont(new Font("Liberation Serif", Font.BOLD, 12));
		btFrameButton_0.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addElement();
			}
		});
		
		btFrameButton_1 = new JButton("Entfernen");
		btFrameButton_1.setFont(new Font("Liberation Serif", Font.BOLD, 12));
		btFrameButton_1.setEnabled(false);
		btFrameButton_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				removeElement();
			}
		});
		
		btFrameButton_2 = new JButton("Abbrechen");
		btFrameButton_2.setFont(new Font("Liberation Serif", Font.BOLD, 12));
		btFrameButton_2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cancel();
			}
		});
		GroupLayout gl_pShowPane = new GroupLayout(pShowPane);
		gl_pShowPane.setHorizontalGroup(
			gl_pShowPane.createParallelGroup(Alignment.LEADING)
				.addComponent(btFrameButton_0, GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
				.addComponent(btFrameButton_2, GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
				.addComponent(btFrameButton_1, GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
		);
		gl_pShowPane.setVerticalGroup(
			gl_pShowPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_pShowPane.createSequentialGroup()
					.addGap(39)
					.addComponent(btFrameButton_0, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btFrameButton_1, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
					.addComponent(btFrameButton_2, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
					.addGap(29))
		);
		pShowPane.setLayout(gl_pShowPane);
	}
	
//----------------------------------------------------------------------------------------------------
	
	/**	Dh	28.7.2020
	 * 
	 * @param pJCBM
	 * @param pList
	 * @param pType
	 * @throws Exception
	 */
	protected void addListToComboModel(JComboBoxModel pJCBM, List pList, int pType) throws Exception{
		int vCurID;
		
		pJCBM.clearElements();
		if (!pList.isEmpty()) {
			pList.toFirst();
			pJCBM.addElement("", -1);
			
			while(!pList.isEnd()) {
				vCurID = (int) pList.getCurrent();
				
				if (pType == 0) pJCBM.addElement(((ShowManager)rManager).getGenreName(vCurID), vCurID);
				else if (pType == 1) pJCBM.addElement(((ShowManager)rManager).getShowName(vCurID), vCurID); 
				else if (pType == 2) pJCBM.addElement(((ShowManager)rManager).getShowListName(vCurID), vCurID);
				else if (pType == 3) pJCBM.addElement(((SettingManager)rSettingManager).getHosterName(vCurID), vCurID); 
				else throw new Exception("02; MoFra,aLtCM");
				
				pList.next();
			}
		}
	}

	/**	Dh	22.7.2020
	 * 
	 */
	protected void updatedLists() throws Exception{
		int vCur = liFrameList.getSelectedIndex();
		int[] vSelIDs;
		
		if (vCur != -1) vCur = (int)rEditingList.getObjectAt(vCur);
		addListToModel(rEditingList, rManager.getShowIDs(), 1);
		if (vCur != -1) vCur = rEditingList.getObjectIndex(vCur);
		liFrameList.setSelectedIndex(vCur);
		
		if (cbShowComboBox.getSelectedIndex() != -1)vCur = (int)rComboBoxModel.getSelectedObject();
		else vCur = -1;
		addListToComboModel(rComboBoxModel, rSettingManager.getHostIDList(), 3);
		if (vCur != -1) rComboBoxModel.setSelectedObject(vCur);
		
		vSelIDs = liShowList.getSelectedIndices();
		if (vSelIDs.length > 0) {
			for (int i=0; i < vSelIDs.length; i++) {
				vSelIDs[i] = (int)rListModel.getObjectAt(vSelIDs[i]);
			}
		}
		addListToModel(rListModel, rManager.getGenreIDs(), 0);
		if (vSelIDs.length > 0) {
			for (int i=0; i < vSelIDs.length; i++) {
				vSelIDs[i] = rListModel.getObjectIndex(vSelIDs[i]);
			}
			
			liShowList.setSelectedIndices(vSelIDs);
		}
	}
	
	/**	Dh	28.7.2020
	 * 
	 * @param pEnabel
	 */
	private void enableListElements(boolean pEnabel) {
		int vID;
		List vCur;
		int[] vCurInds;
		int vSelInd = liFrameList.getSelectedIndex();
		
		if (vSelInd != -1) {
			btFrameButton_0.setText("Anwenden");
			
			tfShowField_0.setText((String)rEditingList.getElementAt(vSelInd));
			try {
				vID = (int)rEditingList.getObjectAt(vSelInd);
				
				if (rManager.getShowHostID(vID) != -1) hostURL = rSettingManager.getHosterURL(rManager.getShowHostID(vID));
				
				tfShowField_1.setText(rManager.getShowURL(vID));
				rComboBoxModel.setSelectedObject(rManager.getShowHostID(vID));
				spShowSpinner.setValue(rManager.getShowRating(vID));
				
				vCur = rManager.getShowGenreIDs(vID);
				if (!vCur.isEmpty()) {
					vCurInds = new int[vCur.getContentNumber()];
					vCur.toFirst();
					
					for (int i=0; i < vCurInds.length; i++) {
						vCurInds[i] = rListModel.getObjectIndex(vCur.getCurrent());
						
						vCur.next();
					}
					
					liShowList.setSelectedIndices(vCurInds);
				}else liShowList.clearSelection();
			} catch(Exception ex) {MainManager.handleException(ex);}
		}
		else {
			btFrameButton_0.setText("Neue Serie");
			tfShowField_0.setText("");
			tfShowField_1.setText("");
			
			if (rComboBoxModel.getSize() >= 1) cbShowComboBox.setSelectedIndex(0);
			spShowSpinner.setValue(0);
			liShowList.clearSelection();
			
			pEnabel = false;
		}
		
		tfShowField_0.setEnabled(pEnabel);
		tfShowField_1.setEnabled(pEnabel);
		cbShowComboBox.setEnabled(pEnabel);
		spShowSpinner.setEnabled(pEnabel);
		btShowButton.setEnabled(pEnabel);
		liShowList.setEnabled(pEnabel);
		
		btFrameButton_1.setEnabled(pEnabel);
	}
	
//----------------------------------------------------------------------------------------------------

	/**	Dh	31.7.2020
	 * 
	 */
	protected void addElement() {
		int vID, vRating, vHostID;;
		int vSelInd = liFrameList.getSelectedIndex();
		int[] vSelIDs;
		String vName, vURL;
		List vGenreIDs;
		
		if (vSelInd != -1) {
			if (!tfShowField_0.getText().equals("")) vName = tfShowField_0.getText();
			else vName = "";
			
			if (!tfShowField_1.getText().equals("")) vURL = tfShowField_1.getText();
			else vURL = "";
			
			vRating = (int)spShowSpinner.getValue();
			
			if (cbShowComboBox.getSelectedIndex() != -1) vHostID = (int)rComboBoxModel.getSelectedObject();
			else vHostID = -1;
			
			vSelIDs = liShowList.getSelectedIndices();
			if (vSelIDs.length > 0) {
				vGenreIDs = new List();
				
				for (int i=0; i < vSelIDs.length; i++) {
					vGenreIDs.append(rListModel.getObjectAt(vSelIDs[i]));
				}
			} else vGenreIDs = new List();
			
			if (!vName.equals("")) {
				try{
					vID = (int)rEditingList.getObjectAt(vSelInd);
					
					rManager.setShowName(vID, vName);
					rManager.setShowHostID(vID, vHostID);
					rManager.setShowURL(vID, vURL);
					rManager.setShowRating(vID, vRating);
					rManager.setShowGenreIDs(vID, vGenreIDs);
					
					rEditingList.setElementAt(new JListModelElement(vName, vID), vSelInd);
				} catch(Exception ex) {MainManager.handleException(ex);}  
			}
		}else {
			try {rGUIManager.openModFrame(1);}
			catch(Exception ex) {MainManager.handleException(ex);}
		}
	}
	
	/**	Dh	22.7.2020
	 * 
	 */
	protected void removeElement() {
		int vSelInd = liFrameList.getSelectedIndex();
		
		if (vSelInd != -1) {
			try {
				rManager.removeShow((int)rEditingList.getObjectAt(vSelInd));
				rEditingList.removeElementAt(vSelInd);
			} catch(Exception ex) {MainManager.handleException(ex);}
		}
	}
	
	//------------------------------------------------------------------------------------------------
	
	/**	Dh	22.7.2020
	 * 
	 */
	protected void freezeFrame(boolean pFreeze) throws Exception{
		enableListElements(!pFreeze);
		
		liFrameList.setEnabled(!pFreeze);
		
		btFrameButton_0.setEnabled(!pFreeze);
		btFrameButton_2.setEnabled(!pFreeze);
		
		if (pFreeze == false) updatedLists();		
	}
}
