package com.backoffice.app.subject;

import com.backoffice.core.subject.adapter.SubjectDataProvider;
import com.backoffice.core.subject.model.Subject;
import com.backoffice.core.subject.response.SubjectResponseVO;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class SubjectRepository implements SubjectDataProvider {

    @Override
    public void save(Subject subject) {

    }

    @Override
    public List<SubjectResponseVO> findAllByCreateDate(LocalDateTime localDate) {
        return List.of();
    }

    @Override
    public List<SubjectResponseVO> findAll() {
        return List.of();
    }
}
