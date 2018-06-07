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
import javax.persistence.Temporal;

/**
 *
 * @author djenadi
 */
@Entity
public class Sale implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date startDate;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date endDate;
    private float rating;
    private boolean saleState;

    public Sale() {
    }

    public Sale(int id, Date start, Date end, float rating, boolean state) {
        this.id = id;
        this.startDate = start;
        this.endDate = end;
        
        if (rating > 0 && rating < 1){
        this.rating = rating;
        }
        else
        {
            this.rating = 1;
        }
        
        
        this.saleState = state;
    }

    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getStart() {
        return startDate;
    }

    public void setStart(Date start) {
        this.startDate = start;
    }

    public Date getEnd() {
        return endDate;
    }

    public void setEnd(Date end) {
        this.endDate = end;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public boolean isState() {
        return saleState;
    }

    public void setState(boolean state) {
        this.saleState = state;
    }
   }
