/**	ShowManager v0.0	Dh	31.7.2020
 * 
 * 	pGUI
 * 	  ModFrame
 * 		EditingFrame
 * 		  SettingEditingFrame
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
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileFilter;

import pLogic.MainManager;
import pLogic.SettingManager;
import javax.swing.JSplitPane;
import javax.swing.JCheckBox;
import java.awt.Color;

public class SettingEditingFrame extends EditingFrame {
	protected SettingManager rManager;
	
	private JPanel contentPane, pTopPane, pBottomPane, pBrowserPane, pGUIPane, pDatabasePane, pHostPane;
	private JSplitPane splitPane;
	private JScrollPane spFrameScrollPane;
	
	private JLabel lGUITitle, lblEinstellungenBearbeiten, lblDatenbankVerbindung;
	private JLabel lHostPaneTitle, lHostLable_0, lHostLable_1, lHostLable_2;
	private JLabel lFrameTitle, lBrowserLable_0, lBrowserLable_1;
	private JTextField tfHostField_0, tfHostField_1, tfBrowserField_0, tfBrowserField_1;
	
	private JList liFrameList;
	
	private JCheckBox cbGUICheckBox_0, cbGUICheckBox_1, cbGUICheckBox_2, cbGUICheckBox_3;
	
	private JButton btHostButton_0, btHostButton_1, btDataButton;
	private JButton btBrowserButton, btFrameButton_0, btFrameButton_1;
		
	private JFileChooser vChooser;
	
	/**	Dh	29.7.2020
	 * 
	 * @param pGUIManager
	 * @param pManager
	 * @param pX
	 * @param pY
	 */
	public SettingEditingFrame(GUIManager pGUIManager, SettingManager pManager, int pX, int pY) {
		super(pGUIManager, pManager, pX, pY);
		
		rManager = (SettingManager)super.rManager;		
		
		try{ updatedLists();}
		catch(Exception ex) {MainManager.handleException(ex);}
	}
	
	//------------------------------------------------------------------------------------------------
	
	/**	Dh	29.7.2020
	 * 
	 */
	protected void initFrame() {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(xPos-183, yPos-164, 711, 465);
		setResizable(false);
		setTitle("Einstellungen bearbeiten");
		
		contentPane = new JPanel();
		getContentPane().add(contentPane, BorderLayout.CENTER);
		
		splitPane = new JSplitPane();
		splitPane.setEnabled(false);
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		
		initTopPanel();
		splitPane.setDividerLocation(335);
		initBottomPanel();
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addComponent(splitPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 897, Short.MAX_VALUE)
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addComponent(splitPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 485, Short.MAX_VALUE)
		);
		contentPane.setLayout(gl_contentPane);
	}
	
	/**	Dh	29.7.2020
	 * 
	 */
	private void initTopPanel() {
		pTopPane = new JPanel();
		splitPane.setLeftComponent(pTopPane);
		
		initBrowserPanel();
		initGUIPanel();
		initDatabasePanel();
		initHostPanel();
		
		lblEinstellungenBearbeiten = new JLabel("Einstellungen bearbeiten");
		lblEinstellungenBearbeiten.setHorizontalAlignment(SwingConstants.CENTER);
		lblEinstellungenBearbeiten.setFont(new Font("Liberation Serif", Font.BOLD, 18));
		
		GroupLayout gl_pTopPane = new GroupLayout(pTopPane);
		gl_pTopPane.setHorizontalGroup(
			gl_pTopPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pTopPane.createSequentialGroup()
					.addGroup(gl_pTopPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pTopPane.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_pTopPane.createParallelGroup(Alignment.LEADING, false)
								.addComponent(pDatabasePane, 0, 0, Short.MAX_VALUE)
								.addComponent(pGUIPane, 0, 0, Short.MAX_VALUE)
								.addComponent(pBrowserPane, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addGap(18)
							.addComponent(pHostPane, GroupLayout.PREFERRED_SIZE, 365, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_pTopPane.createSequentialGroup()
							.addGap(10)
							.addComponent(lblEinstellungenBearbeiten, GroupLayout.DEFAULT_SIZE, 683, Short.MAX_VALUE)))
					.addContainerGap())
		);
		gl_pTopPane.setVerticalGroup(
			gl_pTopPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pTopPane.createSequentialGroup()
					.addComponent(lblEinstellungenBearbeiten, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_pTopPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pTopPane.createSequentialGroup()
							.addComponent(pBrowserPane, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(pGUIPane, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(pDatabasePane, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE))
						.addComponent(pHostPane, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addContainerGap())
		);
		
		
		pTopPane.setLayout(gl_pTopPane);
	}
	/**	Dh	29.7.2020
	 * 
	 */
	private void initBottomPanel() {
		pBottomPane = new JPanel();
		splitPane.setRightComponent(pBottomPane);
		
		btFrameButton_0 = new JButton("Anwenden");
		btFrameButton_0.setFont(new Font("Liberation Serif", Font.BOLD, 12));
		btFrameButton_0.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				apply();
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
		
		GroupLayout gl_pBottomPane = new GroupLayout(pBottomPane);
		gl_pBottomPane.setHorizontalGroup(
			gl_pBottomPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pBottomPane.createSequentialGroup()
					.addGap(208)
					.addGroup(gl_pBottomPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(btFrameButton_0, GroupLayout.PREFERRED_SIZE, 264, GroupLayout.PREFERRED_SIZE)
						.addComponent(btFrameButton_1, GroupLayout.PREFERRED_SIZE, 264, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(231, Short.MAX_VALUE))
		);
		gl_pBottomPane.setVerticalGroup(
			gl_pBottomPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pBottomPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(btFrameButton_0, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btFrameButton_1, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(26, Short.MAX_VALUE))
		);
		pBottomPane.setLayout(gl_pBottomPane);
	}
	
	/**	Dh	29.7.2020
	 * 
	 */
	private void initBrowserPanel() {
		pBrowserPane = new JPanel();
		pBrowserPane.setBackground(Color.LIGHT_GRAY);
		
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
		
		GroupLayout gl_pBrowserPane = new GroupLayout(pBrowserPane);
		gl_pBrowserPane.setHorizontalGroup(
			gl_pBrowserPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pBrowserPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_pBrowserPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lFrameTitle, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 264, Short.MAX_VALUE)
						.addGroup(gl_pBrowserPane.createSequentialGroup()
							.addComponent(lBrowserLable_0)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(tfBrowserField_0, GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE))
						.addGroup(gl_pBrowserPane.createSequentialGroup()
							.addComponent(lBrowserLable_1, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(tfBrowserField_1, GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btBrowserButton)))
					.addContainerGap())
		);
		gl_pBrowserPane.setVerticalGroup(
			gl_pBrowserPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pBrowserPane.createSequentialGroup()
					.addComponent(lFrameTitle)
					.addGap(18)
					.addGroup(gl_pBrowserPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lBrowserLable_0)
						.addComponent(tfBrowserField_0, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_pBrowserPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lBrowserLable_1)
						.addComponent(tfBrowserField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btBrowserButton, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(105, Short.MAX_VALUE))
		);
		pBrowserPane.setLayout(gl_pBrowserPane);
	}
	/**	Dh	29.7.2020
	 * 
	 */
	private void initGUIPanel() {
		pGUIPane = new JPanel();
		pGUIPane.setBackground(Color.LIGHT_GRAY);
		
		lGUITitle = new JLabel("Oberfl\u00E4che bearbeiten");
		lGUITitle.setHorizontalAlignment(SwingConstants.CENTER);
		lGUITitle.setFont(new Font("Liberation Serif", Font.BOLD, 18));
		
		cbGUICheckBox_0 = new JCheckBox("Bewertung an");
		cbGUICheckBox_0.setBackground(Color.LIGHT_GRAY);
		cbGUICheckBox_0.setFont(new Font("Liberation Serif", Font.PLAIN, 12));
		
		cbGUICheckBox_1 = new JCheckBox("Genre an");
		cbGUICheckBox_1.setBackground(Color.LIGHT_GRAY);
		cbGUICheckBox_1.setFont(new Font("Liberation Serif", Font.PLAIN, 12));
		
		cbGUICheckBox_2 = new JCheckBox("Host an");
		cbGUICheckBox_2.setBackground(Color.LIGHT_GRAY);
		cbGUICheckBox_2.setFont(new Font("Liberation Serif", Font.PLAIN, 12));
		
		cbGUICheckBox_3 = new JCheckBox("URL an");
		cbGUICheckBox_3.setBackground(Color.LIGHT_GRAY);
		cbGUICheckBox_3.setFont(new Font("Liberation Serif", Font.PLAIN, 12));
		
		GroupLayout gl_pGUIPane = new GroupLayout(pGUIPane);
		gl_pGUIPane.setHorizontalGroup(
			gl_pGUIPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pGUIPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_pGUIPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lGUITitle, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 264, GroupLayout.PREFERRED_SIZE)
						.addGroup(Alignment.TRAILING, gl_pGUIPane.createSequentialGroup()
							.addGroup(gl_pGUIPane.createParallelGroup(Alignment.LEADING)
								.addComponent(cbGUICheckBox_1, GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
								.addComponent(cbGUICheckBox_0, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_pGUIPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(cbGUICheckBox_3, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE)
								.addComponent(cbGUICheckBox_2, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE))))
					.addGap(12))
		);
		gl_pGUIPane.setVerticalGroup(
			gl_pGUIPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pGUIPane.createSequentialGroup()
					.addComponent(lGUITitle, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_pGUIPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(cbGUICheckBox_2)
						.addComponent(cbGUICheckBox_0))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_pGUIPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(cbGUICheckBox_1)
						.addComponent(cbGUICheckBox_3))
					.addContainerGap(14, Short.MAX_VALUE))
		);
		pGUIPane.setLayout(gl_pGUIPane);
	}
	/**	Dh	31.7.2020
	 * 
	 */
	private void initDatabasePanel() {
		pDatabasePane = new JPanel();
		pDatabasePane.setBackground(Color.LIGHT_GRAY);
		
		lblDatenbankVerbindung = new JLabel("Datenbank Verbindung");
		lblDatenbankVerbindung.setHorizontalAlignment(SwingConstants.CENTER);
		lblDatenbankVerbindung.setFont(new Font("Liberation Serif", Font.BOLD, 18));
		
		btDataButton = new JButton("Fenster \u00F6ffnen");
		btDataButton.setFont(new Font("Liberation Serif", Font.BOLD, 12));
		btDataButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try{rGUIManager.openModFrame(11);}
				catch(Exception ex) {MainManager.handleException(ex);}
			}
		});
		
		GroupLayout gl_pDatabasePane = new GroupLayout(pDatabasePane);
		gl_pDatabasePane.setHorizontalGroup(
			gl_pDatabasePane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pDatabasePane.createSequentialGroup()
					.addContainerGap(8, Short.MAX_VALUE)
					.addComponent(lblDatenbankVerbindung, GroupLayout.PREFERRED_SIZE, 264, GroupLayout.PREFERRED_SIZE)
					.addGap(12))
				.addGroup(gl_pDatabasePane.createSequentialGroup()
					.addGap(52)
					.addComponent(btDataButton, GroupLayout.PREFERRED_SIZE, 181, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(53, Short.MAX_VALUE))
		);
		gl_pDatabasePane.setVerticalGroup(
			gl_pDatabasePane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pDatabasePane.createSequentialGroup()
					.addComponent(lblDatenbankVerbindung, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btDataButton, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(19, Short.MAX_VALUE))
		);
		pDatabasePane.setLayout(gl_pDatabasePane);
	}
	//-----
	/**	Dh	29.7.2020
	 * 
	 */
  	private void initHostPanel() {
 		pHostPane = new JPanel();
 		pHostPane.setBackground(Color.LIGHT_GRAY);
		
		lHostPaneTitle = new JLabel("Hosts bearbeiten");
		lHostPaneTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lHostPaneTitle.setFont(new Font("Liberation Serif", Font.BOLD, 18));
		
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
		
		btHostButton_0 = new JButton("Neuer Host");
		btHostButton_0.setFont(new Font("Liberation Serif", Font.BOLD, 12));
		btHostButton_0.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addElement();
			}
		});
		
		btHostButton_1 = new JButton("Entfernen");
		btHostButton_1.setFont(new Font("Liberation Serif", Font.BOLD, 12));
		btHostButton_1.setEnabled(false);
		btHostButton_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				removeElement();
			}
		});
		
		GroupLayout gl_pHostPane = new GroupLayout(pHostPane);
		gl_pHostPane.setHorizontalGroup(
			gl_pHostPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pHostPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_pHostPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lHostLable_0)
						.addComponent(spFrameScrollPane, GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_pHostPane.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(btHostButton_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btHostButton_0, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(tfHostField_1)
						.addGroup(gl_pHostPane.createParallelGroup(Alignment.LEADING)
							.addComponent(lHostLable_2, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE)
							.addGroup(Alignment.TRAILING, gl_pHostPane.createParallelGroup(Alignment.LEADING, false)
								.addComponent(tfHostField_0, Alignment.TRAILING)
								.addComponent(lHostLable_1, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE))))
					.addContainerGap())
				.addComponent(lHostPaneTitle, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 365, Short.MAX_VALUE)
		);
		gl_pHostPane.setVerticalGroup(
			gl_pHostPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pHostPane.createSequentialGroup()
					.addComponent(lHostPaneTitle)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lHostLable_0)
					.addGroup(gl_pHostPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pHostPane.createSequentialGroup()
							.addGap(5)
							.addComponent(lHostLable_1)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(tfHostField_0, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lHostLable_2)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(tfHostField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
							.addComponent(btHostButton_0, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btHostButton_1, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_pHostPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(spFrameScrollPane, GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE)))
					.addContainerGap())
		);
		pHostPane.setLayout(gl_pHostPane);
	}
	
//----------------------------------------------------------------------------------------------------

  	/**	Dh	29.7.2020
  	 * 
  	 */
	protected void updatedLists() throws Exception{
		int vCur = liFrameList.getSelectedIndex();
		
		if (vCur != -1) vCur = (int)rEditingList.getObjectAt(vCur);
		addListToModel(rEditingList, rManager.getHostIDList(), 3);
		if (vCur != -1) vCur = rEditingList.getObjectIndex(vCur);
		liFrameList.setSelectedIndex(vCur);
		
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
		
		cbGUICheckBox_0.setSelected(rManager.isRatingEnabled());
		cbGUICheckBox_1.setSelected(rManager.isGenreEnabled());
		cbGUICheckBox_2.setSelected(rManager.isHostEnabled());
		cbGUICheckBox_3.setSelected(rManager.isURLEnabled());
	}
	/**	Dh	29.7.2020
	 * 
	 * @param pEnabel
	 */
	private void enableListElements(boolean pEnabel) {
		int vSelInd = liFrameList.getSelectedIndex();
		if (liFrameList.getSelectedIndex() != -1) {
			btHostButton_0.setText("Anwenden");
			tfHostField_0.setText((String)rEditingList.getElementAt(vSelInd));
			try {tfHostField_1.setText(rManager.getHosterURL((int)rEditingList.getObjectAt(vSelInd)));}
			catch(Exception ex) {MainManager.handleException(ex);}
		}
		else {
			btHostButton_0.setText("Neuer Host");
			tfHostField_0.setText("");
			tfHostField_1.setText("");
			pEnabel = false;
		}
		
		tfHostField_0.setEnabled(pEnabel);
		tfHostField_1.setEnabled(pEnabel);
		btHostButton_1.setEnabled(pEnabel);
	}
	
//----------------------------------------------------------------------------------------------------
	
	/**	Dh	29.7.2020
	 * 
	 */
	private void openFileSelector() {
		int vOpenerRet;
		
		vOpenerRet = vChooser.showOpenDialog(this);
		
		if (vOpenerRet == JFileChooser.APPROVE_OPTION) {
			tfBrowserField_1.setText(vChooser.getSelectedFile().getPath());
		}
	}
	
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
	/**	Dh	29.7.2020
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
	
	/**	Dh	29.7.2020
	 * 
	 */
	private void apply() {
		try {
			if ((!tfBrowserField_0.getText().equals("")) && isExistingFile(tfBrowserField_1.getText()) ) {
				rManager.setBrowserName(tfBrowserField_0.getText());
				rManager.setBrowserPath(tfBrowserField_1.getText());
				
				rManager.setRatingEnabled(cbGUICheckBox_0.isSelected());
				rManager.setGenreEnabled(cbGUICheckBox_1.isSelected());
				rManager.setHostEnabled(cbGUICheckBox_2.isSelected());
				rManager.setURLEnabled(cbGUICheckBox_3.isSelected());
				
				cancel();
			}
		} catch(Exception ex) {MainManager.handleException(ex);}
	}
	
	//------------------------------------------------------------------------------------------------
	
	/**	Dh	29.7.2020
	 * 
	 */
	protected void freezeFrame(boolean pFreeze) throws Exception{
		enableListElements(!pFreeze);
		
		liFrameList.setEnabled(!pFreeze);
		
		cbGUICheckBox_0.setEnabled(!pFreeze);
		cbGUICheckBox_1.setEnabled(!pFreeze);
		cbGUICheckBox_2.setEnabled(!pFreeze);
		cbGUICheckBox_3.setEnabled(!pFreeze);
		
		tfBrowserField_0.setEnabled(!pFreeze);
		tfBrowserField_1.setEnabled(!pFreeze);
		
		tfHostField_0.setEnabled(!pFreeze);
		tfHostField_1.setEnabled(!pFreeze);
		
		btHostButton_0.setEnabled(!pFreeze);
		btHostButton_1.setEnabled(!pFreeze);
		
		btBrowserButton.setEnabled(!pFreeze);
		
		btFrameButton_0.setEnabled(!pFreeze);
		btFrameButton_1.setEnabled(!pFreeze);
		
		if (pFreeze == false) updatedLists();
	}
	
//----------------------------------------------------------------------------------------------------
	
	/**	Dh	29.7.2020
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
