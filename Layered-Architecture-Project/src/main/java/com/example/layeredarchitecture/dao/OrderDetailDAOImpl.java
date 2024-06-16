package com.example.layeredarchitecture.dao;

import com.example.layeredarchitecture.db.DBConnection;
import com.example.layeredarchitecture.model.OrderDetailDTO;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrderDetailDAOImpl implements OrderDetailDAO {
    @Override
    public boolean saveOrderDetails(String orderId, OrderDetailDTO detail) throws SQLException, ClassNotFoundException {

        PreparedStatement stm = DBConnection.getDbConnection().getConnection()
                .prepareStatement("INSERT INTO OrderDetails (oid, itemCode, unitPrice, qty) VALUES (?,?,?,?)");
        stm.setString(1, orderId);
        stm.setString(2, detail.getItemCode());
        stm.setBigDecimal(3, detail.getUnitPrice());
        stm.setInt(4, detail.getQty());

        return stm.executeUpdate()>0;
    }
}
