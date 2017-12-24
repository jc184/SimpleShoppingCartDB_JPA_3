/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.murach.controller;

import com.murach.data.ProductDB;
import com.murach.model.CartItem;
import com.murach.model.Product;
import com.murach.model.ShoppingCart;
import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author james
 */
public class ShoppingCartServlet extends HttpServlet {

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

        ServletContext sc = getServletContext();

        String action = request.getParameter("action");
        if (action == null) {
            action = "cart";
        }

        String url = "/index.jsp";
        if (action.equals("shop")) {
            url = "/index.jsp";
        } else if (action.equals("cart")) {
            int code = Integer.parseInt(request.getParameter("code"));
            String quantityString = request.getParameter("quantity");

            HttpSession session = request.getSession();
            ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
            if (cart == null) {
                cart = new ShoppingCart();
            }

            int quantity;
            try {
                quantity = Integer.parseInt(quantityString);
                if (quantity < 0) {
                    quantity = 1;
                }
            } catch (NumberFormatException nfe) {
                quantity = 1;
            }

            Product product = ProductDB.selectProduct(code);

            CartItem cItem = new CartItem();
            cItem.setProduct(product);
            cItem.setQuantity(quantity);
            if (quantity > 0) {
                cart.addItem(cItem);
            } else if (quantity == 0) {
                cart.removeItem(cItem);
            }
            
            session.setAttribute("cart", cart);
            double overallTotal = cart.calculateOverallTotal(cart);
            String totalSum = cart.getOverallTotalCurrencyFormat(overallTotal);
            request.setAttribute("overallTotal", overallTotal);
            request.setAttribute("totalSum", totalSum);
            session.setAttribute("overallTotal", overallTotal);
            url = "/cart.jsp";

        } else if (action.equals("checkout")) {
            url = "/checkout.jsp";
        }

        sc.getRequestDispatcher(url)
                .forward(request, response);
    }

}
