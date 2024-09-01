package com.backoffice.core.associate.v1.usecase;

import com.backoffice.core.associate.adapter.AssociateDataProvider;
import com.backoffice.core.associate.exception.AssociateException;
import com.backoffice.core.associate.model.Associate;
import lombok.RequiredArgsConstructor;

import javax.inject.Named;
import java.util.Optional;

import static java.util.Objects.isNull;

@Named
@RequiredArgsConstructor
public class UpdateAssociateUseCase extends AssociateUseCaseAbstract {

    private final AssociateDataProvider dataProvider;

    public Optional<Associate> update(Associate associate) throws AssociateException {
        if (isNull(associate.getId()) || associate.getId().isEmpty()) {
            throw new AssociateException("Associate id is required");
        }

        if (dataProvider.existsCpfInAnotherAssociate(associate.getCpf(), associate.getId())) {
            throw new AssociateException(String.format("Cpf %s already exists", associate.getCpf()));
        }

        dataProvider.getById(associate.getId())
                .orElseThrow(() -> new AssociateException(String.format("Associate %s not found", associate.getId())));

        validate(associate);
        configStatus(associate);

        return dataProvider.update(associate);
    }
}
