package pl.shoeshop.shoeshop.service;

import pl.shoeshop.shoeshop.dto.VariantDTO;
import pl.shoeshop.shoeshop.entity.SizedShoe;
import pl.shoeshop.shoeshop.projection.SizeDictionaryProjection;

import java.util.List;

public interface ShoeVariantService {

    List<SizeDictionaryProjection> getSizes(Long variantId);
    Iterable<SizedShoe> getSizedShoes(List<VariantDTO> variants);
}
