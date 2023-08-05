/**	ShowManager v0.1	Dh	05.08.2023
 * 
 * 	gui.controller
 * 	BasicController
 * 	  ShowListSceneController
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

package org.showmanager.gui.controller.subparts;

import java.util.ArrayList;

import org.showmanager.gui.controller.AbstractParentController;
import org.showmanager.gui.controller.BasicController;
import org.showmanager.gui.tableElements.ShowTableElement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ShowListSceneController extends BasicController {	
	private int showListID;
	
	@FXML
	private Label lShowListTitle, lShowListTotalNumber;
	
	@FXML
	private TableView<ShowTableElement> tbShowListTable;
	@FXML
	private TableColumn<ShowTableElement, String> tcShowListNames, tcShowListGenres, tcShowListHosts, tcShowListURLs;
	@FXML
	private TableColumn<ShowTableElement, Integer> tcShowListRatings;
	
	private ObservableList<ShowTableElement> liShowList;
	
	/**	Dh	29.07.2023
	 * 
	 */
	public ShowListSceneController() {
		showListID = -1;
		
		liShowList = null;
	}
	
	//----------------------------------------------------------------------------------------------------
	
	/**	Dh	05.08.2023
	 * 
	 */
	public void setUp(AbstractParentController pParentController, int pShowListID) throws Exception{
		super.setUp(pParentController);
		
		showListID = pShowListID;
		
		liShowList = FXCollections.observableArrayList();
		
		tcShowListNames.setCellValueFactory(new PropertyValueFactory<ShowTableElement, String>("name"));
		tcShowListRatings.setCellValueFactory(new PropertyValueFactory<ShowTableElement, Integer>("rating"));
		tcShowListGenres.setCellValueFactory(new PropertyValueFactory<ShowTableElement, String>("genres"));
		tcShowListHosts.setCellValueFactory(new PropertyValueFactory<ShowTableElement, String>("host"));
		tcShowListURLs.setCellValueFactory(new PropertyValueFactory<ShowTableElement, String>("url"));
		
		tcShowListGenres.setComparator((pCoVa1, pCoVa2) -> {
			int vRet = 0;
			String vMinValue1, vMinValue2;
			String[] vCoGenres1, vCoGenres2;
			ArrayList<String> vGenreList1, vGenreList2;
			
			if (!pCoVa1.equals("")) vCoGenres1 = pCoVa1.split(", ");
			else vCoGenres1 = new String[] {};
			
			if (!pCoVa2.equals("")) vCoGenres2 = pCoVa2.split(", ");
			else vCoGenres2 = new String[] {};
			
			if ((vCoGenres1.length != 0) && (vCoGenres2.length != 0)) {
				vGenreList1 = convertArrayToList(vCoGenres1);
				vGenreList2 = convertArrayToList(vCoGenres2);
				
				while ((vRet == 0) && ((vGenreList1.size() > 0) && (vGenreList2.size() > 0))) {
					vMinValue1 = getMinStringFromList(vGenreList1);
					vMinValue2 = getMinStringFromList(vGenreList2);
					
					vRet = vMinValue1.compareTo(vMinValue2);
					if (vRet == 0) {
						vGenreList1.remove(vMinValue1);
						vGenreList2.remove(vMinValue2);
						
						if ((vGenreList1.size() == 0) && (vGenreList2.size() != 0)) vRet = -30;
						else if ((vGenreList1.size() != 0) && (vGenreList2.size() == 0)) vRet = 30;
					}
				}
			}else if ((vCoGenres1.length == 0) && (vCoGenres2.length == 0)) vRet = 0;
			else if (vCoGenres1.length == 0) vRet = -30;
			else vRet = 30;
			
			return vRet;
		});
		
		tcShowListNames.setSortable(true);
		tcShowListRatings.setSortable(true);
		tcShowListGenres.setSortable(true);
		tcShowListHosts.setSortable(true);
		tcShowListURLs.setSortable(true);
		
		tbShowListTable.setItems(liShowList);
		
		tbShowListTable.getSelectionModel().selectedItemProperty().addListener((pObs, pOldValue, pNewValue) -> {
			if (pNewValue != pOldValue) parentController.setEnabled();
		});
	}

//--------------------------------------------------------------------------------------------------------

	/**	Dh	01.08.2023
	 * 
	 * @param pVisibility
	 */
	public void setRatingColumnVisibility(boolean pVisibility) {
		tcShowListRatings.setVisible(pVisibility);
	}
	/**	Dh	01.08.2023
	 * 
	 * @param pVisibility
	 */
	public void setGenresColumnVisibility(boolean pVisibility) {
		tcShowListGenres.setVisible(pVisibility);
	}
	/**	Dh	01.08.2023
	 * 
	 * @param pVisibility
	 */
	public void setHostColumnVisibility(boolean pVisibility) {
		tcShowListHosts.setVisible(pVisibility);
	}
	/**	Dh	01.08.2023
	 * 
	 * @param pVisibility
	 */
	public void setURLColumnVisibility(boolean pVisibility) {
		tcShowListURLs.setVisible(pVisibility);
	}
	
	//-----
	
	/**	Dh	29.07.2023
	 * 
	 */
	protected void setEnabled(boolean pEnable) {
		tbShowListTable.setDisable(!pEnable);
	}
	
//--------------------------------------------------------------------------------------------------------

	/**	Dh	29.07.2023
	 * 
	 * @return
	 */
	public int getShowListID() {
		return showListID;
	}
	
	//-----
	
	/**	Dh	29.07.2023
	 * 
	 * @param pTitle
	 */
	public void setShowListTitle(String pTitle) throws Exception {
		if (pTitle != null) lShowListTitle.setText(pTitle);
		else throw new Exception("04; sSLT,SLSC");
	}
	
	/**	Dh	29.07.2023
	 * 
	 * @param pShowListShowTableElements
	 * @throws Exception
	 */
	public void setShowListTable(ArrayList<ShowTableElement> pShowListShowTableElements) throws Exception {
		liShowList.setAll(pShowListShowTableElements);
		lShowListTotalNumber.setText("Anzahl: " + liShowList.size());
	}
	
	//----------------------------------------------------------------------------------------------------
	
	/**	Dh	01.08.2023
	 * 
	 * @return
	 */
	public ShowTableElement getShowListSelection() {
		return tbShowListTable.getSelectionModel().getSelectedItem();
	}
	
//--------------------------------------------------------------------------------------------------------

	/**	Dh	05.08.2023
	 * 
	 * @param <T>
	 * @param pArray
	 * @return
	 */
	private <T> ArrayList<T> convertArrayToList(T[] pArray){
		ArrayList<T> vRet = null;
		
		if (pArray != null) {
			vRet = new ArrayList<T>();
			
			for (T vElement : pArray) {
				vRet.add(vElement);
			}
		}
		
		return vRet;
	}
	
	/**	Dh	05.08.2023
	 * 
	 * @param pStringArray
	 * @return
	 * @throws Exception
	 */
	private String getMinStringFromList(ArrayList<String> pStringList) {
		String vRet = null;
		
		if ((pStringList != null) && (pStringList.size() != 0)) {
			for (String vTemp : pStringList) {
				if ((vRet == null) || (vRet.compareTo(vTemp) > 0)) vRet = vTemp;
			}
		}
		
		return vRet;
	}
	
}
