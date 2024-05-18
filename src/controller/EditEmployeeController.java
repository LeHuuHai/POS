package controller;

import application.authentication.AlertMessage;
import application.dao.EmployeeDAO;
import application.model.Employee;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EditEmployeeController {
	private int id_employee;
	
	@FXML
	private TextField editemployee_name_textfield;
	
	@FXML
	private TextField editemployee_dob_textfield;
	
	@FXML
	private TextField editemployee_phone_textfield;
	
	@FXML
	private TextField editemployee_idcard_textfield;
	
	@FXML
	private TextField editemployee_salary_textfield;
	
	@FXML
	private TextField editemployee_address_textfield;
	
	@FXML
	private ChoiceBox<String> editemployee_gender_choicebox;
	
	@FXML
	private Button save_editemployee_btn;
	
	@FXML
	private Button delete_editemployee_btn;

    public void initialize(@SuppressWarnings("exports") Employee t) {
		ObservableList<String> options = FXCollections.observableArrayList("Nam", "Nữ");
        editemployee_gender_choicebox.setValue(t.getGender()); // this statement shows the default value
        editemployee_gender_choicebox.setItems(options); // this statement adds all values to the ChoiceBox
        editemployee_name_textfield.setText(t.getName());
        editemployee_dob_textfield.setText(t.getDob());
        editemployee_phone_textfield.setText(t.getPhone());
        editemployee_idcard_textfield.setText(t.getIdcard());
        editemployee_salary_textfield.setText(String.valueOf(t.getSalary()));
        editemployee_address_textfield.setText(t.getAddress());
        id_employee = t.getId();
	}
    
    public void clickBtnSaveEditEmployee() {
    	String name = editemployee_name_textfield.getText();
		if(name.isBlank()) {
			AlertMessage.showInformationMessage("You must enter the name");
			return;
		}
		String phone = editemployee_phone_textfield.getText();
		if(phone.isBlank() || phone.length()!=10 || !phone.matches("[0-9]+")) {
			AlertMessage.showInformationMessage("phone must be 10 characters and only digit");
			return;
		}
		String dob = editemployee_dob_textfield.getText();
		if(dob.isBlank()) {
			AlertMessage.showInformationMessage("Incorrect form of dob value");
			return;
		}
		String idcard = editemployee_idcard_textfield.getText();
		if(idcard.isBlank() || idcard.length()!=12 || !idcard.matches("[0-9]+")) {
			AlertMessage.showInformationMessage("idcard must be 12 characters and only digits");
			return;
		}
		double salary = 0.0;
		if(editemployee_salary_textfield.getText()!=null) {
			if (editemployee_salary_textfield.getText().matches("^\\d*\\.?\\d+$")) {
				salary = Double.parseDouble(editemployee_salary_textfield.getText());
		    } else {
		        AlertMessage.showInformationMessage("You must enter a valid salary, it must be only digits or include a '.'");
		        return;
		    }
		}else {
			AlertMessage.showInformationMessage("You must enter the salary");
			return;
		}
		String address = editemployee_address_textfield.getText();
		if(address.isBlank()) {
			AlertMessage.showInformationMessage("You must enter the address");
			return;
		}
		String gender = editemployee_gender_choicebox.getValue();
//		if(gender.isBlank()) {
//			AlertMessage.showInformationMessage("You must enter the gender");
//			return;
//		}
		Employee e = new Employee(id_employee, name, gender, dob, phone, address, idcard, salary, "active"); 
		boolean edit = EmployeeDAO.getInstance().update(e);
		if(edit) {
			AlertMessage.showSuccessMessage("Successfully edit this employee!");
			Stage currentStage = (Stage) save_editemployee_btn.getScene().getWindow(); 
            currentStage.close(); 
		}else {
			AlertMessage.showErrorMessage("Cannot edit this employee");
		}
    }
    
    public void clickBtnDeleteEditEmployee() {
    	Employee e = new Employee(id_employee); 
    	boolean delete = EmployeeDAO.getInstance().delete(e);
    	if(delete) {
			AlertMessage.showSuccessMessage("Successfully delete this employee!");
			Stage currentStage = (Stage) save_editemployee_btn.getScene().getWindow(); 
            currentStage.close(); 
		}else {
			AlertMessage.showErrorMessage("Cannot delete this employee");
		}
    }
}
