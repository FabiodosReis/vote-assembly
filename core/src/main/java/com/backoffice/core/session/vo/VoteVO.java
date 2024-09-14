package com.backoffice.core.session.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Builder
public class VoteVO {

    private String id;
    private String voteStatus;
    private String cpf;
    private String subjectId;
    private String subjectDescription;
    private String subjectStatus;
}
