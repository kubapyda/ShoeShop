package pl.shoeshop.shoeshop.service.impl;

import com.google.common.base.Splitter;
import com.querydsl.core.Query;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.shoeshop.shoeshop.dto.ShoeDTO;
import pl.shoeshop.shoeshop.dto.ShoeSearchDTO;
import pl.shoeshop.shoeshop.entity.QShoe;
import pl.shoeshop.shoeshop.entity.QShoeVariant;
import pl.shoeshop.shoeshop.entity.Shoe;
import pl.shoeshop.shoeshop.entity.ShoeVariant;
import pl.shoeshop.shoeshop.mapper.ShoeMapper;
import pl.shoeshop.shoeshop.repository.ShoeRepository;
import pl.shoeshop.shoeshop.repository.ShoeVariantRepository;
import pl.shoeshop.shoeshop.service.ShoeSearchService;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ShoeSearchServiceImpl implements ShoeSearchService {

    private ShoeRepository shoeRepository;
    private ShoeVariantRepository shoeVariantRepository;
    private ShoeMapper shoeMapper;

    @Autowired
    public ShoeSearchServiceImpl(ShoeRepository shoeRepository,
                                 ShoeVariantRepository shoeVariantRepository,
                                 ShoeMapper shoeMapper) {
        this.shoeMapper = shoeMapper;
        this.shoeRepository = shoeRepository;
        this.shoeVariantRepository = shoeVariantRepository;
    }

    @Override
    public Optional<Shoe> getShoe(Long id) {
        return shoeRepository.findById(id);
    }

    @Override
    public List<ShoeDTO> getShoes(Pageable pageable) {
        return mapToDTO(shoeVariantRepository.findAll());
    }

    @Override
    public List<ShoeDTO> getShoes(String phrase, Pageable pageable) {
        QShoe shoe = QShoeVariant.shoeVariant.shoe;

        Iterable<String> matchers = Splitter.on(" ").split(phrase);

        BooleanExpression expression = shoe.isNull();

        for (String matcher : matchers) {
            expression = expression
                    .or(shoe.brand.stringValue().contains(matcher))
                    .or(shoe.model.contains(matcher))
                    .or(shoe.gender.stringValue().startsWith(matcher))
                    .or(shoe.shoeType.stringValue().startsWith(matcher));
        }

        List<ShoeVariant> shoeVariants = shoeVariantRepository.findAll(expression, pageable).getContent();

        return mapToDTO(shoeVariants);
    }

    @Override
    public List<ShoeDTO> getShoes(ShoeSearchDTO dto, Pageable pageable) {
        QShoeVariant variant = QShoeVariant.shoeVariant;

        BooleanExpression expression = variant.shoe.id.isNotNull();

        if (CollectionUtils.isNotEmpty(dto.getBrands())) {
            expression = expression.and(variant.shoe.brand.in(dto.getBrands()));
        }
        if (CollectionUtils.isNotEmpty(dto.getShoeTypes())) {
            expression = expression.and(variant.shoe.shoeType.in(dto.getShoeTypes()));
        }
        if (dto.getPriceFrom() != null || dto.getPriceTo() != null) {
            expression = expression.and(variant.shoe.price.between(dto.getPriceFrom(), dto.getPriceTo()));
        }
        if (CollectionUtils.isNotEmpty(dto.getShankColors())) {
            expression = expression.and(variant.shankColor.in(dto.getShankColors()));
        }
        if (CollectionUtils.isNotEmpty(dto.getSoleColors())) {
            expression = expression.and(variant.soleColor.in(dto.getSoleColors()));
        }

        List<ShoeVariant> shoeVariants = shoeVariantRepository.findAll(expression, pageable).getContent();

        return mapToDTO(shoeVariants);
    }

    private List<ShoeDTO> mapToDTO(List<ShoeVariant> shoeVariants) {
        return shoeVariants.stream()
                .map(shoeMapper::toDTO)
                .distinct()
                .collect(Collectors.toList());
    }
}
