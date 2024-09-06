package com.backoffice.core.associate.v1.usecase;

import com.backoffice.core.associate.adapter.AssociateDataProvider;
import com.backoffice.core.associate.model.Associate;
import lombok.RequiredArgsConstructor;

import javax.inject.Named;
import java.util.Optional;

@Named
@RequiredArgsConstructor
public class FindByIdAssociateUseCase {

    private final AssociateDataProvider dataProvider;

    public Optional<Associate> findById(String id){
        return dataProvider.findById(id);
    }
}
