package com.backoffice.core.associate.v1.usecase.dataProvider;

import com.backoffice.core.associate.adapter.AssociateDataProvider;
import com.backoffice.core.associate.model.Associate;

import java.util.Optional;

public class NotFoundAssociateDataProviderTest implements AssociateDataProvider {

    @Override
    public Optional<Associate> save(Associate associate) {
        return Optional.of(associate);
    }

    @Override
    public Optional<Associate> update(Associate associate) {
        return Optional.of(associate);
    }

    @Override
    public Optional<Associate> getById(String id) {
        return Optional.empty();
    }

    @Override
    public boolean existsCpfInAnotherAssociate(String cpf, String associateId) {
        return false;
    }
}
