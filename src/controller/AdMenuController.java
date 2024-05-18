package controller;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class AdMenuController {
	@FXML
	private Button employee_nav;
	
	@FXML
	private Button item_nav;
	
	@FXML
	private Button revenue_nav;
	
	@FXML
	private Button payroll_nav;
	
	@FXML
	private Button shift_nav;
	
	@FXML
	private Button logout_btn;
	
	@FXML
    public void clickEmployeeNav(@SuppressWarnings("exports") Event event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/EmployeeView.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("EmployeeSpace");
            stage.setScene(new Scene(root));
            		
            stage.show();
           
            Stage currentStage = (Stage) employee_nav.getScene().getWindow(); // Get the current stage
            currentStage.close(); // Close the current stage
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
	@FXML
    public void clickItemNav(@SuppressWarnings("exports") Event event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ItemView.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("ItemSpace");
            stage.setScene(new Scene(root));
            stage.show();
           
            Stage currentStage = (Stage) item_nav.getScene().getWindow(); // Get the current stage
            currentStage.close(); // Close the current stage
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
	@FXML
    public void clickRevenueNav(@SuppressWarnings("exports") Event event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/RevenueView.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("RevenueSpace");
            stage.setScene(new Scene(root));
            stage.show();
           
            Stage currentStage = (Stage) revenue_nav.getScene().getWindow(); // Get the current stage
            currentStage.close(); // Close the current stage
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
	@FXML
    public void clickPayrollNav(@SuppressWarnings("exports") Event event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/PayrollView.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("PayrollSpace");
            stage.setScene(new Scene(root));
            stage.show();
           
            Stage currentStage = (Stage) payroll_nav.getScene().getWindow(); // Get the current stage
            currentStage.close(); // Close the current stage
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
	@FXML
    public void clickShiftNav(@SuppressWarnings("exports") Event event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ShiftView.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("ShiftSpace");
            stage.setScene(new Scene(root));
            stage.show();
           
            Stage currentStage = (Stage) shift_nav.getScene().getWindow(); // Get the current stage
            currentStage.close(); // Close the current stage
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
	@FXML
    public void clickLogoutBtn(@SuppressWarnings("exports") Event event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/LoginView.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Login");
            stage.setScene(new Scene(root));
            stage.show();
           
            Stage currentStage = (Stage) logout_btn.getScene().getWindow(); // Get the current stage
            currentStage.close(); // Close the current stage
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
