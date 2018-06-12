/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import business.SupplierBusiness;
import com.fasterxml.jackson.databind.ObjectMapper;
import entity.Supplier;
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
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author badaroux
 */
@Stateless
@Path("suppliermanager")
public class SupplierManager {

    @EJB SupplierBusiness sb;
    
    /**
     * Méthode permettant d'ajouter un nouveau fournisseur à notre catalogue
     *
     * @param supplier : le fournisseur que nous souhaitons ajouter a notre collection
     * en format json
     * @return un code de retour indiquant si l'ajout s'est bien passé
     */
    @Path("add")
    @Consumes("application/json")
    @POST
    public int add(Supplier supplier) {
            return sb.add(supplier);
    }

    /**
     * Méthode permettant de supprimer un fournisseur a notre catalogue
     *
     * @param id : l'id du fournisseur que nous souhaitons supprimer
     * @return un code de retour indiquant si la suppression s'est bien passé
     */
    @Path("remove/{id}")
    @DELETE
    public int remove(@PathParam("id") int id) {
        return sb.remove(id);
    }

    /**
     * Méthode permettant de mettre a jour un fournisseur
     *
     * @param supplier : les informations du fournisseur à mettre a jour
     * @return un code de retour indiquant si la mise à jour s'est bien passé
     */
    @Path("update")
    @PUT
    public int update(Supplier supplier) {
            return sb.update(supplier);
    }

    /**
     * Méthode renvoyant l'ensemble des fournisseur que nous possédons
     * @return un Json comprenant la liste des fournisseurs
     */
    @Path("getlist")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<Supplier> getList() {

        return sb.getAll();
    }
}
