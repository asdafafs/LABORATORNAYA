package application;

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
	@FXML private TableColumn<Inspector, Integer> tcIdInspector;
	@FXML private TableColumn<Inspector, String> tcName;
	@FXML private TableColumn<Inspector, String> tcSurname;
	@FXML private TableColumn<Inspector, String> tcPosition;
	@FXML private TableColumn<Inspector, String> tcRank;
	@FXML private TableColumn<Inspector, String> tcConclusion;
	@FXML private Label lIdInspector;

	@FXML private Label lName;
	@FXML private Label lSurname;
	@FXML private Label lPosition;
	@FXML private Label lRank;
	@FXML private Label lConsclusion;
	@FXML private TextField tfIdInspector;
	@FXML private TextField tfName;
	@FXML private TextField tfSurname;
	@FXML private TextField tfPosition;
	@FXML private TextField tfRank;
	@FXML private TextField tfConclusion;
	@FXML private Button bNew;
	@FXML private Button bEdit;
	@FXML private Button bDelete;	
	
	private Database db = new Database();
	
	@FXML
	private void initialize() {
	tcIdInspector.setCellValueFactory(new PropertyValueFactory<Inspector, Integer>("IdInspector"));
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
		tfIdInspector.setText(Integer.toString(cl.getidInspector()));
		tfName.setText(cl.getName());
		tfSurname.setText(cl.getSurname());
		tfPosition.setText(cl.getPosition());
		tfRank.setText(cl.getRank());
		tfConclusion.setText(cl.getConclusion());
		} else {
		tfIdInspector.setText("");
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
	if (isInputValid()) {
	db.newInspector(tfName.getText(), tfSurname.getText(), tfPosition.getText(),tfRank.getText(),tfConclusion.getText());
	} 
	tvInspector.setItems(FXCollections.observableArrayList(db.getAllInspector()));
	}
	
	/**
	* check input
	* @return true if input correct
	*/
	private boolean isInputValid() {
	String errorMessage = "";
	if (tfName.getText() == null || tfName.getText().length() == 0) {
	errorMessage += "No valid name!\n";
	}
	if (tfSurname.getText() == null || tfSurname.getText().length() == 0) {
	errorMessage += "No valid surname!\n";
	}
	if (tfPosition.getText() == null || tfPosition.getText().length() == 0) {
		errorMessage += "No valid position!\n";
		}
	if (tfRank.getText() == null || tfRank.getText().length() == 0) {
		errorMessage += "No valid rank!\n";
		}
	if (tfConclusion.getText() == null || tfConclusion.getText().length() == 0) {
		errorMessage += "No valid conclusion!\n";
		}
	if (errorMessage.length() == 0) {
	return true;
	} else {
	Alert alert = new Alert(AlertType.ERROR);
	alert.setTitle("Invalid Fields");
	alert.setHeaderText("Please correct invalid fields");
	alert.setContentText(errorMessage);
	alert.showAndWait();
	return false;
	}
	}
}
