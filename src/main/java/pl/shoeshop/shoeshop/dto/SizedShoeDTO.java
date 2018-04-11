package pl.shoeshop.shoeshop.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SizedShoeDTO {
    private Integer size;
    private ShoeAvailabilityStatus status;
}
