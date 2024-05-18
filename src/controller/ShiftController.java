package controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import application.dao.ShiftDAO;
import application.model.Shift;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ShiftController extends AdMenuController{
	
	@FXML
	private TableView<Shift> shift_table;
	
	@FXML
	private TableColumn<Shift, Integer> shift_id_col;
	
	@FXML
	private TableColumn<Shift, Integer> shift_idemployee_col;
	
	@FXML
	private TableColumn<Shift, String> shift_time_col;
	
	@FXML
	private TableColumn<Shift, String> shift_infor_col;
	
	@FXML
    private DatePicker shift_fromtime_datePicker;
	
	@FXML
    private DatePicker shift_totime_datePicker;
	
    @FXML
    private Button shift_search_btn;
    
	@FXML
    private void initialize() {
		ArrayList<Shift> list = ShiftDAO.getInstance().selectAll();
        ObservableList<Shift> shift_table_list = FXCollections.observableArrayList(list);
        shift_id_col.setCellValueFactory(new PropertyValueFactory<Shift, Integer>("id_shift"));
        shift_idemployee_col.setCellValueFactory(new PropertyValueFactory<Shift, Integer>("id_employee"));
        shift_time_col.setCellValueFactory(new PropertyValueFactory<Shift, String>("time"));
        shift_infor_col.setCellValueFactory(new PropertyValueFactory<Shift, String>("infor"));
        shift_table.setItems(shift_table_list);
    }
	
	public void clickBtnSearchShift(@SuppressWarnings("exports") Event event) {
		DateTimeFormatter Formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		
		String fromDate = "1900-01-01";
		LocalDate currentDate = LocalDate.now();
		String toDate = currentDate.format(Formatter);
		
		LocalDate from = shift_fromtime_datePicker.getValue();
		if(from!=null) {
		    fromDate = from.format(Formatter);
		}
		LocalDate to = shift_totime_datePicker.getValue();
		if(to!=null) {
		    toDate = to.format(Formatter);
		}
		
		ArrayList<Shift> alist = ShiftDAO.getInstance().selectPeriod(fromDate, toDate);
		ObservableList<Shift> olist = FXCollections.observableArrayList(alist);
		shift_table.setItems(olist);
	}
}
