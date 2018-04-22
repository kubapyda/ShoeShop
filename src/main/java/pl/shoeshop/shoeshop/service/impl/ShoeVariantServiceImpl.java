package pl.shoeshop.shoeshop.service.impl;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.shoeshop.shoeshop.dto.SizeDictionaryDTO;
import pl.shoeshop.shoeshop.dto.VariantDTO;
import pl.shoeshop.shoeshop.entity.QSizedShoe;
import pl.shoeshop.shoeshop.entity.SizedShoe;
import pl.shoeshop.shoeshop.repository.SizedShoeRepository;
import pl.shoeshop.shoeshop.service.ShoeVariantService;
import pl.shoeshop.shoeshop.type.AvailabilityType;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class ShoeVariantServiceImpl implements ShoeVariantService {

    @PersistenceContext
    private EntityManager entityManager;
    private SizedShoeRepository sizedShoeRepository;

    @Autowired
    public ShoeVariantServiceImpl(SizedShoeRepository sizedShoeRepository) {
        this.sizedShoeRepository = sizedShoeRepository;
    }

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

    @Override
    public Iterable<SizedShoe> getSizedShoes(List<VariantDTO> variants) {
        QSizedShoe sizedShoe = QSizedShoe.sizedShoe;
        JPAQuery<SizeDictionaryDTO> query = new JPAQuery<>(entityManager);

        BooleanExpression expression = sizedShoe.isNull();

        for (VariantDTO dto : variants) {
            BooleanExpression condition = sizedShoe.shoeVariant.id.eq(dto.getVariantId())
                    .and(sizedShoe.size.eq(dto.getSize()));

            expression = expression.or(condition);
        }

        return sizedShoeRepository.findAll(expression);
    }
}
