package be.vdab.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
//@IdClass(MyIdClass.class)
@Table(name = "orderdetails")
public class OrderDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private long id;

//    @Id
    private long productId;

    private long quantityOrdered;
    private BigDecimal priceEach;
    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "orderId")
    private Order order;  
    
    @OneToMany(mappedBy = "orderdetail")
    private Set<Product> products;

    public OrderDetail(long quantityOrdered, BigDecimal priceEach) {
	this.quantityOrdered = quantityOrdered;
	this.priceEach = priceEach;
	products = new LinkedHashSet<>();
    }

    public OrderDetail() {

    }

    public long getId() {
	return id;
    }

    public long getProductId() {
	return productId;
    }

    public Order getOrder() {
	return order;
    }

    public long getQuantityOrdered() {
	return quantityOrdered;
    }

    public BigDecimal getPriceEach() {
	return priceEach;
    }

    public Set<Product> getProducts() {
        return products;
    }
    
    public BigDecimal getValue() {
	return priceEach.multiply(BigDecimal.valueOf(quantityOrdered));
    }

}
