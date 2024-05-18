package controller;

import java.io.IOException;
import application.dao.ItemDAO;
import application.model.Item;
import application.model.ObjectOfBill;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.stage.Stage;

public class OrderItemController {
	@FXML
	Label orderitem_name_label;
	
	@FXML
	Label orderitem_price_label;
	
	@FXML
	Button orderitem_add_btn;
	
	@FXML
	Spinner<Integer> orderitem_quantity_spinner;
	
	@FXML
	Label orderitem_id_label;
	
	public void initialize(@SuppressWarnings("exports") Item i) {
		orderitem_name_label.setText(i.getName());
		orderitem_price_label.setText(String.valueOf(i.getPrice()) + "$");
		orderitem_id_label.setText(String.valueOf(i.getId()));
	}
	
	public void clickAddItemOrder(@SuppressWarnings("exports") Event event) throws IOException {
	    int id_item = Integer.parseInt(orderitem_id_label.getText());
	    Item i = ItemDAO.getInstance().selectById(new Item(id_item));
	    String name_item = i.getName();
	    int quantity = orderitem_quantity_spinner.getValue();
	    double price = i.getPrice() * quantity;
	    ObjectOfBill o = new ObjectOfBill(id_item, name_item, quantity, price);
	    
//	    vì StaffOrderView đã được gọi trước đó, tức là đã có controller, ta không thể dùng các new FXMLLoader vì nó sẽ tạo 1 đối tượng StaffOrderController mới,
//	    cái mà không chứa table view ta muốn thay đổi, giải pháp là dùng static
	    StaffOrderController controller = StaffOrderController.instance;
        if (controller != null) {
            controller.updateTableView(o);
        }
        Stage currentStage = (Stage) orderitem_add_btn.getScene().getWindow(); // Get the current stage
        currentStage.close(); // Close the current stage
	}
}
