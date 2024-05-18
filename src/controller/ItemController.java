package controller;

import java.util.ArrayList;

import application.dao.ItemDAO;
import application.model.Item;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class ItemController extends AdMenuController {
	
	@FXML 
	private Button new_item_btn;
	
	@FXML
	private TableView<Item> item_table;
	
	@FXML
	private TableColumn<Item, Integer> item_id_col;
	
	@FXML
	private TableColumn<Item, String> item_name_col;
	
	@FXML
	private TableColumn<Item, String> item_type_col;
	
	@FXML
	private TableColumn<Item, Double> item_price_col;
	
	@FXML
	private TableColumn<Item, String> item_imgpath_col;
	
	@FXML
	private TableColumn<Item, String> item_state_col;
	
	@FXML
	private Label total_item;
	
	@FXML
	private Label active_item;
	
	@FXML
	private Label deactive_item;
	
	@FXML
    private void initialize() {
		ArrayList<Item> list = ItemDAO.getInstance().selectAll();
        ObservableList<Item> item_table_list = FXCollections.observableArrayList(list);
        item_id_col.setCellValueFactory(new PropertyValueFactory<Item, Integer>("id"));
        item_name_col.setCellValueFactory(new PropertyValueFactory<Item, String>("name"));
        item_type_col.setCellValueFactory(new PropertyValueFactory<Item, String>("type"));
        item_price_col.setCellValueFactory(new PropertyValueFactory<Item, Double>("price"));
        item_imgpath_col.setCellValueFactory(new PropertyValueFactory<Item, String>("img_path"));
        item_state_col.setCellValueFactory(new PropertyValueFactory<Item, String>("state"));
        item_table.setItems(item_table_list);
        
        int total = list.size(); 
        int active = 0;
        for(Item e : list) {
        	if(e.getState().equals("active")) {
        		active += 1;
        	}
        }
        int deactive = total - active;
        total_item.setText(String.valueOf(total));
        active_item.setText(String.valueOf(active));
        deactive_item.setText(String.valueOf(deactive));
        
     // Tạo một RowFactory tùy chỉnh để thiết lập sự kiện cho từng dòng
        item_table.setRowFactory(tv -> {
            TableRow<Item> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && !row.isEmpty()) {
                    Item t = row.getItem();
                    try {
        	            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/EditItemView.fxml"));
        	            Parent root = loader.load();
        	            EditItemController controller = loader.getController();
        	            controller.initialize(t);
        	            Stage stage = new Stage();
        	            stage.setTitle("EditItemSpace");
        	            stage.setScene(new Scene(root));
        	            stage.show();
        	        } catch (Exception e) {
        	            e.printStackTrace();
        	        }
                }
            });
            return row;
        });
    }
	
	public void clickBtnNewItem(@SuppressWarnings("exports") Event event) {
		 try {
	            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/NewItemView.fxml"));
	            Parent root = loader.load();
	            Stage stage = new Stage();
	            stage.setTitle("NewItemSpace");
	            stage.setScene(new Scene(root));
	            stage.show();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	}
}
