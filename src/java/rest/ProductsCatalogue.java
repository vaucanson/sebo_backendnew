/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import business.AddressManager;
import business.Catalog;
import business.ClientOrderManager;
import business.PaymentManager;
import business.SupplierBusiness;
import entity.Address;
import entity.Category;
import entity.ClientAccount;
import entity.ClientOrder;
import entity.OrderLine;
import entity.PaymentMethod;
import entity.Product;
import entity.Supplier;
import entity.Type;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author badaroux
 */
@Stateless
@Path("productscatalogue")
public class ProductsCatalogue {

    @EJB Catalog cat;
    @EJB SupplierBusiness sb;
    @EJB business.ClientAccount ca;
    @EJB AddressManager am;
    @EJB PaymentManager pm;
    @EJB ClientOrderManager com;
    
    /**
     * Méthode renvoyant un article a partir de son id
     *
     * @param id : id de l'article demandé
     * @return un Json
     */
    @Path("getoneproduct/{id}")
    @GET
    @Produces({MediaType.APPLICATION_JSON}) 
    public Product getOneProduct(@PathParam("id") int id) {

        return cat.getProduct(id);
    }

    /**
     * Méthode qui retourne l'ensemble du catalogue d'articles
     *
     * @return un Json
     */
    @Path("getlist")
    @GET
    @Produces({MediaType.APPLICATION_JSON}) 
    public List<Product>getList() {

       return cat.getProducts();
    }
    
    


    /**
     * Méthode qui retourne l'ensemble des articles de meme categories
     *
     * @param category est le nom de la catégorie des articles que nous souhaitons
     * @return un Json
     */
    @Path("getlistbycategory/{category}")
    @GET
    @Produces({MediaType.APPLICATION_JSON}) 
    public List<Product> getListByCategory(@PathParam("category") String category) {

        return cat.getProductsByCategory(category);
    }

    /**
     * Méthode qui retourne l'ensemble des articles de meme genre
     *
     * @param type est le nom du genre des articles que nous souhaitons
     * @return un Json
     */
    @Path("getlistbytype/{type}")
    @GET
    @Produces({MediaType.APPLICATION_JSON}) 
    public List<Product> getListByType(@PathParam("type") String type) {

        return cat.getProductsByType(type);
    }

}
