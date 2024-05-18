
package application.dao;

import application.database.JDBCUtil;
import application.model.Item;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;


public class ItemDAO implements DAOInterface<Item>{
    public static ItemDAO getInstance(){
        return new ItemDAO();
    }
    
    @Override
    public boolean insert(Item t){
        try {
            Connection connection = JDBCUtil.getConnection();
            String sql = "INSERT INTO ITEM(NAME, PRICE, TYPE, IMG, STATE) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, t.getName());
            preparedStatement.setDouble(2, t.getPrice());
            preparedStatement.setString(3, t.getType());
            preparedStatement.setString(4, t.getImg_path());
            preparedStatement.setString(5, t.getState());
            int ans = preparedStatement.executeUpdate();
            JDBCUtil.closeConnection(connection);
            return ans > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    @Override 
    public boolean update(Item t){
        try {
            Connection connection = JDBCUtil.getConnection();
            String sql = "UPDATE ITEM SET NAME = ?, PRICE = ?, TYPE = ? , IMG = ?, STATE = ? WHERE ID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, t.getName());
            preparedStatement.setDouble(2, t.getPrice());
            preparedStatement.setString(3, t.getType());
            preparedStatement.setString(4, t.getImg_path());
            preparedStatement.setInt(6, t.getId());
            preparedStatement.setString(5, t.getState());
            int ans = preparedStatement.executeUpdate();
            JDBCUtil.closeConnection(connection);
            return ans > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    @Override
    public boolean delete(Item t) {
        try {
            Connection connection = JDBCUtil.getConnection();
            String sql = "UPDATE ITEM SET STATE = 'deactive' WHERE ID = ?";
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
    public ArrayList<Item> selectAll(){
        ArrayList<Item> li = new ArrayList<Item>();
        try{
            Connection connection = JDBCUtil.getConnection();
            String sql = "SELECT * FROM ITEM ORDER BY STATE";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();  
            while(rs.next()){
                int id = rs.getInt("ID");
                String name = rs.getString("NAME");
                double price = rs.getDouble("PRICE");
                String type = rs.getString("TYPE");
                String path = rs.getString("IMG");
                String state = rs.getString("STATE");
                Item tmp = new Item(id, name, price, type, path, state);
                li.add(tmp);
            }
            JDBCUtil.closeConnection(connection);
        }catch(Exception e){
            e.printStackTrace();
        }
        return li;
    }
    
    @Override 
    public Item selectById(Item t){
        Item u = null;
        try{
            Connection connection = JDBCUtil.getConnection();
            String sql = "SELECT * FROM ITEM WHERE ID = ? ";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, t.getId());
            ResultSet rs = preparedStatement.executeQuery();
            rs.next();
            int id = rs.getInt("ID");
            String name = rs.getString("NAME");
            double price = rs.getDouble("PRICE");
            String type = rs.getString("TYPE");
            String path = rs.getString("IMG");
            String state = rs.getString("STATE");
            u = new Item(id, name, price, type, path, state);
            JDBCUtil.closeConnection(connection);
        }catch(Exception e){
            e.printStackTrace();
        }
        return u;
    }
    
    public ArrayList<Item> selectAllActive(){
        ArrayList<Item> li = new ArrayList<Item>();
        try{
            Connection connection = JDBCUtil.getConnection();
            String sql = "SELECT * FROM ITEM WHERE STATE = 'active' ORDER BY TYPE";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();  
            while(rs.next()){
                int id = rs.getInt("ID");
                String name = rs.getString("NAME");
                double price = rs.getDouble("PRICE");
                String type = rs.getString("TYPE");
                String path = rs.getString("IMG");
                String state = "activate";
                Item tmp = new Item(id, name, price, type, path, state);
                li.add(tmp);
            }
            JDBCUtil.closeConnection(connection);
        }catch(Exception e){
            e.printStackTrace();
        }
        return li;
    }
    
    public ArrayList<Item> selectTypeActive(String type){
        ArrayList<Item> li = new ArrayList<Item>();
        try{
            Connection connection = JDBCUtil.getConnection();
            String sql = "SELECT * FROM ITEM WHERE STATE = 'active' AND TYPE = ? ";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, type);
            ResultSet rs = preparedStatement.executeQuery();  
            while(rs.next()){
                int id = rs.getInt("ID");
                String name = rs.getString("NAME");
                double price = rs.getDouble("PRICE");
//                String type = rs.getString("TYPE");
                String path = rs.getString("IMG");
                String state = "activate";
                Item tmp = new Item(id, name, price, type, path, state);
                li.add(tmp);
            }
            JDBCUtil.closeConnection(connection);
        }catch(Exception e){
            e.printStackTrace();
        }
        return li;
    }
}
