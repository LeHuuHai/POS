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

public class CheckInController {
	private boolean isFormValid = false;

    @FXML
    private TextField checkin_idemployee_textfield;

    @FXML
    private Label checkin_time_label;

    @FXML
    private Button checkin_btn;

    @FXML
    private Label checkin_name_label;

    @FXML
    public void initialize() {
    	 LocalDateTime currentDateTime = LocalDateTime.now();
         DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
         String time = currentDateTime.format(formatter);
         checkin_time_label.setText(time);
    }
    
    public void changeIdEmployee() {
    	checkin_name_label.setText("");
    	int id = 0;
    	try {
    		id = Integer.parseInt(checkin_idemployee_textfield.getText());
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
    		checkin_name_label.setText(e.getName());
    		isFormValid = true;
    	}
    }
    
    public void clickBtnCheckIn(@SuppressWarnings("exports") Event event) {
    	if(!isFormValid) {
    		AlertMessage.showErrorMessage("ID is not valid, check in is failure");
    		return;
    	}
    	int id = Integer.parseInt(checkin_idemployee_textfield.getText());
    	String time = checkin_time_label.getText();
    	Shift s = new Shift(id, time, "Check In");
    	boolean ans = ShiftDAO.getInstance().insert(s);
    	if(ans) {
    		AlertMessage.showSuccessMessage("Check in is successful");
    	}else {
    		AlertMessage.showErrorMessage("Your id are not checked out or have been deactivated, check in is failure");
    	}
    	 Stage currentStage = (Stage) checkin_btn.getScene().getWindow(); 
         currentStage.close(); 
    }
}
