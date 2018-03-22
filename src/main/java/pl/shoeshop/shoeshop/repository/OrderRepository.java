package pl.shoeshop.shoeshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.shoeshop.shoeshop.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
