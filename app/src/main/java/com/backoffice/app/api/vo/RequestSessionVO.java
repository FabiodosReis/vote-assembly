package com.backoffice.app.api.vo;

import com.backoffice.core.session.v1.model.Session;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestSessionVO {


    @Schema(example = "Session max credit limit")
    private String description;

    public Session toEntity(){
        return new Session(
                null,
                description
        );
    }
}
