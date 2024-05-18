package controller;

import java.util.ArrayList;

import application.dao.BillDAO;
import application.model.Bill;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class RevenueController extends AdMenuController {

    @FXML
    private Button shift_nav;

    @FXML
    private Button item_nav;

    @FXML
    private Button revenue_nav;

    @FXML
    private DatePicker revenue_fromtime_datePicker;

    @FXML
    private Label revenue_total_label;

    @FXML
    private TableColumn<Bill, Double> revenue_total_col;

    @FXML
    private DatePicker revenue_totime_datePicker;

    @FXML
    private Button logout_btn;

    @FXML
    private TableView<Bill> revenue_table;

    @FXML
    private TableColumn<Bill, Double> revenue_discount_col;

    @FXML
    private TableColumn<Bill, Double> revenue_vat_col;

    @FXML
    private Button employee_nav;

    @FXML
    private Button payroll_nav;

    @FXML
    private TableColumn<Bill, Integer> revenue_id_col;

    @FXML
    private TableColumn<Bill, String> revenue_timetrans_col;

    @FXML
    private Button revenue_search_btn;

    @FXML
    private void initialize() {
        ArrayList<Bill> alist = BillDAO.getInstance().selectAll();
        ObservableList<Bill> olist = FXCollections.observableArrayList(alist);
        revenue_id_col.setCellValueFactory(new PropertyValueFactory<Bill, Integer>("id"));
        revenue_discount_col.setCellValueFactory(new PropertyValueFactory<Bill, Double>("discount"));
        revenue_vat_col.setCellValueFactory(new PropertyValueFactory<Bill, Double>("VAT"));
        revenue_timetrans_col.setCellValueFactory(new PropertyValueFactory<Bill, String>("time"));
        revenue_total_col.setCellValueFactory(new PropertyValueFactory<Bill, Double>("total"));
        revenue_table.setItems(olist);
        
        double total = 0.0; 
        for(Bill b : olist) {
        	total += b.getTotal();
        }
        revenue_total_label.setText(String.format("%.3f", total));

        // Tạo một RowFactory tùy chỉnh để thiết lập sự kiện cho từng dòng
        revenue_table.setRowFactory(tv -> {
            TableRow<Bill> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && !row.isEmpty()) {
                    Bill b = row.getItem();
                    try {
        	            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/BillItemView.fxml"));
        	            Parent root = loader.load();
        	            BillItemController controller = loader.getController();
        	            controller.initialize(b);
        	            Stage stage = new Stage();
        	            stage.setTitle("BillItemSpace");
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

}
