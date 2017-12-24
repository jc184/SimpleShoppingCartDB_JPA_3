/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.murach.controller;

import com.murach.data.CustomerDB;
import com.murach.model.Customer;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.murach.util.MailUtil;
import javax.mail.MessagingException;

/**
 *
 * @author james
 */
public class CustomerServlet extends HttpServlet {

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
        doPost(request, response);
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
        String url = "/checkout.jsp";
        String loginname = request.getParameter("loginname");
        String password = request.getParameter("password");
        String submit = request.getParameter("submit");
        HttpSession session = request.getSession();
        boolean authorized = Boolean.parseBoolean(request.getParameter("authorized"));
        if (submit != null && submit.length() > 0) {
            if (submit.equals("login")) {
                if (CustomerDB.loginCustomer(loginname, password)) {
                    Customer customer = CustomerDB.selectCustomer(loginname, password);
                    session.setAttribute("customer", customer);
                    // remove all items from the customer's cart
                    session.setAttribute("cart", null);

                    url = "/paypalJS.jsp";
                    if (authorized) {
                        sendEmailConfirmation(request, response, customer.getEmail());
                    }
                } else {
                    String message = "Your login details are wrong. Please re-enter or register to continue.";
                    request.setAttribute("message", message);
                    url = "/checkout.jsp";
                }
            } else if (submit.equals("continue")) {
                try {
                    String firstName = request.getParameter("firstName");
                    String lastName = request.getParameter("lastName");
                    String address1 = request.getParameter("address1");
                    String address2 = request.getParameter("address2");
                    String city = request.getParameter("city");
                    String state = request.getParameter("state");
                    String creditcardtype = request.getParameter("creditcardtype");
                    String creditcardnumber = request.getParameter("creditcardnumber");
                    String ccexpirydate = request.getParameter("ccexpirydate");
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

                    Date sdfccExpiryDate = formatter.parse(ccexpirydate);

                    String email = request.getParameter("email");
                    String mobile = request.getParameter("mobile");

                    Customer customer = (Customer) session.getAttribute("customer");
                    if (customer == null) {
                        customer = new Customer();
                    }

                    customer.setFirstName(firstName);
                    customer.setLastName(lastName);
                    customer.setAddress1(address1);
                    customer.setAddress2(address2);
                    customer.setCity(city);
                    customer.setState(state);
                    customer.setCreditcardtype(creditcardtype);
                    customer.setCreditcardnumber(creditcardnumber);
                    customer.setCcexpirydate(sdfccExpiryDate);
                    customer.setEmail(email);
                    customer.setMobile(mobile);
                    customer.setLoginname(loginname);
                    customer.setPassword(password);

                    if (CustomerDB.checkCustomerExists(loginname, password, email)) {
                        //CustomerDB.updateCustomer(customer);
                        String message = "You have already registered with those details. Please login with your username and password.";
                        request.setAttribute("message", message);

                        url = "/checkout.jsp";
                    } else {
                        CustomerDB.insertCustomer(customer);
                        String message = "You have been added to our database.";
                        request.setAttribute("message", message);
                        // remove all items from the customer's cart
                        session.setAttribute("cart", null);

                        url = "/paypalJS.jsp";
                        if (authorized) {
                            sendEmailConfirmation(request, response, customer.getEmail());
                        }
                    }
                    session.setAttribute("customer", customer);
                } catch (ParseException ex) {
                    Logger.getLogger(CustomerServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
    }

    private void sendEmailConfirmation(HttpServletRequest request, HttpServletResponse response, String email) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("customer");
        // send an email to the user to confirm the order.
        String to = customer.getEmail();
        String from = "confirmation@moravianmountainbikes.com";
        String subject = "Moravian Mountain Bikes Order Confirmation";
        String body = "Dear " + customer.getFirstName() + ",\n\n"
                + "Thanks for ordering from us. "
                + "You should receive your order in 3-5 business days. "
                + "Please contact us if you have any questions.\n"
                + "Have a great day and thanks again!\n\n"
                + "John Smith\n"
                + "Moravian Mountain Bikes";
        boolean isBodyHTML = false;
        try {
            MailUtil.sendMail(to, from, subject, body, isBodyHTML);
        } catch (MessagingException e) {
            this.log(
                    "Unable to send email. \n"
                    + "You may need to configure your system as "
                    + "described in chapter 15. \n"
                    + "Here is the email you tried to send: \n"
                    + "=====================================\n"
                    + "TO: " + to + "\n"
                    + "FROM: " + from + "\n"
                    + "SUBJECT: " + subject + "\n"
                    + "\n"
                    + body + "\n\n");
        }
    }
}
