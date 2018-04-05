package pl.shoeshop.shoeshop.service;

import org.springframework.core.io.Resource;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;
import pl.shoeshop.shoeshop.dto.ShoeSearchDTO;
import pl.shoeshop.shoeshop.entity.Shoe;

import java.io.IOException;
import java.util.List;

public interface ShoeService {
    List<Shoe> getShoes(String phrase, Pageable pageable);

    List<Shoe> getShoes(ShoeSearchDTO dto, Pageable pageable);

    void addShoe(Shoe shoe);

    void editShoe(Shoe shoe);

    Resource getImage(Long variantId);

    void setImage(Long variantId, MultipartFile file) throws IOException;
}
