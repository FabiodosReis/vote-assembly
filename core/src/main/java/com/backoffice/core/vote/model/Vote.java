package com.backoffice.core.vote.model;

import com.backoffice.core.associate.model.Associate;
import com.backoffice.core.vote.enums.VoteStatusEnum;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.ZoneId;


@Setter @Getter
public class Vote {

    public Vote(Associate associate, VoteStatusEnum status) {
        this.associate = associate;
        this.status = status;
        this.createDate = LocalDateTime.now(ZoneId.of("UTC"));
    }

    private String id;
    private VoteStatusEnum status;
    private LocalDateTime createDate;
    private Associate associate;

}
