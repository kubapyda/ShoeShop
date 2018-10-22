package pl.shoeshop.shoeshop.projection;

import java.math.BigDecimal;

public interface OrderedShoeProjection {

    Long getShoeId();
    String getBrand();
    String getModel();
    String getShankColor();
    String getSoleColor();
    Integer getSize();
    BigDecimal getPrice();
    Integer getQuantity();
}