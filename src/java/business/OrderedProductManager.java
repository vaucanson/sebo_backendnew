
package business;

import dto.OrderedProduct;
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
     * Renvoie la liste des OrderedProducts dont la commande est en état ORDERED
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
 
}
