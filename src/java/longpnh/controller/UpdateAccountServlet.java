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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import longpnh.registration.RegistrationDAO;

/**
 *
 * @author arceu
 */
public class UpdateAccountServlet extends HttpServlet {

    private final String SEARCH_PAGE = "search.jsp";
    private final String ERROR_PAGE = "error.html";

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

        String urlRewriting = ERROR_PAGE;

        try {
            String username = request.getParameter("txtUsername");
            String password = request.getParameter("txtPassword");
            String role = request.getParameter("chkAdmin");
            String searchValue = request.getParameter("lastSearchValue");
            boolean foundErr = false;
            boolean isAdmin = false;
            if (password.trim().length() < 6 || password.trim().length() > 30) {
                foundErr = true;
                request.setAttribute("ERROR","Password is required input from 6 to 30 chars");
                urlRewriting = "search"
                            + "?btAction=Search"
                            + "&txtSearchValue=" + searchValue;
            }
            else{
                if (role != null) {
                    isAdmin = true;
                }

                RegistrationDAO dao = new RegistrationDAO();
                boolean result = dao.updateAccount(username, password, isAdmin);
                if (result) {
                    urlRewriting = "search"
                            + "?btAction=Search"
                            + "&txtSearchValue=" + searchValue;
                }
            }
        } catch (NamingException e) {
//            log("UpdateAccountServlet    _Naming: " + e.getMessage());
e.printStackTrace();
        } catch (SQLException e) {
//            log("UpdateAccountServlet    _SQL: " + e.getMessage());
e.printStackTrace();
        } finally {
            response.sendRedirect(urlRewriting);
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
