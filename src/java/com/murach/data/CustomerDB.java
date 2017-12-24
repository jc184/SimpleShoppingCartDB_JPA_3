/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.murach.data;

import com.murach.model.Customer;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

/**
 *
 * @author james
 */
public class CustomerDB {

    public static void insertCustomer(Customer customer) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            em.persist(customer);
            tx.commit();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            em.close();
        }
    }

    public static void updateCustomer(Customer customer) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            em.merge(customer);
            tx.commit();
        } catch (Exception e) {
            System.out.println(e);
            tx.rollback();
        } finally {
            em.close();
        }
    }

    public static Customer selectCustomer(String email) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qString = "SELECT c FROM Customer c WHERE c.email = :email";
        TypedQuery<Customer> tq = em.createQuery(qString, Customer.class);
        tq.setParameter("email", email);
        Customer result = null;
        try {
            result = tq.getSingleResult();
        } catch (NoResultException nre) {
            return null;
        } finally {
            em.close();
        }
        return result;
    }

    public static Customer selectCustomer(String loginname, String password) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qString = "SELECT c FROM Customer c WHERE (c.loginname = :loginName) AND (c.password = :password)";
        TypedQuery<Customer> tq = em.createQuery(qString, Customer.class);
        tq.setParameter("loginName", loginname);
        tq.setParameter("password", password);
        Customer result = null;
        try {
            result = tq.getSingleResult();
        } catch (NoResultException nre) {
            return null;
        } finally {
            em.close();
        }
        return result;
    }

    public static List<Customer> selectCustomers() {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qString = "SELECT c from Customer c";
        TypedQuery<Customer> q = em.createQuery(qString, Customer.class);
        List<Customer> results = null;
        try {
            results = q.getResultList();
        } catch (NoResultException ex) {
            return null;
        } finally {
            em.close();
        }

        return results;
    }

    public static boolean checkEmailExists(String email) {
        Customer c = selectCustomer(email);
        return c != null;
    }

    public static boolean checkCustomerExists(String loginName, String loginPasswd, String emailAddress) {
        for (Customer c : selectCustomers()) {
            if (c.getLoginname().equals(loginName) && (c.getPassword().equals(loginPasswd)) || (c.getEmail().equals(emailAddress))) {
                return true;
            }
        }
        return false;
    }

    public static boolean loginCustomer(String loginName, String loginPasswd) {
        for (Customer c : selectCustomers()) {
            if (c.getLoginname().equals(loginName) && (c.getPassword().equals(loginPasswd))) {
                return true;
            }
        }
        return false;
    }

}
