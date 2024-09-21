package com.backoffice.core.associate.v1.usecase;

import com.backoffice.core.associate.v1.adapter.AssociateDataProcess;
import com.backoffice.core.associate.v1.exception.AssociateException;
import com.backoffice.core.associate.v1.model.Associate;
import lombok.RequiredArgsConstructor;

import java.util.regex.Pattern;

import static java.util.Objects.isNull;

@RequiredArgsConstructor
public abstract class AssociateUseCaseAbstract {

    private final AssociateDataProcess dataProvider;
    private final Pattern CPF_FORMAT = Pattern.compile("(\\d{3})[.](\\d{3})[.](\\d{3})-(\\d{2})");

    protected void validate(Associate associate) throws AssociateException {
        if (isNull(associate.getCpf()) || associate.getCpf().isEmpty()) {
            throw new AssociateException("Associate cpf is required");
        }

        if (!CPF_FORMAT.matcher(associate.getCpf()).matches()) {
            throw new AssociateException("cpf need to formated");
        }

        if (dataProvider.existsCpfInAnotherAssociate(associate.getCpf(), associate.getId())) {
            throw new AssociateException(String.format("Cpf %s already exist", associate.getCpf()));
        }

        if (isNull(associate.getName()) || associate.getName().isEmpty()) {
            throw new AssociateException("Associate name is required");
        }
    }
}
