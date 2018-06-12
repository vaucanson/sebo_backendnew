package business;

import entity.Category;
import entity.Product;
import entity.Sale;
import entity.Type;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJBTransactionRolledbackException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author mattar
 */
@Stateless
public class Catalog {

    @PersistenceContext
    EntityManager em;

    public Catalog() {
    }

    /**
     * Cette méthode permet de rechercher un produit dans la base de donnée
     *
     * @param ref est la référence de l'objet "Product" (article) qu'on
     * recherche
     * @return un boolean disant si l'article est deja présent dans la base
     */
    public boolean findProduct(String ref) {
        boolean ret = false;

        String sqlStr = "select a from Product a where a.reference = :aRef";
        Product product;

        Query query = em.createQuery(sqlStr);
        query.setParameter("aRef", ref);

        try {
            product = (Product) query.getSingleResult();

            if (product.getReference().equals(ref)) {
                ret = true;
            }
        } catch (NoResultException nre) {
            System.out.println("Product reference research : " + nre.getMessage());
        }
        return ret;
    }

    /**
     * Cette méthode permet d'ajouter un nouvel article au catalogue
     *
     * @param p : est l'objet "Product" qui sera ajouté
     */
    public int addProduct(Product p) {
        int codeRet = 1;

        if (!findProduct(p.getReference())) {
            em.persist(p);
            codeRet = 0;
        }

        return codeRet;
    }

    /**
     * Cette méthode permet de retirer un article du catalogue
     *
     * @param idProduct : est l'identifiant du produit qui sera retiré
     * @return
     */
    public int removeProduct(int idProduct) {
        int codeRet = 1;

        try {
            Product p = em.find(Product.class, idProduct);
            if (p != null && p.getId() == idProduct) {
                em.remove(p);
                codeRet = 0;
            }
        } catch (NoResultException nre) {
            System.out.println(nre.getMessage());
        }

        return codeRet;
    }

    /**
     * Cette méthode permet de mettre à jour les informations d'un article du
     * catalogue
     *
     * @param p : est l'objet Product qui sera mis à jour
     * @return
     */
    public int updateProduct(Product p) {
        int codeRet = 1;

        try {
            em.merge(p);

        } catch (NoResultException nre) {
            System.out.println(nre.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return codeRet;
    }
    
        /**
     * Cette méthode permet de mettre à jour la quantité d'un article du
     * catalogue
     *
     * @param ref : la référence qui sera mise à jour
     * @param Qty : la quantité à ajouter
     * @return
     */
    public int updateProductQty(String ref, int Qty) {
        int codeRet = 1;
        Product product;
        if (findProduct(ref)) {
            product = this.getProduct(ref);
            product.setStock(product.getStock()+Qty);
            em.merge(product);
            codeRet = 0;
        }
        return codeRet;
    }

    /**
     * Cette méthode permet de retrouver un article à partir de son identifiant
     *
     * @param idProduct : est l'identifiant de l'article recherché
     * @return l'objet "Product" (article) correspondant à l'identifiant
     */
    public Product getProduct(int idProduct) {
        
        Product product = null;

        try
      {        
         product = em.find(Product.class, idProduct);
      }
      catch (IllegalArgumentException iae)
      {
          System.out.println(iae.getMessage());
      }
      catch (Exception e)
      {
          System.out.println(e.getMessage());
      }
        
        return product;
    }
    
        /**
     * Cette méthode permet de retrouver un article à partir de son identifiant
     *
     * @param ref : est l'identifiant de l'article recherché
     * @return l'objet "Product" (article) correspondant à l'identifiant
     */
    public Product getProduct(String ref) {
        String sqlStr = "select p from Product p where p.reference = :aRef";
        Product product = null;

        Query query = em.createQuery(sqlStr);
        query.setParameter("aRef", ref);

        try {
            product = (Product) query.getSingleResult();
        } catch (NoResultException nre) {
            System.out.println(nre.getMessage());
        }

        return product;
    }

    /**
     * Cette méthode permet de récupérer la liste de tous les articles du
     * catalogue
     *
     * @return renvoie une liste des articles
     */
    public List<Product> getProducts() {

        String sqlStr = "select a from Product a";
        List<Product> productsList = new ArrayList<>();

        Query query = em.createQuery(sqlStr);

        try {
            productsList = (List) query.getResultList();
            
        } catch (NoResultException nre) {
            System.out.println(nre.getMessage());
        }

        return productsList;
    }

    /**
     * Cette méthode permet de récupérer une liste d'articles d'un genre précis
     *
     * @param nameType : est le nom du genre qui servira de filtre
     * @return renvoie une liste de "Product" (article) contenant le genre
     * concerné
     */
    public List<Product> getProductsByType(String nameType) {
        String sqlStr = "select a from Product a where a.type = :aType";
        List<Product> productsList = new ArrayList<>();

        Query query = em.createQuery(sqlStr);

        Type typ = em.find(Type.class, nameType);

        query.setParameter("aType", typ);

        try {
            productsList = (List) query.getResultList();
        } catch (NoResultException nre) {
            System.out.println(nre.getMessage());
        } catch (EJBTransactionRolledbackException etre) {
            System.out.println(etre.getMessage());
        }

        return productsList;

    }

    /**
     * Cette méthode permet de récupérer une liste d'articles d'une catégorie
     * précise
     *
     * @param nameCat : est le nom de la catégorie qui servira de filtre
     * @return renvoie une liste de "Product" (article) concernant la catégorie
     * concernée
     */
    public List<Product> getProductsByCategory(String nameCat) {
        String sqlStr = "select a from Product a where a.category = :aCategory";
        List<Product> productsList = new ArrayList<>();

        Query query = em.createQuery(sqlStr);

        Category cat = em.find(Category.class, nameCat);

        query.setParameter("aCategory", cat);

        try {
            productsList = (List) query.getResultList();
        } catch (NoResultException nre) {
            System.out.println(nre.getMessage());
        } catch (EJBTransactionRolledbackException etre) {
            System.out.println(etre.getMessage());
        }

        return productsList;
    }

    /**
     * Cette méthode permet d'ajouter un nouveau Genre dans la base
     *
     * @param t est l'objet "Type" (genre) qui sera enregistré
     * @return
     */
    public int addType(Type t) {
        int codeRet = 1;

        if (em.find(Type.class, t.getName()) == null) {
            em.persist(t);
            codeRet = 0;
        } else {
            System.out.println("Type already exists");
        }

        return codeRet;
    }

    /**
     * Cette méthode permet de retirer un Genre existant de la base
     *
     * @param name est le nom du "Type" (genre) qui sera retiré
     * @return
     */
    public int removeType(String name) {
        int codeRet = 1;

        try {
            Type typ = em.find(Type.class, name);

            if (typ != null) {
                em.remove(typ);
                codeRet = 0;
            } else {
                System.out.println("Type does not exist");
            }
        } catch (NoResultException nre) {
            System.out.println(nre.getMessage());
        }

        return codeRet;
    }

    /**
     * Cette méthode permet de mettre à jour les informations d'un Genre entré
     * en base
     * La seule information qui peut être changée est la promotion ("Sale") appliquée sur le "Type" (genre)
     *
     * @param t est l'objet "Type" (genre) concerné par la modification
     * @return
     */
    public int updateType(Type t) {
        int codeRet = 1;

        if (em.find(Type.class, t.getName()) != null) 
        {
            em.merge(t);
            codeRet = 0;
        } else 
        {
            System.out.println("Type does not exist");
        }

        return codeRet;
    }

    /**
     * Cette méthode permet de retrouver un Genre à partir de son nom
     *
     * @param name est le nom du Genre recherché
     * @return renvoie l'objet "Type" (genre) trouvé via son nom
     */
    public Type getType(String name) {

        Type typ = null;

        try
      {        
         typ = em.find(Type.class, name);
      }
      catch (IllegalArgumentException iae)
      {
          System.out.println(iae.getMessage());
      }
      catch (Exception e)
      {
          System.out.println(e.getMessage());
      }
        
        
        return typ;
    }

    /**
     * Cette méthode permet de récupérer la liste des Genres
     *
     * @return renvoie une liste de "Type" (genre)
     */
    public List<Type> getTypes() {
        String sqlStr = "select a from Type a";
        List<Type> typesList = new ArrayList<>();

        Query query = em.createQuery(sqlStr);

        try {
            typesList = (List) query.getResultList();
        } 
        catch (NoResultException nre) 
        {
            System.out.println(nre.getMessage());
        }

        return typesList;
    }

    /**
     * Cette méthode permet d'ajouter une nouvelle Catégorie à la base
     *
     * @param cat est l'objet "Category" qui sera ajouté
     */
    public int addCategory(Category cat) {

        int codeRet = 1;

        if (em.find(Category.class, cat.getName()) == null) {
            em.persist(cat);
            codeRet = 0;
        } 
        else 
        {
            System.out.println("Category already exists");
        }
        return codeRet;
    }

    /**
     * Cette méthode permet de retirer une Catégorie existante de la base
     *
     * @param name est le nom de la Catégorie qui sera retirée
     */
    public int removeCategory(String name) {
        int codeRet = 1;

        try {
            Category cat = em.find(Category.class, name);

            if (cat != null) {
                em.remove(cat);
                codeRet = 0;
            } else {
                System.out.println("Category does not exist");
            }
        } catch (NoResultException nre) {
            System.out.println(nre.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return codeRet;
    }

    /**
     * Cette méthode permet de mettre à jour les informations d'une Catégorie
     * existante en base
     *
     * @param cat est l'objet "Category" concerné par la modification
     */
    public int updateCategory(Category cat) {
        int codeRet = 1;

        if (em.find(Category.class, cat.getName()) != null) {
            em.merge(cat);
            codeRet = 0;
        } else {
            System.out.println("Category does not exist");
        }

        return codeRet;
    }

    /**
     * Cette méthode permet de retrouver une Catégorie via son nom
     *
     * @param name est le nom de la Catégorie recherchée
     * @return renvoie l'objet "Category" correspondant au nom entré
     */
    public Category getCategory(String name) {

        
        Category cat = null;

       try
      {        
         cat = em.find(Category.class, name);
      }
      catch (IllegalArgumentException iae)
      {
          System.out.println(iae.getMessage());
      }
      catch (Exception e)
      {
          System.out.println(e.getMessage());
      }
                
        return cat;
    }

    /**
     * Cette méthode permet de récupérer la liste des Catégories enregistrées en
     * base
     *
     * @return renvoie une liste de "Category"
     */
    public List<Category> getCategoryList() {
        String sqlStr = "select a from Category a";
        List<Category> catList = null;

        Query query = em.createQuery(sqlStr);

        try {
            catList = (List) query.getResultList();
        } catch (NoResultException nre) {
            System.out.println(nre.getMessage());
        }

        return catList;
    }

    /**
     * Cette méthode permet d'ajouter une nouvelle promotion à la base
     *
     * @param s est l'objet "Sale" (promotion) qui sera enregistré en base
     */
    public int addSale(Sale s) {
        int codeRet = 1;

        if (em.find(Sale.class, s.getId()) == null) {
            em.persist(s);
            codeRet = 0;
        } else {
            System.out.println("Sale already exists");
        }

        return codeRet;
    }

    /**
     * Cette méthode permet de retirer une promotion existante de la base
     *
     * @param idSale est l'identifiant de l'objet "Sale" (promotion) qui sera
     * retiré
     */
    public int removeSale(int idSale) {
        int codeRet = 1;

        try {
            Sale sale = em.find(Sale.class, idSale);

            if (sale == null) {
                System.out.println("Sale does not exist");
               
            } else 
            {
                em.remove(sale);
                codeRet = 0;
            }
        } catch (NoResultException nre) {
            System.out.println(nre.getMessage());
        }

        return codeRet;
    }

    /**
     * Cette méthode permet de mettre à jour les informations d'une promotion
     * existante en base
     *
     * @param s est l'objet "Sale" (promotion) qui sera modifié
     */
    public int updateSale(Sale s) {
        int codeRet = 1;    
        
        try
        {
            if (em.find(Sale.class, s.getId()) == null) {
                System.out.println("Sale does not exist");

            } else {
                 em.merge(s);
                codeRet = 0;
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

        return codeRet;
    }
    
    /**
     * Cette méthode permet de retrouver une promotion enregistrée dans la base
     * @param id est l'identifiant de l'objet "Sale" (promotion) recherché
     * @return renvoie l'objet "Sale" (promotion) trouvé, s'il existe en base
     */
    public Sale getSale(int id)
    {
        Sale sale = null;
        
      try
      {        
         sale = em.find(Sale.class, id);
      }
      catch (IllegalArgumentException iae)
      {
          System.out.println(iae.getMessage());
      }
      catch (Exception e)
      {
          System.out.println(e.getMessage());
      }
        
        return sale;
    }
    

    /**
     * Cette méthode permet de récupérer une liste des Genres ("Type") en
     * promotion ("Sale")
     *
     * @return renvoie une liste d'objets "Type" (genre) concernés par une
     * promotion
     */
    public List<Type> getSaledTypes() {

        String sqlStr = "select a from Type a where a.sale is not null";
        List<Type> saledTypeList = null;

        Query query = em.createQuery(sqlStr);

        try {
            saledTypeList = (List) query.getResultList();
        } catch (NoResultException nre) {
            System.out.println(nre.getMessage());
        }

        return saledTypeList;
    }
}
