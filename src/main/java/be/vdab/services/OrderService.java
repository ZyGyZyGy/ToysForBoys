package be.vdab.services;

import java.util.List;

import be.vdab.entities.Order;
import be.vdab.repositories.OrderRepository;

public class OrderService extends AbstractService {

    private final OrderRepository orderRepository = new OrderRepository();
    
    public List<Order> findAll() {
	return orderRepository.findAll();
    }
    
}
