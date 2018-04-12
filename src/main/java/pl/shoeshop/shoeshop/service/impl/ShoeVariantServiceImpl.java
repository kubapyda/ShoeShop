package pl.shoeshop.shoeshop.service.impl;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.stereotype.Service;
import pl.shoeshop.shoeshop.dto.SizeDictionaryDTO;
import pl.shoeshop.shoeshop.entity.QSizedShoe;
import pl.shoeshop.shoeshop.service.ShoeVariantService;
import pl.shoeshop.shoeshop.type.AvailabilityType;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class ShoeVariantServiceImpl implements ShoeVariantService {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<SizeDictionaryDTO> getSizes(Long variantId) {
        QSizedShoe sizedShoe = QSizedShoe.sizedShoe;
        JPAQuery<SizeDictionaryDTO> query = new JPAQuery<>(entityManager);

        StringExpression caseExpression = new CaseBuilder()
                .when(sizedShoe.quantity.loe(0))
                .then(AvailabilityType.UNAVAILABLE.name())
                .when(sizedShoe.quantity.loe(5))
                .then(AvailabilityType.LAST_PIECES.name())
                .otherwise(AvailabilityType.AVAILABLE.name());

        return query.from(sizedShoe)
                .select(Projections.constructor(SizeDictionaryDTO.class, sizedShoe.size, caseExpression))
                .fetch();
    }
}
