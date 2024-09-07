package com.backoffice.core.session.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Getter @Setter
public class Session {

    public Session(String id, String description) {
        this.id = id;
        this.description = description;
        this.startDate = LocalDateTime.now(ZoneId.of("UTC"));;
    }

    private String id;
    private String description;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

}
