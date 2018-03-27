package pl.shoeshop.shoeshop.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.shoeshop.shoeshop.entity.*;
import pl.shoeshop.shoeshop.repository.*;

import java.util.List;

@RestController
@RequestMapping("test/")
public class TestResource {

    @Autowired
    private ShoeVariantRepository orderRepository;

    @GetMapping("orders")
    public List<ShoeVariant> read() {
        return orderRepository.findAll();
    }

}
