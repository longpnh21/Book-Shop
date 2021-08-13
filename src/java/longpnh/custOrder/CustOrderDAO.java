/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longpnh.custOrder;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.naming.NamingException;
import longpnh.utils.DBHelper;
import longpnh.utils.Support;

/**
 *
 * @author arceu
 */
public class CustOrderDAO implements Serializable {

    private List<CustOrderDTO> OrderList;
    private Connection con = null;
    private PreparedStatement stm = null;
    private ResultSet rs = null;

    public List<CustOrderDTO> getCustList() {
        return OrderList;
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

    public String addOrder(String name, String address, String username) throws NamingException, SQLException {
        try {
            connectDB();
            if (con != null) {
                String sql = "Insert into CustOrder (OrderID, CustomerName, CustomerAddress, username) "
                        + "Values (?, ?, ?, ?)";

                stm = con.prepareStatement(sql);
                String id = Support.getAlphaNumericString(10);

                stm.setString(1, id);
                stm.setString(2, name);
                stm.setString(3, address);
                stm.setString(4, username);

                int affectedRows = stm.executeUpdate();
                if (affectedRows > 0) {
                    return id;
                }
            }
            return null;
        } finally {
            closeConnection();
        }
    }
}
