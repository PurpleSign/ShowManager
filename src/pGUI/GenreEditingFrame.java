/**	ShowManager v0.0	Dh	28.7.2020
 * 
 * 	pGUI
 * 	  ModFrame
 * 		EditingFrame
 * 		  GenreEditingFrame
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
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import pLogic.MainManager;
import pLogic.ShowManager;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.ListSelectionModel;

public class GenreEditingFrame extends EditingFrame {
	protected ShowManager rManager;
	
	private JPanel contentPane;
	private JScrollPane spFrameScrollPane;
	
	private JLabel lFrameTitle, lGenreLable_0, lGenreLable_1;
	private JTextField tfGenreField;
	
	private JList liFrameList;
	
	private JButton btFrameButton_0, btFrameButton_1, btFrameButton_2;

	/**	Dh	23.7.2020
	 * 
	 * @param pGUIManager
	 * @param pManager
	 */
	public GenreEditingFrame(GUIManager pGUIManager, ShowManager pManager, int pX, int pY) {
		super(pGUIManager, pManager, pX, pY);
		
		rManager = (ShowManager)super.rManager;		
		
		try{ updatedLists();}
		catch(Exception ex) {MainManager.handleException(ex);}
	}

	//------------------------------------------------------------------------------------------------
	
	/**	Dh	23.7.2020
	 * 
	 */
	protected void initFrame() {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(xPos-182, yPos-164, 365, 327);
		setResizable(false);
		setTitle("Genres bearbeiten");
		
		contentPane = new JPanel();
		getContentPane().add(contentPane, BorderLayout.CENTER);
		
		lFrameTitle = new JLabel("Genres bearbeiten");
		lFrameTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lFrameTitle.setFont(new Font("Liberation Serif", Font.BOLD, 18));
		
		lGenreLable_0 = new JLabel("Alle Genres");
		lGenreLable_0.setHorizontalAlignment(SwingConstants.CENTER);
		lGenreLable_0.setFont(new Font("Liberation Serif", Font.BOLD, 12));
		
		lGenreLable_1 = new JLabel("Genre:");
		lGenreLable_1.setFont(new Font("Liberation Serif", Font.PLAIN, 12));
		
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
		
		tfGenreField = new JTextField();
		tfGenreField.setColumns(10);
		tfGenreField.setFont(new Font("Liberation Serif", Font.PLAIN, 12));
		tfGenreField.setEnabled(false);
		
		btFrameButton_0 = new JButton("Neues Genre");
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
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lFrameTitle, GroupLayout.DEFAULT_SIZE, 349, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(lGenreLable_0, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(spFrameScrollPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(btFrameButton_2, GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)
								.addComponent(btFrameButton_1, GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)
								.addComponent(btFrameButton_0, GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)
								.addComponent(tfGenreField, GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)
								.addComponent(lGenreLable_1, GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE))
							.addContainerGap())))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(lFrameTitle)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lGenreLable_0)
					.addGap(0)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(29)
							.addComponent(lGenreLable_1)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(tfGenreField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 56, Short.MAX_VALUE)
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
		addListToModel(rEditingList, rManager.getGenreIDs(), 0);
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
			tfGenreField.setText((String)rEditingList.getElementAt(vSelInd));
		}
		else {
			btFrameButton_0.setText("Neues Genre");
			tfGenreField.setText("");
			pEnabel = false;
		}
		
		tfGenreField.setEnabled(pEnabel);
		btFrameButton_1.setEnabled(pEnabel);
	}
	
//----------------------------------------------------------------------------------------------------
	
	/**	Dh	22.7.2020
	 * 
	 */
	protected void addElement() {
		int vSelInd = liFrameList.getSelectedIndex();
		
		if (vSelInd != -1) {
			if (!tfGenreField.getText().equals("")) {
				try{
					rManager.setGenreName((int)rEditingList.getObjectAt(vSelInd), tfGenreField.getText());
					rEditingList.setElementAt(new JListModelElement(tfGenreField.getText(), rEditingList.getObjectAt(vSelInd)), vSelInd);
				} catch(Exception ex) {MainManager.handleException(ex);}  
			}
		}else {
			try {rGUIManager.openModFrame(0);}
			catch(Exception ex) {MainManager.handleException(ex);}
		}
	}
	/**	Dh	28.7.2020
	 * 
	 */
	protected void removeElement() {
		int vSelInd = liFrameList.getSelectedIndex();
		
		if (vSelInd != -1) {
			try {
				rManager.removeGenre((int)rEditingList.getObjectAt(vSelInd));
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
