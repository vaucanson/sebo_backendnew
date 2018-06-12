/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import business.Catalog;
import javax.ejb.EJB;

import entity.Category;
import java.util.List;

import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
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
    public int add(Category category) {

        return cat.addCategory(category);

    }

    /**
     * Méthode permettant de supprimer une catégorie a notre catalogue
     *
     * @param name: le nom de la catégorie que nous souhaitons supprimer
     * @return un code de retour indiquant si la suppression s'est bien passée
     */
    @Path("remove/{name}")
    @DELETE
     @Consumes({MediaType.APPLICATION_JSON}) //pas obligé ici (testé et approuvé et pipozé)
    public int remove(@PathParam ("name") String name) {
            
        return cat.removeCategory(name);
      
    }

    /**
     * Méthode renvoyant l'ensemble des catégories que nous possédons
     *
     * @return un Json contenant notre liste
     */
    @Path("getList")
    @GET
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
<<<<<<< HEAD
    @Path("getCategory/{name}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Category getCategory(@PathParam ("name") String name) {
=======
    @Path("getList/{category}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Category getCategory(@PathParam("category") String name) {
>>>>>>> master

        return cat.getCategory(name);
    }
}
