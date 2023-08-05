/**	ShowManager v0.1	Dh	27.7.2023
 * 
 * 	logic.showentities
 * 	BasicShowentity
 * 	  Host
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

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.persistence.Entity;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Table;

@JsonPropertyOrder({
	"id", "name", "url"
})
@Entity
@Table
public class Host extends BasicShowentity {
	@JsonProperty("url")
	protected String url;
	
	/**	Dh	26.07.2023
	 * 
	 */
	public Host() {
		super();
		
		url = "";
	}
	/**	Dh	26.07.2023
	 * 
	 * @param pID
	 * @param pName
	 * @param pURL
	 */
	public Host(int pID, String pName, String pURL) {
		super(pID, pName);
		
		url = pURL;
	}

//----------------------------------------------------------------------------------------------------

	/**	Dh	26.07.2023
	 * 
	 * @return
	 */
	public String getUrl() {
		return url;
	}
	
	/**	Dh	26.07.2023
	 * 
	 * @param pURL
	 * @throws Exception
	 */
	public void setUrl(String pURL) throws Exception{
		if (pURL != null) url = pURL;
		else throw new Exception("04; sURL,Hos");
	}
}
