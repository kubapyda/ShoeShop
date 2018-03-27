package pl.shoeshop.shoeshop.resource;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.shoeshop.shoeshop.dto.ShoeSearchDTO;
import pl.shoeshop.shoeshop.entity.Shoe;

import java.util.List;

@RestController
@RequestMapping("shoes/")
public class ShoeResource {

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

    @RequestMapping(value = "addPicture/{variantId}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> addPicture(@PathVariable Long variantId, MultipartFile file) {
        return null;
    }
}
