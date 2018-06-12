/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import business.Catalog;
import com.fasterxml.jackson.databind.ObjectMapper;
import entity.Sale;
import entity.Type;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
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
@Path("salesmanager")
public class SalesManager {

    @EJB
    Catalog cat;

    /**
     * Méthode permettant de définir une nouvelle promotion qui s'appliquera à
     * un genre d'articles
     *
     * @param sale :Json représentant la nouvelle promotion
     * @return un code de retour qui indique si tout s'est bien passé
     */
    @Path("add")
    @Consumes("application/json")
    @POST
    public int add(Sale mysale) {
        int retour = 1;
        try {
            cat.addSale(mysale);
            retour = 0;

        } catch (Exception ex) {
            Logger.getLogger(SalesManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retour;
    }

    /**
     * Méthode permettant de supprimer une promotion
     *
     * @param id : id de la promotion à supprimer
     * @return un code de retour qui indique si tout s'est bien passé
     */
    @Path("remove/{id}")
    @DELETE
    public int remove(@PathParam("id") int id) {
        int retour = 1;
        
        if (cat.removeSale(id) == 0) {
            retour = 0;
        }
        return retour;
    }

    /**
     * Méthode permettant de metre à jour une promotion
     *
     * @param sale : liste des parametres composant la nouvelle promotion
     * @return un code de retour qui indique si tout s'est bien passé
     */
    @Path("update")
    @Consumes("application/json")
    @POST
    public int update(Sale mysale) {
        int retour = 1;
        try {
            retour = cat.updateSale(mysale);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
         
         return retour;
    } 

    /**
     * Méthode renvoyant l'ensemble des genres d'articles qui ont actuellement
     * une promotion
     *
     * @return un Json comprenant l'ensemble de la liste
     */
    @Path("gettype")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<Type> getTypeInSales() {

        return cat.getSaledTypes();
    }
    
    /**
     * Methode renvoyant une promotion 
     * @param id : id de la promotion désirée
     * @return un Json représentant la promotion
     */
    @Produces({MediaType.APPLICATION_JSON})
    @Path("getsale/{id}")
    @GET
    public Sale getSale(@PathParam("id") int id)
    {
        return cat.getSale(id);
    }

}
