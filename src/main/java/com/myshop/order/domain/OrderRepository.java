package com.myshop.order.domain;

import org.springframework.data.repository.Repository;

public interface OrderRepository extends Repository<Order, OrderNo> {
            Order findById(OrderNo no);

            void save(Order order);
}
