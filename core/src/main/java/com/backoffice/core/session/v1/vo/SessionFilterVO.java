package com.backoffice.core.session.v1.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Builder
public class SessionFilterVO {

    private Integer page;
    private Integer size;
}
