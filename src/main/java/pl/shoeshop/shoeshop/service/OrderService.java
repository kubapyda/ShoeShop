package pl.shoeshop.shoeshop.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pl.shoeshop.shoeshop.dto.OrderDTO;
import pl.shoeshop.shoeshop.entity.Order;
import pl.shoeshop.shoeshop.type.OrderStatusType;

import javax.mail.MessagingException;

public interface OrderService {

    Page<Order> getOrders(OrderStatusType status, Pageable pageable);

    void changeStatus(Long id, OrderStatusType status);

    void order(OrderDTO order) throws MessagingException;
}