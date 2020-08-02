/**	ShowManager v0.0	Dh	31.7.2020
 * 
 * 	pGUI
 * 	  ModFrame
 * 		EditingFrame
 * 		  HostEditingFrame
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

import pLogic.MainManager;
import pLogic.SettingManager;
import javax.swing.ListSelectionModel;

public class HostEditingFrame extends EditingFrame {
	protected SettingManager rManager;
	
	private JPanel contentPane;
	private JScrollPane spFrameScrollPane;
	
	private JLabel lFrameTitle, lHostLable_0, lHostLable_1;
	private JTextField tfHostField_0;
	
	private JList liFrameList;
	
	private JButton btFrameButton_0, btFrameButton_1, btFrameButton_2;
	private JLabel lHostLable_2;
	private JTextField tfHostField_1;

	/**	Dh	23.7.2020
	 * 
	 * @param pGUIManager
	 * @param pManager
	 */
	public HostEditingFrame(GUIManager pGUIManager, SettingManager pManager, int pX, int pY) {
		super(pGUIManager, pManager, pX, pY);
		
		rManager = (SettingManager)super.rManager;		
		
		try{ updatedLists();}
		catch(Exception ex) {MainManager.handleException(ex);}
	}

	//------------------------------------------------------------------------------------------------
	
	/**	Dh	23.7.2020
	 * 
	 */
	protected void initFrame() {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(xPos-183, yPos-164, 365, 327);
		setResizable(false);
		setTitle("Hosts bearbeiten");
		
		contentPane = new JPanel();
		getContentPane().add(contentPane, BorderLayout.CENTER);
		
		lFrameTitle = new JLabel("Hosts bearbeiten");
		lFrameTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lFrameTitle.setFont(new Font("Liberation Serif", Font.BOLD, 18));
		
		lHostLable_0 = new JLabel("Alle Hosts");
		lHostLable_0.setHorizontalAlignment(SwingConstants.CENTER);
		lHostLable_0.setFont(new Font("Liberation Serif", Font.BOLD, 12));
		
		lHostLable_1 = new JLabel("Host:");
		lHostLable_1.setFont(new Font("Liberation Serif", Font.PLAIN, 12));
		
		lHostLable_2 = new JLabel("URL:");
		lHostLable_2.setFont(new Font("Liberation Serif", Font.PLAIN, 12));
		
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
		
		tfHostField_0 = new JTextField();
		tfHostField_0.setColumns(10);
		tfHostField_0.setFont(new Font("Liberation Serif", Font.PLAIN, 12));
		tfHostField_0.setEnabled(false);
		
		tfHostField_1 = new JTextField();
		tfHostField_1.setFont(new Font("Liberation Serif", Font.PLAIN, 12));
		tfHostField_1.setEnabled(false);
		tfHostField_1.setColumns(10);
		
		btFrameButton_0 = new JButton("Neuer Host");
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
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lFrameTitle, GroupLayout.DEFAULT_SIZE, 349, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(lHostLable_0, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(spFrameScrollPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(btFrameButton_2, GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)
								.addComponent(btFrameButton_1, GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)
								.addComponent(btFrameButton_0, GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)
								.addComponent(lHostLable_1, GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)
								.addComponent(tfHostField_0, GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)
								.addComponent(lHostLable_2, GroupLayout.PREFERRED_SIZE, 164, GroupLayout.PREFERRED_SIZE)
								.addComponent(tfHostField_1, GroupLayout.PREFERRED_SIZE, 164, GroupLayout.PREFERRED_SIZE))
							.addContainerGap())))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(lFrameTitle)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lHostLable_0)
					.addGap(0)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(5)
							.addComponent(lHostLable_1)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(tfHostField_0, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lHostLable_2)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(tfHostField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
							.addComponent(btFrameButton_0, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btFrameButton_1, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btFrameButton_2, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
						.addComponent(spFrameScrollPane, GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE))
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
	}
		
//----------------------------------------------------------------------------------------------------

	/**	Dh	22.7.2020
	 * 
	 */
	protected void updatedLists() throws Exception{
		int vCur = liFrameList.getSelectedIndex();
		
		if (vCur != -1) vCur = (int)rEditingList.getObjectAt(vCur);
		addListToModel(rEditingList, rManager.getHostIDList(), 3);
		if (vCur != -1) vCur = rEditingList.getObjectIndex(vCur);
		liFrameList.setSelectedIndex(vCur);
	}
	
	/**	Dh	22.7.2020
	 * 
	 * @param pEnabel
	 */
	private void enableListElements(boolean pEnabel) {
		int vSelInd = liFrameList.getSelectedIndex();
		if (liFrameList.getSelectedIndex() != -1) {
			btFrameButton_0.setText("Anwenden");
			tfHostField_0.setText((String)rEditingList.getElementAt(vSelInd));
			try {tfHostField_1.setText(rManager.getHosterURL((int)rEditingList.getObjectAt(vSelInd)));}
			catch(Exception ex) {MainManager.handleException(ex);}
		}
		else {
			btFrameButton_0.setText("Neuer Host");
			tfHostField_0.setText("");
			tfHostField_1.setText("");
			pEnabel = false;
		}
		
		tfHostField_0.setEnabled(pEnabel);
		tfHostField_1.setEnabled(pEnabel);
		btFrameButton_1.setEnabled(pEnabel);
	}
	
//----------------------------------------------------------------------------------------------------

	/**	Dh	31.7.2020
	 * 
	 */
	protected void addElement() {
		int vID;
		int vSelInd = liFrameList.getSelectedIndex();
		
		if (vSelInd != -1) {
			if (!tfHostField_0.getText().equals("")) {
				try{
					vID = (int)rEditingList.getObjectAt(vSelInd);
					
					rManager.setHosterName(vID, tfHostField_0.getText());
					rManager.setHosterURL(vID, tfHostField_1.getText());
					rEditingList.setElementAt(new JListModelElement(tfHostField_0.getText(), vID), vSelInd);
				} catch(Exception ex) {MainManager.handleException(ex);}  
			}
		}else {
			try {rGUIManager.openModFrame(4);}
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
				rManager.removeHost((int)rEditingList.getObjectAt(vSelInd));
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
