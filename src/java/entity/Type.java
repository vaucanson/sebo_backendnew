/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

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

    @Override
    public String toString() {
        return "Type{" + "name=" + name + ", sale=" + sale + '}';
    }
    
    
    
}
