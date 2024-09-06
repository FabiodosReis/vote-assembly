package com.backoffice.core.subject.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.UUID;

@Getter
@Setter
public class Subject {

    public Subject(String description) {
        var now = LocalDateTime.now(ZoneId.of("UTC"));
        this.id = UUID.randomUUID().toString();
        this.description = description;
        this.startDate = now;
        this.endDate = now.plusMinutes(1);
    }

    public Subject(LocalDateTime endDate, String description) {
        var now = LocalDateTime.now(ZoneId.of("UTC"));
        this.id = UUID.randomUUID().toString();
        this.description = description;
        this.startDate = now;
        this.endDate = endDate == null ? now.plusMinutes(1) : endDate;
    }

    private String id;
    private String description;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

}
