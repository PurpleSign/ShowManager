/**	ShowManager v0.0	Dh	28.7.2020
 * 
 * 	pLogic
 * 	  LogicSettings
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

import java.io.File;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
//@XmlType(propOrder = {"browserName", "browserPath"})
public class LogicSettings {
	private String browserName, browserPath;
	
	/**	Dh	19.7.2020
	 * 
	 */
	public LogicSettings() {
		browserName = "";
		browserPath = "";
	}

//----------------------------------------------------------------------------------------------------
	
	/**	Dh	19.7.2020
	 * 
	 * @return
	 */
	@XmlElement(name = "BrowserName")
	public String getBrowserName() {
		return browserName;
	}
	/**	Dh	19.7.2020
	 * 
	 * @return
	 */
	@XmlElement(name = "BrowserPath")
	public String getBrowserPath() {
		return browserPath;
	}
	/**	Dh	19.7.2020
	 * 
	 * @return
	 */
	@XmlTransient
	public File getBrowserFile() {
		return new File(browserPath);
	}
	
	//------------------------------------------------------------------------------------------------
	
	/**	Dh	28.7.2020
	 * 
	 * @param pBrowserName
	 * @throws Exception
	 */
	public void setBrowserName(String pBrowserName) throws Exception {
		if (pBrowserName != null) {
			browserName = pBrowserName;
		} else throw new Exception("04; LoSet,sBN");
	}
	/**	Dh	28.7.2020
	 * 
	 * @param pBroswerPath
	 * @throws Exception
	 */
	public void setBrowserPath(String pBrowserPath) throws Exception {
		File vBrowserFile;
		
		if (pBrowserPath != null) {
			if (!pBrowserPath.equals("")) {
				vBrowserFile = new File(pBrowserPath);
				
				if (vBrowserFile.exists()) browserPath = pBrowserPath;
				else throw new Exception("02; LoSet,sBP");
			} else browserPath = "";
		} else throw new Exception("04; LoSet,sBP");
	}
	
//----------------------------------------------------------------------------------------------------
	
	/**	Dh	23.7.2020
	 * 
	 */
	protected void resetSetting() {
		browserName = "";
		browserPath = "";
	}
}
