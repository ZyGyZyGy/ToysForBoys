package be.vdab.services;

import java.util.List;

import be.vdab.repositories.OrderDetailRepository;
import be.vdab.valueobjects.OrderDetail;

public class OrderDetailService extends AbstractService {

    private final OrderDetailRepository orderDetailRepository = new OrderDetailRepository();
    
    public List<OrderDetail> findOrderDetailByOrderId(long id) {
	return orderDetailRepository.findOrderDetailByOrderId(id);
    }
}
