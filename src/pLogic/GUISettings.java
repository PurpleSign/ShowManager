/**	ShowManager v0.0	Dh	23.7.2020
 * 
 * 	pLogic
 * 	  GUISettings
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

@XmlRootElement
public class GUISettings {
	private boolean enabledRating, enabledGenres, enabledHost, enabledURL;
	private int frameWidth, frameHeight;
	
	/**	Dh	23.7.2020
	 * 
	 */
	public GUISettings() {
		enabledRating = false;
		enabledGenres = true;
		enabledHost = true;
		enabledURL = false;
		
		frameWidth = 1024;
		frameHeight = 728;
	}

//----------------------------------------------------------------------------------------------------
	
	/**	Dh	21.7.2020
	 * 
	 * @return
	 */
	@XmlElement
	public boolean isEnabledRating() {
		return enabledRating;
	}
	/**	Dh	21.7.2020
	 * 
	 * @return
	 */
	@XmlElement
	public boolean isEnabledGenres() {
		return enabledGenres;
	}
	/**	Dh	21.7.2020
	 * 
	 * @return
	 */
	@XmlElement
	public boolean isEnabledHost() {
		return enabledHost;
	}
	/**	Dh	21.7.2020
	 * 
	 * @return
	 */
	@XmlElement
	public boolean isEnabledURL() {
		return enabledURL;
	}
	
	/**	Dh	19.7.2002
	 * 
	 * @return
	 */
	@XmlElement(name = "FrameWidth")
	public int getFrameWidth() {
		return frameWidth;
	}
	/**	Dh	19.7.2020
	 * 
	 * @return
	 */
	@XmlElement(name = "FrameHeight")
	public int getFrameHeight() {
		return frameHeight;
	}

	//------------------------------------------------------------------------------------------------
	
	/**	Dh	21.7.2020
	 * 
	 * @param enabledRating
	 */
	public void setEnabledRating(boolean enabledRating) {
		this.enabledRating = enabledRating;
	}
	/**	Dh	21.7.2020
	 * 
	 * @param enabledGeneres
	 */
	public void setEnabledGenres(boolean enabledGenres) {
		this.enabledGenres = enabledGenres;
	}
	/**	Dh	21.7.2020
	 * 
	 * @param enabledHost
	 */
	public void setEnabledHost(boolean enabledHost) {
		this.enabledHost = enabledHost;
	}
	/**	Dh	21.7.2020
	 * 
	 * @param enabledURL
	 */
	public void setEnabledURL(boolean enabledURL) {
		this.enabledURL = enabledURL;
	}
	
	/**	Dh	19.7.2020
	 * 
	 * @param pFrameWidth
	 * @throws Exception
	 */
	public void setFrameWidth(int pFrameWidth) throws Exception{
		if (pFrameWidth >= 0) frameWidth = pFrameWidth;
		else throw new Exception("02; GUISet,sFW");
	}
	/**	Dh	19.7.2020
	 * 
	 * @param pFrameHeight
	 * @throws Exception
	 */
	public void setFrameHeight(int pFrameHeight) throws Exception {
		if (pFrameHeight >= 0) frameHeight = pFrameHeight;
		else throw new Exception("02; GUISet,sFH");
	}

//----------------------------------------------------------------------------------------------------

	/**	Dh	23.7.2020
	 * 
	 */
	protected void resetSetting() {
		enabledRating = false;
		enabledGenres = true;
		enabledHost = true;
		enabledURL = false;
	}
}
