package pl.shoeshop.shoeshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.shoeshop.shoeshop.entity.Vote;

public interface VoteRepository extends JpaRepository<Vote, Long> {

}
