package com.backoffice.core.subject.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.UUID;

@Getter @Setter
public class Subject {

    public Subject(String description) {
        this.id = UUID.randomUUID().toString();
        this.description = description;
        this.createDate = LocalDateTime.now(ZoneId.of("UTC"));
    }

    private String id;
    private String description;
    private LocalDateTime createDate;

 }
