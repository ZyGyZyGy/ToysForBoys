package be.vdab.entities;

import java.io.Serializable;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Version;

import be.vdab.valueobjects.Adres;

@Entity
@Table(name = "customers")
public class Customer implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToMany(mappedBy = "customer")
    @OrderBy("name")
    private Set<Order> orders;

    private String name;

    @Embedded
    private Adres adres;

    @Version
    private long version;

    public Customer(String name, Adres adres) {
	this.name = name;
	setAdres(adres);
	this.orders = new LinkedHashSet<>();
    }
    
    protected Customer() {
	
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Adres getAdres() {
        return adres;
    }
    
    public void setAdres(Adres adres) {
	this.adres = adres;
    }
    
    public Set<Order> getOrders() {
        return Collections.unmodifiableSet(orders);
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }
    
    public void add(Order order) {
	orders.add(order);
	if (order.getCustomer() != this) {
	    order.setCustomer(this);
	}
    }
    
    public void remove(Order order) {
	orders.remove(order);
	if (order.getCustomer() != this) {
	    order.setCustomer(null);
	}
    }
    

}












