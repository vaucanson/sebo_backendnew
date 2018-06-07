/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

/**
 *
 * @author djenadi
 */
@Entity
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @NotNull
    private String name;
    
    @NotNull
    @ManyToOne
    private Type type;
    
    @NotNull
    @ManyToOne
    private Category category;
    
    private float price;
    
    @NotNull
    @ManyToOne
    private Supplier supplier;
    
    private int stock;
    
    @NotNull
    @Column (unique = true)
    private String reference;
    
    private int minLimit;
    
    @OneToMany(mappedBy = "product")
    private List<OrderLine> orderLines;
    
    @OneToMany(mappedBy = "product")
    private List<SupplierOrderLine> supplierOrderLines;

    public Product() {
    }

    public Product(int id, String name, Type type, Category category, float price, Supplier supplier, int Stock, int limit, String reference) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.category = category;
        this.price = price;
        this.supplier = supplier;
        this.stock = Stock;
        this.minLimit = limit;
        this.reference = reference;
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

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int Stock) {
        this.stock = Stock;
    }

    public int getMinLimit() {
        return minLimit;
    }

    public void setMinLimit(int minLimit) {
        this.minLimit = minLimit;
    }

    public List<OrderLine> getOrderLines() {
        return orderLines;
    }

    public void setOrderLines(List<OrderLine> orderLines) {
        this.orderLines = orderLines;
    }

    public List<SupplierOrderLine> getSupplierOrderLines() {
        return supplierOrderLines;
    }

    public void setSupplierOrderLines(List<SupplierOrderLine> supplierOrderLines) {
        this.supplierOrderLines = supplierOrderLines;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }
           
}
