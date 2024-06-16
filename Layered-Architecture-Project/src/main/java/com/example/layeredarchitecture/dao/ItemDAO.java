package com.example.layeredarchitecture.dao;

import com.example.layeredarchitecture.db.DBConnection;
import com.example.layeredarchitecture.model.ItemDTO;

import java.sql.*;
import java.util.ArrayList;

public interface ItemDAO {
    public ArrayList<ItemDTO> getAllItems() throws SQLException, ClassNotFoundException ;
    public ArrayList<String> loadAllItemsCode() throws SQLException, ClassNotFoundException ;
    public void saveItems(ItemDTO item) throws SQLException, ClassNotFoundException;
    public void updateItems(ItemDTO item) throws SQLException, ClassNotFoundException;
    public void deleteItem(String code) throws SQLException, ClassNotFoundException ;
    public void searchItem(ItemDTO item) throws SQLException, ClassNotFoundException;
    public boolean existCode(String code) throws SQLException, ClassNotFoundException;
    public String generateId() throws SQLException, ClassNotFoundException;
    public boolean findItem(String code) throws SQLException, ClassNotFoundException;
    public ItemDTO Item(String newItemCode) throws SQLException, ClassNotFoundException;
    public boolean update(ItemDTO item) throws SQLException, ClassNotFoundException;
    public ItemDTO getItem(String code) throws SQLException, ClassNotFoundException;
}
