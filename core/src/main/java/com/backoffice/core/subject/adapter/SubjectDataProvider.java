package com.backoffice.core.subject.adapter;

import com.backoffice.core.subject.model.Subject;
import com.backoffice.core.subject.response.SubjectResponseVO;

import java.time.LocalDateTime;
import java.util.List;

public interface SubjectDataProvider {

    void save(Subject subject);

    List<SubjectResponseVO> findAllByCreateDate(LocalDateTime localDate);

    List<SubjectResponseVO> findAll();
}
