
package application.dao;

import application.database.JDBCUtil;
import application.model.BillItem;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class BillItemDAO{
    public static BillItemDAO getInstance(){
        return new BillItemDAO();
    }
    
    public boolean insert(BillItem t) {
        try {
            Connection connection = JDBCUtil.getConnection();
            String sql = "INSERT INTO BILL_ITEM (ID_BILL, ID_ITEM, QUANTITY) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, t.getId_bill());
            preparedStatement.setInt(2, t.getId_item());
            preparedStatement.setInt(3, t.getQuantity());
            int ans = preparedStatement.executeUpdate();
            JDBCUtil.closeConnection(connection);
            return ans > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(int id_bill) {
        try{
            Connection connection = JDBCUtil.getConnection();
            String sql = "DELETE FROM BILL_ITEM WHERE ID_BILL = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id_bill);
            int ans = preparedStatement.executeUpdate();
            JDBCUtil.closeConnection(connection);
            return ans > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public ArrayList<BillItem> selectAll() {
        ArrayList<BillItem> li = new ArrayList<BillItem>();
        try{
            Connection connection = JDBCUtil.getConnection();
            String sql =  "SELECT * FROM BILL_ITEM";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id_bill = rs.getInt("ID_BILL");
                int id_item = rs.getInt("ID_ITEM");
                int quantity = rs.getInt("QUANTITY");
                BillItem tmp = new BillItem(id_bill, id_item, quantity);
                li.add(tmp);
            }
            JDBCUtil.closeConnection(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return li;
    }

    public ArrayList<BillItem> selectById(int id_bill) {
        ArrayList<BillItem> li = new ArrayList<BillItem>();
        try {
            Connection connection = JDBCUtil.getConnection();
            String sql = "SELECT * FROM BILL_ITEM WHERE ID_BILL = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id_bill);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id_item = rs.getInt("ID_ITEM");
                int quantity = rs.getInt("QUANTITY");
                BillItem tmp = new BillItem(id_bill, id_item, quantity);
                li.add(tmp);
            }
            JDBCUtil.closeConnection(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return li;
    }

    
}
