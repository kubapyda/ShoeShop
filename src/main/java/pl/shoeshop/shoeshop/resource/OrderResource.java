package pl.shoeshop.shoeshop.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.shoeshop.shoeshop.dto.OrderDTO;
import pl.shoeshop.shoeshop.entity.Order;
import pl.shoeshop.shoeshop.service.OrderService;
import pl.shoeshop.shoeshop.type.OrderStatusType;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("orders/")
public class OrderResource {

    private OrderService orderService;

    @Autowired
    public OrderResource(OrderService orderService) {
       this.orderService = orderService;
    }

    @RequestMapping(value = "find/{status}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<Order>> getOrders(@PathVariable OrderStatusType status, Pageable pageable) {
        return null;
    }

    @RequestMapping(value = "changeStatus/{id}/{status}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> changeStatus(@PathVariable Long id, @PathVariable OrderStatusType status) {
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "order", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> order(@Valid @RequestBody OrderDTO order) {
        orderService.order(order);
        return ResponseEntity.ok().build();
    }
}
