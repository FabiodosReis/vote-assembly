package com.backoffice.core.session.v1.usecase;

import com.backoffice.core.session.v1.adapter.SessionDataProcess;
import com.backoffice.core.session.v1.vo.SessionFilterVO;
import com.backoffice.core.session.v1.vo.SessionVO;
import lombok.RequiredArgsConstructor;

import javax.inject.Named;
import java.util.List;

@Named
@RequiredArgsConstructor
public class FindAllSessionUseCase {

    private final SessionDataProcess dataProvider;

    public List<SessionVO> execute(SessionFilterVO vo) {
        return dataProvider.findAll(vo);
    }

    public List<String[]> findAllCsvFormat(SessionFilterVO vo){
        return dataProvider.findAllCsv(vo);
    }
}
