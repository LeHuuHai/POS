package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import application.authentication.AlertMessage;
import application.dao.BillDAO;
import application.dao.BillItemDAO;
import application.dao.ItemDAO;
import application.model.Bill;
import application.model.BillItem;
import application.model.Item;
import application.model.ObjectOfBill;
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
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class StaffOrderController {
	@FXML
	private Button home_nav;
	
	@FXML
	private Button checkin_nav;
	
	@FXML
	private Button checkout_nav;

    @FXML
    private VBox item_vbox;

    @FXML
    private Button coffee_btn;

    @FXML
    private Button tea_btn;

    @FXML
    private Button cake_btn;

    @FXML
    private Button all_btn;
    
    @FXML
	private TableView<ObjectOfBill> Bill_tableview;
	
	@FXML
    private TableColumn<ObjectOfBill, String> Bill_nameitem_col;
	
	@FXML
    private TableColumn<ObjectOfBill, Integer> Bill_quantity_col;
	
	@FXML
    private TableColumn<ObjectOfBill, Double> Bill_price_col;
	
	@FXML 
	private Button Bill_remove_btn;
	
	@FXML 
	private Button Bill_pay_btn;
	
	@FXML 
	private Label bill_subtotal_label;
	
	@FXML
	private Label bill_total_label;
	
	@FXML
	private Label bill_vat_label;
	
	@FXML
	private TextField bill_discount_textfield;
	
//	đối tượng static để controller khác gọi đến, nhằm thay đổi đối tượng bên trong(trong th này là tableview từ OrderItemController)
	public static StaffOrderController instance;
	
	public void updateTableView(@SuppressWarnings("exports") ObjectOfBill o) {
		ObservableList<ObjectOfBill> oList = Bill_tableview.getItems();
	    int id = o.getId_item();
	    Iterator<ObjectOfBill> iterator = oList.iterator();
	    while (iterator.hasNext()) {
	        ObjectOfBill e = iterator.next();
	        if (e.getId_item()==id) {
	            iterator.remove();
	            break;
	        }
	    }
	    if (o.getQuantity() != 0) {
	        oList.add(o);
	    } 
	    
	    double subtotal = 0;
	    for(ObjectOfBill e : oList) {
	        subtotal += e.getPrice();
	    }
	    Bill_tableview.setItems(oList);
	    bill_subtotal_label.setText(String.format("%.3f", subtotal));
	    double discount = Double.parseDouble(bill_discount_textfield.getText());
	    double vat = Double.parseDouble(bill_vat_label.getText());
	    double total = subtotal * (100+vat) * (100-discount) / 10000;
	    bill_total_label.setText(String.format("%.3f", total));
//	    Bill_tableview.refresh();
	}
	
	public void changeDiscount(@SuppressWarnings("exports") Event event) {
		double subtotal = Double.parseDouble(bill_subtotal_label.getText());
		double discount = Double.parseDouble(bill_discount_textfield.getText());
	    double vat = Double.parseDouble(bill_vat_label.getText());
	    double total = subtotal * (100+vat) * (100-discount) / 10000;
	    bill_total_label.setText(String.format("%.3f", total));
	}
	
	
	@FXML
    public void initialize() throws IOException {
//		khi FXML được load, JavaFX tạo một instance của controller được chỉ định trong tệp FXML và sau đó gọi phương thức initialize() của controller đó.
//		khi được khởi tạo, biến instance sẽ lưu lại controller này làm static, các controller khác sẽ gọi tới controller này thông qua biến instance
		instance = this;
		
    	clickBtnAll(null);
        
    	Bill_nameitem_col.setCellValueFactory(new PropertyValueFactory<ObjectOfBill, String>("name_item"));
    	Bill_quantity_col.setCellValueFactory(new PropertyValueFactory<ObjectOfBill, Integer>("quantity"));
    	Bill_price_col.setCellValueFactory(new PropertyValueFactory<ObjectOfBill, Double>("price"));
    	
    	ObservableList<ObjectOfBill> list = FXCollections.observableArrayList();
    	Bill_tableview.setItems(list);

    }
	
	@FXML
    public void clickBtnHomeNav(@SuppressWarnings("exports") Event event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/LoginView.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Login");
            stage.setScene(new Scene(root));
            stage.show();
           
            Stage currentStage = (Stage) home_nav.getScene().getWindow(); // Get the current stage
            currentStage.close(); // Close the current stage
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
	@FXML
    public void clickBtnCheckInNav(@SuppressWarnings("exports") Event event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/CheckInView.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("CheckIn");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
	@FXML
    public void clickBtnCheckOutNav(@SuppressWarnings("exports") Event event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/CheckOutView.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("CheckOut");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
	public void clickBtnAll(@SuppressWarnings("exports") Event event) throws IOException{
		ArrayList<Item> li = ItemDAO.getInstance().selectAllActive();
		refreshMenuBox(li);
	}
	
	public void clickBtnCoffee(@SuppressWarnings("exports") Event event) throws IOException{
		ArrayList<Item> li = ItemDAO.getInstance().selectTypeActive("Coffee");
		refreshMenuBox(li);
	}
	
	public void clickBtnTea(@SuppressWarnings("exports") Event event) throws IOException{
		ArrayList<Item> li = ItemDAO.getInstance().selectTypeActive("Tea");
		refreshMenuBox(li);
	}
	
	public void clickBtnCake(@SuppressWarnings("exports") Event event) throws IOException{
		ArrayList<Item> li = ItemDAO.getInstance().selectTypeActive("Cake");
		refreshMenuBox(li);
	}
	
	public void refreshMenuBox(@SuppressWarnings("exports") ArrayList<Item> li) throws IOException {
		item_vbox.getChildren().clear();
    	int index = 0;
    	HBox currenthbox = null;
    	for(Item i : li) {
    		if(index % 3 == 0) {
    			HBox newHBox = new HBox();
    			newHBox.setPrefHeight(250);
    			newHBox.setPrefWidth(200);
    			item_vbox.getChildren().add(newHBox);
    			currenthbox = newHBox;
    		}
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ItemBoxView.fxml"));
            VBox itembox = loader.load();
            ItemBoxController controller = loader.getController();
            controller.initialize(i);
            currenthbox.getChildren().add(itembox);
            index+=1;
    	}

	}
	
	public void clickBtnRemoveBill(@SuppressWarnings("exports") Event event) {
		refreshBill();
	}
	
	public void clickBtnPayBill(@SuppressWarnings("exports") Event event) {
		double discount = Double.parseDouble(bill_discount_textfield.getText());
	    double vat = Double.parseDouble(bill_vat_label.getText());
	    double total = Double.parseDouble(bill_total_label.getText());
	    Bill b = new Bill(discount, vat, total);
	    
	    int id_bill = BillDAO.getInstance().insert(b);
	    
	    ObservableList<ObjectOfBill> oList = Bill_tableview.getItems();
	    for(ObjectOfBill e : oList) {
	    	BillItem bi = new BillItem(id_bill, e.getId_item(), e.getQuantity());
	    	BillItemDAO.getInstance().insert(bi);
	    }
		AlertMessage.showInformationMessage("Payment Successful");
		refreshBill();
	}
	
	public void refreshBill() {
		Bill_tableview.setItems(FXCollections.observableArrayList());
		bill_subtotal_label.setText("0");
		bill_discount_textfield.setText("0");
		bill_total_label.setText("0");
	}
}
