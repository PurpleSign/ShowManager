/**	ShowManager v0.0	Dh	27.7.2020
 * 
 * 	pGUI
 * 	  ModFrame
 * 		ShowAddFrame
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
import java.awt.color.CMMException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;

import pDataStructures.List;

import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import pLogic.MainManager;
import pLogic.SettingManager;
import pLogic.ShowManager;
import javax.swing.JSpinner;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JList;

public class ShowAddFrame extends ModFrame {
	private int showID;
	private String hostURL;
	
	protected ShowManager rManager;
	protected SettingManager rSettingManager;
	
	private JPanel contentPane;
	private JScrollPane spShowScrollPane;
	
	private JLabel lFrameTitle, lShowLable_0, lShowLable_1, lShowLable_2, lShowLable_3, lShowLable_4;
	private JTextField tfShowField_0, tfShowField_1;
	
	private JComboBox cbShowComboBox;
	private JSpinner sShowSpinner;
	private JList liShowList;
	
	private JComboBoxModel rComboBoxModel;
	private JListModel rListModel;
	
	private JButton btShowButton, btFrameButton_0, btFrameButton_1;

	/**	Dh	28.7.2020
	 * 
	 * @param pGUIManager
	 * @param pManager
	 * @param pSettingManager
	 */
	public ShowAddFrame(GUIManager pGUIManager, ShowManager pManager, SettingManager pSettingManager, int pShowID, int pX, int pY) {
		super(pGUIManager, pManager, pX, pY);
		int[] vCurInds;
		List vCur;
		
		rManager = (ShowManager)super.rManager;
		rSettingManager = pSettingManager;
		if (pShowID >= -1) showID = pShowID;
		else MainManager.handleException(new Exception("02 ShAdFra"));
		
		try{ updatedLists();}
		catch(Exception ex) {MainManager.handleException(ex);}
		
		if (pShowID != -1) {
			lFrameTitle.setText("Serie bearbeiten");
			btFrameButton_0.setText("Bearbeiten");
			try {
				if (rManager.getShowHostID(showID) != -1) hostURL = rSettingManager.getHosterURL(rManager.getShowHostID(showID));
				else hostURL = "";
				
				tfShowField_0.setText(rManager.getShowName(showID));
				tfShowField_1.setText(rManager.getShowURL(showID));
				rComboBoxModel.setSelectedObject(rManager.getShowHostID(showID));
				sShowSpinner.setValue(rManager.getShowRating(showID));
				
				vCur = rManager.getShowGenreIDs(showID);
				if (!vCur.isEmpty()) {
					vCurInds = new int[vCur.getContentNumber()];
					vCur.toFirst();
					
					for (int i=0; i < vCurInds.length; i++) {
						vCurInds[i] = rListModel.getObjectIndex(vCur.getCurrent());
						
						vCur.next();
					}
					
					liShowList.setSelectedIndices(vCurInds);
				}
			} catch(Exception ex) {MainManager.handleException(ex);}
		} else hostURL = "";
	}

	//------------------------------------------------------------------------------------------------
	
	/**	Dh	21.7.2020
	 * 
	 */
	protected void initModels() {
		rComboBoxModel = new JComboBoxModel();
		rListModel = new JListModel();
	}
	
	//------------------------------------------------------------------------------------------------
	
	/**	Dh	23.7.2020
	 * 
	 */
	protected void initFrame() {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(xPos-150, yPos-183, 300, 365);
		setResizable(false);
		setTitle("Serie hinzuf\u00FCgen");
		
		contentPane = new JPanel();
		getContentPane().add(contentPane, BorderLayout.CENTER);
		
		lFrameTitle = new JLabel("Serie hinzuf\u00FCgen");
		lFrameTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lFrameTitle.setFont(new Font("Liberation Serif", Font.BOLD, 18));
		
		lShowLable_0 = new JLabel("Serie:");
		lShowLable_0.setFont(new Font("Liberation Serif", Font.PLAIN, 12));
		
		lShowLable_1 = new JLabel("URL:");
		lShowLable_1.setFont(new Font("Liberation Serif", Font.PLAIN, 12));
		
		lShowLable_2 = new JLabel("Host:");
		lShowLable_2.setFont(new Font("Liberation Serif", Font.PLAIN, 12));
		
		lShowLable_3 = new JLabel("Rating:");
		lShowLable_3.setFont(new Font("Liberation Serif", Font.PLAIN, 12));
		
		lShowLable_4 = new JLabel("Genre:");
		lShowLable_4.setFont(new Font("Liberation Serif", Font.PLAIN, 12));
		
		tfShowField_0 = new JTextField();
		tfShowField_0.setColumns(10);
		tfShowField_0.setFont(new Font("Liberation Serif", Font.PLAIN, 12));
		
		tfShowField_1 = new JTextField();
		tfShowField_1.setColumns(10);
		tfShowField_1.setFont(new Font("Liberation Serif", Font.PLAIN, 12));
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
		
		sShowSpinner = new JSpinner();
		sShowSpinner.setModel(new SpinnerNumberModel(0, 0, 10, 1));
		
		btShowButton = new JButton("Neues Genre");
		btShowButton.setFont(new Font("Liberation Serif", Font.BOLD, 12));
		btShowButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try{rGUIManager.openModFrame(0);}
				catch(Exception ex) {MainManager.handleException(ex);}
			}
		});
		
		spShowScrollPane = new JScrollPane();
		
		liShowList = new JList();
		liShowList.setModel(rListModel);
		spShowScrollPane.setViewportView(liShowList);
		
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
						.addComponent(btFrameButton_1, GroupLayout.DEFAULT_SIZE, 264, Short.MAX_VALUE)
						.addComponent(spShowScrollPane, GroupLayout.DEFAULT_SIZE, 264, Short.MAX_VALUE)
						.addComponent(lFrameTitle, GroupLayout.DEFAULT_SIZE, 264, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lShowLable_2, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(cbShowComboBox, 0, 107, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(lShowLable_3, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(sShowSpinner, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lShowLable_4, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 119, Short.MAX_VALUE)
							.addComponent(btShowButton))
						.addComponent(btFrameButton_0, GroupLayout.DEFAULT_SIZE, 264, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(lShowLable_0, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(tfShowField_0))
							.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
								.addComponent(lShowLable_1, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(tfShowField_1, GroupLayout.PREFERRED_SIZE, 216, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(lFrameTitle)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lShowLable_0)
						.addComponent(tfShowField_0, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lShowLable_1)
						.addComponent(tfShowField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lShowLable_2)
						.addComponent(sShowSpinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lShowLable_3)
						.addComponent(cbShowComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lShowLable_4)
						.addComponent(btShowButton))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(spShowScrollPane, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btFrameButton_0, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btFrameButton_1, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(13, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
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
	 * @throws Exception
	 */
	protected void updatedLists() throws Exception{
		int vCurID, vSelID;
		int[] vSelIDs;
		
		if (cbShowComboBox.getSelectedIndex() != -1)vSelID = (int)rComboBoxModel.getSelectedObject();
		else vSelID = -1;
		addListToComboModel(rComboBoxModel, rSettingManager.getHostIDList(), 3);
		if (vSelID != -1) rComboBoxModel.setSelectedObject(vSelID);
		
		
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
	
//----------------------------------------------------------------------------------------------------
	
	/**	Dh	28.7.2020
	 * 
	 */
	protected void addElement() {
		int vRating, vHostID;
		int[] vSelIDs;
		String vName, vURL;
		List vGenreIDs;
		
		if (!tfShowField_0.getText().equals("")) vName = tfShowField_0.getText();
		else vName = null;
		
		vURL = tfShowField_1.getText();
		
		vRating = (int)sShowSpinner.getValue();
		
		if (cbShowComboBox.getSelectedIndex() != -1) vHostID = (int)rComboBoxModel.getSelectedObject();
		else vHostID = -1;
		
		vSelIDs = liShowList.getSelectedIndices();
		if (vSelIDs.length > 0) {
			vGenreIDs = new List();
			
			for (int i=0; i < vSelIDs.length; i++) {
				vGenreIDs.append(rListModel.getObjectAt(vSelIDs[i]));
			}
		} else vGenreIDs = new List();
		
		if ((vName != null) && (vURL != null)) {
			try {
				if (showID == -1) {
					if (!vGenreIDs.isEmpty()) rManager.addShow(vName, vHostID, vURL, vRating, vGenreIDs);
					else rManager.addShow(vName, vHostID, vURL, vRating);
				}else {
					rManager.setShowName(showID, vName);
					rManager.setShowHostID(showID, vHostID);
					rManager.setShowURL(showID, vURL);
					rManager.setShowRating(showID, vRating);
					rManager.setShowGenreIDs(showID, vGenreIDs);
				}
				
				cancel();
			} catch(Exception ex) {MainManager.handleException(ex);}
		}
	}

	//------------------------------------------------------------------------------------------------
	
	/**	Dh	22.7.2020
	 * 
	 */
	protected void freezeFrame(boolean pFreeze) throws Exception {
		btFrameButton_0.setEnabled(!pFreeze);
		btFrameButton_1.setEnabled(!pFreeze);
		btShowButton.setEnabled(!pFreeze);
		
		tfShowField_0.setEnabled(!pFreeze);
		tfShowField_1.setEnabled(!pFreeze);
		cbShowComboBox.setEnabled(!pFreeze);
		spShowScrollPane.setEnabled(!pFreeze);
		liShowList.setEnabled(!pFreeze);
		
		if (pFreeze == false) {
			try{updatedLists();}
			catch(Exception ex) {throw ex;}
		}
	}
}
