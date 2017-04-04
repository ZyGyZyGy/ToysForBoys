package be.vdab.services;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.persistence.PersistenceException;

import be.vdab.entities.Order;
import be.vdab.repositories.OrderRepository;

public class OrderService extends AbstractService {

    private final OrderRepository orderRepository = new OrderRepository();
    
    public List<Order> findAll() {
	return orderRepository.findAll();
    }
    
    public Optional<Order> read(long id) {
	return orderRepository.read(id);
    }
    
    public void ship(String[] orderIds) {
	beginTransaction();
	try {
	    Arrays.stream(orderIds)
	    	.forEach(id -> orderRepository.read(Long.parseLong(id))
	    		.ifPresent(order -> order.ship()));
	    commit();
	} catch (PersistenceException ex) {
	    rollback();
	    throw ex;
	}
    }
}
