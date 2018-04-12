package pl.shoeshop.shoeshop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.shoeshop.shoeshop.dto.ShoeAvailabilityStatus;
import pl.shoeshop.shoeshop.dto.ShoeDTO;
import pl.shoeshop.shoeshop.dto.ShoeSearchDTO;
import pl.shoeshop.shoeshop.dto.SizedShoeDTO;
import pl.shoeshop.shoeshop.entity.Shoe;
import pl.shoeshop.shoeshop.entity.ShoeVariant;
import pl.shoeshop.shoeshop.entity.SizedShoe;
import pl.shoeshop.shoeshop.repository.ShoeRepository;
import pl.shoeshop.shoeshop.repository.ShoeVariantRepository;
import pl.shoeshop.shoeshop.service.ShoeSearchService;
import pl.shoeshop.shoeshop.service.ShoeService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ShoeServiceImpl implements ShoeService {

    private static final String UPLOAD_ROOT = "/upload/shoeImg";

    private ShoeSearchService shoeSearchService;
    private ShoeRepository shoeRepository;
    private ShoeVariantRepository shoeVariantRepository;
    private ResourceLoader resourceLoader;

    @Autowired
    public ShoeServiceImpl(ShoeSearchService shoeSearchService,
                           ShoeRepository shoeRepository,
                           ShoeVariantRepository shoeVariantRepository,
                           ResourceLoader resourceLoader) {
        this.shoeSearchService = shoeSearchService;
        this.shoeRepository = shoeRepository;
        this.shoeVariantRepository = shoeVariantRepository;
        this.resourceLoader = resourceLoader;
    }

    @Override
    public Optional<Shoe> getShoe(Long id) {
        return shoeSearchService.getShoe(id);
    }

    @Override
    public List<ShoeDTO> getShoes(Pageable pageable) {
        return shoeSearchService.getShoes(pageable);
    }

    @Override
    public List<ShoeDTO> getShoes(String phrase, Pageable pageable) {
        return shoeSearchService.getShoes(phrase, pageable);
    }

    @Override
    public List<ShoeDTO> getShoes(ShoeSearchDTO dto, Pageable pageable) {
        return shoeSearchService.getShoes(dto, pageable);
    }

    @Override
    public void addShoe(Shoe shoe) {
        if (shoe != null && shoe.getId() == null) {
            addRelations(shoe);
            shoeRepository.saveAndFlush(shoe);
        }
    }

    @Override
    public void editShoe(Shoe shoe) {
        if (shoe != null && shoe.getId() != null) {
            addRelations(shoe);
            shoeRepository.saveAndFlush(shoe);
        }
    }

    @Override
    public void deleteShoe(Long shoeId) {
        Shoe one = shoeRepository.getOne(shoeId);
        shoeRepository.delete(one);
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

    @Override
    public List<SizedShoeDTO> getSizes(Long variantId) {
        ShoeVariant shoeVariant = shoeVariantRepository.getOne(variantId);
        return shoeVariant.getSizedShoes().stream()
                .map(s -> {
                    ShoeAvailabilityStatus status;
                    Integer quantity = s.getQuantity();

                    if (quantity <= 0) {
                        status = ShoeAvailabilityStatus.UNAVAILABLE;
                    } else if (quantity <= 5) {
                        status = ShoeAvailabilityStatus.LAST_ITEMS;
                    } else {
                        status = ShoeAvailabilityStatus.AVAILABLE;
                    }

                    return SizedShoeDTO.builder()
                            .size(s.getSize())
                            .status(status)
                            .build();
                }).collect(Collectors.toList());
    }

    private void addRelations(Shoe shoe) {
        if (shoe != null && shoe.getVariants() != null) {
            for (ShoeVariant shoeVariant : shoe.getVariants()) {
                shoeVariant.setShoe(shoe);
                if (shoeVariant.getSizedShoes() != null) {
                    for (SizedShoe sizedShoe : shoeVariant.getSizedShoes()) {
                        sizedShoe.setShoeVariant(shoeVariant);
                    }
                }
            }
        }
    }
}
