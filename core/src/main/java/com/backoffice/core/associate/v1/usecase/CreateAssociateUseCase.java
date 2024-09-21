package com.backoffice.core.associate.v1.usecase;

import com.backoffice.core.associate.v1.adapter.AssociateDataProcess;
import com.backoffice.core.associate.v1.exception.AssociateException;
import com.backoffice.core.associate.v1.model.Associate;

import javax.inject.Named;
import java.util.Optional;
import java.util.UUID;


@Named
public class CreateAssociateUseCase extends AssociateUseCaseAbstract {

    public CreateAssociateUseCase(AssociateDataProcess associateDataProcess) {
        super(associateDataProcess);
        this.dataProvider = associateDataProcess;
    }

    private final AssociateDataProcess dataProvider;

    public Optional<Associate> execute(Associate associate) throws AssociateException {
        associate.setId(UUID.randomUUID().toString());
        validate(associate);

        return dataProvider.save(associate);
    }
}
