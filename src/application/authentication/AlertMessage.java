package application.authentication;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class AlertMessage {
	 public static void showErrorMessage(String message) {
	        Alert alert = new Alert(AlertType.ERROR);
	        alert.setTitle("Error message");
	        alert.setHeaderText("Error");
	        alert.setContentText(message);
	        alert.showAndWait();
	    }

	    public static void showSuccessMessage(String message) {
	        Alert alert = new Alert(AlertType.INFORMATION);
	        alert.setTitle("Success message");
	        alert.setHeaderText("Success");
	        alert.setContentText(message);
	        alert.showAndWait();
	    }

	    public static void showInformationMessage(String message) {
	        Alert alert = new Alert(AlertType.INFORMATION);
	        alert.setTitle("Information message");
	        alert.setHeaderText("Information");
	        alert.setContentText(message);
	        alert.showAndWait();
	    }
}
