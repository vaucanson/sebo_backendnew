package business;

import entity.Product;
import entity.ResupplyOrder;
import entity.Supplier;
import entity.SupplierOrderLine;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author boilleau
 */
@Stateless
public class Stock
{
    @PersistenceContext
    EntityManager em;

    /**
     * Renvoie la commande fournisseur d'id donné
     *
     * @param id
     * @return
     */
    public ResupplyOrder findResupply(int id) {
        ResupplyOrder ro = em.find(ResupplyOrder.class, id);
        
        List<SupplierOrderLine> myLinesList = new ArrayList<>();
        String sqlStr = "select sol from SupplierOrderLine sol where sol.order = :thisorder";
        try 
        {
            Query query = em.createQuery(sqlStr);
            query.setParameter ("thisorder", ro);
            myLinesList = query.getResultList();
        }
        catch (Exception e) 
        {
            e.printStackTrace();
        }
        
        ro.setSupplierOrderLines(myLinesList);
        
        return ro;
    }

    /**
     * Donne la liste des commandes fournisseur pour le fournisseur donné.
     *
     * @param idSupplier
     * @return
     */
    public List<ResupplyOrder> findResuppliesBySupplier(int idSupplier)
    {
        // on cherche le supplier, pour donner ensuite en paramètre
        Supplier myParamSuppl = em.find(Supplier.class, idSupplier);
        
        List<ResupplyOrder> retour = new ArrayList<>();
        String sqlStr = "select ro from ResupplyOrder ro where ro.supplier = :suppl";
        try 
        {
            Query query = em.createQuery(sqlStr);
            // le paramètre est notre Supplier, et pas juste son id
            query.setParameter ("suppl", myParamSuppl);
            retour = query.getResultList();
        }
        catch (Exception e) 
        {
            e.printStackTrace();
        }
        return retour;
    }
    
    /**
     * 
     * @return 
     */
    public List<SupplierOrderLine> getSupplierLines()
    {
       List<SupplierOrderLine> list = new ArrayList<>();
       String strSQL = "select li from SupplierOrderLine where RessuplyOrder = null";
       
       try {
           Query query = em.createQuery(strSQL);
           list = query.getResultList();
       }
       catch (Exception e)
       {
           e.printStackTrace();
       }
       return list;
    }

    /**
     * Envoie la commande fournisseur donnée au fournisseur.
     * @param SupplierLineList est une liste de ligne de commande fournisseur
     */
    public void order(List<SupplierOrderLine> SupplierLineList)
    {
        ResupplyOrder ro = new ResupplyOrder();
        ro.setSupplier(SupplierLineList.get(0).getProduct().getSupplier());
        
        for (SupplierOrderLine line : SupplierLineList) {
            line.setOrder(ro);
        }
        
        ro.setEtat(ResupplyOrder.ORDERED);
    }

    /**
     * Met la commande fournisseur donnée en état "reçu"
     * @param res une commande fournisseur
     */
    public void receive(ResupplyOrder res)
    {
        res.setEtat(ResupplyOrder.RECEIVED);
    }


    /**
     * Crée une ligne de commande avec le produit et la quantité donnés
     * @param p un produit à commander
     * @param quantity la quantité à commander
     * @return un int : 0 si tout s'est bien passé, une autre valeur sinon
     */
    public int newResupplyLine(Product p, int quantity)
    {
        // on crée une ligne de commande avec le produit et la quantité donnés
        SupplierOrderLine sol = new SupplierOrderLine(p, quantity);
        
        int retour = 0;
        try
        {
            em.persist(sol);
        }
        catch (Exception e)
        {
            retour = 1;
            e.printStackTrace();
        }
        
        return retour;
    }


    /**
     * Passe la commande fournisseur donnée en état ORDERED, et
     * lui donne un numéro en la persistant
     * @param ro 
     */
    public void setOrderNum(ResupplyOrder ro)
    {
        ro.setEtat(ResupplyOrder.ORDERED);
        em.persist(ro);
    }
    
    
    /**
     * Donne une commande fournisseur sans numéro, contenant la liste des 
     * lignes de commande fournisseur non commandées et
     * destinées au fournisseur donné.
     * @param supplierId int id du fournisseur voulu
     * @return une ResupplyOrder sans numéro et en état CREATED
     */
    public ResupplyOrder supplierOrderLinesBySupplier(int supplierId) {
        
        // on crée une commande fournisseur avec pour fournisseur
        // celui donné
        ResupplyOrder ro = new ResupplyOrder();
        Supplier mySupplier = em.find(Supplier.class, supplierId);
        ro.setSupplier(mySupplier);
        
        // on cherche toutes les lignes de commande fournisseur qui n'ont pas de commande
        // Y'a mieux à faire que ci-dessous, par exemple en joinant directement dans la requête. 
        // À étudier.
        List<SupplierOrderLine> lines = new ArrayList<SupplierOrderLine>();
        String sqlStr = "select sol from SupplierOrderLine s where s.order = null";
        try {
            Query query = em.createQuery(sqlStr);
            lines = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // pour chaque ligne, si elle appartient à notre fournisseur, on l'ajoute à la commande
        for (SupplierOrderLine line : lines)
        {
            if (line.getProduct().getSupplier().getId() == supplierId) 
            {
                line.setOrder(ro);
            }
        }
        return ro;
    }

    /**
     * Renvoie la liste de tous les articles.
     *
     * @return
     */
    public List<Product> getStock()
    {
        List<Product> retour = new ArrayList<Product>();
        String sqlStr = "select pr from Product pr";
        try 
        {
            Query query = em.createQuery(sqlStr);
            retour = query.getResultList();
        }
        catch (Exception e) 
        {
            e.printStackTrace();
        }
        return retour;
    }

    /**
     * Renvoie la liste des articles dont la quantité en stock est inférieure à
     * leur limite.
     *
     * @return
     */
    public List<Product> getStockUnderLimit()
    {
        List<Product> retour = new ArrayList<Product>();
        String sqlStr = "select pr from Product pr where pr.stock <= pr.minLimit";
        try 
        {
            Query query = em.createQuery(sqlStr);
            retour = query.getResultList();
        }
        catch (Exception e) 
        {
            e.printStackTrace();
        }
        return retour;    
    }
}
