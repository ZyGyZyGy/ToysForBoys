package be.vdab.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import be.vdab.exceptions.VoorraadException;

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

    @Version
    private long version;
    
    public Product(String name, String scale, String description, long quantityInStock, long quantityInOrder,
	    BigDecimal buyPrice) {
	this.name = name;
	this.scale = scale;
	this.description = description;
	this.quantityInStock = quantityInStock;
	this.quantityInOrder = quantityInOrder;
	this.buyPrice = buyPrice;
    }

    protected Product() {

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
    
    public void setQuantityInStock(long quantityInStock) {
        this.quantityInStock = quantityInStock;
    }
    
    public void reduceQuantityInStock(long quantity) {
	if (quantity <= quantityInStock) {
	    this.quantityInStock -= quantity;
	} else {
	    throw new VoorraadException();
	}
    }

    public long getQuantityInOrder() {
	return quantityInOrder;
    }
    
    public void setQuantityInOrder(long quantityInOrder) {
        this.quantityInOrder = quantityInOrder;
    } 
    
    public void reduceQuantityInOrder(long quantity) {
	this.quantityInOrder -= quantity;
    }

    public BigDecimal getBuyPrice() {
	return buyPrice;
    }
    
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
