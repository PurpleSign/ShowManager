/**	ShowManager v0.1	Dh	26.7.2023
 * 
 * 	MainManagerInterface
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

package org.showmanager;

public interface MainManagerInterface {

	public abstract void closeApp();
	
//--------------------------------------------------------------------------------------------------------

	public abstract void handleException(Exception ex);
	public abstract void handleMessage(String pMessage);
	
}
