package com.backoffice.app.infraestructure.handler;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;


@Builder @Getter
public class DetailError {

    private int code;
    private LocalDateTime date;
    private String message;

}
