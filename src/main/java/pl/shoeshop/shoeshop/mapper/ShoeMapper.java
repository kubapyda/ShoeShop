package pl.shoeshop.shoeshop.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import pl.shoeshop.shoeshop.dto.ShoeDTO;
import pl.shoeshop.shoeshop.entity.ShoeVariant;

@Mapper
public interface ShoeMapper {

    @Mappings(value = {
            @Mapping(source = "shoe.id", target = "id"),
            @Mapping(source = "id", target = "variantId"),
            @Mapping(source = "shoe.model", target = "model"),
            @Mapping(source = "shoe.brand", target = "brand"),
            @Mapping(source = "shoe.price", target = "price"),
            @Mapping(source = "shoe.gender", target = "gender"),
            @Mapping(source = "shoe.shoeType", target = "shoeType"),
            @Mapping(source = "shoe.description", target = "description"),
            @Mapping(source = "shankColor", target = "shankColor"),
            @Mapping(source = "soleColor", target = "soleColor")
    })
    ShoeDTO toDTO(ShoeVariant variant);
}
