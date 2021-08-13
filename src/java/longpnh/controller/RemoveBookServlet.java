/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longpnh.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import longpnh.book.BookDAO;
import longpnh.book.BookDTO;
import longpnh.cart.CartObj;

/**
 *
 * @author arceu
 */
public class RemoveBookServlet extends HttpServlet {

    private final String SHOPPING_PAGE = "viewCart.jsp";

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
        String urlRewriting = "cart?btAction=View your cart";
        try {
            //1. Cust goes to his/her cart place
            HttpSession session = request.getSession(false);
            if (session != null) {
                //2.Cust takes his/her cart
                CartObj cart = (CartObj) session.getAttribute("CART");
                if (cart != null) {
                    Map<BookDTO, Integer> items = cart.getItems();
                    if (items != null) {
                        //4. Cust chooses removed items
                        String[] removedItems = request.getParameterValues("chkItem");
                        if (removedItems != null) {
                            BookDAO dao = new BookDAO();
                            //5. Cust removes items from cart
                            for (String title : removedItems) {
                                BookDTO dto = dao.searchAvailableBook(title);
                                cart.removeBookFromCart(dto);
                            }//end for title
                            session.setAttribute("CART", cart);
                        }
                    }
                }//end if cart is existed
            }//session is existed
            //6. Refresh or call view cart function path;
            urlRewriting = "cart"
                    + "?btAction=View your cart";
        } catch (SQLException e) {
//            log("RemoveBookServlet    _SQL: " + e.getMessage());
e.printStackTrace();
        } catch (NamingException e) {
            e.printStackTrace();
//            log("RemoveBookServlet    _Naming: " + e.getMessage());
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
