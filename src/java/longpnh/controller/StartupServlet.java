/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longpnh.controller;

import java.io.IOException;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import longpnh.registration.RegistrationDAO;
import longpnh.registration.RegistrationDTO;

/**
 *
 * @author arceu
 */
public class StartupServlet extends HttpServlet {

    private final String LOGIN_PAGE = "login.html";
    private final String WELCOME_PAGE = "welcome.jsp";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String url = LOGIN_PAGE;

        try {
            //1. Check cookie existed or not !!!
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                String username = "", password = "";
                for (int i = 0; i < cookies.length; i++) {
                    if (cookies[i].getName().equals("username")) {
                        username = cookies[i].getValue();
                    } else if (cookies[i].getName().equals("password")) {
                        password = cookies[i].getValue();
                    }
                }
                //3. Check login
                RegistrationDAO dao = new RegistrationDAO();
                boolean result = dao.checkLogin(username, password);

                if (result) {
                    HttpSession session = request.getSession();
                    RegistrationDTO dto = dao.getAccountList().get(0);
                    session.setAttribute("ACCOUNT", dto);
                    url = WELCOME_PAGE;;
                }//end if user is valid
            }//end if cookies have existed
        } catch (NamingException e) {
//            log("StartUpServlet    _Naming: " + e.getMessage());
e.printStackTrace();
        } catch (SQLException e) {
//            log("StartUpServlet    _SQL: " + e.getMessage());
e.printStackTrace();
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
