package pl.shoeshop.shoeshop.dto;

import lombok.Data;
import pl.shoeshop.shoeshop.type.BrandType;
import pl.shoeshop.shoeshop.type.ColorType;
import pl.shoeshop.shoeshop.type.GenderType;
import pl.shoeshop.shoeshop.type.ShoeType;

import java.math.BigDecimal;

@Data
public class ShoeDTO {

    private Long id;

    private Long variantId;

    private BrandType brand;

    private String model;

    private BigDecimal price;

    private GenderType gender;

    private ShoeType shoeType;

    private ColorType shankColor;

    private ColorType soleColor;

    private String description;
}
