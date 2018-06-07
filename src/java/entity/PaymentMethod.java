/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.validation.constraints.NotNull;

/**
 *
 * @author djenadi
 */
@Entity
public class PaymentMethod implements Serializable { 
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    private int creditCardNumber;
    @NotNull
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date expirationDate; 
    @ManyToOne
    private ClientAccount client;

    public PaymentMethod() {
    }

    public PaymentMethod(int id, int creditCardNumber, Date expirationDate) {
        this.id = id;
        this.creditCardNumber = creditCardNumber;
        this.expirationDate = expirationDate;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(int creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }
       
    
}
