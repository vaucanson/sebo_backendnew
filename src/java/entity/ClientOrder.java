
package entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
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
public class ClientOrder implements Serializable {
    
    
    // Les états dans lesquels la commande peut être.
    public static final int PASSED = 1;
    public static final int VALIDATED_BY_BANK = 21;
    public static final int REJECTED_BY_BANK = 22;
    public static final int STOCK_COMPLETE = 31;
    public static final int STOCK_INCOMPLETE = 32;
    public static final int CANCELLED = 41;
    public static final int PARTIAL = 42;
    public static final int WAITING = 43;
    public static final int IN_PREPARATION = 5;
    public static final int SENT = 6;
    public static final int DELIVERED = 7;
    
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private int orderState;
    
    @NotNull
    @ManyToOne (cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Address address;
    
    @NotNull
    @ManyToOne
    private ClientAccount client;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date orderDate;

    @ManyToOne (cascade = CascadeType.ALL)
    private PaymentMethod paymet;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date deliveryDate;

    public ClientOrder() {
        
    }
    
    
    // constructeur qui sert pour la création d'une commande via le service REST
    public ClientOrder(int id, Address address, ClientAccount client, PaymentMethod paymet)
    {
        this.id = id;
        this.orderState = 1;
        this.client = client;
        this.address = address;
        this.orderDate = new Date();
        this.paymet = paymet;
 
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

//    public List<OrderLine> getOrderLines() {
//        return orderLines;
//    }
//
//    public void setOrderLines(List<OrderLine> orderLines) {
//        this.orderLines = orderLines;
//    }

    public int getOrderState() {
        return orderState;
    }

    public void setOrderState(int orderState) {
        this.orderState = orderState;
    }


    @Override
    public String toString() {
        return "ClientOrder{" + "id=" + id + ", orderState=" + orderState + ", address=" + address + ", client=" + client + ", orderDate=" + orderDate + ", paymet=" + paymet + ", deliveryDate=" + deliveryDate + '}';
    }

}
