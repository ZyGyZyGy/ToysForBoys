package be.vdab.valueobjects;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import be.vdab.entities.Product;

@Embeddable
public class OrderDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    private long quantityOrdered;
    private BigDecimal priceEach;  
    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "productid")
    private Product product;

    public OrderDetail(long quantityOrdered, BigDecimal priceEach) {
	this.quantityOrdered = quantityOrdered;
	this.priceEach = priceEach;
    }

    protected OrderDetail() {

    }

    public long getQuantityOrdered() {
	return quantityOrdered;
    }

    public BigDecimal getPriceEach() {
	return priceEach;
    }
    
    public BigDecimal getValue() {
	return priceEach.multiply(BigDecimal.valueOf(quantityOrdered));
    }
    
    public Product getProduct() {
	return product;
    }
    
//    public void setProduct(Product product) {
//	if (this.product != null && this.product.getOrderDetails().contains(this)) {
//	    this.product.remove(this);
//	}
//	this.product = product;
//	if (product != null && !product.getOrderDetails().contains(this)) {
//	    product.add(this);
//	}
//    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((priceEach == null) ? 0 : priceEach.hashCode());
	result = prime * result + (int) (quantityOrdered ^ (quantityOrdered >>> 32));
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (!(obj instanceof OrderDetail))
	    return false;
	OrderDetail other = (OrderDetail) obj;
	if (priceEach == null) {
	    if (other.priceEach != null)
		return false;
	} else if (!priceEach.equals(other.priceEach))
	    return false;
	if (quantityOrdered != other.quantityOrdered)
	    return false;
	return true;
    }
    
    

}



