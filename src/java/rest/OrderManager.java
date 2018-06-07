/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import business.ClientOrderManager;
import com.fasterxml.jackson.databind.ObjectMapper;
import entity.Category;
import entity.ClientOrder;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
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
    @Path("getOrdersByCustommer/{custommerId}")
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
    @Path("getOrderById/{id}")
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
    @Path("getOrdersByState/{state}")
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
    public int send(String order) {

        int retour = 1;
        try {
            ObjectMapper mapper = new ObjectMapper();
            ClientOrder co = mapper.readValue(order, ClientOrder.class);

            com.setState(4, co.getId());
            retour = 0;

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return retour;
    }

    /**
     * Méthode permettant de passer une commande en état envoyé partiellement
     *
     * @param order est un Json représentant une commande
     * @return un code de retour si l'opération s'est bien passée.
     */
    @Path("partialsend")
    @PUT
    public int partialSend(String order) {

        int retour = 1;
        try {
            ObjectMapper mapper = new ObjectMapper();
            ClientOrder co = mapper.readValue(order, ClientOrder.class);

            com.setState(5, co.getId());
            retour = 0;

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return retour;
    }

    /**
     * Méthode permettant de passer une commande en état annulé
     *
     * @param order est un Json représentant une commande
     * @return un code de retour si l'opération s'est bien passée.
     */
    @Path("cancel")
    @PUT
    public int cancel(String order) {

        int retour = 1;
        try {
            ObjectMapper mapper = new ObjectMapper();
            ClientOrder co = mapper.readValue(order, ClientOrder.class);

            com.setState(-1, co.getId());
            retour = 0;

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return retour;
    }

    /**
     * Méthode permettant de passer une commande en état d'attente
     *
     * @param order est un Json représentant une commande
     * @return un code de retour si l'opération s'est bien passée.
     */
    @Path("standBy")
    @PUT
    public int standBy(String order) {

        int retour = 1;
        try {
            ObjectMapper mapper = new ObjectMapper();
            ClientOrder co = mapper.readValue(order, ClientOrder.class);

            com.setState(6, co.getId());
            retour = 0;

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return retour;
    }

    /**
     * Méthode permettant de passer une commande en état fermé
     *
     * @param order est un Json représentant une commande
     * @return un code de retour si l'opération s'est bien passée.
     */
    @Path("close")
    @PUT
    public int close(String order) {

        int retour = 1;
        try {
            ObjectMapper mapper = new ObjectMapper();
            ClientOrder co = mapper.readValue(order, ClientOrder.class);

            com.setState(7, co.getId());
            retour = 0;

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return retour;
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
    public int prepare(String order) {
        int retour = 1;
        try {
            ObjectMapper mapper = new ObjectMapper();
            ClientOrder co = mapper.readValue(order, ClientOrder.class);

            com.setState(3, co.getId());
            retour = 0;

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return retour;
    }

}
