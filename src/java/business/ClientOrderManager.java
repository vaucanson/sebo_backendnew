
package business;

import entity.ClientAccount;
import entity.ClientOrder;
import entity.OrderLine;
import entity.PaymentMethod;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

/**
 *
 * @author boilleau
 */
@Stateless
public class ClientOrderManager
{
    @PersistenceContext
    EntityManager em;
    
    /**
     * Constructeur vide par défaut
     */
    public ClientOrderManager() {
        // rien
    }
    

    /**
     * Persiste un ClientOrder.
     * @param co un ClientOrder à persister
     * @return un int à 0 si ça se passe bien, une autre valeur sinon.
     */
    public int create (ClientOrder co) {
        int retour = 0;
        try
        {
            em.persist(co);
        }
        catch (Exception e)
        {
            retour = 1;
            e.printStackTrace();
        }
        
        return retour;
    }
    
    /**
     * Sette l'état de la commande donnée à l'état donné
     * @param state
     * @param orderId 
     */
    public void setState (int state, int orderId) {
        ClientOrder co = getById(orderId);
        co.setEtat(state);
    }
    
    /**
     * Paye la commande donnée avec la cb donnée. Ça doit demander l'autorisation à la banque.
     * @param cb
     * @param orderId 
     */
    public void pay (PaymentMethod cb, int orderId) {
        ClientOrder co = getById(orderId);
        co.setPaymet(cb);
        co.setEtat(ClientOrder.PAYED);  // TODO : mettre l'état "payé"
    }
    
    
    /**
     * Renvoie la commande ayant l'id donné.
     * @param id
     * @return 
     */
    public ClientOrder getById(int id) {
        ClientOrder co = em.find(ClientOrder.class, id);
        return co;
    }
    
    
    /**
     * Liste des commandes appartenant à un client donné
     * @param customerId
     * @return 
     */
    public List<ClientOrder> getByCustomer(int customerId) {
        List<ClientOrder> retour=null;
        String strSql = "select co from ClientOrder co where co.client.id =:anId";
        try {
        Query query = em.createQuery(strSql);
        retour = query.getResultList();   
        } catch (PersistenceException pe) {
            System.out.println("Problem retrieving ClientOrder list : " + pe.getMessage());
        }  
        return retour;
    }
    
    /**
     * Liste des commandes qui sont dans un état donné.
     * @param state
     * @return 
     */
    public List<ClientOrder> getByState(int state) {
        List<ClientOrder> retour=null;
        String sqlStr = "select co from ClientOrder co where co.etat = :etat";
        try 
        {
            Query query = em.createQuery(sqlStr);
            query.setParameter ("etat", state);
            retour = query.getResultList();
        }
        catch (Exception e) 
        {
            e.printStackTrace();
        }
        return retour;
    }
    
    public int createOrderLine(OrderLine oline){
        int codeRet = 1;
        try {
          em.persist(oline);  
          codeRet = 0;
        } catch (PersistenceException pe) {
            System.out.println("Problem creating Orderline : " + pe.getMessage());
        }
        
        return codeRet;
            }
}
