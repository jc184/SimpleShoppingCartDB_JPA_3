package com.murach.data;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DBUtil {
    private static final EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("SimpleShoppingCartDB_JPA_2PU");
    
    public static EntityManagerFactory getEmFactory() {
        return emf;
    }
}
