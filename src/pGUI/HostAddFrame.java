/**	ShowManager v0.0	Dh	31.7.2020
 * 
 * 	pGUI
 * 	  ModFrame
 * 		HostAddFrame
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
import pLogic.SettingManager;
import pLogic.ShowManager;

public class HostAddFrame extends ModFrame {
	protected SettingManager rManager;
	
	private JPanel contentPane;
	
	private JLabel lFrameTitle, lHostLable_0;
	private JTextField tfHostField_0;
	
	private JButton btFrameButton_0, btFrameButton_1;
	private JLabel lHostLable_1;
	private JTextField tfHostField_1;
	
	/**	Dh	23.7.2020
	 * 
	 * @param pGUIManager
	 * @param pManager
	 */
	public HostAddFrame(GUIManager pGUIManager, SettingManager pManager, int pX, int pY) {
		super(pGUIManager, pManager, pX, pY);
		
		rManager = (SettingManager)super.rManager;
	}

	//------------------------------------------------------------------------------------------------
	
	/**	Dh	23.7.2020
	 * 
	 */
	protected void initFrame() {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(xPos-145, yPos-108, 290, 217);
		setResizable(false);
		setTitle("Host hinzuf\u00FCgen");
		
		contentPane = new JPanel();
		getContentPane().add(contentPane, BorderLayout.CENTER);
		
		lFrameTitle = new JLabel("Host hinzuf\u00FCgen");
		lFrameTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lFrameTitle.setFont(new Font("Liberation Serif", Font.BOLD, 18));
		
		lHostLable_0 = new JLabel("Host:");
		lHostLable_0.setFont(new Font("Liberation Serif", Font.PLAIN, 12));
		
		tfHostField_0 = new JTextField();
		tfHostField_0.setColumns(10);
		tfHostField_0.setFont(new Font("Liberation Serif", Font.PLAIN, 12));
		
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
		
		lHostLable_1 = new JLabel("URL:");
		lHostLable_1.setFont(new Font("Liberation Serif", Font.PLAIN, 12));
		
		tfHostField_1 = new JTextField();
		tfHostField_1.setFont(new Font("Liberation Serif", Font.PLAIN, 12));
		tfHostField_1.setColumns(10);
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lFrameTitle, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 264, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lHostLable_0)
								.addComponent(lHostLable_1, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(tfHostField_1, GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE)
								.addComponent(tfHostField_0, GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE)))
						.addComponent(btFrameButton_0, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 264, Short.MAX_VALUE)
						.addComponent(btFrameButton_1, GroupLayout.DEFAULT_SIZE, 264, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(lFrameTitle)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lHostLable_0)
						.addComponent(tfHostField_0, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lHostLable_1)
						.addComponent(tfHostField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(btFrameButton_0, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btFrameButton_1, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(35, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
	
//----------------------------------------------------------------------------------------------------

	/**	Dh	31.7.2020
	 * 
	 */
	protected void addElement() {
		if (!tfHostField_0.getText().equals("")) {
			try {
				rManager.addHost(tfHostField_0.getText(), tfHostField_1.getText());
				
				cancel();
			}catch(Exception ex) {MainManager.handleException(ex);}
		}
		
	}
	
//----------------------------------------------------------------------------------------------------

	/**	Dh	22.7.2020
	 * 
	 */
	protected void freezeFrame(boolean pFreeze) {
		btFrameButton_0.setEnabled(!pFreeze);
		btFrameButton_1.setEnabled(!pFreeze);
		
		tfHostField_0.setEnabled(!pFreeze);
		tfHostField_1.setEnabled(!pFreeze);
	}
}
