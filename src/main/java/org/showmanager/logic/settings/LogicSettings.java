/**	ShowManager v0.1	Dh	28.7.2023
 * 
 * 	logic.settings
 * 	LogicSetting
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

import java.io.File;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({
	"BrowserName", "BrowserPath"
})
public class LogicSettings {
	@JsonProperty("BrowserName")
	private String browserName;
	@JsonProperty("BrowserPath")
	private String browserPath;
	
	/**	Dh	26.07.2023
	 * 
	 */
	public LogicSettings() {
		browserName = "";
		browserPath = "";
	}

//----------------------------------------------------------------------------------------------------
	
	/**	Dh	26.07.2023
	 * 
	 * @return
	 */
	public String getBrowserName() {
		return browserName;
	}
	/**	Dh	26.07.2023
	 * 
	 * @return
	 */
	public String getBrowserPath() {
		return browserPath;
	}
	/**	Dh	26.07.2023
	 * 
	 * @return
	 */
	@JsonIgnore
	public File getBrowserFile() {
		return new File(browserPath);
	}
	
	//-----
	
	/**	Dh	26.07.2023
	 * 
	 * @param pBrowserName
	 * @throws Exception
	 */
	public void setBrowserName(String pBrowserName) throws Exception {
		if (pBrowserName != null) browserName = pBrowserName;
		else throw new Exception("04; sBN,LoS");
	}
	/**	Dh	26.07.2023
	 * 
	 * @param pBrowserPath
	 * @throws Exception
	 */
	public void setBrowserPath(String pBrowserPath) throws Exception {
		File vBrowserFile;
		
		if (pBrowserPath != null) {
			if (!pBrowserPath.equals("")) {
				vBrowserFile = new File(pBrowserPath);
				
				if (vBrowserFile.exists()) browserPath = pBrowserPath;
				else throw new Exception("02; sBP,LoS");
			} else browserPath = "";
		} else throw new Exception("04; sBP,LoS");
	}
	
//----------------------------------------------------------------------------------------------------
	
	/**	Dh	26.07.2023
	 * 
	 */
	public void resetSetting() {
		browserName = "";
		browserPath = "";
	}
	
}
