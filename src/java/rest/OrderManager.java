/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import business.ClientOrderManager;
import entity.ClientOrder;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
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
@Path("ordermanager")
public class OrderManager {

    @EJB
    ClientOrderManager com;

    /**
     * Méthode renvoyant l'ensemble des commandes qu'un client a effectué.
     *
     * @param custommerId : id du client
     * @return un Json comprenant la liste de commande
     */
    @Path("getordersbycustommer/{custommerId}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<ClientOrder> getOrdersByCustommer(@PathParam("custommerId") int custommerId) {
        return com.getByCustomer(custommerId);
    }

    /**
     * Méthode renvoyant l'ensemble la commande associé à l'id
     *
     * @param id : id de la commande
     * @return un Json comprenant les informations de la commande
     */
    @Path("getorderbyid/{id}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public ClientOrder getOrderById(@PathParam("id") int id) {
        return com.getById(id);
    }

    /**
     * Méthode renvoyant l'ensemble des commandes qui sont d'un même état
     *
     * @param orderState : état de la commande désiré
     * @return un Json qui contient la liste des commandes
     */
    @Path("getordersbystate/{state}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<ClientOrder> getOrdersByState(@PathParam("state") int orderState) {
        return com.getByState(orderState);
    }

    /**
     * Méthode permettant de passer une commande en état envoyé
     *
     * @param order est un Json représentant une commande
     * @return un code de retour si l'opération s'est bien passée.
     */
    @Path("send")
    @PUT
    @Consumes("application/json")
    public int send(ClientOrder order) {
        return com.setState(4, order.getId());
    }

    /**
     * Méthode permettant de passer une commande en état envoyé partiellement
     *
     * @param order est un Json représentant une commande
     * @return un code de retour si l'opération s'est bien passée.
     */
    @Path("partialsend")
    @PUT
    @Consumes("application/json")
    public int partialSend(ClientOrder order) {
        return com.setState(5, order.getId());
    }

    /**
     * Méthode permettant de passer une commande en état annulé
     *
     * @param order est un Json représentant une commande
     * @return un code de retour si l'opération s'est bien passée.
     */
    @Path("cancel")
    @PUT
    @Consumes("application/json")
    public int cancel(ClientOrder order) {
        return com.setState(-1, order.getId());
    }

    /**
     * Méthode permettant de passer une commande en état d'attente
     *
     * @param order est un Json représentant une commande
     * @return un code de retour si l'opération s'est bien passée.
     */
    @Path("standBy")
    @PUT
    @Consumes("application/json")
    public int standBy(ClientOrder order) {
        return com.setState(6, order.getId());
    }

    /**
     * Méthode permettant de passer une commande en état fermé
     *
     * @param order est un Json représentant une commande
     * @return un code de retour si l'opération s'est bien passée.
     */
    @Path("close")
    @PUT
    @Consumes("application/json")
    public int close(ClientOrder order) {
        return com.setState(7, order.getId());
    }

    /**
     * Méthode permettant de passer une commande en état "en cours de
     * préparation"
     *
     * @param order est un Json représentant une commande
     * @return un code de retour si l'opération s'est bien passée.
     */
    @Path("prepare")
    @PUT
    @Consumes("application/json")
    public int prepare(ClientOrder order) {
        return com.setState(3, order.getId());
    }

}
