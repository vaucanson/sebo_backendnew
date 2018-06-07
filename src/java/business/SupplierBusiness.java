/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import entity.Product;
import entity.Supplier;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

/**
 *
 * @author djenadi
 */
@Stateless
public class SupplierBusiness {
    
@PersistenceContext
EntityManager em;

/**
 * Search an existing occurrence of a supplier name in the database.
 * @param supplierName to search
 * @return true if the name exists, false otherwise
 */
public boolean findSupplier(String supplierName){
    boolean ret = false;
    
    String strSql = "SELECT s from Supplier s where s.name= :supName";
    Supplier supplier;
    Query query = em.createQuery(strSql);
    query.setParameter("supName", supplierName);
    try {
             supplier = (Supplier) query.getSingleResult();

            if (supplier.getName().equals(supplierName)) {
                ret = true;
            }
        } catch (NoResultException nre) {
            System.out.println("Supplier name research : " + nre.getMessage());
        }
    return ret;
}

/**
 * this method is used to add a supplier in the db
 * @param sup is the supplier to add
 * @return 0 if the operation went well
 */
public int add (Supplier sup){
    int ret = 1;
    if(!findSupplier(sup.getName())){
        try {
         em.persist(sup); 
         ret=0;  
        } catch (PersistenceException pe) {
            System.out.println("Problem adding supplier : " + pe.getMessage());
        } 
    }
    
    return ret;
}

/**
 * Remove a supplier from the database
 * @param idSupplier is the supplier id to search
 * @return 0 if the operation well
 */
public int remove (int idSupplier){
    int codeRet = 1;

        try {
            Product p = em.find(Product.class, idSupplier);

            if (p != null && p.getId() == idSupplier) {
                em.remove(p);
                codeRet = 0;
            }
        } catch (NoResultException nre) {
            System.out.println(nre.getMessage());
        }
    return codeRet;
}

/**
 * Update a supplier in the db
 * @param sup is the supplier to update
 * @return 0 if the operation went well
 */
public int update (SupplierBusiness sup){
     int codeRet = 1;

        try {
            em.merge(sup);

        } catch (NoResultException nre) {
            System.out.println(nre.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    return codeRet;
}

/**
 * Retrieve the complete list of suppliers from the db
 * @return a List object containing the suppliers
 */
public List<Supplier> getAll(){
    List<Supplier>list=null;
    String strSql = "Select s from Supplier s";
    Query query = em.createQuery(strSql);
    
    try {
      list=query.getResultList();  
    } catch (PersistenceException pe) {
        System.out.println("Problem extrating Supplier List : " + pe.getMessage());
    }
    
    
    return list;
}

/**
 * Retrieve a supplier from the db
 * @param supplierId is the id of the supplier to search
 * @return the supplier object found
 */
public Supplier getOne(int supplierId){
    Supplier supplier=null;
    
    try {
       supplier = em.find(Supplier.class, supplierId); 
    } catch (PersistenceException pe) {
        System.out.println("Problem retrieving the supplier : " + pe.getMessage());
    }
    
    
    return supplier;
}
    
}
