package pl.shoeshop.shoeshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.shoeshop.shoeshop.entity.Rate;
import pl.shoeshop.shoeshop.projection.CommentProjection;
import pl.shoeshop.shoeshop.projection.RatingSummaryProjection;

import java.util.List;

public interface RateRepository extends JpaRepository<Rate, Long> {

    @Query(nativeQuery = true, value =
            "select case when count(*) > 0 then 'true' else 'false' end " +
            "from receiver r " +
            "       left join vote v on r.id = v.receiver_id and v.rate_id = :rate_id " +
            "where r.email = :email " +
            "       and v.id is null")
    Boolean couldVote(@Param("email") String email, @Param("rate_id") Long rateId);

    @Query(nativeQuery = true, value =
            "select case when count(*) > 0 then 'true' else 'false' end " +
                    "from receiver r " +
                    "       join t_order o on r.id = o.receiver_id " +
                    "       join ordered_shoe os on o.id = os.order_id " +
                    "       join sized_shoe ss on os.sized_shoe_id = ss.id " +
                    "       join shoe_variant sv on ss.shoe_variant_id = sv.id " +
                    "       join shoe s on sv.shoe_id = s.id " +
                    "       left join rate r2 on s.id = r2.shoe_id and r2.order_id = o.id " +
                    "where r.email = :email " +
                    "       and o.id = :order_id " +
                    "       and s.id = :shoe_id " +
                    "       and r2.id is null")
    Boolean couldRate(@Param("email") String email, @Param("order_id") Long orderId, @Param("shoe_id") Long shoeId);

    @Query(nativeQuery = true, value = 
            "select avg(r.rate) as rate, count(r.id) as totalRates " +
            "from rate r " +
            "where r.shoe_id = :shoe_id " +
            "group by r.shoe_id")
    RatingSummaryProjection getRatingSummary(@Param("shoe_id") Long shoeId);

    @Query(nativeQuery = true, value = 
            "select r.rate, " +
            "       r.rate_comment as rateComment, " +
            "       r.id as rateId, " +
            "       coalesce(useful_votes.number, 0) - coalesce(unuseful_votes.number, 0) as usability " +
            "from rate r " +
            "       left join (select rate_id, count(is_useful) number " +
            "             from vote " +
            "             where is_useful = 1 " +
            "             group by rate_id) useful_votes on r.id = useful_votes.rate_id " +
            "       left join (select rate_id, count(is_useful) number " +
            "             from vote " +
            "             where is_useful = 0 " +
            "             group by rate_id) unuseful_votes on r.id = unuseful_votes.rate_id " +
            "       where r.shoe_id = :shoe_id")
    List<CommentProjection> getComments(@Param("shoe_id") Long shoeId);
}
