package com.backoffice.app.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Builder
public class ObjectResponseVO {

    private byte[] content;
    private String fileName;
    private String contentType;
}
