package com.backoffice.core.associate.v1.usecase.dataProvider;

import com.backoffice.core.associate.adapter.AssociateDataProvider;
import com.backoffice.core.associate.enums.StatusAssociateEnum;
import com.backoffice.core.associate.model.Associate;
import com.backoffice.core.associate.vo.AssociateFilterVO;

import java.util.List;
import java.util.Optional;

public class FindDataProviderTest implements AssociateDataProvider {

    @Override
    public Optional<Associate> save(Associate associate) {
        return Optional.empty();
    }

    @Override
    public Optional<Associate> update(Associate associate) {
        return Optional.empty();
    }

    @Override
    public Optional<Associate> findById(String id) {
        var associate = new Associate(
                "9941c81c-4f47-447e-b19e-11a75c74c9a7",
                "test",
                "055.613.735-36"
        );

        associate.setStatus(StatusAssociateEnum.ABLE_TO_VOTE);
        return Optional.of(associate);
    }

    @Override
    public List<Associate> findAll(AssociateFilterVO vo) {
        return List.of(
                new Associate(
                        "9941c81c-4f47-447e-b19e-11a75c74c9a7",
                        "test",
                        "055.613.735-36"
                )
        );

    }

    @Override
    public boolean existsCpfInAnotherAssociate(String cpf, String associateId) {
        return false;
    }
}
