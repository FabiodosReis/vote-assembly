package com.backoffice.app.application.service.client.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Builder
public class SnsEventDto {

    private String system;
    private String action;
    private Object event;
    private String createDate;
    private String version = "1.0";
    private String entity;

}
