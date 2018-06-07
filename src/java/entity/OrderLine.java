/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

/**
 *
 * @author djenadi
 */
@Entity
public class OrderLine implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int quantity;
    @NotNull
    @ManyToOne
    private Product product;
    @NotNull
    @ManyToOne
    private ClientOrder clientOrder;
    private float subtotal;

    public OrderLine() {
    }

    public OrderLine(int id, int quantity, Product product, ClientOrder clientOrder, float subtotal) {
        this.id = id;
        
        if (quantity > 0)
        {
            this.quantity = quantity;
        }
        else
        {
            this.quantity = 1;
        }
        
        this.product = product;
        this.clientOrder = clientOrder;
        
        this.subtotal = quantity * product.getPrice();
    }

  
    
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public ClientOrder getClientOrder() {
        return clientOrder;
    }

    public void setClientOrder(ClientOrder clientOrder) {
        this.clientOrder = clientOrder;
    }

    public float getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(float subtotal) {
        this.subtotal = subtotal;
    }
    
    
    
}
