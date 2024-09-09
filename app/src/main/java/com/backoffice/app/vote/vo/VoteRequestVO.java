package com.backoffice.app.vote.vo;

import com.backoffice.core.vote.enums.VoteStatusEnum;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class VoteRequestVO {

    private String associateId;
    private String subjectId;
    private VoteStatusEnum status;
}
