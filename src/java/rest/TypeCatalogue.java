/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import business.Catalog;
import com.fasterxml.jackson.databind.ObjectMapper;
import entity.Type;
import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
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
@Path("typecatalogue")
public class TypeCatalogue {

    @EJB
    Catalog cat;

    /**
     * Méthode permettant d'ajouter un nouveau genre à notre catalogue
     *
     * @param type : le genre que nous souhaitons ajouter a notre collection en
     * format json
     * @return un code de retour indiquant si l'ajout s'est bien passé
     */
    @Path("add")
    @Consumes("application/json")
    @POST
    public int add(String type) {

        int retour = 1;
        try {
            ObjectMapper mapper = new ObjectMapper();
            Type t = mapper.readValue(type, Type.class);
            cat.addType(t);
            retour = 0;

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return retour;
    }

    /**
     * Méthode permettant de supprimer un genre a notre catalogue
     *
     * @param name : le nom du genre que nous souhaitons supprimer
     * @return un code de retour indiquant si la suppression s'est bien passé
     */
    @Path("remove")
    @DELETE
    public int remove(String name) {

        int retour = 1;

        if (cat.removeType(name) == 0) {
            retour = 0;
        }

        return retour;
    }

    /**
     * Méthode permettant de mettre a jour un genre
     *
     * @param type : les informations du genre à mettre a jour
     * @return un code de retour indiquant si l'update s'est bien passé
     */
    @Path("update")
    @PUT
    public int update(String type) {

        int retour = 1;
        try {
            ObjectMapper mapper = new ObjectMapper();
            Type t = mapper.readValue(type, Type.class);
            cat.updateType(t);
            retour = 0;

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return retour;
    }

    /**
     * Méthode qui retourne l'ensemble des genre en promotions
     *
     * @return un Json
     */
    @Path("getsaledtypes")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<Type> getListBySales() {

        return cat.getSaledTypes();
    }

    /**
     * Méthode renvoyant l'ensemble des types que nous possédons
     *
     * @return une liste de tous les types
     */
    @Path("getList")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<Type> getList() {

        return cat.getTypes();
    }
}
