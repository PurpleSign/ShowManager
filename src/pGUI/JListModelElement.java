/**	ShowManager v0.0	Dh	21.7.2020
 * 
 * 	pGUI
 * 	  JListModelElement
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

public class JListModelElement {
	private Object object;
	private String text;
	
	/**	Dh	6.5.2020
	 * 	
	 */
	public JListModelElement() {
		object = null;
		text = null;
	}
	/**	Dh	6.5.2020
	 * 
	 * @param pText
	 * @param pObject
	 */
	public JListModelElement(String pText, Object pObject) {
		text = pText;
		object = pObject;
	}
	
//--------------------------------------------------------------------------------------------------------

	/**	Dh	6.5.2020
	 * 
	 * @return
	 */
	public Object getObject() {
		return object;
	}
	/**	Dh	6.5.2020
	 * 
	 * @return
	 */
	public String getText() {
		return text;
	}
	
	//----------------------------------------------------------------------------------------------------
	
	/**	Dh	6.5.2020
	 * 
	 * @param pObject
	 */
	public void setObject(Object pObject) {
		object = pObject;
	}
	/**	Dh	6.5.2020
	 * 
	 * @param pText
	 */
	public void setText(String pText) {
		text = pText;
	}
}
