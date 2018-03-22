package pl.shoeshop.shoeshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.shoeshop.shoeshop.entity.ShoeVariant;

public interface ShoeVariantRepository extends JpaRepository<ShoeVariant, Long> {
}
