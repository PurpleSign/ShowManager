/**	ShowManager v0.0	Dh	27.7.2020
 * 
 * 	pLogic
 * 	  ShowManagerElemet
 * 		ShowList
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
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import pDataStructures.List;

@XmlRootElement
@Entity
@Table
public class ShowList extends ShowManagerElement{
	@Transient
	private List showList;
	
	/**	Dh	18.7.2020
	 * 
	 */
	public ShowList() {
		super();
		
		showList = new List();
	}
	/**	Dh	18.7.2020
	 * 
	 * @param pID
	 * @param pName
	 */
	public ShowList(int pID, String pName) {
		super(pID, pName);
		
		showList = new List();
	}
	/**	Dh	18.7.2020
	 * 
	 * @param pID
	 * @param pName
	 * @param pShowList
	 */
	public ShowList(int pID, String pName, List pShowList) {
		super(pID, pName);
		
		try {setShowList(pShowList);}
		catch(Exception ex) {
			showList = new List();
			MainManager.handleException(ex);
		}
	}

//----------------------------------------------------------------------------------------------------
	
	/**	Dh	18.7.2020
	 * 
	 * @return
	 */
	@XmlElement(name = "ShowList")
	public List getShowList() {
		return showList;
	}

	//------------------------------------------------------------------------------------------------
	
	/**	Dh	18.7.2020
	 * 
	 * @param pShowList
	 * @throws Exception
	 */
	public void setShowList(List pShowList) throws Exception {
		Object vCur;
		
		if (pShowList != null) {
			if (!pShowList.isEmpty()) {
				pShowList.toFirst();
				
				while(!pShowList.isEnd()) {
					vCur = pShowList.getCurrent();
					
					if (vCur != null) {
						if ((vCur instanceof Integer)) {
							if ( ((int)vCur) <= -1) throw new Exception("02; ShLis,sSL");
						} else throw new Exception("06; ShLis,sSL");
					} else throw new Exception("04; ShLis,sSL");
					
					pShowList.next();
				}
			}
			showList = pShowList.copyList();
		} else throw new Exception("04; ShLis,sSL");
	}
	
//----------------------------------------------------------------------------------------------------
	
	/**	Dh	18.7.2020
	 * 
	 * @param pID
	 * @return
	 * @throws Exception
	 */
	protected boolean haveShow(int pID) throws Exception {
		boolean vRet = false;
		
		if (pID >= 0) {
			if (!showList.isEmpty()) {
				showList.toFirst();
				
				while(!showList.isEnd() && (vRet == false)) {
					if ( ((Integer)showList.getCurrent()) == pID ) vRet = true;
					
					showList.next();
				}
			}
		}else throw new Exception("02; ShLis,hS");
		
		return vRet;
	}
	
	//------------------------------------------------------------------------------------------------
	
	/**	Dh	18.7.2020
	 * 
	 * @param pShowID
	 * @throws Exception
	 */
	protected void addShow(int pShowID) throws Exception{
		if (pShowID >= 0) showList.append(pShowID);
		else throw new Exception("02; ShLis,aS");
	}
	
	/**	Dh	18.7.2020
	 * 
	 * @param pID
	 * @throws Exception
	 */
	protected void removeShow(int pID) throws Exception{
		if (pID >= 0) {
			if (!showList.isEmpty()) {
				showList.toFirst();
				
				while(!showList.isEnd() && (pID != -1)) {
					if ( ((int)showList.getCurrent()) == pID ) {
						showList.remove();
						pID = -1;
					}
					
					showList.next();
				}
			}
		} else throw new Exception("02; ShLis,rS");
	}
	
}
