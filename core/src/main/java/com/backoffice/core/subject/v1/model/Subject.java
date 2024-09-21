package com.backoffice.core.subject.v1.model;

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
        this.endDate = endDate;
        this.sessionId = sessionId;
    }

    public Subject(String id, String description, String sessionId) {
        var now = LocalDateTime.now(ZoneId.of("UTC"));
        this.id = id;
        this.description = description;
        this.startDate = now;
        this.endDate = now.plusMinutes(1);
        this.sessionId = sessionId;
    }

    private String id;
    private String description;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String sessionId;

}
