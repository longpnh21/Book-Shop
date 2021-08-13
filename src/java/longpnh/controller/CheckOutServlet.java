/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longpnh.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.InputMismatchException;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import longpnh.cart.CartObj;
import longpnh.custOrder.CustOrderDAO;
import longpnh.orderdetails.OrderDetailsDAO;
import longpnh.registration.RegistrationDTO;

/**
 *
 * @author arceu
 */
public class CheckOutServlet extends HttpServlet {

    private final String RECEIPT_PAGE = "receiptPage.jsp";
    private final String VIEWCART_PAGE = "viewCart.jsp";
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

        String url = VIEWCART_PAGE;
        String name = request.getParameter("txtName");
        String address = request.getParameter("txtAddress");

        try {
            if(name.length() > 0  && address.length() > 0){
                HttpSession session = request.getSession(false);
                if (session != null) {
                    CartObj cart = (CartObj) session.getAttribute("CART");
                    if (cart != null) {
                        RegistrationDTO account = (RegistrationDTO) session.getAttribute("ACCOUNT");
                        CustOrderDAO orderDao = new CustOrderDAO();
                        String orderID = orderDao.addOrder(name, address, account.getUsername());
                        if (orderID != null) {
                            OrderDetailsDAO orderDetailsDAO = new OrderDetailsDAO();
                            orderDetailsDAO.addOrderDetails(orderID, cart.getItems());
                            request.setAttribute("NAME", name);
                            request.setAttribute("ADDRESS", address);
                            request.setAttribute("CART", cart);
                            session.removeAttribute("CART");
                            url = RECEIPT_PAGE;
                        }
                    }
                }
            }
            else{
                request.setAttribute("ERROR_NAME", "Please input your name");
                request.setAttribute("ERROR_ADDRESS", "Please input your address");
            }
        } catch (SQLException e) {
//            log("CheckOutServlet     SQL: " + e.getMessage());
e.printStackTrace();
            e.printStackTrace();
        } catch (NamingException e) {
//            log("CheckOutServlet    Naming: " + e.getMessage());
e.printStackTrace();
        } catch (InputMismatchException e) {
            e.printStackTrace();
//            log("CheckOutServlet    InputMisMatch: " + e.getMessage());
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
