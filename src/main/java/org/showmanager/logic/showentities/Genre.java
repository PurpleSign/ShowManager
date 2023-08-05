/**	ShowManager v0.1	Dh	27.7.2023
 * 
 * 	logic.showentities
 * 	BasicShowentity
 * 	  Genre
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

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.persistence.Entity;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Table;

@JsonPropertyOrder({
	"id", "name"
})
@Entity
@Table
public class Genre extends BasicShowentity {
	
	/**	Dh	27.07.2023
	 * 
	 */
	public Genre() {
		super();
	}
	/**	Dh	27.07.2023
	 * 
	 * @param pID
	 * @param pName
	 */
	public Genre(int pID, String pName) {
		super(pID, pName);
	}

}
