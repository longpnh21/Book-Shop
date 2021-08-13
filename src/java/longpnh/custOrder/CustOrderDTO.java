/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longpnh.custOrder;

import java.io.Serializable;

/**
 *
 * @author arceu
 */
public class CustOrderDTO implements Serializable {

    private String OrderID;
    private String CustomerName;
    private String Address;

    public CustOrderDTO() {
    }

    public CustOrderDTO(String CustomerID, String CustomerName, String Address) {
        this.OrderID = CustomerID;
        this.CustomerName = CustomerName;
        this.Address = Address;
    }

    /**
     * @return the OrderID
     */
    public String getOrderID() {
        return OrderID;
    }

    /**
     * @param OrderID the OrderID to set
     */
    public void setOrderID(String OrderID) {
        this.OrderID = OrderID;
    }

    /**
     * @return the CustomerName
     */
    public String getCustomerName() {
        return CustomerName;
    }

    /**
     * @param CustomerName the CustomerName to set
     */
    public void setCustomerName(String CustomerName) {
        this.CustomerName = CustomerName;
    }

    /**
     * @return the Address
     */
    public String getAddress() {
        return Address;
    }

    /**
     * @param Address the Address to set
     */
    public void setAddress(String Address) {
        this.Address = Address;
    }

}
