package com.backoffice.core.associate.v1.model;

import br.com.caelum.stella.validation.CPFValidator;
import br.com.caelum.stella.validation.InvalidStateException;
import com.backoffice.core.associate.v1.enums.StatusAssociateEnum;
import lombok.Getter;
import lombok.Setter;


@Setter @Getter
public class Associate {


    public Associate(String id, String name, String cpf) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.status = isAbleToVote() ? StatusAssociateEnum.ABLE_TO_VOTE : StatusAssociateEnum.UNABLE_TO_VOTE;
    }

    private String id;
    private String name;
    private String cpf;
    private StatusAssociateEnum status;

    public boolean isAbleToVote() {
        try {
            var cpfValidator = new CPFValidator(true);
            cpfValidator.assertValid(cpf);
        } catch (InvalidStateException e) {
            return false;
        }
        return true;
    }
}
