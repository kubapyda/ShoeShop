package pl.shoeshop.shoeshop.service.impl;

import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.shoeshop.shoeshop.dto.VariantDTO;
import pl.shoeshop.shoeshop.entity.QSizedShoe;
import pl.shoeshop.shoeshop.entity.SizedShoe;
import pl.shoeshop.shoeshop.projection.SizeDictionary;
import pl.shoeshop.shoeshop.repository.ShoeVariantRepository;
import pl.shoeshop.shoeshop.repository.SizedShoeRepository;
import pl.shoeshop.shoeshop.service.ShoeVariantService;

import java.util.List;

@Service
public class ShoeVariantServiceImpl implements ShoeVariantService {

    private ShoeVariantRepository shoeVariantRepository;
    private SizedShoeRepository sizedShoeRepository;

    @Autowired
    public ShoeVariantServiceImpl(ShoeVariantRepository shoeVariantRepository,
                                  SizedShoeRepository sizedShoeRepository) {
        this.shoeVariantRepository = shoeVariantRepository;
        this.sizedShoeRepository = sizedShoeRepository;
    }

    @Override
    public List<SizeDictionary> getSizes(Long variantId) {
        return shoeVariantRepository.getAvailabilityPerSize(variantId);
    }

    @Override
    public Iterable<SizedShoe> getSizedShoes(List<VariantDTO> variants) {
        QSizedShoe sizedShoe = QSizedShoe.sizedShoe;

        BooleanExpression expression = sizedShoe.isNull();

        for (VariantDTO dto : variants) {
            BooleanExpression condition = sizedShoe.shoeVariant.id.eq(dto.getVariantId())
                    .and(sizedShoe.size.eq(dto.getSize()));

            expression = expression.or(condition);
        }

        return sizedShoeRepository.findAll(expression);
    }
}
