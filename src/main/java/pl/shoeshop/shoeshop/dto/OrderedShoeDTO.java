package pl.shoeshop.shoeshop.dto;

import lombok.Getter;
import pl.shoeshop.shoeshop.projection.OrderedShoeProjection;

import java.math.BigDecimal;

@Getter
public class OrderedShoeDTO {

    private String brand;
    private String shankColor;
    private String soleColor;
    private Integer size;
    private BigDecimal price;
    private Integer quantity;

    public OrderedShoeDTO(OrderedShoeProjection projection) {
        brand = projection.getBrand();
        shankColor = projection.getShankColor();
        soleColor = projection.getSoleColor();
        size = projection.getSize();
        price = projection.getPrice();
        quantity = projection.getQuantity();
    }
}