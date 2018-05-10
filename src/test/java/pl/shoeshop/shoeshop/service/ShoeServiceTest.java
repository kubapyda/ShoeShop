package pl.shoeshop.shoeshop.service;

import org.assertj.core.util.Lists;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import pl.shoeshop.shoeshop.dto.ShoeDTO;
import pl.shoeshop.shoeshop.dto.ShoeSearchDTO;
import pl.shoeshop.shoeshop.entity.Shoe;
import pl.shoeshop.shoeshop.entity.ShoeVariant;
import pl.shoeshop.shoeshop.repository.ShoeRepository;
import pl.shoeshop.shoeshop.type.BrandType;

import java.math.BigDecimal;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class ShoeServiceTest {

    @Autowired
    private ShoeService shoeService;

    @Autowired
    private ShoeRepository shoeRepository;

    @Test
    public void getShoesByPhrase() {
        Shoe shoe1 = Shoe.builder().brand(BrandType.ADIDAS).model("superstar")
                .variants(Lists.newArrayList(new ShoeVariant()))
                .build();

        Shoe shoe2 = Shoe.builder().brand(BrandType.NEW_BALANCE).model("some model")
                .variants(Lists.newArrayList(new ShoeVariant()))
                .build();

        Shoe shoe3 = Shoe.builder().brand(BrandType.NIKE).model("air max")
                .variants(Lists.newArrayList(new ShoeVariant()))
                .build();

        shoeService.addShoe(shoe1);
        shoeService.addShoe(shoe2);
        shoeService.addShoe(shoe3);

        Pageable pageable = PageRequest.of(0, 100);

        String phrase = "air";

        List<ShoeDTO> shoes = shoeService.getShoes(phrase, pageable).getContent();
        Assert.assertEquals(shoe3.getModel(), shoes.get(0).getModel());
        Assert.assertEquals(1, shoes.size());

        phrase = "model";

        shoes = shoeService.getShoes(phrase, pageable).getContent();
        Assert.assertEquals(shoe2.getModel(), shoes.get(0).getModel());
        Assert.assertEquals(1, shoes.size());

        phrase = "N"; //H2 distinguish between lower and uppercase letters but MySQL does not

        shoes = shoeService.getShoes(phrase, pageable).getContent();
        Assert.assertEquals(2, shoes.size());
    }

    @Test
    public void getShoesBySearchDTO() {
        Shoe shoe1 = Shoe.builder().brand(BrandType.ADIDAS).model("superstar").price(BigDecimal.valueOf(150))
                .variants(Lists.newArrayList(new ShoeVariant()))
                .build();

        Shoe shoe2 = Shoe.builder().brand(BrandType.NEW_BALANCE).model("some model").price(BigDecimal.valueOf(250))
                .variants(Lists.newArrayList(new ShoeVariant()))
                .build();

        Shoe shoe3 = Shoe.builder().brand(BrandType.NIKE).model("air max").price(BigDecimal.valueOf(300))
                .variants(Lists.newArrayList(new ShoeVariant()))
                .build();

        shoeService.addShoe(shoe1);
        shoeService.addShoe(shoe2);
        shoeService.addShoe(shoe3);

        ShoeSearchDTO dto = new ShoeSearchDTO();
        dto.setBrands(Lists.newArrayList(BrandType.NEW_BALANCE, BrandType.NIKE));

        List<ShoeDTO> shoes = shoeService.getShoes(dto, Pageable.unpaged()).getContent();

        Assert.assertEquals(2, shoes.size());
        Assert.assertFalse(shoes.stream().anyMatch(s -> BrandType.ADIDAS.equals(s.getBrand())));

        dto.setPriceFrom(BigDecimal.valueOf(200));
        dto.setPriceTo(BigDecimal.valueOf(290));

        shoes = shoeService.getShoes(dto, Pageable.unpaged()).getContent();

        Assert.assertEquals(1, shoes.size());
        Assert.assertTrue("some model".equals(shoes.get(0).getModel()));
    }

    @Before
    public void clearDatabase() {
        shoeRepository.deleteAll();
    }
}