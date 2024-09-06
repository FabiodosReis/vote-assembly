package com.backoffice.core.associate.v1.usecase;

import com.backoffice.core.associate.adapter.AssociateDataProvider;
import com.backoffice.core.associate.model.Associate;
import com.backoffice.core.associate.vo.AssociateFilterVO;
import lombok.RequiredArgsConstructor;

import javax.inject.Named;
import java.util.List;

@Named
@RequiredArgsConstructor
public class FindAllAssociateUseCase {

    private final AssociateDataProvider dataProvider;

    public List<Associate> findAll(AssociateFilterVO vo){
        return dataProvider.findAll(vo);
    }

}
