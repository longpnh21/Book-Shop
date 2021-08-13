/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longpnh.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Enumeration;
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
public class LoginServlet extends HttpServlet {

    private final String INVALID_PAGE = "invalid.html";
    private final String WELCOME_PAGE = "welcome.jsp";
    private final int cookieTime = 60 * 2;

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

        String url = INVALID_PAGE;

        try {
            String username = request.getParameter("txtUsername");
            String password = request.getParameter("txtPassword");
            RegistrationDAO dao = new RegistrationDAO();
            boolean result = dao.checkLogin(username, password);

            if (result) {
                RegistrationDTO dto = dao.getAccountList().get(0);
                url = WELCOME_PAGE;
                HttpSession session = request.getSession();
                session.setAttribute("ACCOUNT", dto);

                Cookie cookie = new Cookie("username", username);
                cookie.setMaxAge(cookieTime);
                response.addCookie(cookie);
                cookie = new Cookie("password", password);
                cookie.setMaxAge(cookieTime);
                response.addCookie(cookie);
            }
        } catch (SQLException e) {
            log("LoginServlet    _SQL: " + e.getMessage());
            e.printStackTrace();
        } catch (NamingException e) {
            log("LoginServlet    _Naming: " + e.getMessage());
        } finally {
            response.sendRedirect(url);
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
