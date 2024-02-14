package com.Identity_reconcilation.Identity.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class IdentityResponse {
    private int id;
    private String phoneNumber;
    private String email;
    private int linkedID;
    private String linkPrecedence;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
