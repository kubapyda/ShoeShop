package pl.shoeshop.shoeshop.projection;

import java.math.BigDecimal;

public interface OrderedShoeProjection {

    String getBrand();
    String getShankColor();
    String getSoleColor();
    Integer getSize();
    BigDecimal getPrice();
    Integer getQuantity();
}