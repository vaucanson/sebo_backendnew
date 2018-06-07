/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import entity.Supplier;
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
@Path("suppliermanager")
public class SupplierManager {

    /**
     * Méthode permettant d'ajouter un nouveau fournisseur à notre catalogue
     *
     * @param Json : le fournisseur que nous souhaitons ajouter a notre collection
     * en format json
     * @return un code de retour indiquant si l'ajout s'est bien passé
     */
    @Path("add")
    @Consumes("application/json")
    @POST
    public int add(String Json) {

        return 0;
    }

    /**
     * Méthode permettant de supprimer un fournisseur a notre catalogue
     *
     * @param id : l'id du fournisseur que nous souhaitons supprimer
     * @return un code de retour indiquant si la suppression s'est bien passé
     */
    @Path("remove")
    @DELETE
    public int remove(int id) {

        return 0;
    }

    /**
     * Méthode permettant de mettre a jour un fournisseur
     *
     * @param Json : les informations du fournisseur à mettre a jour
     * @return un code de retour indiquant si la mise à jour s'est bien passé
     */
    @Path("update")
    @PUT
    public int update(String Json) {

        return 0;
    }

    /**
     * Méthode renvoyant l'ensemble des fournisseur que nous possédons
     * @return un Json comprenant la liste des fournisseurs
     */
    @Path("getList")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<Supplier> getList() {

        return null;
    }
}
