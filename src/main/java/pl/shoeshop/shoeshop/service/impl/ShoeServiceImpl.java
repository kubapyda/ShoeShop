package pl.shoeshop.shoeshop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.shoeshop.shoeshop.dto.ShoeSearchDTO;
import pl.shoeshop.shoeshop.entity.Shoe;
import pl.shoeshop.shoeshop.repository.ShoeVariantRepository;
import pl.shoeshop.shoeshop.service.ShoeService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

@Service
public class ShoeServiceImpl implements ShoeService {

    private static final String UPLOAD_ROOT = "/upload/shoeImg";

    private ShoeVariantRepository shoeVariantRepository;
    private ResourceLoader resourceLoader;

    @Autowired
    public ShoeServiceImpl(ShoeVariantRepository shoeVariantRepository, ResourceLoader resourceLoader) {
        this.shoeVariantRepository = shoeVariantRepository;
        this.resourceLoader = resourceLoader;
    }

    @Override
    public List<Shoe> getShoes(String phrase, Pageable pageable) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Shoe> getShoes(ShoeSearchDTO dto, Pageable pageable) {
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
    public Resource getImage(Long variantId) {
        return resourceLoader.getResource("file:" + UPLOAD_ROOT + "/" + variantId + ".png");
    }

    @Override
    public void setImage(Long variantId, MultipartFile file) throws IOException {
        if (!file.isEmpty()) {
            File fileDir = new File(UPLOAD_ROOT, variantId + ".png");
            if (!fileDir.exists()) {
                fileDir.getParentFile().mkdirs();
            } else {
                fileDir.delete();
            }
            Files.copy(file.getInputStream(), fileDir.toPath());
        }
    }
}
