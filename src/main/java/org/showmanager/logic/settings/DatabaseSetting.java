/**	ShowManager v0.1	Dh	26.7.2023
 * 
 * 	logic.settings
 * 	DatabaseSetting
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

package org.showmanager.logic.settings;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({
	"DatabaseName", "UserName", "Password"
})
public class DatabaseSetting {
	@JsonProperty("DatabaseName")
	private String databaseName;
	@JsonProperty("UserName")
	private String userName;
	@JsonProperty("Password")
	private String password;

	/**	Dh	26.07.2023
	 * 
	 */
	public DatabaseSetting() {
		databaseName = "showmanager_database";
		userName = "";
		password = "";
	}

//----------------------------------------------------------------------------------------------------

	/**	Dh	26.07.2023
	 * 
	 * @return
	 */
	public String getDatabaseName() {
		return databaseName;
	}
	/**	Dh	26.07.2023
	 * 
	 * @return
	 */
	public String getUserName() {
		return userName;
	}
	/**	Dh	26.07.2023
	 * 
	 * @return
	 */
	public String getPassword() {
		return password;
	}
	
	//-----
	
	/**	Dh	26.07.2023
	 * 
	 * @param pDatabaseName
	 * @throws Exception
	 */
	public void setDatabaseName(String pDatabaseName) throws Exception{
		if (pDatabaseName != null) {
			databaseName = pDatabaseName;
		} else throw new Exception("04; sDN,DbS");
	}
	/**	Dh	26.07.2023
	 * 
	 * @param pUserName
	 * @throws Exception
	 */
	public void setUserName(String pUserName) throws Exception{
		if (pUserName != null) {
			userName = pUserName;
		} else throw new Exception("04; sUN,DbS");
	}
	/**	Dh	26.07.2023
	 * 
	 * @param pPassword
	 * @throws Exception
	 */
	public void setPassword(String pPassword) throws Exception{
		if (pPassword != null) {
			password = pPassword;
		} else throw new Exception("04; sPw,DbS");
	}
	
}
