package pl.shoeshop.shoeshop.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

@Data
public class SizeDictionaryDTO {
    private Integer size;
    private String status;

    @QueryProjection
    public SizeDictionaryDTO(Integer size, String status) {
        this.size = size;
        this.status = status;
    }
}
