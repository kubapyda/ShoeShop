package pl.shoeshop.shoeshop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.shoeshop.shoeshop.dto.OrderDTO;
import pl.shoeshop.shoeshop.dto.VariantDTO;
import pl.shoeshop.shoeshop.entity.*;
import pl.shoeshop.shoeshop.repository.OrderRepository;
import pl.shoeshop.shoeshop.repository.SizedShoeRepository;
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
    private SizedShoeRepository sizedShoeRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository,
                            ReceiverService receiverService,
                            ShoeVariantService shoeVariantService,
                            SizedShoeRepository sizedShoeRepository) {
        this.orderRepository = orderRepository;
        this.receiverService = receiverService;
        this.shoeVariantService = shoeVariantService;
        this.sizedShoeRepository = sizedShoeRepository;
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
        Iterable<SizedShoe> sizedShoes = shoeVariantService.getSizedShoes(orderDTO.getVariants());
        List<OrderedShoe> orderedShoes = new ArrayList<>();

        Order order = prepareOrder(receiver);

        for (SizedShoe sizedShoe : sizedShoes) {
            OrderedShoe orderedShoe = OrderedShoe.builder()
                    .order(order)
                    .sizedShoe(sizedShoe)
                    .quantity(getMatchingQuantity(sizedShoe, orderDTO.getVariants()))
                    .build();

            orderedShoes.add(orderedShoe);
        }

        order.setOrderedShoes(orderedShoes);
        orderRepository.saveAndFlush(order);
    }

    private Order prepareOrder(Receiver receiver) {
        return Order.builder()
                .receiver(receiver)
                .applicationDate(ZonedDateTime.now())
                .orderStatus(OrderStatusType.APPROVED)
                .build();
    }

    private Integer getMatchingQuantity(SizedShoe sizedShoe, List<VariantDTO> variants) {
        ShoeVariant shoeVariant = sizedShoe.getShoeVariant();
        Integer matchingQuantity = null;

        if (shoeVariant != null) {
            Long variantId = shoeVariant.getId();
            Integer size = sizedShoe.getSize();

            if (variantId != null && size != null) {
                matchingQuantity = variants.stream()
                        .filter(dto -> size.equals(dto.getSize()) && variantId.equals(dto.getVariantId()))
                        .map(VariantDTO::getQuantity)
                        .findFirst()
                        .orElse(null);
            }
        }

        if (requestedQuantityIsPresent(sizedShoe, matchingQuantity)) {
            return matchingQuantity;
        } else {
            throw new IllegalStateException("There is not enough shoes in warehouse");
        }
    }

    private boolean requestedQuantityIsPresent(SizedShoe sizedShoe, Integer requestedQuantity) {
        Long actualQuantity = sizedShoeRepository.getActualQuantity(sizedShoe.getId());

        return (requestedQuantity != null) && requestedQuantity <= actualQuantity;
    }
}