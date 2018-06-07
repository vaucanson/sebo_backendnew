/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import business.Catalog;
import business.SupplierBusiness;
import com.fasterxml.jackson.databind.ObjectMapper;
import entity.Product;
import entity.Sale;
import entity.Supplier;
import entity.Type;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;

import entity.Category;
import entity.ClientAccount;
import java.util.List;

import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author badaroux
 */
@Stateless
@Path("categorycatalogue")
public class CategoryCatalogue {

    @EJB
    Catalog cat;

    /**
     * Méthode permettant d'ajouter une nouvelle catégorie à notre catalogue
     *
     * @param category : la catégorie que nous souhaitons ajouter a notre
     * collection en format json
     * @return un code de retour indiquant si l'ajout s'est bien passée
     */
    @Path("add")
    @Consumes({MediaType.APPLICATION_JSON})
    @POST
    public int add(String category) {

        int retour = 1;
        try {
            ObjectMapper mapper = new ObjectMapper();
            Category c = mapper.readValue(category, Category.class);
            cat.addCategory(c);
            retour = 0;

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return retour;
    }

    /**
     * Méthode permettant de supprimer une catégorie a notre catalogue
     *
     * @param name: le nom de la catégorie que nous souhaitons supprimer
     * @return un code de retour indiquant si la suppression s'est bien passée
     */
    @Path("remove")
    @DELETE
    public int remove(String name) {

        int retour = 1;

        if (cat.removeCategory(name) == 0) {
            retour = 0;
        }

        return retour;
    }

    /**
     * Méthode permettant de mettre a jour une catégorie
     *
     * @param category : les informations de la catégorie à mettre a jour
     * @return un code de retour indiquant si la mise à jour s'est bien passée
     */
    @Path("update")
    @PUT
    public int update(String category) {

        int retour = 1;
        try {
            ObjectMapper mapper = new ObjectMapper();
            Category c = mapper.readValue(category, Category.class);
            cat.updateCategory(c);
            retour = 0;

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return retour;
    }

    /**
     * Méthode renvoyant l'ensemble des catégories que nous possédons
     *
     * @return un Json contenant notre liste
     */

    @Produces({MediaType.APPLICATION_JSON})
    public List<Category> getList() {

        return cat.getCategoryList();
    }
    
    
        /**
     * Méthode renvoyant une catégorie que nous possédons
     *
     * @param name est le nom de la catégorie que nous souhaitons obtenir
     * @return un Json contenant notre catégorie
     */
    @Path("getList")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Category getCategory(String name) {

        return cat.getCategory(name);
    }
}
