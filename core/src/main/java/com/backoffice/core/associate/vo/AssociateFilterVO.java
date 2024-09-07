package com.backoffice.core.associate.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Builder
public class AssociateFilterVO {

    private Integer page;
    private Integer size;
    private String cpf;

}
