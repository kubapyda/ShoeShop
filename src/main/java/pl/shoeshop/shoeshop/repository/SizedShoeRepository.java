package pl.shoeshop.shoeshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import pl.shoeshop.shoeshop.entity.SizedShoe;

public interface SizedShoeRepository extends JpaRepository<SizedShoe, Long>, QuerydslPredicateExecutor<SizedShoe> {

    @Query(nativeQuery = true, value = 
            "select ss.quantity - coalesce(sum(os.quantity), 0) " +
            "from sized_shoe ss " +
            "left join ordered_shoe os on ss.id = os.sized_shoe_id " +
            "where ss.id = :id")
    Long getActualQuantity(@Param("id") Long id);
}