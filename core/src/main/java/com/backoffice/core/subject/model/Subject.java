package com.backoffice.core.subject.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Getter
@Setter
public class Subject {

    public Subject(String id, String description, String sessionId, LocalDateTime endDate) {
        var now = LocalDateTime.now(ZoneId.of("UTC"));
        this.id = id;
        this.description = description;
        this.startDate = now;
        if(endDate == null) this.endDate = now.plusMinutes(1);
        this.sessionId = sessionId;
    }

    private String id;
    private String description;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String sessionId;

}
