package be.vdab.entities;

import java.io.Serializable;

public class MyIdClass implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private long id;
    private long productId;
    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + (int) (id ^ (id >>> 32));
	result = prime * result + (int) (productId ^ (productId >>> 32));
	return result;
    }
    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (!(obj instanceof MyIdClass))
	    return false;
	MyIdClass other = (MyIdClass) obj;
	if (id != other.id)
	    return false;
	if (productId != other.productId)
	    return false;
	return true;
    }
    
    
}
