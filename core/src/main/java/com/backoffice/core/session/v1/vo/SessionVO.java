package com.backoffice.core.session.v1.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
@Builder
public class SessionVO {

    private String id;
    private String description;
    private String status;
    private List<VoteVO> votes;
}
