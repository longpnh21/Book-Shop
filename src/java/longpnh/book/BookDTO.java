/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longpnh.book;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author arceu
 */
public class BookDTO implements Serializable, Comparable<BookDTO> {

    private String ID;
    private String title;
    private String author;
    private int publishYear;
    private String desc;
    private int price;

    public BookDTO() {
    }

    public BookDTO(String ID, String title, String author, int publishYear, String desc, int price) {
        this.ID = ID;
        this.title = title;
        this.author = author;
        this.publishYear = publishYear;
        this.desc = desc;
        this.price = price;
    }

    /**
     * @return the ID
     */
    public String getID() {
        return ID;
    }

    /**
     * @param ID the ID to set
     */
    public void setID(String ID) {
        this.ID = ID;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * @param author the author to set
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * @return the publishYear
     */
    public int getPublishYear() {
        return publishYear;
    }

    /**
     * @param publishYear the publishYear to set
     */
    public void setPublishYear(int publishYear) {
        this.publishYear = publishYear;
    }

    /**
     * @return the desc
     */
    public String getDesc() {
        return desc;
    }

    /**
     * @param desc the desc to set
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public int compareTo(BookDTO s) {
        if (this.getID().equals(s.getID())) {
            return 0;
        } else {
            return -1;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof BookDTO) {
            return this.compareTo((BookDTO) obj) == 0;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return 31 + ID.hashCode();
    }

    /**
     * @return the price
     */
    public int getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(int price) {
        this.price = price;
    }

}
