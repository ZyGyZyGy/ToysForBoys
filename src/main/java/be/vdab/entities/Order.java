package be.vdab.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import be.vdab.enums.Status;

@Entity
@Table(name = "orders")
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @Temporal(TemporalType.DATE)
    private Date orderDate;
    
    @Temporal(TemporalType.DATE)
    private Date requiredDate;
    
    @Temporal(TemporalType.DATE)
    private Date shippedDate;
    
    private String comments;
    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customerId")
    private Customer customer;
    
    @Enumerated(EnumType.STRING)
    private Status status;
    
    @Version
    private long version;

    public Order(Date orderDate, Date requiredDate, Date shippedDate, String comments, Customer customer, Status status,
	    long version) {
	this.orderDate = orderDate;
	this.requiredDate = requiredDate;
	this.shippedDate = shippedDate;
	this.comments = comments;
	this.customer = customer;
	this.status = status;
    }
    
    protected Order() {
	
    }

    public long getId() {
        return id;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public Date getRequiredDate() {
        return requiredDate;
    }

    public Date getShippedDate() {
        return shippedDate;
    }

    public String getComments() {
        return comments;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Status getStatus() {
        return status;
    }
    
    public void setCustomer(Customer customer) {
	if (this.customer != null && this.customer.getOrders().contains(this)) {
	    this.customer.remove(this); 	// als de andere kant nog niet bijgewerkt is
	} 				// werk je de andere kant bij
	this.customer = customer;
	if (customer != null && !customer.getOrders().contains(this)) {
	    customer.add(this); 	// als de andere kant nog niet bijgewerkt is
	}			// werk je de andere kant bij
    }

    @Override
    public boolean equals(Object obj) {
	if (!(obj instanceof Order))
	    return false;
	Order other = (Order) obj;
	if (customer == null) {
	    if (other.customer != null)
		return false;
	} else if (!customer.equals(other.customer))
	    return false;
	if (orderDate == null) {
	    if (other.orderDate != null)
		return false;
	} else if (!orderDate.equals(other.orderDate))
	    return false;
	if (requiredDate == null) {
	    if (other.requiredDate != null)
		return false;
	} else if (!requiredDate.equals(other.requiredDate))
	    return false;
	return true;
    }
        
    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((customer == null) ? 0 : customer.hashCode());
	result = prime * result + ((orderDate == null) ? 0 : orderDate.hashCode());
	result = prime * result + ((requiredDate == null) ? 0 : requiredDate.hashCode());
	return result;
    }
    
    
    
}









