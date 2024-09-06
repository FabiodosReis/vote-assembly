package com.backoffice.core.associate.v1.usecase.dataProvider;

import com.backoffice.core.associate.adapter.AssociateDataProvider;
import com.backoffice.core.associate.model.Associate;
import com.backoffice.core.associate.vo.AssociateFilterVO;

import java.util.List;
import java.util.Optional;

public class CpfAlreadyExistsAssociateDataProviderTest implements AssociateDataProvider {

    @Override
    public Optional<Associate> save(Associate associate) {
        return Optional.of(associate);
    }

    @Override
    public Optional<Associate> update(Associate associate) {
        return Optional.of(associate);
    }

    @Override
    public Optional<Associate> findById(String id) {
        return Optional.empty();
    }

    @Override
    public List<Associate> findAll(AssociateFilterVO vo) {
        return List.of();
    }

    @Override
    public boolean existsCpfInAnotherAssociate(String cpf, String associateId) {
        return true;
    }
}
