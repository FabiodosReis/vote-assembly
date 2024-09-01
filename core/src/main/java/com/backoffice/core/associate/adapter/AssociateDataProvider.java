package com.backoffice.core.associate.adapter;

import com.backoffice.core.associate.model.Associate;

import java.util.Optional;

public interface AssociateDataProvider {

    Optional<Associate> save(Associate associate);

    Optional<Associate> update(Associate associate);

    Optional<Associate> getById(String id);

    boolean existsCpfInAnotherAssociate(String cpf, String associateId);
}
