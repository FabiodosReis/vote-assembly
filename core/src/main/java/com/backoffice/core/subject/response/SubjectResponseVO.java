package com.backoffice.core.subject.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class SubjectResponseVO {

    private String id;
    private String description;
    private LocalDateTime createDate;
    private Integer VoteQuantity;

}
