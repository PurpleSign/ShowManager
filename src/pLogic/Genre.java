/**	ShowManager v0.0	Dh	27.7.2020
 * 
 * 	pLogic
 * 	  ShowManagerElemet
 * 		Genre
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
import javax.xml.bind.annotation.XmlRootElement;

import pDataStructures.List;

@XmlRootElement
@Entity
@Table
public class Genre extends ShowManagerElement{
	
	/**	Dh	18.7.2020
	 * 
	 */
	public Genre() {
		super();
	}
	/**	Dh	18.7.2020
	 * 
	 * @param pID
	 * @param pName
	 */
	public Genre(int pID, String pName) {
		super(pID, pName);
	}

}
