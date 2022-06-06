package com.vyo.robologger.main.db;

import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Creates static references for database submit
 */
public class DatabaseHelper {


    /**
     * Submits the imageDataObject entity to the mysql table
     * @param imageData
     */
    public static void insertImageData(ImageData imageData){
        Session session = DatabaseInit.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(imageData);
        transaction.commit();
        session.close();
    }
}
