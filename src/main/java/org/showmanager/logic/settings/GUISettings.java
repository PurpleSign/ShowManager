/**	ShowManager v0.1	Dh	26.7.2023
 * 
 * 	logic.settings
 * 	GUISetting
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
	"enabledRating", "enabledGenres", "enabledHost", "enabledURL", "FrameWidth", "FrameHeight"
})
public class GUISettings {
	@JsonProperty("enabledRating")
	private boolean enabledRating;
	@JsonProperty("enabledGenres")
	private boolean enabledGenres;
	@JsonProperty("enabledHost")
	private boolean enabledHost;
	@JsonProperty("enabledURL")
	private boolean enabledURL;
	
	@JsonProperty("FrameWidth")
	private int frameWidth;
	@JsonProperty("FrameHeight")
	private int frameHeight;
	
	public GUISettings() {
		// TODO Auto-generated constructor stub
	}

//----------------------------------------------------------------------------------------------------

	/**	Dh	26.07.2023
	 * 
	 * @return
	 */
	public boolean isEnabledRating() {
		return enabledRating;
	}
	/**	Dh	26.07.2023
	 * 
	 * @return
	 */
	public boolean isEnabledGenres() {
		return enabledGenres;
	}
	/**	Dh	26.07.2023
	 * 
	 * @return
	 */
	public boolean isEnabledHost() {
		return enabledHost;
	}
	/**	Dh	26.07.2023
	 * 
	 * @return
	 */
	public boolean isEnabledURL() {
		return enabledURL;
	}
	
	/**	Dh	26.07.2023
	 * 
	 * @return
	 */
	public int getFrameWidth() {
		return frameWidth;
	}
	/**	Dh	26.07.2023
	 * 
	 * @return
	 */
	public int getFrameHeight() {
		return frameHeight;
	}
	
	//-----
	
	/**	Dh	26.07.2023
	 * 
	 * @param enabledRating
	 */
	public void setEnabledRating(boolean enabledRating) {
		this.enabledRating = enabledRating;
	}
	/**	Dh	26.07.2023
	 * 
	 * @param enabledGenres
	 */
	public void setEnabledGenres(boolean enabledGenres) {
		this.enabledGenres = enabledGenres;
	}
	/**	Dh	26.07.2023
	 * 
	 * @param enabledHost
	 */
	public void setEnabledHost(boolean enabledHost) {
		this.enabledHost = enabledHost;
	}
	/**	Dh	26.07.2023
	 * 
	 * @param enabledURL
	 */
	public void setEnabledURL(boolean enabledURL) {
		this.enabledURL = enabledURL;
	}
	
	/**	Dh	26.07.2023
	 * 
	 * @param pFrameWidth
	 * @throws Exception
	 */
	public void setFrameWidth(int pFrameWidth) throws Exception{
		if (pFrameWidth >= 0) frameWidth = pFrameWidth;
		else throw new Exception("02; sFW,GUIS");
	}
	/**	Dh	26.07.2023
	 * 
	 * @param pFrameHeight
	 * @throws Exception
	 */
	public void setFrameHeight(int pFrameHeight) throws Exception {
		if (pFrameHeight >= 0) frameHeight = pFrameHeight;
		else throw new Exception("02; sFH,GUIS");
	}
	
//----------------------------------------------------------------------------------------------------
	
	/**	Dh	23.07.2023
	 * 
	 */
	public void resetSetting() {
		enabledRating = false;
		enabledGenres = true;
		enabledHost = true;
		enabledURL = false;
	}
	
}
