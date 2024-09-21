package com.backoffice.core.vote.v1.model;

import com.backoffice.core.vote.v1.enums.VoteStatusEnum;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.ZoneId;


@Setter @Getter
public class Vote {

    public Vote(String id, String associateId, String subjectId, VoteStatusEnum status) {
        this.associateId = associateId;
        this.status = status;
        this.createDate = LocalDateTime.now(ZoneId.of("UTC"));
        this.subjectId = subjectId;
        this.id = id;
    }

    private String id;
    private VoteStatusEnum status;
    private LocalDateTime createDate;
    private String subjectId;
    private String associateId;

}
