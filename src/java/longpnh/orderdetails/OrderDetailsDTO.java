/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longpnh.orderdetails;

import java.io.Serializable;

/**
 *
 * @author arceu
 */
public class OrderDetailsDTO implements Serializable {

    private String OrderID;
    private String BookID;
    private int Quantity;

    public OrderDetailsDTO() {
    }

    public OrderDetailsDTO(String OrderID, String BookID, int Quantity) {
        this.OrderID = OrderID;
        this.BookID = BookID;
        this.Quantity = Quantity;
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
     * @return the BookID
     */
    public String getBookID() {
        return BookID;
    }

    /**
     * @param BookID the BookID to set
     */
    public void setBookID(String BookID) {
        this.BookID = BookID;
    }

    /**
     * @return the Quantity
     */
    public int getQuantity() {
        return Quantity;
    }

    /**
     * @param Quantity the Quantity to set
     */
    public void setQuantity(int Quantity) {
        this.Quantity = Quantity;
    }
}
