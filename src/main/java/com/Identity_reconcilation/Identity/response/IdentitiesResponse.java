package com.Identity_reconcilation.Identity.response;

import lombok.Data;
import java.util.List;
@Data
public class IdentitiesResponse {
    private String message;
    private List<IdentityResponse> details;
}
