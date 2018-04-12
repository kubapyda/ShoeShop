package pl.shoeshop.shoeshop.service;

import pl.shoeshop.shoeshop.dto.SizeDictionaryDTO;

import java.util.List;

public interface ShoeVariantService {

    List<SizeDictionaryDTO> getSizes(Long variantId);
}
