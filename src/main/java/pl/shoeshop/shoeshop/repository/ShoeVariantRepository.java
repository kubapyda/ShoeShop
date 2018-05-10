package pl.shoeshop.shoeshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import pl.shoeshop.shoeshop.entity.ShoeVariant;
import pl.shoeshop.shoeshop.projection.SizeDictionaryProjection;

import java.util.List;

public interface ShoeVariantRepository extends JpaRepository<ShoeVariant, Long>, QuerydslPredicateExecutor<ShoeVariant> {

    @Query(nativeQuery = true, value = "SELECT " +
            "  size, " +
            "  CASE " +
            "  WHEN actual_quantity <= 0 " +
            "    THEN 'UNAVAILABLE' " +
            "  WHEN actual_quantity < 5 " +
            "    THEN 'LAST_PIECES' " +
            "  ELSE 'AVAILABLE' " +
            "  END AS availability " +
            "FROM (SELECT " +
            "        ss.size, " +
            "        ss.quantity - coalesce(sum(os.quantity), 0) AS actual_quantity " +
            "      FROM sized_shoe ss " +
            "        JOIN shoe_variant sv ON ss.shoe_variant_id = sv.id " +
            "        LEFT JOIN ordered_shoe os ON ss.id = os.sized_shoe_id " +
            "      WHERE sv.id = :id " +
            "      GROUP BY ss.size) AS quantity_per_size")
    List<SizeDictionaryProjection> getAvailabilityPerSize(@Param("id") Long id);
}