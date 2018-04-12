package pl.shoeshop.shoeshop.service;

import org.springframework.core.io.Resource;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;
import pl.shoeshop.shoeshop.dto.ShoeDTO;
import pl.shoeshop.shoeshop.dto.ShoeSearchDTO;
import pl.shoeshop.shoeshop.entity.Shoe;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface ShoeService {

    Optional<Shoe> getShoe(Long id);

    List<ShoeDTO> getShoes(Pageable pageable);

    List<ShoeDTO> getShoes(String phrase, Pageable pageable);

    List<ShoeDTO> getShoes(ShoeSearchDTO dto, Pageable pageable);

    void addShoe(Shoe shoe);

    void editShoe(Shoe shoe);

    void deleteShoe(Long shoeId);

    Resource getImage(Long variantId);

    void setImage(Long variantId, MultipartFile file) throws IOException;
}
