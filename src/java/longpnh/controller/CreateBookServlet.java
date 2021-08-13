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
import longpnh.book.BookCreateErr;
import longpnh.book.BookDAO;

/**
 *
 * @author arceu
 */
public class CreateBookServlet extends HttpServlet {
    private final String CREATE_BOOK_PAGE = "addProduct.html";
    private final String ERROR_BOOK_PAGE = "addProduct.jsp";
    private final String ERROR_PAGE = "error.html";
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
        String url = CREATE_BOOK_PAGE;
        String title = request.getParameter("Title");
        String author = request.getParameter("Author");
        String publishYear = request.getParameter("publishYear");
        String descript = request.getParameter("Descript");
        String price = request.getParameter("Title");
        String quantity = request.getParameter("Quantity");
        boolean err = false;
        
        BookCreateErr errors = new BookCreateErr();
        
        try {
            if(title.trim().length() <=0){
                err = true;
                errors.setEmptyTitle("Title cannot be empty");
            }
            if(title.trim().length() <= 0){
                err = true;
                errors.setInvalidPrice("Invalid price");
            }
            if(quantity.trim().length() <= 0){
                err = true;
                errors.setInvalidQuantity("Invalid quantity");
            }
            if(err){
                request.setAttribute("CREATE_ERROR", errors);
                url = ERROR_BOOK_PAGE;
            }
            else{
                BookDAO dao = new BookDAO();
                boolean result = dao.createBook(title, author, publishYear, descript, price, quantity);
                if(result){
                    request.setAttribute("SUCCESS", "ADD BOOK SUCCESS");
                    url = WELCOME_PAGE;
                }
            }
        }
         catch (SQLException e) {
            String errMsg = e.getMessage();
//            log("CreateBookServlet    _SQL: " + errMsg);
            if (errMsg.contains("duplicate")) {
                url = ERROR_PAGE;
            }
            e.printStackTrace();
        
        } catch (NamingException e) {
//            log("CreateBookServlet    _Naming: " + e.getMessage());
e.printStackTrace();
        }
        catch(NumberFormatException e){
            String errMsg = e.getMessage();
            log("CreateBookServlet    _NumberFormat: " + e.getMessage());
            errors.setInvalidPrice("Invalid price");
            errors.setInvalidQuantity("Invalid quantity");
            request.setAttribute("CREATE_ERROR", errors);
            url = ERROR_BOOK_PAGE;
        }
        finally{
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
