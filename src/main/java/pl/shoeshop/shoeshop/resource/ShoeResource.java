package pl.shoeshop.shoeshop.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.shoeshop.shoeshop.dto.ShoeSearchDTO;
import pl.shoeshop.shoeshop.entity.Shoe;
import pl.shoeshop.shoeshop.service.ShoeService;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("shoes/")
public class ShoeResource {

    @Autowired
    ShoeService shoeService;

    @RequestMapping(value = "find/{phrase}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<Shoe>> getShoes(@PathVariable String phrase, Pageable pageable, Sort sort) {
        return null;
    }

    @RequestMapping(value = "find", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<Shoe>> getShoes(@RequestBody ShoeSearchDTO dto, Pageable pageable, Sort sort) {
        return null;
    }

    @RequestMapping(value = "add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> addShoe(Shoe shoe) {
        return null;
    }

    @RequestMapping(value = "edit", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> editShoe(Shoe shoe) {
        return null;
    }

    @RequestMapping(value = "{variantId}/picture", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Resource> getPicture(@PathVariable Long variantId) throws IOException {
        Resource image = shoeService.getImage(variantId);
        return ResponseEntity.ok(image);
    }

    @RequestMapping(value = "{variantId}/picture", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Void> addPicture(@PathVariable Long variantId, @RequestPart MultipartFile file) throws IOException {
        shoeService.setImage(variantId, file);
        return ResponseEntity.ok().build();
    }
}
