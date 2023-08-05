/**	ShowManager v0.1	Dh	29.7.2023
 * 
 * 	logic.showentities
 * 	BasicShowentity
 * 	  ShowList
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

package org.showmanager.logic.showentities;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.persistence.Entity;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@JsonPropertyOrder({
	"id", "name"
})
@Entity
@Table
public class ShowList extends BasicShowentity {
	@Transient
	protected ArrayList<Integer> showList;
	
	/**	Dh	26.07.2023
	 * 
	 */
 	public ShowList() {
		super();
		
		showList = new ArrayList<Integer>();
	}
 	/**	Dh	26.07.2023
 	 * 
 	 * @param pID
 	 * @param pName
 	 */
	public ShowList(int pID, String pName) {
		this(pID, pName, new ArrayList<Integer>());
	}
	/**	Dh	26.07.2023
	 * 
	 * @param pID
	 * @param pName
	 * @param pShowList
	 */
	public ShowList(int pID, String pName, ArrayList<Integer> pShowList) {
		super(pID, pName);
		
		showList = pShowList;
	}

//----------------------------------------------------------------------------------------------------

	/**	Dh	26.07.2032
	 * 
	 * @return
	 */
	public ArrayList<Integer> getShowList(){
		return showList;
	}
	
	/**	Dh	26.07.2023
	 * 
	 * @param pShowList
	 * @throws Exception
	 */
	public void setShowList(ArrayList<Integer> pShowList) throws Exception{
		if (pShowList != null) {
			for (Integer vShowID : pShowList) {
				if (vShowID != null) {
					if (vShowID <= -1) throw new Exception("02; sSL,ShL");
				}else throw new Exception("04b, sSL,ShL");
			}
			
			showList = (ArrayList<Integer>)pShowList.clone();
		}else throw new Exception("04; sSL,ShL");
	}
	
//----------------------------------------------------------------------------------------------------
	
	/**	Dh	26.07.2023
	 * 
	 * @param pID
	 * @return
	 * @throws Exception
	 */
	public boolean haveShow(int pID) throws Exception {
		boolean vRet = false;
		
		if (pID >= 0) {
			if (!showList.isEmpty()) vRet = showList.contains(Integer.valueOf(pID));
		}else throw new Exception("02; hSh,ShL");
		
		return vRet;
	}
	
	//-----
	
	/**	Dh	26.07.2023
	 * 
	 * @param pShowID
	 * @throws Exception
	 */
	public void addShow(int pShowID) throws Exception{
		if (pShowID >= 0) showList.add(Integer.valueOf(pShowID));
		else throw new Exception("02; adS,ShL");
	}
	
	/**	Dh	26.07.2023
	 * 
	 * @param pID
	 * @throws Exception
	 */
	public void removeShow(int pID) throws Exception{
		if (pID >= 0) {
			if (!showList.isEmpty()) {
				showList.remove(Integer.valueOf(pID));
			}
		} else throw new Exception("02; reS,ShL");
	}
	
}
