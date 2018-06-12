/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import entity.PaymentMethod;
import entity.Product;
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
public class PaymentManager {
    @PersistenceContext
    EntityManager em;
    
    
        /**
     * Cette méthode permet de rechercher un produit dans la base de donnée
     *
     * @param cardnumber ref est la référence de l'objet "Product" (article) qu'on
     * recherche
     * @return un boolean disant si l'article est deja présent dans la base
     */
    public boolean findPaymentMethod(int cardnumber) {
        boolean ret = false;

        String sqlStr = "select a from PaymentMethod a where a.creditCardNumber = :aRef";
        PaymentMethod paymet;

        Query query = em.createQuery(sqlStr);
        query.setParameter("aRef", cardnumber);

        try {
            paymet = (PaymentMethod) query.getSingleResult();

            if (paymet.getCreditCardNumber()== cardnumber){
                ret = true;
            }
        } catch (PersistenceException pe) {
            System.out.println("PaymentMethod research : " + pe.getMessage());
        }
        return ret;
    }
    
    /** This method adds a payment method in the db
     * @param pm
     * @return 0 if the operation went well
     */
    public int addPaymentMethod(PaymentMethod pm){
        int codeRet = 1;
        try {
            System.out.println("pre recherche");
           if(!findPaymentMethod(pm.getCreditCardNumber())){
               System.out.println("post recherche");
            em.persist(this);
            codeRet = 0;
        }   
        } catch (PersistenceException pe) {
            System.out.println("Problem adding Payment Method : " + pe.getMessage());
        }
         
        return codeRet;
    }
    
}
