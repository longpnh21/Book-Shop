/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longpnh.book;

import java.io.Serializable;

/**
 *
 * @author arceu
 */
public class BookCreateErr implements Serializable{
    private String duplicateBookID;
    private String emptyTitle;
    private String invalidPrice;
    private String invalidQuantity;

    public BookCreateErr() {
    }

    public BookCreateErr(String duplicateBookID, String emptyTitle, String invalidPrice, String invalidQuantity) {
        this.duplicateBookID = duplicateBookID;
        this.emptyTitle = emptyTitle;
        this.invalidPrice = invalidPrice;
        this.invalidQuantity = invalidQuantity;
    }

    /**
     * @return the duplicateBookID
     */
    public String getDuplicateBookID() {
        return duplicateBookID;
    }

    /**
     * @param duplicateBookID the duplicateBookID to set
     */
    public void setDuplicateBookID(String duplicateBookID) {
        this.duplicateBookID = duplicateBookID;
    }

    /**
     * @return the emptyTitle
     */
    public String getEmptyTitle() {
        return emptyTitle;
    }

    /**
     * @param emptyTitle the emptyTitle to set
     */
    public void setEmptyTitle(String emptyTitle) {
        this.emptyTitle = emptyTitle;
    }

    /**
     * @return the invalidPrice
     */
    public String getInvalidPrice() {
        return invalidPrice;
    }

    /**
     * @param invalidPrice the invalidPrice to set
     */
    public void setInvalidPrice(String invalidPrice) {
        this.invalidPrice = invalidPrice;
    }

    /**
     * @return the invalidQuantity
     */
    public String getInvalidQuantity() {
        return invalidQuantity;
    }

    /**
     * @param invalidQuantity the invalidQuantity to set
     */
    public void setInvalidQuantity(String invalidQuantity) {
        this.invalidQuantity = invalidQuantity;
    }
    
    
}
