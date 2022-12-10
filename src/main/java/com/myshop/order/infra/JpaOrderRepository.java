package com.myshop.order.infra;

import com.myshop.order.domain.Order;
import com.myshop.order.domain.OrderNo;
import com.myshop.order.domain.OrderRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

public class JpaOrderRepository implements OrderRepository {
    @PersistenceContext
    EntityManager em;

    @Override
    public Order findById(OrderNo id) {
        return em.find(Order.class, id);
    }

    @Override
    public void save(Order order) {
        em.persist(order);
    }
}
