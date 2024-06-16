package com.example.layeredarchitecture.dao;

//DAO - Data Access Objects

import com.example.layeredarchitecture.db.DBConnection;
import com.example.layeredarchitecture.model.CustomerDTO;
import com.example.layeredarchitecture.view.tdm.CustomerTM;
import javafx.scene.control.Alert;

import java.sql.*;
import java.util.ArrayList;

public class CustomerDAOImpl implements CustomerDAO{

    @Override
    public ArrayList<CustomerDTO> getAllCustomers() throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getDbConnection().getConnection();
        Statement stm = connection.createStatement();
        ResultSet rst = stm.executeQuery("SELECT * FROM Customer");

        ArrayList<CustomerDTO> allCustomers = new ArrayList<>();

        while (rst.next()) {
            CustomerDTO customerDTO = new CustomerDTO
                    (rst.getString("id"),
                            rst.getString("name"),
                            rst.getString("address"));

            allCustomers.add(customerDTO);

        }
        return allCustomers;

    }

    @Override
    public ArrayList<String> loadAllCustomersId() throws SQLException, ClassNotFoundException {

        ArrayList<String> arrayList = new ArrayList<>();
        Connection connection = DBConnection.getDbConnection().getConnection();
        Statement stm = connection.createStatement();
        ResultSet rst = stm.executeQuery("SELECT * FROM Customer");

        while (rst.next()){
            arrayList.add(rst.getString(1));
        }
        return arrayList;
    }

    @Override
    public void saveCustomer(CustomerDTO customer) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement pstm = connection.prepareStatement("INSERT INTO Customer (id,name, address) VALUES (?,?,?)");
        pstm.setString(1, customer.getId());
        pstm.setString(2, customer.getName());
        pstm.setString(3, customer.getAddress());
        pstm.executeUpdate();


    }
    @Override
    public void updateCustomer(CustomerDTO customer) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement pstm = connection.prepareStatement("UPDATE Customer SET name=?, address=? WHERE id=?");
        pstm.setString(1, customer.getId());
        pstm.setString(2, customer.getName());
        pstm.setString(3, customer.getAddress());
        pstm.executeUpdate();
    }
    @Override
    public boolean existCustomer(String id) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT id FROM Customer WHERE id=?");
        pstm.setString(1, id);

        return pstm.executeQuery().next();

    }
    @Override
    public void deleteCustomer(String id) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement pstm = connection.prepareStatement("DELETE FROM Customer WHERE id=?");
        pstm.setString(1, id);
        pstm.executeUpdate();

    }
    @Override
    public String generateId() throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getDbConnection().getConnection();
        ResultSet rst = connection.createStatement().executeQuery("SELECT id FROM Customer ORDER BY id DESC LIMIT 1;");

        if (rst.next()) {
            String id = rst.getString("id");
            int newCustomerId = Integer.parseInt(id.replace("C00-", "")) + 1;
            return String.format("C00-%03d", newCustomerId);
        } else {
            return "C00-001";
        }
    }
    @Override
    public CustomerDTO searchCustomer(String newValue) throws SQLException, ClassNotFoundException {

        Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT * FROM Customer WHERE id=?");
        pstm.setString(1, newValue + "");
        ResultSet rst = pstm.executeQuery();
        rst.next();
        return new CustomerDTO(newValue + "", rst.getString("name"), rst.getString("address"));

    }

}
