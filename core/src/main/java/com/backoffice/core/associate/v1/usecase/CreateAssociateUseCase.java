package com.backoffice.core.associate.v1.usecase;

import com.backoffice.core.associate.adapter.AssociateDataProvider;
import com.backoffice.core.associate.exception.AssociateException;
import com.backoffice.core.associate.model.Associate;


import javax.inject.Named;
import java.util.Optional;
import java.util.UUID;

@Named
public class CreateAssociateUseCase extends AssociateUseCaseAbstract {

    public CreateAssociateUseCase(AssociateDataProvider associateDataProvider) {
        super(associateDataProvider);
        this.dataProvider = associateDataProvider;
    }

    private final AssociateDataProvider dataProvider;

    public Optional<Associate> execute(Associate associate) throws AssociateException {
        associate.setId(UUID.randomUUID().toString());
        validate(associate);

        return dataProvider.save(associate);
    }
}
