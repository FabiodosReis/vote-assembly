package com.backoffice.core.subject.response;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public class SubjectResponseVO {

    private String id;
    private String description;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Integer VoteQuantity;

}
