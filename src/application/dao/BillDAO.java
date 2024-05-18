
package application.dao;

import application.database.JDBCUtil;
import application.model.Bill;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class BillDAO{
    public static BillDAO getInstance(){
        return new BillDAO();
    }
    
    public int insert(Bill t) {
        int insertedID = 0;
        try {
            Connection connection = JDBCUtil.getConnection();
            String sql = "INSERT INTO BILL(DISCOUNT, VAT, TIME_TRANS, TOTAL) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setDouble(1, t.getDiscount());
            preparedStatement.setDouble(2, t.getVAT());
            preparedStatement.setString(3, t.getTime());
            preparedStatement.setDouble(4, t.getTotal());
            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            generatedKeys.next();
            insertedID = generatedKeys.getInt(1);

            JDBCUtil.closeConnection(connection);
       } catch (Exception e) {
           e.printStackTrace();
       }
        return insertedID;
    }

    public boolean delete(Bill t) {
        try {
            Connection connection = JDBCUtil.getConnection();
            String sql = "DELETE FROM BILL WHERE ID = ?";
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


    public boolean update(Bill t) {
        try{
            Connection connection = JDBCUtil.getConnection();
            String sql = "UPDATE BILL SET DISCOUNT = ?, VAT = ?, TOTAL = ? WHERE ID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setDouble(1, t.getDiscount());
            preparedStatement.setDouble(2, t.getVAT());
            preparedStatement.setDouble(3, t.getTotal());
            preparedStatement.setInt(4, t.getId());
            int ans = preparedStatement.executeUpdate();
            JDBCUtil.closeConnection(connection);
            return ans > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    public ArrayList<Bill> selectAll() {
        ArrayList<Bill> li = new ArrayList<Bill>();
        try {
            Connection connection = JDBCUtil.getConnection();
            String sql = "SELECT * FROM BILL";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("ID");
                String time = rs.getString("TIME_TRANS");
                double discount = rs.getDouble("DISCOUNT");
                double vat = rs.getDouble("VAT");
                double total = rs.getDouble("TOTAL");
                Bill tmp = new Bill(id, time, discount, vat, total);
                li.add(tmp);
            }
            JDBCUtil.closeConnection(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return li;
    }


    public Bill selectById(Bill t) {
        Bill u = null;
        try {
            Connection connection = JDBCUtil.getConnection();
            String sql = "SELECT * FROM BILL WHERE ID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, t.getId());
            try (ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("ID");
                    String time = rs.getString("TIME_TRANS");
                    double discount = rs.getDouble("DISCOUNT");
                    double vat = rs.getDouble("VAT");
                    double total = rs.getDouble("TOTAL");
                    u = new Bill(id, time, discount, vat, total);
                }
            }
            JDBCUtil.closeConnection(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return u;
    }
    
}
