package com.backoffice.app.application.api.v1.vo;

import com.backoffice.core.subject.v1.model.Subject;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;


import java.time.LocalDateTime;



@Setter @Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class SubjectRequestVO {

    private String description;
    private String sessionId;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endDate;

    public Subject toEntity() {
        return new Subject(
                null,
                description,
                sessionId,
                endDate
        );
    }
}
