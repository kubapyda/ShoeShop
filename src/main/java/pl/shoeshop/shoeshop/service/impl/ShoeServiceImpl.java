package pl.shoeshop.shoeshop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.shoeshop.shoeshop.dto.ShoeSearchDTO;
import pl.shoeshop.shoeshop.entity.Shoe;
import pl.shoeshop.shoeshop.entity.ShoeVariant;
import pl.shoeshop.shoeshop.repository.ShoeVariantRepository;
import pl.shoeshop.shoeshop.service.ShoeService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@Service
public class ShoeServiceImpl implements ShoeService {

    private static final String UPLOAD_ROOT = "upload-dir";

    private ShoeVariantRepository shoeVariantRepository;
    private ResourceLoader resourceLoader;

    @Autowired
    public ShoeServiceImpl(ShoeVariantRepository shoeVariantRepository, ResourceLoader resourceLoader) {
        this.shoeVariantRepository = shoeVariantRepository;
        this.resourceLoader = resourceLoader;
    }

    @Override
    public List<Shoe> getShoes(String phrase, Pageable pageable, Sort sort) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Shoe> getShoes(ShoeSearchDTO dto, Pageable pageable, Sort sort) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void addShoe(Shoe shoe) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void editShoe(Shoe shoe) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Resource getImage(String fileName) {
        return resourceLoader.getResource("file:" + UPLOAD_ROOT + "/" + fileName);
    }

    @Override
    public void setImage(Long variantId, MultipartFile file) throws IOException {
        if (!file.isEmpty()) {
            Files.copy(file.getInputStream(), Paths.get(UPLOAD_ROOT, file.getOriginalFilename()));

            ShoeVariant shoeVariant = shoeVariantRepository.getOne(variantId);
            shoeVariant.setImage(file.getOriginalFilename());
            shoeVariantRepository.save(shoeVariant);
        }
    }
}
