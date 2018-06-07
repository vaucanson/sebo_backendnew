/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import javax.persistence.CascadeType;
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
public class ClientAccount implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    @Column(length = 40)
    private String lastName;
    @NotNull
    @Column(length = 40)
    private String firstName;
    @NotNull
    @ManyToOne
    private Address address;
    @NotNull
    @Column(length = 40, unique = true)
    private String email;   // email = login
    @NotNull
    private String password;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "client")
    private Collection<ClientOrder> commandesCollection;
    @OneToMany(mappedBy = "client")
    private List<PaymentMethod> paymentMethods;

    public ClientAccount() {
    }

    public ClientAccount(int id, String lastName, String firstName, Address adress, String email, String password) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.address = adress;
        this.email = email;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Address getAdress() {
        return address;
    }

    public void setAdress(Address adress) {
        this.address = adress;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Collection<ClientOrder> getCommandesCollection() {
        return commandesCollection;
    }

    public void setCommandesCollection(Collection<ClientOrder> commandesCollection) {
        this.commandesCollection = commandesCollection;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<PaymentMethod> getPaymentMethods() {
        return paymentMethods;
    }

    public void setPaymentMethods(List<PaymentMethod> paymentMethods) {
        this.paymentMethods = paymentMethods;
    }
    

}
