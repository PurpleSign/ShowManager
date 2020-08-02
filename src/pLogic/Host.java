/**	ShowManager v0.0	Dh	28.7.2020
 * 
 * 	pLogic
 * 	  ShowManagerElemet
 * 		Hoster
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
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@Table
public class Host extends ShowManagerElement{
	private String url;
	
	/**	Dh	18.7.2020
	 * 
	 */
	public Host() {
		super();
		
		url = "";
	}
	/**	Dh	18.7.2020
	 * 
	 * @param pID
	 * @param pName
	 * @param pURL
	 */
	public Host(int pID, String pName, String pURL) {
		super(pID, pName);
		
		try {setUrl(pURL);}
		catch(Exception ex) {
			url = "";
			MainManager.handleException(ex);
		}
	}

//----------------------------------------------------------------------------------------------------
	
	/**	Dh	18.7.2020
	 * 
	 * @return
	 */
	@XmlElement(name = "URL")
	public String getUrl() {
		return url;
	}

	//------------------------------------------------------------------------------------------------
	
	/**	Dh	28.7.2020
	 * 
	 * @param pURL
	 * @throws Exception
	 */
	public void setUrl(String pURL) throws Exception {
		if (pURL != null) url = pURL;
		else throw new Exception("04; Hos,sURL");
	}
}
