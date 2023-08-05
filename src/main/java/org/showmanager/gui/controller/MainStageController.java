/**	ShowManager v0.1	Dh	05.08.2023
 * 
 * 	gui.controller
 * 	BasicController
 * 	  AbstractParentController
 * 		MainStageController
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

package org.showmanager.gui.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import org.showmanager.MainManager;
import org.showmanager.gui.controller.subparts.ShowListSceneController;
import org.showmanager.gui.stages.editors.BrowserEditorStage;
import org.showmanager.gui.stages.editors.DatabaseConnectionEditorStage;
import org.showmanager.gui.stages.editors.SettingEditorStage;
import org.showmanager.gui.stages.editors.adding.GenreEditorStage;
import org.showmanager.gui.stages.editors.adding.ShowEditorStage;
import org.showmanager.gui.stages.editors.adding.ShowListEditorStage;
import org.showmanager.gui.stages.editors.multiple.MultipleGenreEditorStage;
import org.showmanager.gui.stages.editors.multiple.MultipleHostEditorStage;
import org.showmanager.gui.stages.editors.multiple.MultipleShowEditorStage;
import org.showmanager.gui.tableElements.ShowTableElement;
import org.showmanager.logic.manager.LogManager;
import org.showmanager.logic.manager.SettingManager;
import org.showmanager.logic.manager.ShowManager;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

public class MainStageController extends AbstractParentController {
	private SettingManager settingManager;
	private ShowManager showManager;
	
	private HashMap<Integer, Tab> showListTabs;
	private HashMap<Tab, Integer> tabsShowLists;
	private HashMap<Integer, ShowListSceneController> showListTabController;
	
	@FXML
	private Label lTotalShowNumber, lVersion;
	@FXML
	private Button btStartShow, btEditShow, btAddShow, btRemoveShow, btEditList, btAddList, btRemoveList,
					btShows, btGenres, btOptions, btBack;
	@FXML
	private TabPane tbMain;

	@FXML
	private MenuItem miNewShow, miNewList, miNewGenre, miExportDatabase, miImportDatabase, miSaveAll, miClose;
	@FXML
	private MenuItem miEditShow, miEditList, miEditShows, miEditGenres, miRemoveShow, miRemoveList;
	@FXML
	private MenuItem miEditBrowser, miEditHosts, miEditDatabaseConnection, miDefault;
	@FXML
	private RadioMenuItem rmiRating, rmiGenres, rmiHosts, rmiURLs;
	
	@FXML
	private TableView<ShowTableElement> tbTotalShows;
	@FXML
	private TableColumn<ShowTableElement, String> tcTotalShowsName, tcTotalShowsGenres, tcTotalShowsHosts, tcTotalShowsURLs;
	@FXML
	private TableColumn<ShowTableElement, Integer> tcTotalShowsRating;
	
	private ObservableList<ShowTableElement> liTotalShows;
	
	/**	Dh	01.08.2023	
	 * 
	 */
	public MainStageController() {
		super();
		
		settingManager = null;
		showManager = null;
		
		showListTabs = new HashMap<Integer, Tab>();
		showListTabController = new HashMap<Integer, ShowListSceneController>();
		tabsShowLists = new HashMap<Tab, Integer>();
		
		liTotalShows = null;
	}
	
	//----------------------------------------------------------------------------------------------------

	/**	Dh	01.08.2023
	 * 
	 */
	private void setUpMenuItems() {
		rmiRating.selectedProperty().addListener((pObs, pOldValue, pNewValue) -> {
			if (pNewValue != pOldValue) {
				setRatingVisibility(pNewValue);
				
				setEnabledTables(true);
			}
		});
		rmiGenres.selectedProperty().addListener((pObs, pOldValue, pNewValue) -> {
			if (pNewValue != pOldValue) {
				setGenreVisibility(pNewValue);
				
				setEnabledTables(true);
			}
		});
		rmiHosts.selectedProperty().addListener((pObs, pOldValue, pNewValue) -> {
			if (pNewValue != pOldValue) {
				setHostVisibility(pNewValue);
				
				setEnabledTables(true);
			}
		});
		rmiURLs.selectedProperty().addListener((pObs, pOldValue, pNewValue) -> {
			if (pNewValue != pOldValue) {
				setURLVisibility(pNewValue);
				
				setEnabledTables(true);
			}
		});
		
		rmiRating.setSelected( settingManager.isRatingEnabled() );
		rmiGenres.setSelected( settingManager.isGenreEnabled() );
		rmiHosts.setSelected( settingManager.isHostEnabled() );
		rmiURLs.setSelected( settingManager.isURLEnabled() );
	}
	
	/**	Dh	05.08.2023
	 * 
	 * @param pSettingManager
	 * @param pShowManager
	 * @throws Exception
	 */
	public void setUp(SettingManager pSettingManager, ShowManager pShowManager) throws Exception{
		super.setUp(null);
		
		settingManager = pSettingManager;
		showManager = pShowManager;
		
		lVersion.setText("v" + LogManager.getVersion() );
		
		tbMain.getSelectionModel().selectedItemProperty().addListener(((pObs, pOldValue, pNewValue) ->{
			if (pOldValue != pNewValue) setEnabled();
		} ));
		
		liTotalShows = FXCollections.observableArrayList();
		
		tcTotalShowsName.setCellValueFactory(new PropertyValueFactory<ShowTableElement, String>("name"));
		tcTotalShowsRating.setCellValueFactory(new PropertyValueFactory<ShowTableElement, Integer>("rating"));
		tcTotalShowsGenres.setCellValueFactory(new PropertyValueFactory<ShowTableElement, String>("genres"));
		tcTotalShowsHosts.setCellValueFactory(new PropertyValueFactory<ShowTableElement, String>("host"));
		tcTotalShowsURLs.setCellValueFactory(new PropertyValueFactory<ShowTableElement, String>("url"));
		
		tcTotalShowsGenres.setComparator((pCoVa1, pCoVa2) -> {
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
		
		tcTotalShowsName.setSortable(true);
		tcTotalShowsRating.setSortable(true);
		tcTotalShowsGenres.setSortable(true);
		tcTotalShowsHosts.setSortable(true);
		tcTotalShowsURLs.setSortable(true);
		
		tbTotalShows.setItems(liTotalShows);
		
		tbTotalShows.getSelectionModel().selectedItemProperty().addListener((pObs, pOldValue, pNewValue) -> {
			if ((pNewValue != pOldValue)) setEnabled();
		});
		
		this.setUpMenuItems();
	}
	
//--------------------------------------------------------------------------------------------------------
	
	/**	Dh	28.07.2023
	 * 
	 */
	public void closeChildStage() {
		super.closeChildStage();
		
		try{updateAll();}
		catch(Exception ex) {LogManager.handleException(ex);}
	}
	
	//------------------------------------------------------------------------------------------------
	
	/**	Dh	05.08.2023
	 * 
	 * @param pEnable
	 */
	private void setEnabledFileMenuItems(boolean pEnable) {
		miNewShow.setDisable(!pEnable);
		miNewList.setDisable(!pEnable);
		miNewGenre.setDisable(!pEnable);
		
		miImportDatabase.setDisable(!pEnable);
		miExportDatabase.setDisable(!pEnable);
		
		miSaveAll.setDisable(!pEnable);
		
		miClose.setDisable(!pEnable);
	}
	/**	Dh	01.08.2023
	 * 
	 * @param pEnable
	 */
	private void setEnabledEditMenuItems(boolean pEnable) {
		Tab vSelTab = tbMain.getSelectionModel().getSelectedItem();
		ShowTableElement vCurSel = null;
		
		if (vSelTab != null) {
			if (showListTabs.containsValue(vSelTab)) vCurSel = showListTabController.get( tabsShowLists.get(vSelTab) ).getShowListSelection();
			else {
				vSelTab = null;
				vCurSel = tbTotalShows.getSelectionModel().getSelectedItem();
			}
		}
		
		miEditShow.setDisable( (vCurSel == null ? true : !pEnable) );
		miEditList.setDisable( (vSelTab == null ? true : !pEnable) );
		
		miEditShows.setDisable(!pEnable);
		miEditGenres.setDisable(!pEnable);
		
		miRemoveShow.setDisable( (vCurSel == null ? true : !pEnable) );
		miRemoveList.setDisable( (vSelTab == null ? true : !pEnable) );
	}
	/**	Dh	01.08.2023
	 * 
	 * @param pEnable
	 */
	private void setEnabledOptionMenuItems(boolean pEnable) {
		miEditBrowser.setDisable(!pEnable);
		miEditHosts.setDisable(!pEnable);
		miEditDatabaseConnection.setDisable(!pEnable);
		
		rmiRating.setDisable(!pEnable);
		rmiGenres.setDisable(!pEnable);
		rmiHosts.setDisable(!pEnable);
		rmiURLs.setDisable(!pEnable);
		
		miDefault.setDisable(true);
	}
	//-----
	/**	Dh	01.08.2023
	 * 
	 * @param pEnable
	 */
	private void setEnabledShowButtons(boolean pEnable) {
		Tab vSelTab = tbMain.getSelectionModel().getSelectedItem();
		ShowTableElement vCurSel = null;
		
		if (vSelTab != null) {
			if (showListTabs.containsValue(vSelTab)) vCurSel = showListTabController.get( tabsShowLists.get(vSelTab) ).getShowListSelection();
			else vCurSel = tbTotalShows.getSelectionModel().getSelectedItem();
		}
		
		btStartShow.setDisable( (vCurSel == null ? true : !pEnable) );
		btEditShow.setDisable( (vCurSel == null ? true : !pEnable) );
		btAddShow.setDisable(!pEnable);
		btRemoveShow.setDisable( (vCurSel == null ? true : !pEnable) );
	}
	/**	Dh	01.08.2023
	 * 
	 * @param pEnable
	 */
	private void setEnabledListButtons(boolean pEnable) {
		int vTabSelInd = tbMain.getSelectionModel().getSelectedIndex();
		
		btEditList.setDisable( (vTabSelInd == 0 ? true : !pEnable) );
		btAddList.setDisable(!pEnable);
		btRemoveList.setDisable( (vTabSelInd == 0 ? true : !pEnable) );
	}
	/**	Dh	31.07.2023
	 * 
	 * @param pEnable
	 */
	private void setEnabledOverviewButtons(boolean pEnable) {
		btShows.setDisable(!pEnable);
		btGenres.setDisable(!pEnable);
	}
	/**	Dh	01.08.2023
	 * 
	 * @param pEnable
	 */
	private void setEnabledOtherButton(boolean pEnable) {
		btOptions.setDisable(!pEnable);
		btBack.setDisable(!pEnable);
	}
	//-----
	/**	Dh	01.08.2023
	 * 
	 * @param pEnable
	 */
	private void setEnabledMainTableColumns(boolean pEnable) {
		tcTotalShowsRating.setVisible( settingManager.isRatingEnabled() );
		tcTotalShowsGenres.setVisible( settingManager.isGenreEnabled() );
		tcTotalShowsHosts.setVisible( settingManager.isHostEnabled() );
		tcTotalShowsURLs.setVisible( settingManager.isURLEnabled() );
	}
	/**	Dh	01.08.2023
	 * 
	 * @param pEnable
	 */
	private void setEnabledShowListsTables(boolean pEnable) {
		for (ShowListSceneController vTableController : showListTabController.values()) {
			vTableController.setRatingColumnVisibility( settingManager.isRatingEnabled() );
			vTableController.setGenresColumnVisibility( settingManager.isGenreEnabled() );
			vTableController.setHostColumnVisibility( settingManager.isHostEnabled() );
			vTableController.setURLColumnVisibility( settingManager.isURLEnabled() );
		}
	}
	
	/**	Dh	29.07.2023
	 * 
	 * @param pEnable
	 */
	private void setEnabldeMenuItems(boolean pEnable) {
		setEnabledFileMenuItems(pEnable);
		setEnabledEditMenuItems(pEnable);
		setEnabledOptionMenuItems(pEnable);
	}
	/**	Dh	01.08.2023
	 * 
	 * @param pEnable
	 */
	private void setEnabledTables(boolean pEnable) {
		setEnabledMainTableColumns(pEnable);
		setEnabledShowListsTables(pEnable);
	}
	/**	Dh	28.07.2023
	 * 
	 * @param pEnable
	 */
	private void setEnabledButtons(boolean pEnable) {
		setEnabledShowButtons(pEnable);
		setEnabledListButtons(pEnable);
		setEnabledOverviewButtons(pEnable);
		setEnabledOtherButton(pEnable);
	}
	
	/**	Dh	29.07.2023
	 * 
	 */
	protected void setEnabled(boolean pEnable) {
		setEnabldeMenuItems(pEnable);
		setEnabledTables(pEnable);
		setEnabledButtons(pEnable);
	}
	
//--------------------------------------------------------------------------------------------------------
	
	/**	Dh	01.08.2023
	 * 
	 */
	public void startShow() {
		Tab vSelTab = tbMain.getSelectionModel().getSelectedItem();
		ShowTableElement vCurSel = null;
		
		if (vSelTab != null) {
			if (showListTabs.containsValue(vSelTab)) vCurSel = showListTabController.get( tabsShowLists.get(vSelTab) ).getShowListSelection();
			else vCurSel = tbTotalShows.getSelectionModel().getSelectedItem();
			
			if (vCurSel != null) {
				try { showManager.openShow( vCurSel.getId() ); }
				catch(Exception ex) {LogManager.handleException(ex);}
			}else LogManager.handleMessage("Keien Serie ausgewählt!");
		} else LogManager.handleMessage("Keine Liste ausgewählt!");	
	}
	
	//-----
	
	/**	Dh	27.07.2023
	 * 
	 */
	public void startDatabaseConnectionEditor() {
		super.childStage = new DatabaseConnectionEditorStage(this, settingManager);
		super.setDisabled();
	}
	/**	Dh	30.07.2023
	 * 
	 */
	public void startBrowserEditor() {
		super.childStage = new BrowserEditorStage(this, settingManager);
		super.setDisabled();
	}
	/**	Dh	01.08.2023
	 * 
	 */
	public void startSettingEditor() {
		childStage = new SettingEditorStage(this, settingManager, showManager);
		setDisabled();
	}
	
	/**	Dh	30.07.2023
	 * 
	 */
	public void startGenreEditor() {
		super.childStage = new GenreEditorStage(this, showManager, -1);
		super.setDisabled();
	}
	/**	Dh	01.08.2023
	 * 
	 */
	public void startShowEditor() {
		Tab vSelTab = tbMain.getSelectionModel().getSelectedItem();
		ShowTableElement vCurSel = null;
		
		if (vSelTab != null) {
			if (showListTabs.containsValue(vSelTab)) vCurSel = showListTabController.get( tabsShowLists.get(vSelTab) ).getShowListSelection();
			else vCurSel = tbTotalShows.getSelectionModel().getSelectedItem();
			
			if (vCurSel != null) {
				super.childStage = new ShowEditorStage(this, showManager, vCurSel.getId());
				super.setDisabled();
			}else LogManager.handleMessage("Keien Serie ausgewählt!");
		} else LogManager.handleMessage("Keine Liste ausgewählt!");		
	}
	/**	Dh	01.08.2023
	 * 
	 */
	public void startShowListEditor() {
		Tab vSelTab = tbMain.getSelectionModel().getSelectedItem();
		
		if ((vSelTab != null) && (showListTabs.containsValue(vSelTab))) {
			if (showListTabs.containsValue(vSelTab)) {
				super.childStage = new ShowListEditorStage(this, showManager, tabsShowLists.get(vSelTab) );
				super.setDisabled();
			}
		}else LogManager.handleMessage("Keine Liste ausgewählt!");
	}
	
	/**	Dh	30.07.2023
	 * 
	 */
	public void startMultipleGenreEditor() {
		super.childStage = new MultipleGenreEditorStage(this, showManager);
		super.setDisabled();
	}
	/**	Dh	31.07.2023
	 * 
	 */
	public void startMultipleShowEditor() {
		super.childStage = new MultipleShowEditorStage(this, showManager);
		super.setDisabled();
	}
	/**	Dh	31.07.2023
	 * 
	 */
	public void startMultipleHostEditor() {
		super.childStage = new MultipleHostEditorStage(this, showManager);
		super.setDisabled();
	}
	
	//-----
	
	/**	Dh	01.08.2023
	 * 
	 */
	public void startNewShowEditor() {
		super.childStage = new ShowEditorStage(this, showManager, -1);
		super.setDisabled();
	}
	/**	Dh	01.08.2023
	 * 
	 */
	public void startNewShowListEditor() {
		super.childStage = new ShowListEditorStage(this, showManager, -1);
		super.setDisabled();
	}
	
	//-----
	
	/**	Dh	01.08.2023
	 * 
	 */
	public void removeShow() {
		Tab vSelTab = tbMain.getSelectionModel().getSelectedItem();
		ShowTableElement vCurSel = null;
		
		if (vSelTab != null) {
			if (showListTabs.containsValue(vSelTab)) vCurSel = showListTabController.get( tabsShowLists.get(vSelTab) ).getShowListSelection();
			else vCurSel = tbTotalShows.getSelectionModel().getSelectedItem();
			
			if (vCurSel != null) {
				try { 
					showManager.removeShow( vCurSel.getId() ); 
					
					updateAll();
				} catch(Exception ex) {LogManager.handleException(ex);}
			}else LogManager.handleMessage("Keien Serie ausgewählt!");
		} else LogManager.handleMessage("Keine Liste ausgewählt!");		
	}
	/**	Dh	01.08.2023
	 * 
	 */
	public void removeShowList() {
		Tab vSelTab = tbMain.getSelectionModel().getSelectedItem();

		if ((vSelTab != null) && (showListTabs.containsValue(vSelTab))) {
			try{
				showManager.removeShowList( tabsShowLists.get(vSelTab) );
				
				updateAll();
			} catch(Exception ex) {LogManager.handleException(ex);}
		}else LogManager.handleMessage("Keine Liste ausgewählt!");
	}
	
	//-----
	
	/**	Dh	01.08.2023
	 * 
	 * @param pVisible
	 */
	public void setRatingVisibility(boolean pVisible) {
		settingManager.setRatingEnabled(pVisible);
	}
	/**	Dh	01.08.2023
	 * 
	 * @param pVisible
	 */
	public void setGenreVisibility(boolean pVisible) {
		settingManager.setGenreEnabled(pVisible);
	}
	/**	Dh	01.08.2023
	 * 
	 * @param pVisible
	 */
	public void setHostVisibility(boolean pVisible) {
		settingManager.setHostEnabled(pVisible);
	}
	/**	Dh	01.08.2023
	 * 
	 * @param pVisible
	 */
	public void setURLVisibility(boolean pVisible) {
		settingManager.setURLEnabled(pVisible);
	}
	
	//-----
	
	/**	Dh	05.08.2023
	 * 
	 */
	@FXML
	public void exportDatabase() {
		File vFile;
		FileChooser vFileChooser = new FileChooser();
		
		vFileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
		vFileChooser.setTitle("Wähle Datenbankexport Datei");
		vFileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("XML", "*.xml"));
		
		setDisabled();
		vFile = vFileChooser.showSaveDialog(MainManager.getPrimaryStage());
		setEnabled();
		
		if (vFile != null) {
			try { showManager.exportDatabase( vFile.getAbsolutePath() ); }
			catch(Exception ex) {LogManager.handleException(ex);}
		}
	}
	
	/**	Dh	05.08.2023
	 * 
	 */
	@FXML
	public void importDatabase() {
		File vFile;
		FileChooser vFileChooser = new FileChooser();
		
		vFileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
		vFileChooser.setTitle("Wähle Datenbankexport Datei");
		vFileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("XML", "*.xml"));
		
		setDisabled();
		vFile = vFileChooser.showOpenDialog(MainManager.getPrimaryStage());
		setEnabled();
		
		if (vFile != null) {
			try {
				showManager.importDatabase(vFile.getAbsolutePath());
				
				updateAll();
			}catch(Exception ex) {LogManager.handleException(ex);}
		}
	}
	
	/**	Dh	02.08.2023
	 * 
	 */
	@FXML
	public void saveAll() {
		try { showManager.saveAll(); }
		catch(Exception ex) {LogManager.handleException(ex);}
	}
	
	//------------------------------------------------------------------------------------------------
	
	/**	Dh	27.07.2023
	 * 
	 */
	@FXML
	public void back() {
		LogManager.closeApp();
	}

//--------------------------------------------------------------------------------------------------------

	/**	Dh	29.07.2023
	 * 
	 * @throws Exception
	 */
	private void updateTotalShowList() throws Exception{
		liTotalShows.setAll(generateShowTableList(showManager.getShowIDs()));
		lTotalShowNumber.setText("Anzahl: " + liTotalShows.size());
	}
	/**	Dh	29.07.2023
	 * 
	 * @throws Exception
	 */
	private void updateShowLists() throws Exception{
		ArrayList<Integer> vShowListIDs = showManager.getShowListIDs();
		ShowListSceneController vController;
		
		if (!vShowListIDs.isEmpty()) {
			for (Integer vShowListID : vShowListIDs) {
				if (showListTabs.get(vShowListID) == null) {
					createNewShowListTab(vShowListID);
				}else {
					vController = showListTabController.get(vShowListID);
					
					vController.setShowListTitle(showManager.getShowListName(vShowListID));
					vController.setShowListTable(generateShowTableList(showManager.getShowListShowIDs(vShowListID)));
				}
			}
			
			for (Integer vShowListTabID : showListTabs.keySet()) {
				if (!vShowListIDs.contains(vShowListTabID)) {
					tbMain.getTabs().remove( showListTabs.get(vShowListTabID) );
					showListTabController.remove(vShowListTabID);
					tabsShowLists.remove( showListTabs.get(vShowListTabID) );
					showListTabs.remove(vShowListTabID);
					
				}
			}
		}else {
			for (Integer vShowListTabID : showListTabs.keySet()) {
				tbMain.getTabs().remove( showListTabs.get(vShowListTabID) );
				showListTabController.remove(vShowListTabID);
				tabsShowLists.remove( showListTabs.get(vShowListTabID) );
				showListTabs.remove(vShowListTabID);
			}
		}
	}
	
	/**	Dh	29.07.2023
	 * 
	 * @throws Exception
	 */
	private void updateLists() throws Exception {
		updateTotalShowList();
		updateShowLists();
	}
	
	/**	Dh	29.07.2023
	 * 
	 * @throws Exception
	 */
	protected void updateAll() throws Exception{		
		updateLists();
	}
	
	/**	Dh	31.07.2023
	 * 
	 * @throws Exception
	 */
	public void loadFromDatabase() throws Exception {
		if (settingManager.haveDatabaseConnection()) showManager.loadAll();
	}
		
//--------------------------------------------------------------------------------------------------------

	/**	Dh	01.08.2023
	 * 
	 * @param pShowListID
	 * @return
	 * @throws Exception
	 */
	private void createNewShowListTab(int pShowListID) throws Exception {
		Tab vNewTab = new Tab();
		String vShowListName;
		
		FXMLLoader vFXMLLoader;
		VBox vBox;
		ShowListSceneController vController;
		
		vShowListName = showManager.getShowListName(pShowListID);
		vNewTab.setText(vShowListName);
		
		vFXMLLoader = new FXMLLoader(getClass().getResource("/org/showmanager/gui/subparts/ShowListScene.fxml"));
		vBox = vFXMLLoader.load();
		vController = vFXMLLoader.getController();
		
		vController.setUp(this, pShowListID);
		vController.setShowListTitle(vShowListName);
		vController.setShowListTable(generateShowTableList( showManager.getShowListShowIDs(pShowListID) ));
		
		vNewTab.setContent(vBox);
		
		tbMain.getTabs().add(vNewTab);
		showListTabs.put(pShowListID, vNewTab);
		showListTabController.put(pShowListID, vController);
		tabsShowLists.put( vNewTab, pShowListID );
	}
	
	/**	Dh	31.07.2023
	 * 
	 * @param pShowIDs
	 * @return
	 * @throws Exception
	 */
	private ArrayList<ShowTableElement> generateShowTableList(ArrayList<Integer> pShowIDs) throws Exception{
		boolean vFirst = false;
		String vTempText = "";
		ArrayList<ShowTableElement> vRet = new ArrayList<ShowTableElement>();
		ArrayList<Integer> vGenreIDs;
		ShowTableElement vTemp;
		
		if (!pShowIDs.isEmpty()) {
			for (Integer vShowID : pShowIDs) {
				vTemp = new ShowTableElement(vShowID, showManager.getShowName(vShowID));
				
				vTemp.setRating(showManager.getShowRating(vShowID));
				vTemp.setUrl(showManager.getShowURL(vShowID));
				
				vTempText = showManager.getHostName( showManager.getShowHostID( vShowID ) );
				vTemp.setHost(vTempText);
				
				vTempText = "";
				vGenreIDs = showManager.getShowGenreIDs(vShowID);
				if (vGenreIDs != null) {
					vFirst = true;
					for (Integer vGenreID : vGenreIDs) {
						if (vFirst == true) vFirst = false;
						else vTempText += ", ";
						
						vTempText += showManager.getGenreName(vGenreID);
					}
				}
				vTemp.setGenres(vTempText);
				
				vRet.add(vTemp);
			}
		} 		
		
		return vRet;
	}
		
	//------------------------------------------------------------------------------------------------
	
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
