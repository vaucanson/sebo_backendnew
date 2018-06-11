/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import business.Catalog;
import business.Stock;
import com.fasterxml.jackson.databind.ObjectMapper;
import entity.ResupplyOrder;
import entity.SupplierOrderLine;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
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
@Path("resupplymanager")
public class ResupplyManager {

    @EJB Stock stock;
    @EJB Catalog cat;
    

    /**
     * Méthode qui permet de passer la commande au fournisseur en créant de
     * nouvelles lignes de commandes puis en leur attribuant un numéro de
     * commande
     *
     * @param SupplierLineList est un Json contenant toutes les lignes de commande fournisseur d'un meme fournisseur que l'on souhaite commander
     * @return un code de retour indiquant si l'opération s'est bien passée
     */
    @Path("order")
    @Consumes("application/json")
    @POST
    public int order(String SupplierLineList) {

        try {
            List<SupplierOrderLine> list = new ArrayList<>();
          
            ObjectMapper mapper = new ObjectMapper();
            list  = mapper.readValue(SupplierLineList, List.class);
            
            stock.order(list);
            
            return 0;
        } catch (IOException ex) {
            Logger.getLogger(ResupplyManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    @Path("createResupplyOrderLine")
    @Consumes("application/json")
    @POST
    public int createResupplyOrderLine(String orderLine) {
       int retour = 0;
        try {
            ObjectMapper mapper = new ObjectMapper();
            SupplierOrderLine resOrd = mapper.readValue(orderLine, SupplierOrderLine.class);
            
            if (stock.newResupplyLine(resOrd.getProduct(), resOrd.getQuantity()) == 0)
            {
                retour = 0;
            }
            else {
                retour = 1;
            }
            
        } catch (IOException ex) {
            Logger.getLogger(ResupplyManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retour;
    }

    /**
     * Méthode renvoyant un resupplyorder via son id
     *
     * @param resupplyOrderId : id de la resupplyorder
     * @return une commande fournisseur
     */
    @Path("find/{id}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public ResupplyOrder findById(@PathParam("custommerId") int resupplyOrderId) {

        ResupplyOrder ro = stock.findResupply(resupplyOrderId);

        return ro;
    }

    /**
     * Méthode renvoyant un resupplyorder via son supplier
     *
     * @param supplier est l'id du fournisseur
     * @return une commande fournisseur
     */
    @Path("findbysupplier/{supplier}")
    @GET
    public List<ResupplyOrder> findBySupplier(@PathParam("supplier") int supplier) {

        List<ResupplyOrder> ro = stock.findResuppliesBySupplier(supplier);

        return ro;
    }
    
   

    
    /**
     * Méthode permettant de rentrer en stock les articles d'une commande auprès
     * d'un fournisseur
     *
     * @param supplyOrder est un Json comprenant les informations de la commande
     * fournisseur
     * @return un code de retour indiquant si l'opération s'est bien passée
     */
    @Path("receive")
    @Consumes("application/json")
    @POST
    public int receive(String supplyOrder) {

        // On reconstruit l'entity ressuply order avant de la parcourir et de créer chacun des produits qu'elle contient
        try {
            ObjectMapper mapper = new ObjectMapper();
            ResupplyOrder resOrd = mapper.readValue(supplyOrder, ResupplyOrder.class);
            
            for (SupplierOrderLine line : resOrd.getSupplierOrderLines()) {
                // méthode catalogue a créer qui modifie une quantité 
            }
            
            resOrd.setReceptionDate(new Date());
            
            stock.receive(resOrd);

        } catch (IOException ex) {
            Logger.getLogger(ResupplyManager.class.getName()).log(Level.SEVERE, null, ex);
        }  
        return 0;
    }

}
