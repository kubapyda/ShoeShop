package pl.shoeshop.shoeshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.shoeshop.shoeshop.entity.Shoe;

public interface ShoeRepository extends JpaRepository<Shoe, Long> {

}
