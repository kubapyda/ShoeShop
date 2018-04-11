package pl.shoeshop.shoeshop.service;

import org.springframework.data.domain.Pageable;
import pl.shoeshop.shoeshop.dto.ShoeDTO;
import pl.shoeshop.shoeshop.dto.ShoeSearchDTO;
import pl.shoeshop.shoeshop.entity.Shoe;

import java.util.List;

public interface ShoeSearchService {
    List<ShoeDTO> getShoes(Pageable pageable);

    List<ShoeDTO> getShoes(String phrase, Pageable pageable);

    List<ShoeDTO> getShoes(ShoeSearchDTO dto, Pageable pageable);
}
