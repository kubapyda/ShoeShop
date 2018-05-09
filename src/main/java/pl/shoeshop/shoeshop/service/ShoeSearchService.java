package pl.shoeshop.shoeshop.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pl.shoeshop.shoeshop.dto.ShoeDTO;
import pl.shoeshop.shoeshop.dto.ShoeSearchDTO;
import pl.shoeshop.shoeshop.entity.Shoe;

import java.util.List;
import java.util.Optional;

public interface ShoeSearchService {
    Optional<Shoe> getShoe(Long id);

    Page<ShoeDTO> getShoes(Pageable pageable);

    Page<ShoeDTO> getShoes(String phrase, Pageable pageable);

    Page<ShoeDTO> getShoes(ShoeSearchDTO dto, Pageable pageable);
}
