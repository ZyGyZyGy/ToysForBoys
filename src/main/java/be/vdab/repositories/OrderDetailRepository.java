package be.vdab.repositories;

import java.util.List;

import be.vdab.valueobjects.OrderDetail;

public class OrderDetailRepository extends AbstractRepository {

    public List<OrderDetail> findOrderDetailByOrderId(long id) {
	return getEntityManager()
		.createNamedQuery("OrderDetail.findOrderDetailByOrderId", OrderDetail.class)
		.setParameter("orderid", id)
		.getResultList();
    }
}
