package pl.shoeshop.shoeshop.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
public class VoteDTO {

    Long rateId;

    @NotNull
    Boolean isUseful;

    @Email
    private String identityEmail;
}
