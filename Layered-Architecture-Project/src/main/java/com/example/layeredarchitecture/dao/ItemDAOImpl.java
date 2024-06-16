package com.example.layeredarchitecture.dao;

import com.example.layeredarchitecture.db.DBConnection;
import com.example.layeredarchitecture.model.CustomerDTO;
import com.example.layeredarchitecture.model.ItemDTO;
import com.example.layeredarchitecture.view.tdm.ItemTM;

import java.sql.*;
import java.util.ArrayList;

public class ItemDAOImpl implements ItemDAO{
        public ArrayList<ItemDTO> getAllItems() throws SQLException, ClassNotFoundException {
            Connection connection = DBConnection.getDbConnection().getConnection();
            Statement stm = connection.createStatement();
            ResultSet rst = stm.executeQuery("SELECT * FROM Item");
            ArrayList<ItemDTO> allItems = new ArrayList<>();

            while (rst.next()) {
                ItemDTO itemDTO = new ItemDTO
                        (rst.getString("code"),
                                rst.getString("description"),
                                rst.getBigDecimal("unitPrice"),
                                rst.getInt("qtyOnHand"));

                allItems.add(itemDTO);

            }
            return allItems;
             }

    @Override
    public ArrayList<String> loadAllItemsCode() throws SQLException, ClassNotFoundException {

        ArrayList<String> arrayList = new ArrayList<>();
        Connection connection = DBConnection.getDbConnection().getConnection();
        Statement stm = connection.createStatement();
        ResultSet rst = stm.executeQuery("SELECT * FROM Customer");

        while (rst.next()){
            arrayList.add(rst.getString(1));


        }
        return arrayList;
    }

    public void saveItems(ItemDTO item) throws SQLException, ClassNotFoundException {
             Connection connection = DBConnection.getDbConnection().getConnection();
             PreparedStatement pstm = connection.prepareStatement("INSERT INTO Item (code, description, unitPrice, qtyOnHand) VALUES (?,?,?,?)");
             pstm.setString(1, item.getCode());
             pstm.setString(2, item.getDescription());
             pstm.setBigDecimal(3, item.getUnitPrice());
             pstm.setInt(4, item.getQtyOnHand());
             pstm.executeUpdate();

         }

         public void updateItems(ItemDTO item) throws SQLException, ClassNotFoundException {
             Connection connection = DBConnection.getDbConnection().getConnection();
             PreparedStatement pstm = connection.prepareStatement("UPDATE Item SET description=?, unitPrice=?, qtyOnHand=? WHERE code=?");
             pstm.setString(1, item.getCode());
             pstm.setBigDecimal(2, item.getUnitPrice());
             pstm.setInt(3, item.getQtyOnHand());
             pstm.setString(4, item.getDescription());
             pstm.executeUpdate();

         }
         public void deleteItem(String code) throws SQLException, ClassNotFoundException {
             Connection connection = DBConnection.getDbConnection().getConnection();
             PreparedStatement pstm = connection.prepareStatement("DELETE FROM Item WHERE code=?");
             pstm.setString(1, code);
             pstm.executeUpdate();

         }
         public void searchItem(ItemDTO item) throws SQLException, ClassNotFoundException {

        Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT * FROM Item WHERE code=?");
        pstm.setString(1, item.getCode() + "");

        }

         public boolean existCode(String code) throws SQLException, ClassNotFoundException {
            Connection connection = DBConnection.getDbConnection().getConnection();
            PreparedStatement pstm = connection.prepareStatement("SELECT code FROM Item WHERE code=?");
            pstm.setString(1, code);
            return pstm.executeQuery().next();
        }

         @Override
         public String generateId() throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getDbConnection().getConnection();
        ResultSet rst = connection.createStatement().executeQuery("SELECT code FROM Item ORDER BY code DESC LIMIT 1;");
        if (rst.next()) {
            String id = rst.getString("code");
            int newItemId = Integer.parseInt(id.replace("I00-", "")) + 1;
            return String.format("I00-%03d", newItemId);
        } else {
            return "I00-001";
        }
    }

    @Override
    public boolean findItem(String code) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT code FROM Item WHERE code=?");
        pstm.setString(1, code);
        return pstm.executeQuery().next();
    }

    @Override
    public ItemDTO Item(String newItemCode) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT * FROM Item WHERE code=?");
        pstm.setString(1, newItemCode + "");
        ResultSet rst = pstm.executeQuery();
        rst.next();
        return new ItemDTO(newItemCode + "", rst.getString("description"), rst.getBigDecimal("unitPrice"), rst.getInt("qtyOnHand"));

    }
    public boolean update(ItemDTO item) throws SQLException, ClassNotFoundException {
        PreparedStatement pstm = DBConnection.getDbConnection().getConnection().
                prepareStatement("UPDATE Item SET description=?, unitPrice=?, qtyOnHand=? WHERE code=?");
        pstm.setString(1, item.getDescription());
        pstm.setBigDecimal(2, item.getUnitPrice());
        pstm.setInt(3, item.getQtyOnHand());
        pstm.setString(4, item.getCode());

        return pstm.executeUpdate() > 0;
    }
    public ItemDTO getItem(String code) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT * FROM Item WHERE code=?");
        pstm.setString(1, code);
        ResultSet rst = pstm.executeQuery();
        rst.next();
        return new ItemDTO(code, rst.getString("description"), rst.getBigDecimal("unitPrice"), rst.getInt("qtyOnHand"));

    }

}
