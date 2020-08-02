/**	ShowManager v0.0	Dh	27.7.2020
 * 
 * 	pLogic
 * 	  ShowManagerElemet
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
package pLogic;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@Inheritance( strategy = InheritanceType.TABLE_PER_CLASS )
public abstract class ShowManagerElement {
	@Id
	protected int id;
	protected String name;
	
	/**	Dh	18.7.2020
	 * 
	 */
	public ShowManagerElement() {
		id = -1;
		name = "";
	}
	/**	Dh	18.7.2020
	 * 
	 * @param pID
	 * @param pName
	 */
	public ShowManagerElement(int pID, String pName) {
		try{setId(pID);}
		catch(Exception ex) {
			id = -1;
			MainManager.handleException(ex);
		}
		try{setName(pName);}
		catch(Exception ex) {
			name = "";
			MainManager.handleException(ex);
		}
	}

//--------------------------------------------------------------------------------------------------------
	
	/**	Dh	18.7.2020
	 * 
	 * @return
	 */
	@XmlAttribute
	public int getId() {
		return id;
	}
	/**	Dh	18.7.2020
	 * 
	 * @return
	 */
	@XmlAttribute
	public String getName() {
		return name;
	}

	//----------------------------------------------------------------------------------------------------
	
	/**	Dh	18.7.2020
	 * 
	 * @param pID
	 * @throws Exception
	 */
	public void setId(int pID) throws Exception {
		if (pID >= 0) id = pID;
		else throw new Exception("02; SME,sId");
	}
	/**	Dh	18.7.2020
	 * 
	 * @param pName
	 * @throws Exception
	 */
	public void setName(String pName) throws Exception {
		if (pName != null) {
			if (!pName.equals("")) name = pName;
			else throw new Exception("02; Gen,sN");
		} else throw new Exception("04; SME,sN");
	}

}
