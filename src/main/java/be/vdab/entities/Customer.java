package be.vdab.entities;

import java.io.Serializable;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "countryId")
    private Country country;
    
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
    
    public Country getCountry() {
	return country;
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

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((adres == null) ? 0 : adres.hashCode());
	result = prime * result + ((name == null) ? 0 : name.hashCode());
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (!(obj instanceof Customer))
	    return false;
	Customer other = (Customer) obj;
	if (adres == null) {
	    if (other.adres != null)
		return false;
	} else if (!adres.equals(other.adres))
	    return false;
	if (name == null) {
	    if (other.name != null)
		return false;
	} else if (!name.equals(other.name))
	    return false;
	return true;
    }
    
}












