package be.vdab.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Version;

import be.vdab.valueobjects.OrderDetail;

@Entity
@Table(name = "products")
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private String scale;
    private String description;
    private long quantityInStock;
    private long quantityInOrder;
    private BigDecimal buyPrice;
    private long productlineId;

    @Version
    private long version;
    
//    @ManyToMany
//    @JoinTable(name = "orderdetails",
//    	joinColumns = @JoinColumn(name = "productid"), 
//    	inverseJoinColumns = @JoinColumn(name = "orderid"))
//    private Set<Order> orders = new LinkedHashSet<>();
    
//    @ElementCollection
//    @CollectionTable(name = "orderdetails", 
//    	joinColumns = @JoinColumn(name = "productid"))
//    @OrderBy("productid")
//    private Set<OrderDetail> orderDetails;
    
//    private Set<OrderDetail> orderDetails;
    
    public Product(String name, String scale, String description, long quantityInStock, long quantityInOrder,
	    BigDecimal buyPrice, long productlineId) {
	this.name = name;
	this.scale = scale;
	this.description = description;
	this.quantityInStock = quantityInStock;
	this.quantityInOrder = quantityInOrder;
	this.buyPrice = buyPrice;
	this.productlineId = productlineId;
//	orderDetails = new LinkedHashSet<>();
    }

    public Product() {

    }

    public long getId() {
	return id;
    }

    public String getName() {
	return name;
    }

    public String getScale() {
	return scale;
    }

    public String getDescription() {
	return description;
    }

    public long getQuantityInStock() {
	return quantityInStock;
    }

    public long getQuantityInOrder() {
	return quantityInOrder;
    }

    public BigDecimal getBuyPrice() {
	return buyPrice;
    }

    public long getProductlineId() {
	return productlineId;
    }
    
//    public Set<Order> getOrders() {
//        return Collections.unmodifiableSet(orders);
//    }
    
//  public Set<OrderDetail> getOrderDetails() {
//	return orderDetails;
//  }
    
//    
//    public void add(OrderDetail orderDetail) {
//	orderDetails.add(orderDetail);
//    }
//    
//    public void remove(OrderDetail orderDetail) {
//	orderDetails.remove(orderDetail);
//    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((name == null) ? 0 : name.hashCode());
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (!(obj instanceof Product))
	    return false;
	Product other = (Product) obj;
	if (name == null) {
	    if (other.name != null)
		return false;
	} else if (!name.equals(other.name))
	    return false;
	return true;
    }       

}
