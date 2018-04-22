package pl.shoeshop.shoeshop.service;

import pl.shoeshop.shoeshop.dto.SizeDictionaryDTO;
import pl.shoeshop.shoeshop.dto.VariantDTO;
import pl.shoeshop.shoeshop.entity.SizedShoe;

import java.util.List;

public interface ShoeVariantService {

    List<SizeDictionaryDTO> getSizes(Long variantId);
    Iterable<SizedShoe> getSizedShoes(List<VariantDTO> variants);
}
