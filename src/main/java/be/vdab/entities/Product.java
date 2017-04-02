package be.vdab.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;

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
    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "productId")
    private OrderDetail orderdetail;

    public Product(String name, String scale, String description, long quantityInStock, long quantityInOrder,
	    BigDecimal buyPrice, long productlineId) {
	this.name = name;
	this.scale = scale;
	this.description = description;
	this.quantityInStock = quantityInStock;
	this.quantityInOrder = quantityInOrder;
	this.buyPrice = buyPrice;
	this.productlineId = productlineId;
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

}
