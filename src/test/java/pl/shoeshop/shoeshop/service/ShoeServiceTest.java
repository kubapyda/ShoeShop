package pl.shoeshop.shoeshop.service;

import org.assertj.core.util.Lists;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import pl.shoeshop.shoeshop.dto.ShoeSearchDTO;
import pl.shoeshop.shoeshop.entity.Shoe;
import pl.shoeshop.shoeshop.type.BrandType;
import pl.shoeshop.shoeshop.type.GenderType;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class ShoeServiceTest {

    @Autowired
    private ShoeService shoeService;

    @Test
    public void getShoesByPhrase() {
        Shoe shoe1 = Shoe.builder().brandType(BrandType.ADIDAS).model("superstar").build();
        Shoe shoe2 = Shoe.builder().brandType(BrandType.NEW_BALANCE).model("some model").build();
        Shoe shoe3 = Shoe.builder().brandType(BrandType.NIKE).model("air max").build();

        shoeService.addShoe(shoe1);
        shoeService.addShoe(shoe2);
        shoeService.addShoe(shoe3);

        Pageable pageable = PageRequest.of(1, 100);
        Sort sort = pageable.getSort();

        String phrase = "air";

        List<Shoe> shoes = shoeService.getShoes(phrase, pageable, sort);
        Assert.assertEquals(shoe3.getModel(), shoes.get(0).getModel());
        Assert.assertEquals(1, shoes.size());

        phrase = "model";

        shoes = shoeService.getShoes(phrase, pageable, sort);
        Assert.assertEquals(shoe2.getModel(), shoes.get(0).getModel());
        Assert.assertEquals(1, shoes.size());

        phrase = "n";

        shoes = shoeService.getShoes(phrase, pageable, sort);
        Assert.assertEquals(2, shoes.size());
    }

    @Test
    public void getShoesBySearchDTO() {
        Shoe shoe1 = Shoe.builder().brandType(BrandType.ADIDAS).model("superstar").price(BigDecimal.valueOf(150)).build();
        Shoe shoe2 = Shoe.builder().brandType(BrandType.NEW_BALANCE).model("some model").price(BigDecimal.valueOf(250)).build();
        Shoe shoe3 = Shoe.builder().brandType(BrandType.NIKE).model("air max").price(BigDecimal.valueOf(300)).build();

        shoeService.addShoe(shoe1);
        shoeService.addShoe(shoe2);
        shoeService.addShoe(shoe3);

        ShoeSearchDTO dto = new ShoeSearchDTO();
        dto.setBrands(Lists.newArrayList(BrandType.NEW_BALANCE, BrandType.NIKE));

        List<Shoe> shoes = shoeService.getShoes(dto, Pageable.unpaged(), Sort.unsorted());

        Assert.assertEquals(2, shoes.size());
        Assert.assertFalse(shoes.stream().anyMatch(s -> BrandType.ADIDAS.equals(s.getBrandType())));

        dto.setPriceFrom(BigDecimal.valueOf(200));
        dto.setPriceTo(BigDecimal.valueOf(290));

        shoes = shoeService.getShoes(dto, Pageable.unpaged(), Sort.unsorted());

        Assert.assertEquals(1, shoes.size());
        Assert.assertTrue("some model".equals(shoes.get(0).getModel()));
    }
}