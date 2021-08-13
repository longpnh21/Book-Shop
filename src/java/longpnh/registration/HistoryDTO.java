/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longpnh.registration;

import java.io.Serializable;

/**
 *
 * @author arceu
 */
public class HistoryDTO implements Serializable {
    private String orderID;
    private String custName;
    private String customerAddress;
    private String createDate;
    private String title;
    private int quantity;
    private int price;

    public HistoryDTO() {
    }

    public HistoryDTO(String orderID, String custName, String customerAddress, String createDate, String title, int quantity, int price) {
        this.orderID = orderID;
        this.custName = custName;
        this.customerAddress = customerAddress;
        this.createDate = createDate;
        this.title = title;
        this.quantity = quantity;
        this.price = price;
    }

    /**
     * @return the orderID
     */
    public String getOrderID() {
        return orderID;
    }

    /**
     * @return the custName
     */
    public String getCustName() {
        return custName;
    }

    /**
     * @return the customerAddress
     */
    public String getCustomerAddress() {
        return customerAddress;
    }

    /**
     * @return the createDate
     */
    public String getCreateDate() {
        return createDate;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * @return the price
     */
    public int getPrice() {
        return price;
    }

    
}
