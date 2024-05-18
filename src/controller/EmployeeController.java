
package controller;

import java.util.ArrayList;
import application.dao.EmployeeDAO;
import application.model.Employee;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class EmployeeController extends AdMenuController{
	
	@FXML
	private TableView<Employee> employee_table;
	
	@FXML
	private TableColumn<Employee, Integer> employee_id_col;
	
	@FXML
	private TableColumn<Employee, String> employee_name_col;
	
	@FXML
	private TableColumn<Employee, String> employee_gender_col;
	
	@FXML
	private TableColumn<Employee, String> employee_dob_col;
	
	@FXML
	private TableColumn<Employee, String> employee_phone_col;
	
	@FXML
	private TableColumn<Employee, String> employee_address_col;
	
	@FXML
	private TableColumn<Employee, String> employee_idcard_col;
	
	@FXML
	private TableColumn<Employee, Double> employee_salary_col;
	
	@FXML
	private TableColumn<Employee, String> employee_state_col;
	
	@FXML 
	private Label total_employee;
	
	@FXML 
	private Label active_employee;
	
	@FXML 
	private Label deactive_employee;
	
	@FXML 
	private Button new_employee_btn;
	
	@FXML
    private void initialize() {
        ArrayList<Employee> list = EmployeeDAO.getInstance().selectAll();
        ObservableList<Employee> employee_table_list = FXCollections.observableArrayList(list);
        employee_id_col.setCellValueFactory(new PropertyValueFactory<Employee, Integer>("id"));
        employee_name_col.setCellValueFactory(new PropertyValueFactory<Employee, String>("name"));
        employee_gender_col.setCellValueFactory(new PropertyValueFactory<Employee, String>("gender"));
        employee_dob_col.setCellValueFactory(new PropertyValueFactory<Employee, String>("dob"));
        employee_phone_col.setCellValueFactory(new PropertyValueFactory<Employee, String>("phone"));
        employee_address_col.setCellValueFactory(new PropertyValueFactory<Employee, String>("address"));
        employee_idcard_col.setCellValueFactory(new PropertyValueFactory<Employee, String>("idcard"));
        employee_salary_col.setCellValueFactory(new PropertyValueFactory<Employee, Double>("salary"));
        employee_state_col.setCellValueFactory(new PropertyValueFactory<Employee, String>("state"));
        employee_table.setItems(employee_table_list);
        
        int total = list.size(); 
        int active = 0;
        for(Employee e : list) {
        	if(e.getState().equals("active")) {
        		active += 1;
        	}
        }
        int deactive = total - active;
        total_employee.setText(String.valueOf(total));
        active_employee.setText(String.valueOf(active));
        deactive_employee.setText(String.valueOf(deactive));

        // Tạo một RowFactory tùy chỉnh để thiết lập sự kiện cho từng dòng
        employee_table.setRowFactory(tv -> {
            TableRow<Employee> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && !row.isEmpty()) {
                    Employee t = row.getItem();
                    try {
        	            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/EditEmployeeView.fxml"));
        	            Parent root = loader.load();
        	            EditEmployeeController controller = loader.getController();
        	            controller.initialize(t);
        	            Stage stage = new Stage();
        	            stage.setTitle("EditEmployeeSpace");
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
	
	public void clickBtnNewEmployee(@SuppressWarnings("exports") Event event) {
		 try {
	            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/NewEmployeeView.fxml"));
	            Parent root = loader.load();
	            Stage stage = new Stage();
	            stage.setTitle("NewEmployeeSpace");
	            stage.setScene(new Scene(root));
	            stage.show();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	}
	
}


