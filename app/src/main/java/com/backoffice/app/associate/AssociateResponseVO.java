package com.backoffice.app.associate;

import com.backoffice.core.associate.model.Associate;
import lombok.Getter;

@Getter
public class AssociateResponseVO {

    private String id;
    private String name;
    private String cpf;
    private String status;

    public AssociateResponseVO (Associate associate){
        this.id = associate.getId();
        this.name = associate.getName();
        this.cpf = associate.getCpf();
        this.status = associate.getStatus().name();
    }
}
