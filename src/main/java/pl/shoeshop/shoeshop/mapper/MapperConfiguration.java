package pl.shoeshop.shoeshop.mapper;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfiguration {

    @Bean
    public ShoeMapper shoeMapper() {
        return new pl.shoeshop.shoeshop.mapper.ShoeMapperImpl();
    }
}
