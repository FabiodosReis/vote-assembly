package com.backoffice.core.associate.v1.usecase;

import com.backoffice.core.associate.adapter.AssociateDataProvider;
import com.backoffice.core.associate.exception.AssociateException;
import com.backoffice.core.associate.model.Associate;

import javax.inject.Named;
import java.util.Optional;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;


@Named
public class UpdateAssociateUseCase extends AssociateUseCaseAbstract {

    public UpdateAssociateUseCase(AssociateDataProvider associateDataProvider) {
        super(associateDataProvider);
        this.dataProvider = associateDataProvider;
    }

    private final AssociateDataProvider dataProvider;

    public Optional<Associate> execute(Associate associate) throws AssociateException {
        if (isNull(associate.getId()) || associate.getId().isEmpty()) {
            throw new AssociateException("Associate id is required");
        }

        var optionalAssociate = dataProvider.findById(associate.getId())
                .orElseThrow(() -> new AssociateException(String.format("Associate %s not found", associate.getId())));

        if (nonNull(associate.getCpf())) {
            optionalAssociate.setCpf(associate.getCpf());
        }

        if (nonNull(associate.getName())) {
            optionalAssociate.setName(associate.getName());
        }

        validate(associate);

        return dataProvider.update(optionalAssociate);
    }
}
