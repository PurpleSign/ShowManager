/**	ShowManager v0.1	Dh	29.7.2023
 * 
 * 	logic.showentities
 * 	BasicShowentity
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
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.MappedSuperclass;

@JsonPropertyOrder({
	"id", "name"
})
@Inheritance( strategy = InheritanceType.TABLE_PER_CLASS )
@MappedSuperclass
public abstract class BasicShowentity {
	@JsonProperty("id")
	@Id
	protected int id;
	@JsonProperty("name")
	protected String name;
	
	/**	Dh	29.07.2023
	 * 
	 */
	public BasicShowentity() {
		id = -1;
		name = "";
	}
	/**	Dh	29.07.2023
	 * 
	 * @param pID
	 * @param pName
	 */
	public BasicShowentity(int pID, String pName) {
		id = pID;
		name = pName;
	}

//--------------------------------------------------------------------------------------------------------
	
	/**	Dh	26.07.2023
	 * 
	 * @return
	 */
	public int getId() {
		return id;
	}
	/**	Dh	26.07.2023
	 * 
	 * @return
	 */
	public String getName() {
		return name;
	}
	
	//-----
	
	/**	Dh	26.07.2023
	 * 
	 * @param pID
	 * @throws Exception
	 */
	public void setId(int pID) throws Exception {
		if (pID >= -1) id = pID;
		else throw new Exception("02; sID,BSe");
	}
	/**	Dh	26.07.2023
	 * 
	 * @param pName
	 * @throws Exception
	 */
	public void setName(String pName) throws Exception{
		if (pName != null) name = pName;
		else throw new Exception("04; sNa,BSe");
	}
	
}
