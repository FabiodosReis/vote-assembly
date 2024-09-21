package com.backoffice.app.application.api.v1.vo;

import com.backoffice.core.associate.v1.model.Associate;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AssociateUpdateRequestVO {

    private String id;
    private String cpf;
    private String name;

    public Associate toEntity(){
        return new Associate(
                id,
                name,
                cpf
        );
    }

}
