/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import business.Catalog;
import entity.Product;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

/**
 *
 * @author badaroux
 */
@Stateless
@Path("productsmanager")
public class ProductsManager {

    @EJB
    Catalog cat;

    /**
     * Met a jour un produit dans le catalogue
     *
     * @param product est un Json contenant les informations du produit à mettre
     * à jour
     * @return un code de retour indiquant que l'opération s'est bien passée
     */
    @Path("update")
    @PUT
    @Consumes("application/json")
    public int update(Product product) {
        return cat.updateProduct(product);
    }

    /**
     * Ajouter un produit au catalogue
     *
     * @param product est un Json contenant les informations du produit à mettre
     * à jour
     * @return un code de retour indiquant que l'opération s'est bien passée
     */
    @Path("add")
    @Consumes("application/json")
    @POST
    public int add(Product product) {
        return cat.addProduct(product);  
    }

    /**
     * Supprime un produit du catalogue
     *
     * @param productId est l'Id du produit que nous devons supprimer
     * @return un code de retour indiquant que l'opération s'est bien passée
     */
    @Path("remove/{productId}")
    @DELETE
    public int remove(@PathParam("productId") int productId) {
           return cat.removeProduct(productId);
    }
}
