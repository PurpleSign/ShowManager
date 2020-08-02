/**	ShowManager v0.0	Dh	28.7.2020
 * 
 * 	pGUI
 * 	  ExceptionFrame
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
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingConstants;

import pDataStructures.List;
import pLogic.MainManager;

import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextArea;
import javax.swing.JButton;

public class ExceptionFrame extends JFrame {
	int xPos, yPos;
	GUIManager rGUIM;
	private JScrollPane spScrollPane;
	
	private JLabel lTitle_0, lTitle_1;
	private JTextArea taTextArea;
	
	private JButton btButton;
	
	/**	Dh	23.7.2002
	 * 
	 * @param pExceptionList
	 */
	public ExceptionFrame(List pExceptionList, GUIManager pGUIManager, int pX, int pY) {
		rGUIM = pGUIManager;
		
		xPos = pX;
		yPos = pY;
		
		initFrame();
		setSpecification(pExceptionList);
	}
	
	//------------------------------------------------------------------------------------------------
	
	/**	Dh	23.7.2020
	 * 
	 */
	private void initFrame() {
		setTitle("Fehlermeldung!");
		setBounds(xPos-225, yPos-137, 450, 273);
		
		lTitle_0 = new JLabel("Fehlermeldung!");
		lTitle_0.setHorizontalAlignment(SwingConstants.CENTER);
		lTitle_0.setFont(new Font("Liberation Serif", Font.BOLD, 18));
		
		lTitle_1 = new JLabel("Fehlernachrichten:");
		lTitle_1.setFont(new Font("Liberation Serif", Font.BOLD, 14));
		
		spScrollPane = new JScrollPane();
		
		taTextArea = new JTextArea();
		taTextArea.setFont(new Font("Liberation Serif", Font.PLAIN, 12));
		taTextArea.setEnabled(false);
		spScrollPane.setViewportView(taTextArea);
		
		btButton = new JButton("OK");
		btButton.setFont(new Font("Liberation Serif", Font.BOLD, 12));
		btButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cancle();
			}
		});
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btButton, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE)
						.addComponent(spScrollPane, GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE)
						.addComponent(lTitle_1, GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE)
						.addComponent(lTitle_0, GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(lTitle_0)
					.addGap(18)
					.addComponent(lTitle_1)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(spScrollPane, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btButton, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(54, Short.MAX_VALUE))
		);
		getContentPane().setLayout(groupLayout);
	}
	
//----------------------------------------------------------------------------------------------------

	/**	Dh	28.7.2020
	 * 
	 * @param pExceptions
	 */
	private void setSpecification(List pExceptions) {
		Object vCur;
		
		if (pExceptions != null) {
			if (!pExceptions.isEmpty()) {
				pExceptions.toFirst();
				
				while(!pExceptions.isEnd()) {
					vCur = pExceptions.getCurrent();
					
					if (!pExceptions.isFirst()) taTextArea.append("\n");
					
					if (vCur != null) {
						if (vCur instanceof Exception) {
							taTextArea.append(((Exception)vCur).getMessage());
						} else taTextArea.append( "06; ExFra,sS/n");
					} else taTextArea.append( "04; ExFra,sS/n");
					
					pExceptions.next();
				}
			}
		}
	}
	
//----------------------------------------------------------------------------------------------------
	
	/**	Dh	21.7.2020
	 * 
	 */
	private void cancle() {
		rGUIM.closeExceptionFrame();
		
		setVisible(false);
		dispose();
	}
}
