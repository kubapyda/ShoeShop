package pl.shoeshop.shoeshop.resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.shoeshop.shoeshop.dto.ShoeDTO;
import pl.shoeshop.shoeshop.dto.ShoeSearchDTO;
import pl.shoeshop.shoeshop.dto.SizedShoeDTO;
import pl.shoeshop.shoeshop.entity.Shoe;
import pl.shoeshop.shoeshop.service.ShoeService;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("shoes/")
public class ShoeResource {

    private ShoeService shoeService;

    @Autowired
    public ShoeResource(ShoeService shoeService) {
        this.shoeService = shoeService;
    }

    @RequestMapping(value = "find", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<ShoeDTO>> getShoes(Pageable pageable) {
        List<ShoeDTO> shoes = shoeService.getShoes(StringUtils.EMPTY, pageable);
        return ResponseEntity.ok(shoes);
    }

    @RequestMapping(value = "find/{phrase}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<ShoeDTO>> getShoes(@PathVariable String phrase, Pageable pageable) {
        List<ShoeDTO> shoes = shoeService.getShoes(phrase, pageable);
        return ResponseEntity.ok(shoes);
    }

    @RequestMapping(value = "find", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<ShoeDTO>> getShoes(@RequestBody ShoeSearchDTO dto, Pageable pageable) {
        List<ShoeDTO> shoes = shoeService.getShoes(dto, pageable);
        return ResponseEntity.ok(shoes);
    }

    @RequestMapping(value = "add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Shoe> addShoe(@RequestBody Shoe shoe) {
        shoeService.addShoe(shoe);
        return ResponseEntity.ok(shoe);
    }

    @RequestMapping(value = "edit", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Shoe> editShoe(@RequestBody Shoe shoe) {
        shoeService.editShoe(shoe);
        return ResponseEntity.ok(shoe);
    }

    @RequestMapping(value = "delete/{shoeId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> deleteShoe(@PathVariable Long shoeId) {
        shoeService.deleteShoe(shoeId);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "{variantId}/picture", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Resource> getPicture(@PathVariable Long variantId) {
        Resource image = shoeService.getImage(variantId);
        return ResponseEntity.ok(image);
    }

    @RequestMapping(value = "{variantId}/picture", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Void> addPicture(@PathVariable Long variantId, @RequestPart MultipartFile file) throws IOException {
        shoeService.setImage(variantId, file);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "{variantId}/sizes", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<SizedShoeDTO>> getSizes(@PathVariable("variantId") Long variantId) {
        List<SizedShoeDTO> result = shoeService.getSizes(variantId);
        return ResponseEntity.ok(result);
    }
}
