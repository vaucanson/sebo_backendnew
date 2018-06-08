/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author badaroux
 */
@Stateless
@Path("accountmanager")
public class AccountManager {
    
    /**
     * Vérifie si l'association login / password existe afin de connecter le client
     * @param login est l'identifiant saisi par le client
     * @param password est le mot de passe saisi par le client
     * @return un code de retour indiquant si l'as
     * sociation est bonne ou non afin d'autoriser ou pas la connection
     */
    @GET
    @Path("connect/{login}/{password}")
    public int connect(@PathParam("login") String login, @PathParam("password") String password)
    {
     
        return 0;
    }
    
    /**
     * Permet de créer un nouveau compte pour un client
     * @param customerAccount est un string présentant toutes les informations du nouveau compte à créer
     * @return un code de retour indiquant si la création s'est bien passé et que le mail indiqué n'est pas en doublon dans la base
     */
    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("create")
    public int create(String customerAccount)
    {
        ObjectMapper mapper = new ObjectMapper();
           
            
        return 0;
    }
}
