package pl.shoeshop.shoeshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import pl.shoeshop.shoeshop.entity.SizedShoe;

public interface SizedShoeRepository extends JpaRepository<SizedShoe, Long>, QuerydslPredicateExecutor<SizedShoe> {
}