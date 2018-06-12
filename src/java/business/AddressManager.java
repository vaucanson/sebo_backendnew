/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import entity.Address;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

/**
 *
 * @author djenadi
 */
@Stateless
public class AddressManager {
    @PersistenceContext
    EntityManager em;
    
    /**
     * This method adds an Address in the db
     * @param am is the address to add
     * @return 0 if the operation went well
     */
    public int addAddress(Address am){
        int codeRet = 1;
        try {
            em.persist(am);
            codeRet = 0;
        } catch (PersistenceException pe) {
            System.out.println("Problem creating Address : " + pe.getMessage());
        }
        
        return codeRet;
    }
    
    
    
}
