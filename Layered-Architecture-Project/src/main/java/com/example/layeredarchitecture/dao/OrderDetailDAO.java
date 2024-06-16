package com.example.layeredarchitecture.dao;

import com.example.layeredarchitecture.model.OrderDetailDTO;

import java.sql.SQLException;

public interface OrderDetailDAO {
    boolean saveOrderDetails(String orderId, OrderDetailDTO detail) throws SQLException, ClassNotFoundException;
}
