/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longpnh.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import longpnh.registration.RegistrationCreateError;
import longpnh.registration.RegistrationDAO;

/**
 *
 * @author arceu
 */
public class CreateAccountServlet extends HttpServlet {

    private final String ERROR_PAGE = "createNewAccount.jsp";
    private final String LOGIN_PAGE = "login.html";

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

        String url = ERROR_PAGE;
        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");
        String confirm = request.getParameter("txtConfirm");
        String fullname = request.getParameter("txtFullname");

        RegistrationCreateError errors = new RegistrationCreateError();
        boolean foundErr = false;

        try {
            //1. Check user errors !!
            if (username.trim().length() < 6 || username.trim().length() > 30) {
                foundErr = true;
                errors.setUsernameLengthError("Username is required input from 6 to 30 chars");
            }

            if (password.trim().length() < 6 || password.trim().length() > 30) {
                foundErr = true;
                errors.setPasswordLengthError("Password is required input from 6 to 30 chars");
            } else if (!password.trim().equals(confirm.trim())) {
                foundErr = true;
                errors.setConfirmNotMatch("Confirm must match assword");
            }

            if (fullname.trim().length() < 2 || fullname.trim().length() > 200) {
                foundErr = true;
                errors.setFullnameLengthError("Fullname is required input from 2 to 200 chars");
            }

            if (foundErr) {
                //2.1 Catching errors, then forward to report errors
                request.setAttribute("CREATE_ERROR", errors);
            } else {
                //2.2 call DAO
                RegistrationDAO dao = new RegistrationDAO();
                boolean result = dao.createAccount(username, password, fullname, false);
                if (result) {
                    url = LOGIN_PAGE; //end if create account is succeeded
                }
            }
        } catch (SQLException e) {
            String errMsg = e.getMessage();
//            log("CreateAccountServlet    _SQL: " + errMsg);
            if (errMsg.contains("duplicate")) {
                errors.setUsernameIsExisted(username + " is existed");
                request.setAttribute("CREATE_ERROR", errors);
            }
            e.printStackTrace();

        } catch (NamingException e) {
//            log("CreateAccountServlet    _Naming: " + e.getMessage());
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
