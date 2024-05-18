package controller;

import application.authentication.AlertMessage;
import application.dao.EmployeeDAO;
import application.model.Employee;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class NewEmployeeController {
	@FXML
	private ChoiceBox<String> newemployee_gender_choicebox; // this is fxml choicebox Id name given in fxml file
	
	@FXML
	private Button add_newemployee_btn;
	
	@FXML
	private TextField newemployee_name_textfield;
	
	@FXML
	private TextField newemployee_phone_textfield;
	
	@FXML
	private TextField newemployee_dob_textfield;
	
	@FXML
	private TextField newemployee_idcard_textfield;
	
	@FXML
	private TextField newemployee_salary_textfield;
	
	@FXML
	private TextField newemployee_address_textfield;
	
	public void initialize() {
        ObservableList<String> options = FXCollections.observableArrayList("Nam", "Nữ");
        newemployee_gender_choicebox.setValue("Nam"); // this statement shows the default value
        newemployee_gender_choicebox.setItems(options); // this statement adds all values to the ChoiceBox
    }
	
	public void clickBtnNewEmployee(@SuppressWarnings("exports") Event event) {
		String name = newemployee_name_textfield.getText();
		if(name.isBlank()) {
			AlertMessage.showInformationMessage("You must enter the name");
			return;
		}
		String phone = newemployee_phone_textfield.getText();
		if(phone.isBlank() || phone.length()!=10 || !phone.matches("[0-9]+")) {
			AlertMessage.showInformationMessage("phone must be 10 characters and only digit");
			return;
		}
		String dob = newemployee_dob_textfield.getText();
		if(dob.isBlank()) {
			AlertMessage.showInformationMessage("Incorrect form of dob value");
			return;
		}
		String idcard = newemployee_idcard_textfield.getText();
		if(idcard.isBlank() || idcard.length()!=12 || !idcard.matches("[0-9]+")) {
			AlertMessage.showInformationMessage("idcard must be 12 characters and only digits");
			return;
		}
		double salary = 0.0;
		if(newemployee_salary_textfield.getText()!=null) {
			if (newemployee_salary_textfield.getText().matches("^\\d*\\.?\\d+$")) {
				salary = Double.parseDouble(newemployee_salary_textfield.getText());
		    } else {
		        AlertMessage.showInformationMessage("You must enter a valid salary, it must be only digits or include a '.'");
		        return;
		    }
		}else {
			AlertMessage.showInformationMessage("You must enter the salary");
			return;
		}
		String address = newemployee_address_textfield.getText();
		if(address.isBlank()) {
			AlertMessage.showInformationMessage("You must enter the address");
			return;
		}
		String gender = newemployee_gender_choicebox.getValue();
//		if(gender.isBlank()) {
//			AlertMessage.showInformationMessage("You must enter the gender");
//			return;
//		}
		Employee e = new Employee(name, gender, dob, phone, address, idcard, salary, "active"); 
		boolean add = EmployeeDAO.getInstance().insert(e);
		if(add) {
			AlertMessage.showSuccessMessage("Successfully add new employee!");
			Stage currentStage = (Stage) add_newemployee_btn.getScene().getWindow(); 
            currentStage.close(); 
		}else {
			AlertMessage.showErrorMessage("Cannot add new employee");
		}
	}
	
	
}
