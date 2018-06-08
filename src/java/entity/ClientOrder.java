
package entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

/**
 *
 * @author djenadi
 */
@Entity
public class ClientOrder implements Serializable {
    
    
    // Les états dans lesquels la commande peut être.
    public static final int REJECTED = -1;
    public static final int PASSED = 1;
    public static final int PAYED = 2;
    public static final int IN_PREPARATION = 3;
    public static final int SENT = 4;
    public static final int PARTIALLY_SENT =5;
    public static final int WAITING = 6;
    public static final int DELIVERED = 7;
    
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private int orderState;
    
    @ManyToOne
    private Address address;
    
    @ManyToOne
    private ClientAccount client;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date orderDate;
    
    @ManyToOne
    private PaymentMethod paymet;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date deliveryDate;
    
    

    public ClientOrder() {
        
    }

    public ClientOrder(int id, int etat, Address address, ClientAccount client, Date dateCommande, PaymentMethod paymet, Date dateLivraison) {
        this.id = id;
        this.orderState = etat;
        this.address = address;
        this.client = client;
        this.orderDate = dateCommande;
        this.paymet = paymet;
        this.deliveryDate = dateLivraison;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEtat() {
        return orderState;
    }

    public void setEtat(int os) {
        this.orderState = os;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public ClientAccount getClient() {
        return client;
    }

    public void setClient(ClientAccount client) {
        this.client = client;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public PaymentMethod getPaymet() {
        return paymet;
    }

    public void setPaymet(PaymentMethod paymet) {
        this.paymet = paymet;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }
    
    
}
