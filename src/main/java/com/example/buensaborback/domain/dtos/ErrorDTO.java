package com.example.buensaborback.domain.dtos;

import lombok.Builder;

@Builder
public class ErrorDTO {
    private String errorMsg;
    private String errorClass;
}
