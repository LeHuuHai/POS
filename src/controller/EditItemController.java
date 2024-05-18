package controller;

import application.authentication.AlertMessage;
import application.dao.ItemDAO;
import application.model.Item;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EditItemController {
	private int id_item;
	
	@FXML
	private TextField edititem_name_textfield;
	
	@FXML
	private TextField edititem_price_textfield;
	
	@FXML
	private TextField edititem_imgpath_textfield;
	
	@FXML
	private ChoiceBox<String> edititem_type_choicebox;
	
	@FXML
	private Button save_edititem_btn;
	
	@FXML
	private Button delete_edititem_btn;
	
	public void initialize(@SuppressWarnings("exports") Item t) {
		ObservableList<String> options = FXCollections.observableArrayList("Coffee", "Tea", "Cake");
        edititem_type_choicebox.setValue(t.getType());
        edititem_type_choicebox.setItems(options); 
        edititem_name_textfield.setText(t.getName()); 
        edititem_price_textfield.setText(String.valueOf(t.getPrice())); 
        edititem_imgpath_textfield.setText(t.getImg_path());
        id_item = t.getId();
	}
	
	public void clickBtnSaveEditItem() {
		String name = edititem_name_textfield.getText();
		if(name.isBlank()) {
			AlertMessage.showInformationMessage("You must enter the name");
			return;
		}
		double price = 0.0;
		if(edititem_price_textfield.getText()!=null) {
			if (edititem_price_textfield.getText().matches("^\\d*\\.?\\d+$")) {
				price = Double.parseDouble(edititem_price_textfield.getText());
		    } else {
		        AlertMessage.showInformationMessage("You must enter a valid price, it must be only digits or include a '.'");
		        return;
		    }
		}else {
			AlertMessage.showInformationMessage("You must enter the price");
			return;
		}
		String img_path = edititem_imgpath_textfield.getText();
		String type = edititem_type_choicebox.getValue();
//		if(type.isBlank()) {
//			AlertMessage.showInformationMessage("You must enter the type");
//			return;
//		}
		Item i = new Item(id_item, name, price, type, img_path, "active");
		boolean edit = ItemDAO.getInstance().update(i);
		if(edit) {
			AlertMessage.showSuccessMessage("Successfully edit this item!");
			Stage currentStage = (Stage) save_edititem_btn.getScene().getWindow(); 
            currentStage.close(); 
		}else {
			AlertMessage.showErrorMessage("Cannot edit this item");
		}
	}
	
	public void clickBtnDeleteEditItem() {
    	Item e = new Item(id_item); 
    	boolean delete = ItemDAO.getInstance().delete(e);
    	if(delete) {
			AlertMessage.showSuccessMessage("Successfully delete this item!");
			Stage currentStage = (Stage) delete_edititem_btn.getScene().getWindow(); 
            currentStage.close(); 
		}else {
			AlertMessage.showErrorMessage("Cannot delete this item");
		}
    }
}
