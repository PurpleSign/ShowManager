/**	ShowManager v0.0	Dh	2.8.2020
 * 
 * 	pLogic
 * 	  DatabseSettings
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

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement
public class DatabaseSettings {
	private String databaseName, userName, password;
	
	/**	Dh	31.7.2020
	 * 
	 */
	public DatabaseSettings() {
		databaseName = "showmanager_database";
		userName = "";
		password = "";
	}

//----------------------------------------------------------------------------------------------------
	
	/**	Dh	31.7.2020
	 * 
	 * @return
	 */
	@XmlElement(name = "DatabaseName")
	public String getDatabaseName() {
		return databaseName;
	}
	/**	Dh	31.7.2020
	 * 
	 * @return
	 */
	@XmlElement(name = "UserName")
	public String getUserName() {
		return userName;
	}
	/**	Dh	31.7.2020
	 * 	
	 * @return
	 */
	@XmlTransient
	public String getPassword() {
		return password;
	}
	
	//------------------------------------------------------------------------------------------------

	/**	Dh	2.8.2020
	 * 
	 * @param pDatabaseName
	 * @throws Exception
	 */
	public void setDatabaseName(String pDatabaseName) throws Exception{
		if (pDatabaseName != null) {
			databaseName = pDatabaseName;
		} else throw new Exception("04; DaSet,sDN");
	}
	/**	Dh	2.8.2020
	 * 
	 * @param pUserName
	 * @throws Exception
	 */
	public void setUserName(String pUserName) throws Exception{
		if (pUserName != null) {
			userName = pUserName;
		} else throw new Exception("04; DaSet,sUN");
	}
	/**	Dh	2.8.2020
	 * 
	 * @param pPassword
	 * @throws Exception
	 */
	public void setPassword(String pPassword) throws Exception{
		if (pPassword != null) {
			password = pPassword;
		} else throw new Exception("04; DaSet,sP");
	}
	
}
