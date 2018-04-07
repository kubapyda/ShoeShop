package pl.shoeshop.shoeshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import pl.shoeshop.shoeshop.entity.ShoeVariant;

public interface ShoeVariantRepository extends JpaRepository<ShoeVariant, Long>, QuerydslPredicateExecutor<ShoeVariant> {
}
