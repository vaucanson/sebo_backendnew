/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import business.Catalog;
import com.fasterxml.jackson.databind.ObjectMapper;
import entity.Product;
import java.io.IOException;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;

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
    public int update(String product) {

        int retour = 1;
        try {
            ObjectMapper mapper = new ObjectMapper();
            Product p = mapper.readValue(product, Product.class);
            cat.updateProduct(p);
            retour = 0;

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return retour;
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
    public int add(String product) {

        int retour = 1;
        try {
            ObjectMapper mapper = new ObjectMapper();
            Product p = mapper.readValue(product, Product.class);
            cat.addProduct(p);
            retour = 0;

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return retour;
    }

    /**
     * Supprime un produit du catalogue
     *
     * @param productId est l'Id du produit que nous devons supprimer
     * @return un code de retour indiquant que l'opération s'est bien passée
     */
    @Path("remove")
    @DELETE
    public int remove(int productId) {

        int retour = 1;

           if (cat.removeProduct(productId) == 0)
            retour = 0;
           
        return retour;
    }
}
