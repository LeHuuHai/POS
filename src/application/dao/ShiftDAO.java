package application.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import application.database.JDBCUtil;
import application.model.Employee;
import application.model.Shift;

public class ShiftDAO {
	public static ShiftDAO getInstance(){
        return new ShiftDAO();
    }
	
	public boolean check(Shift t) {
		boolean ans = false;
		Employee e = EmployeeDAO.getInstance().selectById(new Employee(t.getId_employee()));
		if(e.getState().equals("activate")) {
			Connection connection = JDBCUtil.getConnection();
			 String sql = "SELECT TOP 1 * FROM SHIFT "
			 			+ "WHERE ID_EMPLOYEE = ? "
			 			+ "ORDER BY TIME DESC;";
			 PreparedStatement preparedStatement;
			 try {
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setInt(1, t.getId_employee());
				ResultSet rs = preparedStatement.executeQuery();
				if(rs.next()) {
					String info = rs.getString("INFO");		        
			        if (t.getInfor().equals("Check In") && info.equals("Check Out")) {
			        	ans = true;
			        } else if (t.getInfor().equals("Check Out") && info.equals("Check In")) {
			            ans = true;
			        }
				}else {
					if(t.getInfor().equals("Check In")) {
						ans = true;
					}
				}
				JDBCUtil.closeConnection(connection);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return ans;
	}
    
    public boolean insert(Shift t){
    	if(!check(t)) {
    		return false;
    	}
        try {
            Connection connection = JDBCUtil.getConnection();
            String sql = "INSERT INTO SHIFT(ID_EMPLOYEE, TIME, INFO) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, t.getId_employee());
            preparedStatement.setString(2, t.getTime());
            preparedStatement.setString(3, t.getInfor());
            int ans = preparedStatement.executeUpdate();
            JDBCUtil.closeConnection(connection);
            return ans > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public ArrayList<Shift> selectAll(){
        ArrayList<Shift> li = new ArrayList<Shift>();
        try{
            Connection connection = JDBCUtil.getConnection();
            String sql = "SELECT * FROM SHIFT";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();  
            while(rs.next()){
                int id = rs.getInt("ID");
                int id_employee = rs.getInt("ID_EMPLOYEE");
                String time = rs.getString("TIME");
                String infor = rs.getString("INFO");
                Shift tmp = new Shift(id, id_employee, time, infor);
                li.add(tmp);
            }
            JDBCUtil.closeConnection(connection);
        }catch(Exception e){
            e.printStackTrace();
        }
        return li;
    }
    
    public ArrayList<Shift> selectPeriod(String fromDate, String toDate){
        ArrayList<Shift> li = new ArrayList<Shift>();
        try{
            Connection connection = JDBCUtil.getConnection();
            String sql = "SELECT * FROM SHIFT "
            		   + "WHERE CAST(TIME AS DATE) >= ? "
            		   + "AND CAST(TIME AS DATE) <= ? ;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, fromDate);
            preparedStatement.setString(2, toDate);
            ResultSet rs = preparedStatement.executeQuery();  
            while(rs.next()){
                int id = rs.getInt("ID");
                int id_employee = rs.getInt("ID_EMPLOYEE");
                String time = rs.getString("TIME");
                String infor = rs.getString("INFO");
                Shift tmp = new Shift(id, id_employee, time, infor);
                li.add(tmp);
            }
            JDBCUtil.closeConnection(connection);
        }catch(Exception e){
            e.printStackTrace();
        }
        return li;
    } 
}
