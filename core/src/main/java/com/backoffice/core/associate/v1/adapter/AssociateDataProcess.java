package com.backoffice.core.associate.v1.adapter;

import com.backoffice.core.associate.v1.model.Associate;
import com.backoffice.core.associate.v1.vo.AssociateFilterVO;

import java.util.List;
import java.util.Optional;

public interface AssociateDataProcess {

    Optional<Associate> save(Associate associate);

    Optional<Associate> update(Associate associate);

    Optional<Associate> findById(String id);

    List<Associate> findAll(AssociateFilterVO vo);

    boolean existsCpfInAnotherAssociate(String cpf, String associateId);
}
