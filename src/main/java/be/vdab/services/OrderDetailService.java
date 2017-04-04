package be.vdab.services;

import java.util.Arrays;
import java.util.List;

import javax.persistence.PersistenceException;

import be.vdab.repositories.OrderDetailRepository;
import be.vdab.valueobjects.OrderDetail;

public class OrderDetailService extends AbstractService {

    private final OrderDetailRepository orderDetailRepository = new OrderDetailRepository();
    
    public List<OrderDetail> findOrderDetailByOrderId(long id) {
	return orderDetailRepository.findOrderDetailByOrderId(id);
    }
    
//    public void ship(String[] orderIds) {
//	beginTransaction();
//	try {
//	    Arrays.stream(orderIds)
//	    	.forEach(id -> orderRepository.read(Long.parseLong(id))
//	    		.ifPresent(order -> order.ship()));
//	    commit();
//	} catch (PersistenceException ex) {
//	    rollback();
//	    throw ex;
//	}
//    }
}
