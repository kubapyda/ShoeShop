package pl.shoeshop.shoeshop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.shoeshop.shoeshop.dto.OrderDTO;
import pl.shoeshop.shoeshop.entity.Order;
import pl.shoeshop.shoeshop.entity.OrderedShoe;
import pl.shoeshop.shoeshop.entity.Receiver;
import pl.shoeshop.shoeshop.entity.SizedShoe;
import pl.shoeshop.shoeshop.repository.OrderRepository;
import pl.shoeshop.shoeshop.service.OrderService;
import pl.shoeshop.shoeshop.service.ReceiverService;
import pl.shoeshop.shoeshop.service.ShoeVariantService;
import pl.shoeshop.shoeshop.type.OrderStatusType;

import javax.transaction.Transactional;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;
    private ReceiverService receiverService;
    private ShoeVariantService shoeVariantService;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository,
                            ReceiverService receiverService,
                            ShoeVariantService shoeVariantService) {
        this.orderRepository = orderRepository;
        this.receiverService = receiverService;
        this.shoeVariantService = shoeVariantService;
    }

    @Override
    public List<Order> getOrders(OrderStatusType status, Pageable pageable) {
        return null;
    }

    @Override
    public void changeStatus(Long id, OrderStatusType status) {
    }

    @Override
    @Transactional
    public void order(OrderDTO orderDTO) {
        Receiver receiver = receiverService.getOrCreate(orderDTO.getReceiver());

        Order order = Order.builder()
                .receiver(receiver)
                .applicationDate(ZonedDateTime.now())
                .orderStatus(OrderStatusType.APPROVED)
                .build();

        Iterable<SizedShoe> sizedShoes = shoeVariantService.getSizedShoes(orderDTO.getVariants());

        List<OrderedShoe> orderedShoes = new ArrayList<>();

        for (SizedShoe sizedShoe : sizedShoes) {
            OrderedShoe orderedShoe = OrderedShoe.builder()
                    .order(order)
                    .sizedShoe(sizedShoe)
                    .build();

            orderedShoes.add(orderedShoe);
        }

        order.setOrderedShoes(orderedShoes);
        sizedShoes.forEach(sizedShoe -> sizedShoe.setQuantity(sizedShoe.getQuantity() - 1));
        orderRepository.saveAndFlush(order);
    }
}