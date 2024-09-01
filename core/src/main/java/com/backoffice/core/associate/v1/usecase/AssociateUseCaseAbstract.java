package com.backoffice.core.associate.v1.usecase;

import com.backoffice.core.associate.enums.StatusAssociateEnum;
import com.backoffice.core.associate.exception.AssociateException;
import com.backoffice.core.associate.model.Associate;

import java.util.regex.Pattern;

import static java.util.Objects.isNull;

public abstract class AssociateUseCaseAbstract {

    private final Pattern CPF_FORMATED = Pattern.compile("(\\d{3})[.](\\d{3})[.](\\d{3})-(\\d{2})");


    protected void validate(Associate associate) throws AssociateException {
        if (isNull(associate.getCpf()) || associate.getCpf().isEmpty()) {
            throw new AssociateException("Associate cpf is required");
        }

        if (!CPF_FORMATED.matcher(associate.getCpf()).matches()) {
            throw new AssociateException("cpf need to formated");
        }

        if (isNull(associate.getName()) || associate.getName().isEmpty()) {
            throw new AssociateException("Associate name is required");
        }
    }

    protected void configStatus(Associate associate) {
        if (associate.isAbleToVote()) {
            associate.setStatus(StatusAssociateEnum.ABLE_TO_VOTE);
        } else {
            associate.setStatus(StatusAssociateEnum.UNABLE_TO_VOTE);
        }
    }
}
