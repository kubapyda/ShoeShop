package pl.shoeshop.shoeshop.dto;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class RateDTO {

    @Min(1)
    @Max(10)
    private Integer rate;

    @Size(max = 500)
    private String comment;

    @Email
    private String identityEmail;

    @NotNull
    private Long orderId;

    @NotNull
    private Long shoeId;
}
