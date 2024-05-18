package controller;

import java.util.ArrayList;

import application.dao.BillItemDAO;
import application.model.Bill;
import application.model.BillItem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class BillItemController {
	@FXML
    private Label billitem_id_label;

    @FXML
    private Label billitem_vat_label;

    @FXML
    private Label billitem_discount_label;

    @FXML
    private Label billitem_subtotal_label;

    @FXML
    private Label billitem_timetrans_label;

    @FXML
    private TableColumn<BillItem, Integer> billitem_quantity_col;

    @FXML
    private Label billitem_total_label;

    @FXML
    private TableColumn<BillItem, String> billitem_name_col;

    @FXML
    private TableColumn<BillItem, Double> billitem_price_column;

    @FXML
    private TableView<BillItem> billitem_tableview;

    @FXML
    private TableColumn<BillItem, Integer> billitem_id_col;
    
    public void initialize(Bill b) {
//    	billitem_id_label.setText(String.valueOf(b.getId()));
//    	billitem_timetrans_label.setText(b.getTime());
//    	ArrayList<BillItem> alist = BillItemDAO.getInstance().selectById(b.getId());
//    	ObservableList<BillItem> olist = FXCollections.observableArrayList(alist);
//    	billitem_id_col.setCellValueFactory(new PropertyValueFactory<Bill, Integer>("id"));
//    	billitem_tableview.setItems(olist);
    }
}
