/**	ShowManager v0.0	Dh	23.7.2020
 * 
 * 	pGUI
 * 	  ModFrame
 * 		GenereAddFrame
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

import java.awt.EventQueue;

import javax.swing.JFrame;

import pLogic.LogicManager;
import pLogic.MainManager;
import pLogic.ShowManager;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class GenreAddFrame extends ModFrame {
	protected ShowManager rManager;
	
	private JPanel contentPane;
	
	private JLabel lFrameTitle, lGenreLable;
	private JTextField tfGenreField;
	
	private JButton btFrameButton_0, btFrameButton_1;

	/**	Dh	21.7.2020
	 * 
	 * @param pGUIManager
	 * @param pManager
	 */
	public GenreAddFrame(GUIManager pGUIManager, ShowManager pManager, int pX, int pY) {
		super(pGUIManager, pManager, pX, pY);
		
		rManager = (ShowManager)super.rManager;
	}

	//------------------------------------------------------------------------------------------------
	
	/**	Dh	23.7.2020
	 * 
	 */
	protected void initFrame() {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(xPos-145, yPos-95, 290, 190);
		setResizable(false);
		setTitle("Genre hinzuf\u00FCgen");
		
		contentPane = new JPanel();
		getContentPane().add(contentPane, BorderLayout.CENTER);
		
		lFrameTitle = new JLabel("Genre hinzuf\u00FCgen");
		lFrameTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lFrameTitle.setFont(new Font("Liberation Serif", Font.BOLD, 18));
		
		lGenreLable = new JLabel("Genre:");
		lGenreLable.setFont(new Font("Liberation Serif", Font.PLAIN, 12));
		
		tfGenreField = new JTextField();
		tfGenreField.setColumns(10);
		tfGenreField.setFont(new Font("Liberation Serif", Font.PLAIN, 12));
		
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
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(btFrameButton_0, GroupLayout.DEFAULT_SIZE, 998, Short.MAX_VALUE)
						.addComponent(lFrameTitle, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 998, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lGenreLable)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(tfGenreField, GroupLayout.DEFAULT_SIZE, 957, Short.MAX_VALUE))
						.addComponent(btFrameButton_1, GroupLayout.DEFAULT_SIZE, 266, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(lFrameTitle)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lGenreLable)
						.addComponent(tfGenreField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(btFrameButton_0, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btFrameButton_1, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
	
//----------------------------------------------------------------------------------------------------
	
	/**	Dh	21.7.2020
	 * 
	 */
	protected void addElement() {
		if (!tfGenreField.getText().equals("")) {
			try {
				rManager.addGenre(tfGenreField.getText());
				
				cancel();
			}catch(Exception ex) {MainManager.handleException(ex);}
		}
		
	}
	
//----------------------------------------------------------------------------------------------------
	
	/**	Dh	21.7.2020
	 * 
	 */
	protected void freezeFrame(boolean pFreeze) {
		btFrameButton_0.setEnabled(!pFreeze);
		btFrameButton_1.setEnabled(!pFreeze);
		
		tfGenreField.setEnabled(!pFreeze);
	}
}
