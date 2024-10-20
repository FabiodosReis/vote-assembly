package com.backoffice.app.api.vo;

import com.backoffice.core.subject.v1.model.Subject;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;


import java.time.LocalDateTime;



@Setter @Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class SubjectRequestVO {

    @Schema(example = "Initial project group")
    private String description;

    @Schema(example = "0192aa70-ab29-7f29-b3c7-59cca8ccd251")
    private String sessionId;

    @Schema(example = "2024-01-01 12:00:00")
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
