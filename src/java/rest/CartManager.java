/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import business.ClientOrderManager;
import com.fasterxml.jackson.databind.ObjectMapper;
import entity.ClientOrder;
import entity.PaymentMethod;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author badaroux
 */
@Stateless
@Path("cartmanager")
public class CartManager {

    @EJB
    ClientOrderManager com;

    /**
     * Permet de valider un panier de le transformer en commande
     *
     * @param order est un Json contenant toute les informations de la commande
     * @return un code de retour indiquant si l'opération s'est bien passée
     */
    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("confirm")
    public int confirm(ClientOrder order) {

            return com.create(order);
    }

    /**
     * Valide auprès de la banque que le compte du client permet de couvrir
     * l'achat
     *
     * @param paymentMethod est un Json contenant les informations bancaires du
     * client
     * @return un code de retour indiquant si l'opération est validé ou non par
     * la banque
     */
    @PUT
    @Path("pay")
    public int pay(String paymentMethod) {
        
       return 0;
    }

}
