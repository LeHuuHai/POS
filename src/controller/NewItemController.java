package controller;

import application.authentication.AlertMessage;
import application.dao.ItemDAO;
import application.model.Item;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class NewItemController {
	@FXML
	private ChoiceBox<String> newitem_type_choicebox;
	
	@FXML
	private TextField newitem_name_textfield;
	
	@FXML
	private TextField newitem_price_textfield;
	
	@FXML
	private TextField newitem_imgpath_textfield;
	
	@FXML
	private Button add_newitem_btn;

	public void initialize() {
        ObservableList<String> options = FXCollections.observableArrayList("Coffee", "Tea", "Cake");
        newitem_type_choicebox.setValue("Coffee");
        newitem_type_choicebox.setItems(options); 
	}
	
	public void clickBtnNewItem(@SuppressWarnings("exports") Event event) {
		String name = newitem_name_textfield.getText();
		if(name.isBlank()) {
			AlertMessage.showInformationMessage("You must enter the name");
			return;
		}
		double price = 0.0;
		if(newitem_price_textfield.getText()!=null) {
			if (newitem_price_textfield.getText().matches("^\\d*\\.?\\d+$")) {
				price = Double.parseDouble(newitem_price_textfield.getText());
		    } else {
		        AlertMessage.showInformationMessage("You must enter a valid price, it must be only digits or include a '.'");
		        return;
		    }
		}else {
			AlertMessage.showInformationMessage("You must enter the price");
			return;
		}
		String img_path = newitem_imgpath_textfield.getText();
		String type = newitem_type_choicebox.getValue();
//		if(type.isBlank()) {
//			AlertMessage.showInformationMessage("You must enter the type");
//			return;
//		}
		Item i = new Item(name, price, type, img_path, "active");
		boolean add = ItemDAO.getInstance().insert(i);
		if(add) {
			AlertMessage.showSuccessMessage("Successfully add new item!");
			Stage currentStage = (Stage) add_newitem_btn.getScene().getWindow(); 
            currentStage.close(); 
		}else {
			AlertMessage.showErrorMessage("Cannot add new item");
		}
	}
}
