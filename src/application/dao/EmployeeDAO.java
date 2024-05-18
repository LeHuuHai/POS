package application.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import application.database.JDBCUtil;
import application.model.Employee;

public class EmployeeDAO implements DAOInterface<Employee>{
	public static EmployeeDAO getInstance() {
		return new EmployeeDAO();
	}

	@Override
	public boolean insert(Employee t) {
		try {
            Connection connection = JDBCUtil.getConnection();
            String sql = "INSERT INTO EMPLOYEE(NAME, GENDER, DOB, PHONE, ADDRESS, ID_CARD, SALARY, STATE) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, t.getName());
            preparedStatement.setString(2, t.getGender());
            preparedStatement.setString(3, t.getDob());
            preparedStatement.setString(4, t.getPhone());
            preparedStatement.setString(5, t.getAddress());
            preparedStatement.setString(6, t.getIdcard());
            preparedStatement.setDouble(7, t.getSalary());
            preparedStatement.setString(8, t.getState());
            int ans = preparedStatement.executeUpdate();
            JDBCUtil.closeConnection(connection);
            return ans > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
		return false;
	}

	@Override
	public boolean delete(Employee t) {
		try {
            Connection connection = JDBCUtil.getConnection();
            String sql = "UPDATE EMPLOYEE SET STATE = 'deactive' WHERE ID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, t.getId());
            int ans = preparedStatement.executeUpdate();
            JDBCUtil.closeConnection(connection);
            return ans > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
		return false;
	}

	@Override
	public boolean update(Employee t) {
		try {
            Connection connection = JDBCUtil.getConnection();
            String sql = "UPDATE EMPLOYEE SET NAME = ? , GENDER = ? , DOB = ? , PHONE = ? , ADDRESS = ? , ID_CARD = ? , SALARY = ? , STATE = ? WHERE ID =?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(9, t.getId());
            preparedStatement.setString(1, t.getName());
            preparedStatement.setString(2, t.getGender());
            preparedStatement.setString(3, t.getDob());
            preparedStatement.setString(4, t.getPhone());
            preparedStatement.setString(5, t.getAddress());
            preparedStatement.setString(6, t.getIdcard());
            preparedStatement.setDouble(7, t.getSalary());
            preparedStatement.setString(8, t.getState());
            int ans = preparedStatement.executeUpdate();
            JDBCUtil.closeConnection(connection);
            return ans > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
		return false;
	}

	@Override
	public ArrayList<Employee> selectAll() {
		ArrayList<Employee> li = new ArrayList<Employee>();
        try{
            Connection connection = JDBCUtil.getConnection();
            String sql = "SELECT * FROM EMPLOYEE ORDER BY STATE";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()) {
	            int id = rs.getInt("ID");
	            String name = rs.getString("NAME");
	            String gender = rs.getString("GENDER");
	            String dob = rs.getString("DOB");
	            String phone = rs.getString("PHONE");
	            String address = rs.getString("ADDRESS");
	            String idcard = rs.getString("ID_CARD");
	            Double salary = rs.getDouble("SALARY");
	            String state = rs.getString("STATE");
	            Employee u = new Employee(id, name, gender, dob, phone, address, idcard, salary, state);
	            li.add(u);
            }
            JDBCUtil.closeConnection(connection);
        }catch(Exception e){
            e.printStackTrace();
        }
        return li;
	}

	@Override
	public Employee selectById(Employee t) {
		Employee u = null;
		try{
            Connection connection = JDBCUtil.getConnection();
            String sql = "SELECT * FROM EMPLOYEE WHERE ID = ? ";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, t.getId());
            ResultSet rs = preparedStatement.executeQuery();
            rs.next();
            int id = rs.getInt("ID");
            String name = rs.getString("NAME");
            String gender = rs.getString("GENDER");
            String dob = rs.getString("DOB");
            String phone = rs.getString("PHONE");
            String address = rs.getString("ADDRESS");
            String idcard = rs.getString("ID_CARD");
            Double salary = rs.getDouble("SALARY");
            String state = rs.getString("STATE");
            u = new Employee(id, name, gender, dob, phone, address, idcard, salary, state);
            JDBCUtil.closeConnection(connection);
        }catch(Exception e){
            e.printStackTrace();
        }
		return u;
	}
	
	public boolean checkIn(Employee t) {
		if(!isCheckedOut(t)) {
			return false;
		}
		 LocalDateTime currentDateTime = LocalDateTime.now();
	     DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH-mm-ss");
	     String currentTime = currentDateTime.format(formatter);
	     int ans = 0;
	     try {
	    	 Connection connection = JDBCUtil.getConnection();
    		 String checkoutsql = "INSERT INTO SHIFT (ID_EMPLOYEE, TIME, INFO) "
    		 		+ " VALUES( ? , ? , 'Check in');";
    		 PreparedStatement checkoutst = connection.prepareStatement(checkoutsql);
    		 checkoutst.setInt(1, t.getId());
    		 checkoutst.setString(2, currentTime);
    		 ans = checkoutst.executeUpdate(); 
	    	 JDBCUtil.closeConnection(connection);
	     }catch(Exception e) {
	    	 e.printStackTrace();
	     }
	     return ans > 0;
	}
	
	public boolean checkOut(Employee t) {
		if(!isCheckedIn(t)) {
			return false;
		}
		 LocalDateTime currentDateTime = LocalDateTime.now();
	     DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH-mm-ss");
	     String currentTime = currentDateTime.format(formatter);
	     int ans = 0;
	     try {
	    	 Connection connection = JDBCUtil.getConnection();
    		 String checkoutsql = "INSERT INTO SHIFT (ID_EMPLOYEE, TIME, INFO) "
    		 		+ " VALUES( ? , ? , 'Check out');";
    		 PreparedStatement checkoutst = connection.prepareStatement(checkoutsql);
    		 checkoutst.setInt(1, t.getId());
    		 checkoutst.setString(2, currentTime);
    		 ans = checkoutst.executeUpdate(); 
	    	 JDBCUtil.closeConnection(connection);
	     }catch(Exception e) {
	    	 e.printStackTrace();
	     }
	     return ans > 0;
	}
	
	public boolean isCheckedIn(Employee t) {
		LocalDateTime currentDateTime = LocalDateTime.now();
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	    String currentDay = currentDateTime.format(formatter);
	    try {
	    	Connection connection = JDBCUtil.getConnection();
	    	String checksql = "SELECT TOP 1 * FROM SHIFT "
	    			+ "WHERE ID_EMPLOYEE = ?  AND CONVERT(DATE, TIME) = ? "
	    			+ "ORDER BY TIME DESC;";
	    	PreparedStatement checkst = connection.prepareStatement(checksql);
			checkst.setInt(1, t.getId());
			checkst.setString(2, currentDay);
	    	ResultSet rs = checkst.executeQuery();
	    	if(rs.next()==false) {
	    		return false;
	    	} else {
	    		String content = rs.getString("INFO");
	    		String contentRefer = "Check in";
	    		if(!content.equals(contentRefer)) {
	    			return false;
	    		}
	    	}
	    	 JDBCUtil.closeConnection(connection);
	    } catch(Exception e) {
		    e.printStackTrace();
		}
	    return true;
	}
	
	public boolean isCheckedOut(Employee t) {
		LocalDateTime currentDateTime = LocalDateTime.now();
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	    String currentDay = currentDateTime.format(formatter);
	    try {
	    	Connection connection = JDBCUtil.getConnection();
	    	String checksql = "SELECT TOP 1 * FROM SHIFT "
	    			+ "WHERE ID_EMPLOYEE = ?  AND CONVERT(DATE, TIME) = ? "
	    			+ "ORDER BY TIME DESC;";
	    	PreparedStatement checkst = connection.prepareStatement(checksql);
			checkst.setInt(1, t.getId());
			checkst.setString(2, currentDay);
	    	ResultSet rs = checkst.executeQuery();
	    	if(rs.next()==false) {
	    		return true;
	    	} else {
	    		String content = rs.getString("INFO");
	    		String contentRefer = "Check out";
	    		if(!content.equals(contentRefer)) {
	    			return false;
	    		}
	    	}
	    	 JDBCUtil.closeConnection(connection);
	    } catch(Exception e) {
		    e.printStackTrace();
		}
	    return true;
	}
}
