package com.backoffice.core.associate.vo;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AssociateFilterVO {

    private int page = 1;
    private int size = 10;
    private String cpf;

}
