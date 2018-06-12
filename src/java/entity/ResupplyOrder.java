/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.Temporal;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

/**
 *
 * @author djenadi
 */
@Entity
public class ResupplyOrder implements Serializable {
    
    // Les états dans lesquels la commande peut être
    public static final int CREATED = 1;
    public static final int ORDERED = 2;
    public static final int RECEIVED = 3;
    
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @NotNull
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date orderDate;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date receptionDate;
   
    @ManyToOne
    private Supplier supplier;
    
    @OneToOne
    private Address address;
    
    private int orderState = ResupplyOrder.CREATED;
    
    @Transient
    private List<SupplierOrderLine> lines;

    public ResupplyOrder() {
    }

    public ResupplyOrder(int id, Date orderDate, Date receptionDate, Supplier supplier, Address address) {
        this.id = id;
        this.orderDate = orderDate;
        this.receptionDate = receptionDate;
        //this.supplier = supplier;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Date getReceptionDate() {
        return receptionDate;
    }

    public void setReceptionDate(Date receptionDate) {
        this.receptionDate = receptionDate;
    }

    
    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public int getEtat() {
        return orderState;
    }

    public void setEtat(int os) {
        this.orderState = os;
    }

//    public List<SupplierOrderLine> getSupplierOrderLines() {
//        return supplierOrderLines;
//    }
//
//    public void setSupplierOrderLines(List<SupplierOrderLine> supplierOrderLines) {
//        this.supplierOrderLines = supplierOrderLines;
//    }
    
    
    public List<SupplierOrderLine> getSupplierOrderLines() {
        return this.lines;
    }
    
    
    public void setSupplierOrderLines(List<SupplierOrderLine> supplierOrderLines) {
        this.lines = supplierOrderLines;
    }
    
    
  
}
