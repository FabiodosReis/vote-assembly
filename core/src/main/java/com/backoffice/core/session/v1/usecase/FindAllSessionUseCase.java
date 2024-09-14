package com.backoffice.core.session.v1.usecase;

import com.backoffice.core.session.adapter.SessionDataProvider;
import com.backoffice.core.session.vo.SessionFilterVO;
import com.backoffice.core.session.vo.SessionVO;
import lombok.RequiredArgsConstructor;

import javax.inject.Named;
import java.util.List;

@Named
@RequiredArgsConstructor
public class FindAllSessionUseCase {

    private final SessionDataProvider dataProvider;

    public List<SessionVO> execute(SessionFilterVO vo) {
        return dataProvider.findAll(vo);
    }

    public List<String[]> findAllCsvFormat(SessionFilterVO vo){
        return dataProvider.findAllCsv(vo);
    }
}
