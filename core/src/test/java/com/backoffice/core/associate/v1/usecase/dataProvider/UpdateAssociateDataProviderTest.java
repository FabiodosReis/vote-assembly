package com.backoffice.core.associate.v1.usecase.dataProvider;

import com.backoffice.core.associate.adapter.AssociateDataProvider;
import com.backoffice.core.associate.model.Associate;

import java.util.Optional;

public class UpdateAssociateDataProviderTest implements AssociateDataProvider {

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
        var associate = new Associate(
                "d324767c-0ec7-43d1-ac7f-9ffc6ba05b80",
                "Caroline Rocha",
                "055.613.735-36"
        );

        return Optional.of(associate);
    }

    @Override
    public boolean existsCpfInAnotherAssociate(String cpf, String associateId) {
        return false;
    }
}
