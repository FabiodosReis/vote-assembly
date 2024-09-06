package com.backoffice.core.associate.adapter;

import com.backoffice.core.associate.model.Associate;
import com.backoffice.core.associate.vo.AssociateFilterVO;

import java.util.List;
import java.util.Optional;

public interface AssociateDataProvider {

    Optional<Associate> save(Associate associate);

    Optional<Associate> update(Associate associate);

    Optional<Associate> findById(String id);

    List<Associate> findAll(AssociateFilterVO vo);

    boolean existsCpfInAnotherAssociate(String cpf, String associateId);
}
