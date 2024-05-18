package controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import application.authentication.AlertMessage;
import application.dao.EmployeeDAO;
import application.dao.ShiftDAO;
import application.model.Employee;
import application.model.Shift;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CheckOutController {
	private boolean isFormValid = false;

    @FXML
    private TextField checkout_idemployee_textfield;

    @FXML
    private Label checkout_time_label;

    @FXML
    private Button checkout_btn;

    @FXML
    private Label checkout_name_label;

    @FXML
    public void initialize() {
    	 LocalDateTime currentDateTime = LocalDateTime.now();
         DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
         String time = currentDateTime.format(formatter);
         checkout_time_label.setText(time);
    }
    
    public void changeIdEmployee() {
    	checkout_name_label.setText("");
    	int id = 0;
    	try {
    		id = Integer.parseInt(checkout_idemployee_textfield.getText());
    	}catch( Exception e){
    		AlertMessage.showInformationMessage("ID must be Integer and greatter than zero");
    		isFormValid = false;
    		return;
    	}
    	Employee e = EmployeeDAO.getInstance().selectById(new Employee(id));
    	if(e==null) {
    		AlertMessage.showErrorMessage("Cannot found this id");
    		isFormValid = false;
    		return;
    	}else {
    		checkout_name_label.setText(e.getName());
    		isFormValid = true;
    	}
    }
    
    public void clickBtnCheckOut(@SuppressWarnings("exports") Event event) {
    	if(!isFormValid) {
    		AlertMessage.showErrorMessage("ID is not valid, check out is failure");
    		return;
    	}
    	int id = Integer.parseInt(checkout_idemployee_textfield.getText());
    	String time = checkout_time_label.getText();
    	Shift s = new Shift(id, time, "Check Out");
    	boolean ans = ShiftDAO.getInstance().insert(s);
    	if(ans) {
    		AlertMessage.showSuccessMessage("Check out is successful");
    	}else {
    		AlertMessage.showErrorMessage("Your id are not checked in or have been deactivated, check out is failure");
    	}
    	 Stage currentStage = (Stage) checkout_btn.getScene().getWindow(); 
         currentStage.close(); 
    }
}
