/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longpnh.orderdetails;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import javax.naming.NamingException;
import longpnh.book.BookDAO;
import longpnh.book.BookDTO;
import longpnh.utils.DBHelper;

/**
 *
 * @author arceu
 */
public class OrderDetailsDAO implements Serializable {

    private List<OrderDetailsDTO> receipt;
    private Connection con;
    private PreparedStatement stm;
    private ResultSet rs;

    public List<OrderDetailsDTO> getReceipt() {
        return receipt;
    }

    private void connectDB() throws NamingException, SQLException {
        con = null;
        stm = null;
        rs = null;

        con = DBHelper.makeConnection();
    }

    private void closeConnection() throws SQLException, NamingException {
        if (rs != null) {
            rs.close();
        }
        if (stm != null) {
            stm.close();
        }
        if (con != null) {
            con.close();
        }
    }

    public boolean addOrderDetails(String orderID, Map<BookDTO, Integer> items) throws NamingException, SQLException, InputMismatchException {
        try {
            connectDB();

            if (con != null) {
                int affectRows = -1;

                String sql = "Insert into OrderDetails (OrderID, BookID, Quantity)"
                        + "Values (?, ?, ?)";

                stm = con.prepareStatement(sql);
                stm.setString(1, orderID);

                for (BookDTO dto : items.keySet()) {
                    BookDAO dao = new BookDAO();
                    dao.addToCart(dto.getID(), items.get(dto));
                    stm.setString(2, dto.getID());
                    stm.setInt(3, items.get(dto));
                    affectRows = stm.executeUpdate();
                    if (affectRows <= 0) {
                        throw new InputMismatchException("Add details has been corrupted");
                    }
                }
                if (affectRows > 0) {
                    return true;
                }
            }
            return false;
        } finally {
            closeConnection();
        }
    }

    public void printReceipt(String orderID) throws NamingException, SQLException {
        try {
            connectDB();
            if (con != null) {
                String sql = "Select OrderID, BookID, Quantity "
                        + "From OrderDetails "
                        + "Where OrderID = ? ";

                stm = con.prepareStatement(sql);
                stm.setString(1, orderID);

                rs = stm.executeQuery();

                if (rs.next()) {
                    String ID = rs.getString("OrderID");
                    String bookID = rs.getString("BookID");
                    int quantity = rs.getInt("Quantity");

                    OrderDetailsDTO dto = new OrderDetailsDTO(ID, bookID, quantity);
                    if (this.receipt == null) {
                        this.receipt = new ArrayList<OrderDetailsDTO>();
                    }
                    receipt.add(dto);
                }
            }
        } finally {
            closeConnection();
        }
    }
}
