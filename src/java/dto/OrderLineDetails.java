/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.io.Serializable;

/**
 *
 * @author mattar
 */
public class OrderLineDetails implements Serializable{
    private int id;
    private String name;
    private String category;
    private int quantityOrder;
    private int quantityStock;

    public OrderLineDetails() {
    }

    public OrderLineDetails(int id, String name, String category, int quantityOrder, int quantityStock) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.quantityOrder = quantityOrder;
        this.quantityStock = quantityStock;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getQuantityOrder() {
        return quantityOrder;
    }

    public void setQuantityOrder(int quantityOrder) {
        this.quantityOrder = quantityOrder;
    }

    public int getQuantityStock() {
        return quantityStock;
    }

    public void setQuantityStock(int quantityStock) {
        this.quantityStock = quantityStock;
    }
   
   
    

}
