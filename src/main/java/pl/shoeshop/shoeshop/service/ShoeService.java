package pl.shoeshop.shoeshop.service;

import org.springframework.core.io.Resource;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.multipart.MultipartFile;
import pl.shoeshop.shoeshop.dto.ShoeSearchDTO;
import pl.shoeshop.shoeshop.entity.Shoe;

import java.io.IOException;
import java.util.List;

public interface ShoeService {
    List<Shoe> getShoes(String phrase, Pageable pageable, Sort sort);

    List<Shoe> getShoes(ShoeSearchDTO dto, Pageable pageable, Sort sort);

    void addShoe(Shoe shoe);

    void editShoe(Shoe shoe);

    Resource getImage(String fileName);

    void setImage(Long variantId, MultipartFile file) throws IOException;
}
