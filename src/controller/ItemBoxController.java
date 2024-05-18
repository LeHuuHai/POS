package controller;

import application.dao.ItemDAO;
import application.model.Item;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ItemBoxController {
    @FXML
    private Label nameitem_label;

    @FXML
    private Label priceitem_label;

    @FXML
    private ImageView imgitem_view;
    
    @FXML
    private Label iditem_label;
    
    public void initialize(@SuppressWarnings("exports") Item i) {
    	try {
	        Image img = new Image(getClass().getResourceAsStream(i.getImg_path()));
	        imgitem_view.setImage(img);
    	}catch(Exception e) {
    		Image img = null;
    		if(i.getType().equals("Coffee")) {
    			img = new Image(getClass().getResourceAsStream("/assets/item_img/default_coffee.jpg"));
    		}else if(i.getType().equals("Tea")) {
    			img = new Image(getClass().getResourceAsStream("/assets/item_img/default_tea.jpg"));
    		}else {  				//Cake
    			img = new Image(getClass().getResourceAsStream("/assets/item_img/default_cake.jpg"));
    		}
	        imgitem_view.setImage(img);
    	}
        nameitem_label.setText(i.getName());
        priceitem_label.setText(String.valueOf(i.getPrice()) + " $");
        iditem_label.setText(String.valueOf(i.getId()));
    }
    
    public void clickItemBox(@SuppressWarnings("exports") Event event) {
    	try {
//    		lấy thông tin đối tượng đã chọn
//    		lấy VBox đã click(đối tượng này được gán cho event)
    		VBox clickedVBox = (VBox) event.getSource();
//    		lấy trường id (trường con của VBox) có fx:id là "iditem_label", cái này để mình lưu id của item được thể hiện ở vbox
    		Label idLabel = (Label) clickedVBox.lookup("#iditem_label");
    		int id = Integer.parseInt(idLabel.getText());
    		Item i = ItemDAO.getInstance().selectById(new Item(id));
    		
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/OrderItemView.fxml"));
            Parent root = loader.load();
            OrderItemController controller = loader.getController();
            controller.initialize(i);
            Stage stage = new Stage();
            stage.setTitle("");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
