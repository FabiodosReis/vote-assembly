package com.backoffice.core.session.model;

import com.backoffice.core.subject.model.Subject;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Getter @Setter
public class Session {

    public Session(List<Subject> subjectList) {
        this.startDate = LocalDateTime.now(ZoneId.of("UTC"));;
        this.subjectList = subjectList;
    }

    private String id;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private List<Subject> subjectList;

}
