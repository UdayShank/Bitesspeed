package com.Identity_reconcilation.Identity.request;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class UpdateContactRequest {
    @NotBlank(message = "PhoneNumber can not be blank")
    @NotNull(message = "Phone Number can not be null")
    private String phoneNumber;
    @NotBlank(message = "email can not be blank")
    @NotNull(message="email can not be null")
    private String email;
    private String linkPrecedence;
}
