/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author djenadi
 */
@Entity
public class Type implements Serializable {
    @Id
    private String name;
    @ManyToOne
    private Sale sale;
    @OneToMany(mappedBy = "type")
    private Collection<Product> products;

    public Type() {
    }

    public Type(String name, Sale sale) {
        this.name = name;
        this.sale = sale;
    }

    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
    public Sale getSale() {
        return sale;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
    }

    public Collection<Product> getProducts() {
        return products;
    }

    public void setProducts(Collection<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Type{" + "name=" + name + ", sale=" + sale + ", products=" + products + '}';
    }
    
    
    
}
