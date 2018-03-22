package pl.shoeshop.shoeshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.shoeshop.shoeshop.entity.Receiver;

public interface ReceiverRepository extends JpaRepository<Receiver, Long> {
}
