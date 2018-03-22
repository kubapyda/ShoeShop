package pl.shoeshop.shoeshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.shoeshop.shoeshop.entity.OrderedShoe;

public interface OrderedShoeRepository extends JpaRepository<OrderedShoe, Long> {
}
