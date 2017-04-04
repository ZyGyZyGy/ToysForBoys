package be.vdab.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Version;

import be.vdab.enums.Status;
import be.vdab.valueobjects.OrderDetail;

@Entity
@Table(name = "orders")
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

//    @Temporal(TemporalType.DATE) // bij java.sql.Date - niet nodig
    private Date orderDate;

//    @Temporal(TemporalType.DATE)
    private Date requiredDate;

//    @Temporal(TemporalType.DATE)
    private Date shippedDate;

    private String comments;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customerId")
    private Customer customer;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Version
    private long version;
    
    @ElementCollection
    @CollectionTable(name = "orderdetails", 
    	joinColumns = @JoinColumn(name = "orderid"))
    @OrderBy("productid")
    private Set<OrderDetail> orderDetails;
    
    public Order(Date orderDate, Date requiredDate, Date shippedDate, String comments, Customer customer,
	    Status status) {
	this.orderDate = orderDate;
	this.requiredDate = requiredDate;
	this.shippedDate = shippedDate;
	this.comments = comments;
	this.customer = customer;
	this.status = status;
//	products = new LinkedHashSet<>();
	orderDetails = new LinkedHashSet<>();
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

    public void setCustomer(Customer customer) {
	if (this.customer != null && this.customer.getOrders().contains(this)) {
	    this.customer.remove(this); // als de andere kant nog niet
					// bijgewerkt is werk je de andere kant
					// bij
	}
	this.customer = customer;
	if (customer != null && !customer.getOrders().contains(this)) {
	    customer.add(this); // als de andere kant nog niet bijgewerkt is
	} // werk je de andere kant bij
    }

    public Status getStatus() {
	return status;
    }
    
    public Set<OrderDetail> getOrderDetails() {
        return Collections.unmodifiableSet(orderDetails);
    }
    
//    public void setOrderDetails(Set<OrderDetail> orderDetails) {
//	if (this.orderDetails != null && this.orderDetails.) {
//	    
//	}
//    }
    
    public void add(OrderDetail orderdetail) {
	orderDetails.add(orderdetail);
    }
    
    public void remove(OrderDetail orderdetail) {
	orderDetails.remove(orderdetail);
    }
    
    public BigDecimal getTotalValue() {
	BigDecimal totalValue = BigDecimal.ZERO;
	for(OrderDetail orderDetail : orderDetails) {
	    totalValue = totalValue.add(orderDetail.getValue());
	}
	return totalValue;
    }
    
    public void setAsShipped() {
	status = status.SHIPPED;
	shippedDate = Date.valueOf(LocalDate.now());
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
    
    
    
    
// ============================ QUARANTINE ============================
    
//  public Set<Product> getProducts() {
//	return Collections.unmodifiableSet(products);
//  }
        
//  @ManyToMany(mappedBy = "orders")
//  private Set<Product> products;

    
    
    
//  public boolean ship() {
//	boolean gelukt = true;
//	for (OrderDetail orderDetail : orderDetails) {
//	    long quantityOrdered = orderDetail.getQuantityOrdered();
//	    if (orderDetail.getProduct().getQuantityInStock() >= quantityOrdered) {
//		orderDetail.getProduct().reduceQuantityInOrder(quantityOrdered);
//		orderDetail.getProduct().reduceQuantityInStock(quantityOrdered);
//		shippedDate = java.sql.Date.valueOf(LocalDate.now());
//		status = status.SHIPPED;
//	    } else {
//		gelukt = false;
//	    }
//
//	}
//	return gelukt;
//  }
}
