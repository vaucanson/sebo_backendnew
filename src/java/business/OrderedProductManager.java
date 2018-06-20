
package business;

import dto.OrderLineDetails;
import dto.OrderedProduct;
import entity.ClientOrder;
import entity.ResupplyOrder;
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
public class OrderedProductManager
{
    @PersistenceContext
    EntityManager em;
    
    /**
     * Constructeur vide par défaut
     */
    public OrderedProductManager() {
        // rien
    }
    
    /**
     * EN COURS !!!!!!!!!
     * @return 
     */
    public List<OrderedProduct> getOrderedProductsList() {
        List<OrderedProduct> list = null;
        
        String strQuery = "select new dto.OrderedProduct ("
                + "p.reference, p.name, "
                + "p.category.name, p.supplier.name, "
                + "sol.quantity "
                + ") from SupplierOrderLine sol "
                + "join sol.order o "
                + "join sol.product p "
                + "where o.orderState = :state";
        try {
            Query query = em.createQuery(strQuery);
            query.setParameter("state", ResupplyOrder.ORDERED);
            list = query.getResultList();
        }
        catch (PersistenceException pe) {
            System.out.println(pe.getMessage());
        }
                
        return list;
    }
    
    /**
     * Liste des lignes de commandes pour une commande donnée
     * @param id : la commande concernée
     * @return renvoi une liste de lignes de commandes
     * private int id;
     * private String nom;
     * private String cat;
     */
//    public List<dto.OrderLineDetails> getOrderLinesByOrderId(int id)
//    {
//        String strSql = "select new dto.OrderLineDetails("
//                + "p.id, p.name, p.category.name, o.quantity, p.stock"
//                + ") from OrderLine o join o.product p where o.clientOrder = :anId";
//        List<OrderLineDetails> list = null;
//        try{
//            Query query = em.createQuery(strSql);
//            ClientOrder ord = em.find (ClientOrder.class, id);
//            query.setParameter("anId", ord);
//            
//            list = query.getResultList();
//        }
//        catch (PersistenceException pe)
//        {
//            System.out.println("Problem retrieving OrderLines list : " + pe.getMessage());
//        }
//        
//        return list;
//    }
    
 
}
