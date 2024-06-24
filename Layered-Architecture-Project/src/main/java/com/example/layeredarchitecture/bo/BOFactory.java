package com.example.layeredarchitecture.bo;

import com.example.layeredarchitecture.bo.custom.impl.CustomerBOImpl;
import com.example.layeredarchitecture.bo.custom.impl.ItemBOImpl;
import com.example.layeredarchitecture.bo.custom.impl.PurchaseOrderBOImpl;

public class BOFactory {
    private static BOFactory boFactory;
    private BOFactory(){
    }
    public static BOFactory getBoFactory(){
        return (boFactory==null)? boFactory=new BOFactory() : boFactory;
    }

    public enum BOTypes{
        CUSTOMERDAO,ITEMDAO,PLACEORDERBO
    }

    //Object creation logic for BO objects
    public SuperBO getBO(BOTypes types){
        switch (types){
            case CUSTOMERDAO:
                return new CustomerBOImpl();
            case ITEMDAO:
                return new ItemBOImpl();
            case PLACEORDERBO:
                return new PurchaseOrderBOImpl();
            default:
                return null;
        }
    }

}
