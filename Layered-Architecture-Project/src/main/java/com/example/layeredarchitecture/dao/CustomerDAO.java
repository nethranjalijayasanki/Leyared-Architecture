package com.example.layeredarchitecture.dao;

import com.example.layeredarchitecture.db.DBConnection;
import com.example.layeredarchitecture.model.CustomerDTO;
import com.example.layeredarchitecture.model.ItemDTO;

import java.sql.*;
import java.util.ArrayList;

public interface CustomerDAO {

    public ArrayList<CustomerDTO> getAllCustomers() throws SQLException, ClassNotFoundException ;
    public ArrayList<String> loadAllCustomersId() throws SQLException, ClassNotFoundException ;
    public void saveCustomer(CustomerDTO customer) throws SQLException, ClassNotFoundException ;
    public void updateCustomer(CustomerDTO customer) throws SQLException, ClassNotFoundException ;

    public boolean existCustomer(String id) throws SQLException, ClassNotFoundException ;

    public void deleteCustomer(String id) throws SQLException, ClassNotFoundException ;

    public String generateId() throws SQLException, ClassNotFoundException ;

    public CustomerDTO searchCustomer(String newValue) throws SQLException, ClassNotFoundException ;

    }


