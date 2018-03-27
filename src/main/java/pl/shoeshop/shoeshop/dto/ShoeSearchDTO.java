package pl.shoeshop.shoeshop.dto;

import lombok.Data;
import pl.shoeshop.shoeshop.type.BrandType;
import pl.shoeshop.shoeshop.type.ColorType;
import pl.shoeshop.shoeshop.type.ShoeType;

import javax.validation.constraints.Digits;
import java.math.BigDecimal;
import java.util.List;

@Data
public class ShoeSearchDTO {

    private List<BrandType> brands;
    private List<ColorType> colors;
    private List<ShoeType> shoeTypes;

    @Digits(integer = 4, fraction = 2)
    private BigDecimal priceFrom;

    @Digits(integer = 4, fraction = 2)
    private BigDecimal priceTo;
}
