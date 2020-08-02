/**	ShowManager v0.0	Dh	28.7.2020
 * 
 * 	pGUI
 * 	  ModFrame
 * 		BrowserEditFrame
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
import java.io.File;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.filechooser.FileFilter;
import javax.swing.text.Utilities;

import pLogic.MainManager;
import pLogic.SettingManager;
import pLogic.ShowManager;

public class BrowserEditFrame extends ModFrame {
	protected SettingManager rManager;
	
	private JPanel contentPane;
	
	private JLabel lFrameTitle, lBrowserLable_0, lBrowserLable_1;
	private JTextField tfBrowserField_0, tfBrowserField_1;
	
	private JButton btBrowserButton, btFrameButton_0, btFrameButton_1;
	
	private JFileChooser vChooser;

	/**	Dh	28.7.2020
	 * 
	 * @param pGUIManager
	 * @param pManager
	 */
	public BrowserEditFrame(GUIManager pGUIManager, SettingManager pManager, int pX, int pY) {
		super(pGUIManager, pManager, pX, pY);
		
		rManager = (SettingManager)super.rManager;
		
		try{ updated();}
		catch(Exception ex) {MainManager.handleException(ex);}
	}
	
	//------------------------------------------------------------------------------------------------
	
	/**	Dh	23.7.2020
	 * 
	 */
	protected void initFrame() {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(xPos-145, yPos-107, 290, 214);
		setResizable(false);
		setTitle("Browser \u00E4ndern");
		
		contentPane = new JPanel();
		getContentPane().add(contentPane, BorderLayout.CENTER);
		
		lFrameTitle = new JLabel("Browser \u00E4ndern");
		lFrameTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lFrameTitle.setFont(new Font("Liberation Serif", Font.BOLD, 18));
		
		lBrowserLable_0 = new JLabel("Browser:");
		lBrowserLable_0.setFont(new Font("Liberation Serif", Font.PLAIN, 12));
		
		lBrowserLable_1 = new JLabel("Pfad:");
		lBrowserLable_1.setFont(new Font("Liberation Serif", Font.PLAIN, 12));
		
		tfBrowserField_0 = new JTextField();
		tfBrowserField_0.setColumns(10);
		tfBrowserField_0.setFont(new Font("Liberation Serif", Font.PLAIN, 12));
		
		tfBrowserField_1 = new JTextField();
		tfBrowserField_1.setColumns(10);
		tfBrowserField_1.setFont(new Font("Liberation Serif", Font.PLAIN, 12));
		
		btBrowserButton = new JButton("Browser");
		btBrowserButton.setFont(new Font("Liberation Serif", Font.PLAIN, 12));
		btBrowserButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				openFileSelector();
			}
		});
		
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
						.addComponent(lFrameTitle, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 264, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lBrowserLable_0)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(tfBrowserField_0, GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lBrowserLable_1, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(tfBrowserField_1, GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btBrowserButton))
						.addComponent(btFrameButton_0, GroupLayout.DEFAULT_SIZE, 264, Short.MAX_VALUE)
						.addComponent(btFrameButton_1, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 264, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(lFrameTitle)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lBrowserLable_0)
						.addComponent(tfBrowserField_0, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lBrowserLable_1)
						.addComponent(tfBrowserField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btBrowserButton, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(btFrameButton_0, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btFrameButton_1, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(17, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
	
	/**	Dh	28.7.2020
	 * 
	 */
	protected void updated() throws Exception{
		vChooser = new JFileChooser();
		vChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		vChooser.setFileFilter(new FileFilter() {
			@Override
			public String getDescription() {
				// TODO Auto-generated method stub
				return null;
			}
			@Override
			public boolean accept(File pFile) {
				boolean vRet = false;
				int vInd;
				String vFileName, vExt;				
				
				if (pFile.isDirectory()) {
			        vRet = true;
			    }

			    if (vRet == false) {
			    	vFileName = pFile.getName();
			    	vInd = vFileName.lastIndexOf('.');
			    	
			    	if ((vInd > 0) && (vInd < (vFileName.length()-1))) {
			    		vExt = vFileName.substring(vInd+1).toLowerCase();
			    		
			    		if (vExt.equals("exe")) vRet = true;
			    	}
			    }

				return vRet;
			}
		});
		
		tfBrowserField_0.setText(rManager.getBrowserName());
		tfBrowserField_1.setText(rManager.getBrowserPath());
	}
	
//----------------------------------------------------------------------------------------------------

	/**	Dh	28.7.2020
	 * 
	 */
	private void openFileSelector() {
		int vOpenerRet;
		
		vOpenerRet = vChooser.showOpenDialog(this);
		
		if (vOpenerRet == JFileChooser.APPROVE_OPTION) {
			tfBrowserField_1.setText(vChooser.getSelectedFile().getPath());
		}
	}
	
	/**	Dh	28.7.2020
	 * 
	 */
	protected void addElement() {
		try {
			if ((!tfBrowserField_0.getText().equals("")) && isExistingFile(tfBrowserField_1.getText()) ) {
				rManager.setBrowserName(tfBrowserField_0.getText());
				rManager.setBrowserPath(tfBrowserField_1.getText());
				
				cancel();
			}
		} catch(Exception ex) {MainManager.handleException(ex);}
	}
	
	//------------------------------------------------------------------------------------------------
	
	/**	Dh	22.7.2020
	 * 
	 */
	protected void freezeFrame(boolean pFreeze) throws Exception{
		tfBrowserField_0.setEnabled(!pFreeze);
		
		btFrameButton_0.setEnabled(!pFreeze);
		btFrameButton_1.setEnabled(!pFreeze);
	}
	
//----------------------------------------------------------------------------------------------------
	
	/**	Dh	28.7.2020
	 * 
	 * @param pPath
	 * @return
	 * @throws Exception
	 */
	private boolean isExistingFile(String pPath) throws Exception{
		boolean vRet = false;
		File vFile;
		
		if (pPath != null) {
			vFile = new File(pPath);
			
			if (vFile.exists() && vFile.canExecute()) vRet = true;
		} else throw new Exception("04; BrEdFra,iEF");
		
		return vRet;
	}
}
