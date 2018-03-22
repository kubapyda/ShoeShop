package pl.shoeshop.shoeshop.resource;

import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.shoeshop.shoeshop.entity.Shoe;

import java.util.List;

@RestController(value = "api/")
public class ShoeResource {

    @RequestMapping(value = "shoes", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Shoe> getShoes(Pageable pageable) {
        return null;
    }
}
