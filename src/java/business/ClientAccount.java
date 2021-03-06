/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import java.awt.BorderLayout;
import entity.Address;
import javax.ejb.Stateless;
import javax.jms.TransactionRolledBackException;
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
public class ClientAccount {
    @PersistenceContext
    EntityManager em;

/**
 * Search an existing occurrence of a client name in the database.
 * @param email to search
 * @return true if the name exists, false otherwise
 */
public boolean findClient(String email){
    boolean ret = false;
    String strSql = "SELECT c from ClientAccount c where c.email= :anEmail";
    entity.ClientAccount cli;
    Query query = em.createQuery(strSql);
    query.setParameter("anEmail", email);
    try {
             cli = (entity.ClientAccount) query.getSingleResult();
             ret = true;
            
        } catch (PersistenceException pe) {
            System.out.println("Client name research : " + pe.getMessage());
        }
    return ret;
}
    
/**
 * This method is used to connect a client to the db by looking for a match on his credentials
 * @param login is the login of the client
 * @param pwd is the password of the client
 * @return the client object, if the credentials match
 */
    public entity.ClientAccount connect (String login, String pwd)
    {
        entity.ClientAccount client = null;
        
        String strSql = "select c from ClientAccount c where c.email = :anEmail and c.password = :aPwd";
        Query query = em.createQuery(strSql);
        
        query.setParameter("anEmail", login);
        query.setParameter("aPwd", pwd);
               
        try 
        {
            client = (entity.ClientAccount) query.getSingleResult();  
        }
        catch (PersistenceException pe) {
            System.out.println("Client connection problem : " + pe.getMessage());
        }
        
    return client;
}

/**
 * Create a new client in the db if he doesn't exists
 * @param cli is the client to add
 * @return 0 if the operation went well
 */
public int create (entity.ClientAccount cli){
    
    int codeRet = 1;
 
    
    if(!this.findClient(cli.getEmail()))
    {
        try 
        {
          try
          {
            String str = "select a from Address a where a.number = :aNumber and a.road = :aRoad and a.town = :aTown and a.zipcode = :aZipcode";
            Query query = em.createQuery(str);
            
            Address ad = cli.getAddress();
            
            query.setParameter("aNumber", ad.getNumber());
            query.setParameter("aRoad", ad.getRoad());
            query.setParameter("aTown", ad.getTown());
            query.setParameter("aZipcode", ad.getZipcode());
            
            if (query.getSingleResult() == null)
            {
                
            }
            else {
                cli.setAddress((Address) query.getSingleResult());
            }

          }
          catch (NoResultException nre)
          {
              System.out.println("Address searching problem : " + nre.getMessage());
          }          
          finally
          {
            em.persist(cli);
            codeRet = 0;
          }
        } 
        catch (PersistenceException pe) 
        {
            System.out.println("Client creation problem : " + pe.getMessage());
        }
    }
    return codeRet;
}
}
