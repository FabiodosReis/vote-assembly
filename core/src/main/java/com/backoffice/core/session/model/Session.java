package com.backoffice.core.session.model;

import com.backoffice.core.subject.model.Subject;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Getter @Setter
public class Session {

    public Session(LocalDateTime endDate, List<Subject> subjectList) {
        var now =  LocalDateTime.now(ZoneId.of("UTC"));
        if (endDate == null) endDate = now.plusMinutes(1);
        this.startDate = now;
        this.subjectList = subjectList;
        this.endDate = endDate;
    }

    private String id;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private List<Subject> subjectList;

}
