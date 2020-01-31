package application;

import java.sql.Date;
import java.text.ParseException;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class SampleController {
	@FXML private TableView<Inspector> tvInspector;
	@FXML private TableColumn<Inspector, Integer> tcidInspector;
	@FXML private TableColumn<Inspector, String> tcName;
	@FXML private TableColumn<Inspector, String> tcSurname;
	@FXML private TableColumn<Inspector, String> tcPosition;
	@FXML private TableColumn<Inspector, String> tcRank;
	@FXML private TableColumn<Inspector, String> tcConclusion;
	
	@FXML private Label lidInspector;
	@FXML private Label lName;
	@FXML private Label lSurname;
	@FXML private Label lPosition;
	@FXML private Label lRank;
	@FXML private Label lConsclusion;
	
	@FXML private TextField tfidInspector;
	@FXML private TextField tfName;
	@FXML private TextField tfSurname;
	@FXML private TextField tfPosition;
	@FXML private TextField tfRank;
	@FXML private TextField tfConclusion;
	
	@FXML private Button bNew;
	@FXML private Button bUpdate;
	@FXML private Button bDelete;	
	
	private Database db = new Database();
	
	@FXML
	private void initialize() {
	tcidInspector.setCellValueFactory(new PropertyValueFactory<Inspector, Integer>("idInspector"));
	tcName.setCellValueFactory(new PropertyValueFactory<Inspector, String>("Name"));
	tcSurname.setCellValueFactory(new PropertyValueFactory<Inspector, String>("Surname"));
	tcPosition.setCellValueFactory(new PropertyValueFactory<Inspector, String>("Position"));
	tcRank.setCellValueFactory(new PropertyValueFactory<Inspector, String>("Rank"));
	tcConclusion.setCellValueFactory(new PropertyValueFactory<Inspector, String>("Conclusion"));
	
	tvInspector.setItems(FXCollections.observableArrayList(db.getAllInspector()));
	
	showInspectorDetails(null);
	tvInspector.getSelectionModel().selectedItemProperty()
	.addListener((observable, oldValue, newValue) -> showInspectorDetails(newValue));
	}
	private void showInspectorDetails(Inspector cl) {
		if (cl != null) {
		tfidInspector.setText(Integer.toString(cl.getIdInspector()));
		tfName.setText(cl.getName());
		tfSurname.setText(cl.getSurname());
		tfPosition.setText(cl.getPosition());
		tfRank.setText(cl.getRank());
		tfConclusion.setText(cl.getConclusion());
		} else {
		tfidInspector.setText("");
		tfName.setText("");
		tfSurname.setText("");
		tfPosition.setText("");
		tfRank.setText("");
		tfConclusion.setText("");
		}
		}
	/**
	* handle add new Inspector
	*/
	@FXML
	private void handleNew() {
	if (isInputValid(1)) {
	db.newInspector(tfName.getText(), tfSurname.getText(), tfPosition.getText(),tfRank.getText(),tfConclusion.getText());
	} 
	tvInspector.setItems(FXCollections.observableArrayList(db.getAllInspector()));
	}
		//удалить строчку с id n
		@FXML
		private void handleDel() {
			if (isInputValid(2)) {
				db.delInspector(Integer.parseInt(tfidInspector.getText()));
			}
			tvInspector.setItems(FXCollections.observableArrayList(db.getAllInspector()));
		}
		//обновить строку с id n на параметры
	@FXML
	private void handleUpd() {
		if (isInputValid(3)) {
			db.updInspector(Integer.parseInt(tfidInspector.getText()), tfName.getText(),tfSurname.getText(), tfPosition.getText(),tfRank.getText(),tfConclusion.getText());
		}
		tvInspector.setItems(FXCollections.observableArrayList(db.getAllInspector()));
		}
	/**
	* check input
	* @return true if input correct
	*/
	
	private boolean isInputValid(int i) {
		String errorMessage = "";
		if (i == 1) {
			if (tfName.getText() == null || tfName.getText().length() == 0) {
				errorMessage += "No valid name!\n";
			}
			if (tfSurname.getText() == null || tfSurname.getText().length() == 0) {
				errorMessage += "No valid Surname!\n";
			}
			if (tfPosition.getText() == null || tfPosition.getText().length() == 0) {
				errorMessage += "No valid Position!\n";
			}
			if (tfRank.getText() == null || tfRank.getText().length() == 0) {
				errorMessage += "No valid Rank!\n";
			}
			if (tfConclusion.getText() == null || tfConclusion.getText().length() == 0) {
				errorMessage += "No valid conclusion!\n";
				}
			
		}
		if (i == 2) {
			if (tfidInspector.getText() == null || tfidInspector.getText().length() == 0) {
				errorMessage += "No valid ID!\n";				
			}
			else {
				try {
					Integer.parseInt(tfidInspector.getText());
				}
				catch (NumberFormatException e) {
					errorMessage += "Format ID is not a number!\n";
				}
			}
		}
		if (i==3) {
			if (tfidInspector.getText() == null || tfidInspector.getText().length() == 0) {
				errorMessage += "No valid ID!\n";				
			}
			else {
				try {
					Integer.parseInt(tfidInspector.getText());
				}
				catch (NumberFormatException e) {
					errorMessage += "Format ID is not a number!\n";
				}
			}
			if (tfName.getText() == null || tfName.getText().length() == 0) {
				errorMessage += "No validname!\n";
			}
			if (tfSurname.getText() == null || tfSurname.getText().length() == 0) {
				errorMessage += "No valid last name!\n";
			}
			if (tfPosition.getText() == null || tfPosition.getText().length() == 0) {
				errorMessage += "No valid position!\n";
			}
			if (tfRank.getText() == null || tfRank.getText().length() == 0) {
				errorMessage += "No valid Rank!\n";
			}
			if (tfConclusion.getText() == null || tfConclusion.getText().length() == 0) {
				errorMessage += "No valid conclusion!\n";
			}			
		}
		if (errorMessage.length() == 0) {
			return true;
		} 
		else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Invalid Fields");
			alert.setHeaderText("Please correct invalid fields");
			alert.setContentText(errorMessage);
			alert.showAndWait();
			return false;
		}
	}
}
