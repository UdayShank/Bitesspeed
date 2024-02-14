package com.Identity_reconcilation.Identity.util;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@SuppressWarnings("serial")
public class ReconcilationException extends RuntimeException{
    private String errorMessage;
    private int errorCode;
    
    public ReconcilationException(int errorCode, String errorMessage){
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}
