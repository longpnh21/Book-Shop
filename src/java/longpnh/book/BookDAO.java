    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longpnh.book;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import longpnh.utils.DBHelper;
import longpnh.utils.Support;

/**
 *
 * @author arceu
 */
public class BookDAO implements Serializable {

    private List<BookDTO> bookList;

    private Connection con;
    private PreparedStatement stm;
    private ResultSet rs;

    public List<BookDTO> getBookList() {
        return bookList;
    }

    private void connectDB() throws NamingException, SQLException {
        con = null;
        stm = null;
        rs = null;

        con = DBHelper.makeConnection();
    }

    private void closeConnection() throws NamingException, SQLException {
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

    public void searchBook(String searchValue) throws NamingException, SQLException {

        //1. Connect DB
        try {
            connectDB();
            if (con != null) {

                //2. Create SQL String
                String sql = "Select BookID, Title, Author, PublishYear, Descript, Price "
                        + "From Book "
                        + "Where Title Like ? AND AvailableQuantity>0";

                //3. Create Statement and assign Parameter value if any
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + searchValue + "%");

                //4. Execute Query
                rs = stm.executeQuery();

                //5. Process result
                while (rs.next()) {
                    String bookID = rs.getString("BookID");
                    String title = rs.getString("Title");
                    String author = rs.getString("Author");
                    int publishYear = rs.getInt("PublishYear");
                    String descript = rs.getString("Descript");
                    int price = rs.getInt("Price");

                    BookDTO dto = new BookDTO(bookID, title, author, publishYear, descript, price);

                    if (this.bookList == null) {
                        this.bookList = new ArrayList<>();
                    }//end if account is not allocated
                    this.bookList.add(dto);
                }
            }
        } finally {
            closeConnection();
        }
    }

    public BookDTO searchAvailableBook(String searchValue) throws NamingException, SQLException {

        try {
            //1.Connect DB
            connectDB();
            if (con != null) {
                //2. Create SQL String
                String sql = "Select BookID, Title, Author, PublishYear, Descript, Price "
                        + "From Book "
                        + "Where BookID = ? ";

                //3. Create statement and assign value
                stm = con.prepareStatement(sql);
                stm.setString(1, searchValue);

                //4. Execute query
                rs = stm.executeQuery();

                //5.Process result
                if (rs.next()) {
                    String bookID = rs.getString("BookID");
                    String title = rs.getString("Title");
                    String author = rs.getString("Author");
                    int publishYear = rs.getInt("PublishYear");
                    String Descript = rs.getString("Descript");
                    int price = rs.getInt("Price");

                    BookDTO dto = new BookDTO(bookID, title, author, publishYear, Descript, price);
                    return dto;
                }
            }
            return null;
        } finally {
            closeConnection();
        }
    }

    public boolean addToCart(String id, int quantity) throws NamingException, SQLException {
        try {
            connectDB();
            if (con != null) {
                String sql = "UPDATE Book "
                        + "SET AvailableQuantity = AvailableQuantity - ? "
                        + "WHERE BookID = ? ";
                stm = con.prepareStatement(sql);
                stm.setInt(1, quantity);
                stm.setString(2, id);
                int affectRows = stm.executeUpdate();
                if (affectRows > 0) {
                    return true;
                }
            }
            return false;
        } finally {
            closeConnection();
        }

    }

    public boolean createBook(String title, String author, String txtPublishYear, String Descript, String txtPrice, String txtQuantity) throws NamingException, SQLException, NumberFormatException {
        try {
            connectDB();
            if (con != null) {
                int publishYear;
                if (txtPublishYear == null) publishYear = 0;
                else publishYear = Integer.parseInt(txtPublishYear);
                int price;
                if (txtPrice == null) price = 0;
                else price = Integer.parseInt(txtPrice);
                int quantity;
                if (txtQuantity == null) quantity = 0;
                else quantity = Integer.parseInt(txtQuantity);
                String bookID = Support.getAlphaNumericString(10);

                String sql = "Insert Into Book (BookID , Title, Author, publishYear, Descript, Price, AvailableQuantity) "
                        + "Values(?, ?, ?, ?, ?, ?, ?)";
                stm = con.prepareStatement(sql);
                stm.setString(1, bookID);
                stm.setString(2, title);
                stm.setString(3, author);
                stm.setInt(4, publishYear);
                stm.setString(5, Descript);
                stm.setInt(6, price);
                stm.setInt(7, quantity);
                int affectRows = stm.executeUpdate();
                if (affectRows > 0) {
                    return true;
                }
            }
            return false;
        } finally {
            closeConnection();
        }
    }
}
