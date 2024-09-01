package com.backoffice.core.subject.v1.usecase;

import com.backoffice.core.subject.adapter.SubjectDataProvider;
import com.backoffice.core.subject.response.SubjectResponseVO;
import lombok.RequiredArgsConstructor;

import javax.inject.Named;
import java.time.LocalDateTime;
import java.util.List;

import static java.util.Objects.isNull;

@Named
@RequiredArgsConstructor
public class FindSubjectUserCase {

    private final SubjectDataProvider dataProvider;

    private List<SubjectResponseVO> findAllByCreateDate(LocalDateTime createDate) {
        return dataProvider.findAllByCreateDate(createDate);
    }

    private List<SubjectResponseVO> findAll() {
        return dataProvider.findAll();
    }

    public List<SubjectResponseVO> findAllSubjects(LocalDateTime createDate) {
        if (isNull(createDate)) return findAll();
        return findAllByCreateDate(createDate);
    }

}
