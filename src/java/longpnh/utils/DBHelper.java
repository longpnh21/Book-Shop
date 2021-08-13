/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longpnh.utils;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author arceu
 */
public class DBHelper implements Serializable {

    public static Connection makeConnection() throws NamingException, SQLException {

        Context context = new InitialContext();//get current OS
        Context tomcatContext = (Context) context.lookup("java:comp/env");//get tomcat OS
        DataSource ds = (DataSource) tomcatContext.lookup("DS007");
        Connection con = ds.getConnection();

        return con;

    }
}
