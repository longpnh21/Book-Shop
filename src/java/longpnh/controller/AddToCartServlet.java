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
import javax.servlet.http.HttpSession;
import longpnh.book.BookDAO;
import longpnh.book.BookDTO;
import longpnh.cart.CartObj;

/**
 *
 * @author arceu
 */
public class AddToCartServlet extends HttpServlet {

    private final String SHOPPING_PAGE = "onlineBookStore.jsp";

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
        String urlRewriting = SHOPPING_PAGE;
        try {

            //1. Cust goes to cart place
            HttpSession session = request.getSession();
            String lastSearchValue = request.getParameter("lastSearchValue");

            //2. Cust take a cart
            CartObj cart = (CartObj) session.getAttribute("CART");
            if (cart == null) {
                cart = new CartObj(); //end if cart is not existed
            }
            //3. Cust selects/chooses a book
            String ID = request.getParameter("cbo");

            //4. Cust drop item into cart
            BookDAO dao = new BookDAO();
            BookDTO dto = dao.searchAvailableBook(ID);
            cart.addBookToCart(dto);
            session.setAttribute("CART", cart);

            //5. continuously goes to shopping
            urlRewriting = "cart"
                    + "?txtSearchValue=" + lastSearchValue
                    + "&btAction=Search book";

        } catch (SQLException e) {
//            String errMsg = e.getMessage();
//            log("AddToCartServlet    _SQL: " + errMsg);
            e.printStackTrace();
        } catch (NamingException e) {
            e.printStackTrace();
//            log("AddToCartServlet    _Naming: " + e.getMessage());
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
