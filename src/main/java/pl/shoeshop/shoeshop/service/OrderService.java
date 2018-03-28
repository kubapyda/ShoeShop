package pl.shoeshop.shoeshop.service;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import pl.shoeshop.shoeshop.dto.OrderDTO;
import pl.shoeshop.shoeshop.entity.Order;
import pl.shoeshop.shoeshop.type.OrderStatusType;

import java.util.List;

public interface OrderService {
    List<Order> getOrders(OrderStatusType status, Pageable pageable, Sort sort);

    void changeStatus(Long id, OrderStatusType status);

    void order(OrderDTO order);
}
