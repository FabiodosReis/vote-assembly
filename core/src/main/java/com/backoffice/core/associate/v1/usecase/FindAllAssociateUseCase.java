package com.backoffice.core.associate.v1.usecase;

import com.backoffice.core.associate.v1.adapter.AssociateDataProcess;
import com.backoffice.core.associate.v1.model.Associate;
import com.backoffice.core.associate.v1.vo.AssociateFilterVO;
import lombok.RequiredArgsConstructor;

import javax.inject.Named;
import java.util.List;

@Named
@RequiredArgsConstructor
public class FindAllAssociateUseCase {

    private final AssociateDataProcess dataProvider;

    public List<Associate> execute(AssociateFilterVO vo){
        return dataProvider.findAll(vo);
    }

}
