package com.backoffice.core.associate.v1.usecase;

import com.backoffice.core.associate.adapter.AssociateDataProvider;
import com.backoffice.core.associate.exception.AssociateException;
import com.backoffice.core.associate.model.Associate;
import lombok.RequiredArgsConstructor;


import javax.inject.Named;
import java.util.Optional;
import java.util.UUID;

@Named
@RequiredArgsConstructor
public class CreateAssociateUseCase extends AssociateUseCaseAbstract {

    private final AssociateDataProvider dataProvider;

    public Optional<Associate> create(Associate associate) throws AssociateException {
        associate.setId(UUID.randomUUID().toString());
        validate(associate);
        configStatus(associate);

        return dataProvider.save(associate);
    }
}
