package be.vdab.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceException;
import javax.persistence.RollbackException;

import be.vdab.entities.Order;
import be.vdab.exceptions.RecordAangepastException;
import be.vdab.exceptions.VoorraadException;
import be.vdab.repositories.OrderRepository;

public class OrderService extends AbstractService {

    private final OrderRepository orderRepository = new OrderRepository();
    
    public List<Order> findAll(int vanafRij, int aantalRijen) {
	return orderRepository.findAll(vanafRij, aantalRijen);
    }
    
    public Optional<Order> read(long id) {
	return orderRepository.read(id);
    }
    
    public void ship(Order order) {
	beginTransaction();
	try {
	    order.setAsShipped();
	    order.getOrderDetails()
	    	.stream()
	    	.forEach(orderDetail -> {
        		orderDetail.getProduct().reduceQuantityInOrder(orderDetail.getQuantityOrdered());
        		orderDetail.getProduct().reduceQuantityInStock(orderDetail.getQuantityOrdered());
	    		});
	    commit();
	} catch (VoorraadException ex) {
	    rollback();
	    throw ex;
	} catch (RollbackException ex) {
	    if (ex.getCause() instanceof OptimisticLockException) {
		throw new RecordAangepastException();
	    }
	} catch (PersistenceException ex) {
	    rollback();
	    throw ex;
	}
    }
    
}
