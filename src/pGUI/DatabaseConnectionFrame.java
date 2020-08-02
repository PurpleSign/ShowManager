/**	ShowManager v0.0	Dh	31.7.2020
 * 
 * 	pGUI
 * 	  ModFrame
 * 		DatabaseConnectionFrame
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
import javax.swing.JPasswordField;
import javax.swing.JOptionPane;

public class DatabaseConnectionFrame extends ModFrame {
	protected SettingManager rManager;
	
	private JOptionPane jop;
	private JPanel contentPane;
	
	private JLabel lFrameTitle, lDataLable_0, lDataLable_1, lDataLable_2;
	private JTextField tfDataField_0;
	
	private JButton btFrameButton_0, btFrameButton_1, btFrameButton_2;
	private JTextField tfDataField_1;
	private JPasswordField pfDataField;

	/**	Dh	31.7.2020
	 * 
	 * @param pGUIManager
	 * @param pManager
	 * @param pX
	 * @param pY
	 */
	public DatabaseConnectionFrame(GUIManager pGUIManager, SettingManager pManager, int pX, int pY) {
		super(pGUIManager, pManager, pX, pY);
		
		rManager = (SettingManager)super.rManager;
		update();
	}

	//------------------------------------------------------------------------------------------------
	
	/**	Dh	31.7.2020
	 * 
	 */
	protected void initFrame() {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(xPos-145, yPos-95, 290, 297);
		setResizable(false);
		setTitle("Datenbank Verbindung");
		
		contentPane = new JPanel();
		getContentPane().add(contentPane, BorderLayout.CENTER);
		
		lFrameTitle = new JLabel("Datenbank Verbindung");
		lFrameTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lFrameTitle.setFont(new Font("Liberation Serif", Font.BOLD, 18));
		
		lDataLable_0 = new JLabel("Datenbankname:");
		lDataLable_0.setFont(new Font("Liberation Serif", Font.PLAIN, 12));
		
		lDataLable_1 = new JLabel("Benutzer:");
		lDataLable_1.setFont(new Font("Liberation Serif", Font.PLAIN, 12));
		
		lDataLable_2 = new JLabel("Passwort:");
		lDataLable_2.setFont(new Font("Liberation Serif", Font.PLAIN, 12));
		
		tfDataField_0 = new JTextField();
		tfDataField_0.setColumns(10);
		tfDataField_0.setFont(new Font("Liberation Serif", Font.PLAIN, 12));
		
		tfDataField_1 = new JTextField();
		tfDataField_1.setFont(new Font("Liberation Serif", Font.PLAIN, 12));
		tfDataField_1.setColumns(10);
		
		pfDataField = new JPasswordField();
		
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
		
		btFrameButton_2 = new JButton("Teste Verbindung");
		btFrameButton_2.setFont(new Font("Liberation Serif", Font.BOLD, 12));
		btFrameButton_2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ping();
			}
		});
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lFrameTitle, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 264, Short.MAX_VALUE)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
									.addGroup(gl_contentPane.createSequentialGroup()
										.addComponent(lDataLable_2, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(pfDataField))
									.addGroup(gl_contentPane.createSequentialGroup()
										.addComponent(lDataLable_1, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(tfDataField_1))
									.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
										.addComponent(lDataLable_0)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(tfDataField_0, GroupLayout.PREFERRED_SIZE, 172, GroupLayout.PREFERRED_SIZE))))
							.addContainerGap())
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btFrameButton_2, GroupLayout.PREFERRED_SIZE, 256, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(18, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btFrameButton_0, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGap(18))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btFrameButton_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGap(18))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(lFrameTitle)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lDataLable_0)
						.addComponent(tfDataField_0, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lDataLable_1)
						.addComponent(tfDataField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lDataLable_2)
						.addComponent(pfDataField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(btFrameButton_2, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btFrameButton_0, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btFrameButton_1, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(42, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
	
//----------------------------------------------------------------------------------------------------
	
	/**	Dh	31.7.2020
	 * 
	 */
	protected void update() {
		tfDataField_0.setText(rManager.getDatabaseName());
		tfDataField_1.setText(rManager.getUserName());
	}
	
//----------------------------------------------------------------------------------------------------
	
	/**	Dh	31.7.2020
	 * 
	 */
	protected void addElement() {
		String vPassword;
		char[] vPW = pfDataField.getPassword();
		
		vPassword = "";
		for (int i=0; i < vPW.length; i++) {
			vPassword += vPW[i];
		}
		
		if ((!tfDataField_0.getText().equals("")) && (!tfDataField_1.getText().equals("")) && (!vPassword.equals(""))) {
			try {
				
				rManager.setDatabaseName(tfDataField_0.getText());
				
				rManager.setUseName(tfDataField_1.getText());
				
				rManager.setPassword(vPassword);
				
				rManager.connectToDatabase();
				
				cancel();
			}catch(Exception ex) {MainManager.handleException(ex);}
		}
		
	}
	
	/**	Dh	31.7.2020
	 * 
	 */
	protected void ping() {
		String vDatabase, vUser, vPassword;
		char[] vPW = pfDataField.getPassword();
		
		vDatabase = tfDataField_0.getText();
		vUser = tfDataField_1.getText();
		vPassword = "";
		for (int i=0; i < vPW.length; i++) {
			vPassword += vPW[i];
		}
		
		if ((!vDatabase.equals("")) && (!vUser.equals("")) && (!vPassword.equals(""))) {
			try {
				if (rManager.ping(vDatabase, vUser, vPassword) == true) JOptionPane.showMessageDialog(null, "Verbdindungsaufbau erfolgreich!", "Verbdindungsaufbau erfolgreich!", JOptionPane.INFORMATION_MESSAGE);
				else JOptionPane.showMessageDialog(null, "Verbdindung fehlgeschlagen!", "Verbdindung fehlgeschlagen!", JOptionPane.INFORMATION_MESSAGE);
			}catch(Exception ex) {MainManager.handleException(ex);}
		}
		
	}
	
//----------------------------------------------------------------------------------------------------
	
	/**	Dh	31.7.2020
	 * 
	 */
	protected void freezeFrame(boolean pFreeze) {
		btFrameButton_0.setEnabled(!pFreeze);
		btFrameButton_1.setEnabled(!pFreeze);
		
		tfDataField_0.setEnabled(!pFreeze);
		tfDataField_1.setEnabled(!pFreeze);
		pfDataField.setEnabled(!pFreeze);
	}
}
