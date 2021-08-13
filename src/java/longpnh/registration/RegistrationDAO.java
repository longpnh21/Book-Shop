/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longpnh.registration;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import longpnh.utils.DBHelper;

/**
 *
 * @author arceu
 */
public class RegistrationDAO implements Serializable {

    private List<RegistrationDTO> accountList;
    private Connection con;
    private PreparedStatement stm;
    private ResultSet rs;
    private List<HistoryDTO> historyList;
    
    public List<RegistrationDTO> getAccountList() {
        return accountList;
    }

    private void connectDB() throws SQLException, NamingException {
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
        if (rs != null) {
            rs.close();
        }
    }

    public boolean checkLogin(String username, String password) throws SQLException, NamingException {
        //1. Connect DB
        try {
            connectDB();
            if (con != null) {

                //2. Create SQL String
                String sql = "Select username, password, fullname, isAdmin "
                        + "From Registration "
                        + "Where username= ? and password = ?";

                //3. Create Statement and assign Parameter value if any
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, password);

                //4. Execute Query
                rs = stm.executeQuery();

                //5. Process result
                if (rs.next()) {
                    String txtFullname = rs.getString("fullname");
                    boolean role = rs.getBoolean("isAdmin");
                    if (getAccountList() == null) {
                        accountList = new ArrayList<RegistrationDTO>();
                    }
                    accountList.add(new RegistrationDTO(username, password, txtFullname, role));
                    return true;
                }//end if rs is existed
            }// end if con is opened
            return false;
        } finally {
            closeConnection();
        }
    }

    public void searchLasname(String searchValue) throws NamingException, SQLException {
        //1. Connect DB
        try {
            connectDB();
            if (con != null) {
                //2. Create SQL String
                String sql = "Select username, password, fullname, isAdmin "
                        + "From Registration "
                        + "Where fullname Like ?";
                //3. Create Statement and assign Parameter value if any
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + searchValue + "%");
                //4. Execute Query
                rs = stm.executeQuery();
                //5. Process result
                while (rs.next()) {
                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    String fullname = rs.getString("fullname");
                    boolean role = rs.getBoolean("isAdmin");

                    RegistrationDTO dto = new RegistrationDTO(username, password, fullname, role);

                    if (this.getAccountList() == null) {
                        this.accountList = new ArrayList<RegistrationDTO>();
                    }//end if account is not allocated
                    this.accountList.add(dto);
                }
            }
        } finally {
            closeConnection();
        }
    }

    public boolean deleteAccount(String username) throws SQLException, NamingException {

        //1. Connect DB
        try {
            connectDB();
            if (con != null) {

                //2. Create SQL String
                String sql = "Delete from Registration "
                        + "Where username = ?";

                //3. Create Statement and assign Parameter value if any
                stm = con.prepareStatement(sql);
                stm.setString(1, username);

                //4. Execute Query
                int affectedRow = stm.executeUpdate();

                //5. Process result
                if (affectedRow > 0) {
                    return true; //end if rs is existed
                }
            }// end if con is opened
            return false;
        } finally {
            closeConnection();
        }
    }

    public boolean updateAccount(String username, String password, boolean isAdmin) throws SQLException, NamingException {

        //1. Connect DB
        try {
            connectDB();
            if (con != null) {
                //2. Create SQL String
                String sql = "Update Registration "
                        + "Set password = ?, isAdmin = ? "
                        + "Where username = ?";
                //3. Create Statement and assign Parameter value if any
                stm = con.prepareStatement(sql);
                stm.setString(1, password);
                stm.setBoolean(2, isAdmin);
                stm.setString(3, username);
                //4. Execute Query
                int affectedRow = stm.executeUpdate();
                //5. Process result
                if (affectedRow > 0) {
                    return true; //end if rs is existed
                }
            }// end if con is opened
            return false;
        } finally {
            closeConnection();
        }
    }

    public boolean createAccount(String username, String password, String fullname, boolean role) throws NamingException, SQLException {
        try {
            connectDB();
            if (con != null) {
                String sql = "Insert Into Registration (username, password, fullname, isAdmin)"
                        + "Values(?, ?, ?, ?)";
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, password);
                stm.setString(3, fullname);
                stm.setBoolean(4, role);
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
    
    public void checkPurchaseHistory(String username) throws SQLException, NamingException{
        try{
            connectDB();
            if(con != null){
                String sql = "Select r.username, c.OrderID, c.CustomerName, c.CustomerAddress, c.createDate, b.Title, o.Quantity, b.Price " 
                                + "From Registration r inner join (CustOrder c inner join (OrderDetails o inner join Book b on o.BookID = b.BookID ) on c.OrderID = o.OrderID ) on r.username = c.username "
                                + "Where r.username = ? ";
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                rs = stm.executeQuery();
                while(rs.next()){
                    String orderID = rs.getString("orderID");
                    String custName = rs.getString("CustomerName");
                    String customerAddress = rs.getString("CustomerAddress");
                    String date = rs.getDate("createDate").toString();
                    String title = rs.getString("Title");
                    int quantity = rs.getInt("Quantity");
                    int price = rs.getInt("Price");
                    HistoryDTO dto = new HistoryDTO(orderID, custName, customerAddress, date, title, quantity, price);
                    if(historyList == null) historyList = new ArrayList<HistoryDTO>();
                    historyList.add(dto);
                }
            }
        }
        finally{
            closeConnection();
        }
    }


    /**
     * @return the historyList
     */
    public List<HistoryDTO> getHistoryList() {
        return historyList;
    }

}
