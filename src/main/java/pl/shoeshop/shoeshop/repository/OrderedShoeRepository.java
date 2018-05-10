package pl.shoeshop.shoeshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.shoeshop.shoeshop.entity.OrderedShoe;
import pl.shoeshop.shoeshop.projection.OrderedShoeProjection;

import java.util.stream.Stream;

public interface OrderedShoeRepository extends JpaRepository<OrderedShoe, Long> {

    @Query(nativeQuery = true, value = "SELECT " +
            "s.brand, " +
            "s.model, " +
            "sv.shank_color as ShankColor, " +
            "sv.sole_color as SoleColor, " +
            "ss.size, " +
            "s.price," +
            "os.quantity " +
            "FROM t_order o " +
            "JOIN ordered_shoe os ON o.id = os.order_id " +
            "JOIN sized_shoe ss ON os.sized_shoe_id = ss.id " +
            "JOIN shoe_variant sv ON ss.shoe_variant_id = sv.id " +
            "JOIN shoe s ON sv.shoe_id = s.id " +
            "WHERE o.id = :orderId")
    Stream<OrderedShoeProjection> getOrderedShoes(@Param("orderId") Long orderId);
}
