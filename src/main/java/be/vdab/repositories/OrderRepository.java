package be.vdab.repositories;

import java.util.List;

import be.vdab.entities.Order;

public class OrderRepository extends AbstractRepository {

    public List<Order> findAll() {
	return getEntityManager()
		.createNamedQuery("Order.findAll", Order.class)
		.getResultList();
    }
    
}
