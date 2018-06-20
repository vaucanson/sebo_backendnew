/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import business.OrderedProductManager;
import business.Stock;
import entity.Product;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import dto.OrderedProduct;

/**
 *
 * @author badaroux
 */
@Stateless
@Path("stockmanager")
public class StockManager {

    @EJB
    Stock stock;
    
    @EJB
    OrderedProductManager opm;

    /**
     * Méthode qui permet de récupérer les articles et leur quantité
     *
     * @return un Json contenant toutes les informations
     */
    @GET
    @Path("getstock")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Product> getStock() {

        List<Product> list;
        list = stock.getStock();

        return list;
    }
    
    
    @GET
    @Path("getorderedproducts")
    @Produces({MediaType.APPLICATION_JSON})
    public List<OrderedProduct> getOrderedProducts() {

        List<OrderedProduct> list;
        list = opm.getOrderedProductsList();

        return list;
    }

    /**
     * Méthode qui permet de récupérer les articles ayant une quantité inférieur
     * à leur seuil
     *
     * @return un Json représentant toutes les informations
     */
    @GET
    @Path("getstockunderlimit")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Product> getStockUnderLimit() {
       
        List<Product> list;
        list = stock.getStockUnderLimit();

        return list;
    }
    

}
