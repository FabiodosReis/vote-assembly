package com.backoffice.app.associate;

import com.backoffice.core.associate.adapter.AssociateDataProvider;
import com.backoffice.core.associate.model.Associate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class AssociateRepository implements AssociateDataProvider {

    @Override
    public Optional<Associate> save(Associate associate) {
        return null;
    }

    @Override
    public Optional<Associate> update(Associate associate) {
        return null;
    }

    @Override
    public Optional<Associate> getById(String id) {
        return null;
    }

    @Override
    public boolean existsCpfInAnotherAssociate(String cpf, String associateId) {
        return false;
    }
}
