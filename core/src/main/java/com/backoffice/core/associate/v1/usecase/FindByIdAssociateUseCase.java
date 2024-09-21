package com.backoffice.core.associate.v1.usecase;

import com.backoffice.core.associate.v1.adapter.AssociateDataProcess;
import com.backoffice.core.associate.v1.model.Associate;
import lombok.RequiredArgsConstructor;

import javax.inject.Named;
import java.util.Optional;

@Named
@RequiredArgsConstructor
public class FindByIdAssociateUseCase {

    private final AssociateDataProcess dataProvider;

    public Optional<Associate> execute(String id) {
        return dataProvider.findById(id);
    }
}
