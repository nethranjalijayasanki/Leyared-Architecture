package com.example.layeredarchitecture.dao;

import com.example.layeredarchitecture.dao.custom.ItemDAO;
import com.example.layeredarchitecture.dao.custom.impl.*;


public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory() {
    }

    public static DAOFactory getDaoFactory() {
        return (daoFactory == null) ? daoFactory = new DAOFactory() : daoFactory;
    }

    public enum DAOTypes {
        CUSTOMERDAO,ITEMDAO,ORDERDAO,ORDERDETAILDAO,QUERY
    }

    public SuperDAO getDAO(DAOTypes types){
        switch (types) {
            case CUSTOMERDAO:
                return new CustomerDAOImpl();
            case ITEMDAO:
                return new ItemDAOImpl();
            case ORDERDAO:
                return new OrderDAOImpl();
            case ORDERDETAILDAO:
                return new OrderDetailsDAOImpl();
            case QUERY:
                return new QueryDAOImpl();
            default:
                return null;
        }
    }


}