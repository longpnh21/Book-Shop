/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longpnh.cart;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import longpnh.book.BookDTO;

/**
 *
 * @author arceu
 */
public class CartObj implements Serializable {

    private Map<BookDTO, Integer> items;

    public Map<BookDTO, Integer> getItems() {
        return items;
    }

    public void addBookToCart(BookDTO dto) {
        //1. Checked existed cart
        if (this.items == null) {
            this.items = new HashMap<>();
        }

        //2. Check existed item
        int quantity = 1;
        for (BookDTO x : this.items.keySet()) {
            if (x.compareTo(dto) == 0) {
                quantity = this.items.get(x) + 1;
                break;
            }
        }
        this.items.put(dto, quantity);
    }

    public void removeBookFromCart(BookDTO dto) {
        //1. Check existed cart
        if (this.items == null) {
            return;
        }

        //2. Check existed item
        if (this.items.containsKey(dto)) {
            this.items.remove(dto);
            if (this.items.isEmpty()) {
                this.items = null;
            }
        }
    }
}
