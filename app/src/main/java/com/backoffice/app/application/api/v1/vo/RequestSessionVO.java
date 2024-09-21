package com.backoffice.app.application.api.v1.vo;

import com.backoffice.core.session.v1.model.Session;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestSessionVO {


    private String description;

    public Session toEntity(){
        return new Session(
                null,
                description
        );
    }
}
