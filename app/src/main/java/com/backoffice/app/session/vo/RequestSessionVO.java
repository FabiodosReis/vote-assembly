package com.backoffice.app.session.vo;

import com.backoffice.core.session.model.Session;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class RequestSessionVO {


    private String description;

    public Session toEntity(){
        return new Session(
                null,
                description
        );
    }
}
