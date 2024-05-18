
package controller;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public class LoginController {
	
	
    @FXML
    private Button login_staff_btn;

    @FXML
    private Button login_ad_btn;
    
    @FXML
    private void initialize() {
    	
    }

    @FXML
    public void clickBtnLoginStaff(@SuppressWarnings("exports") Event event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/StaffOrderView.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("StaffSpace");
            stage.setScene(new Scene(root));
            stage.show();
           
            Stage currentStage = (Stage) login_staff_btn.getScene().getWindow(); // Get the current stage
            currentStage.close(); // Close the current stage
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    public void clickBtnLoginAd(@SuppressWarnings("exports") Event event) {
    	//vertify
    	
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/EmployeeView.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("StaffSpace");
            stage.setScene(new Scene(root));
            stage.show();
           
            Stage currentStage = (Stage) login_ad_btn.getScene().getWindow(); // Get the current stage
            currentStage.close(); // Close the current stage
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
