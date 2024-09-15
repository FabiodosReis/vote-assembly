package com.backoffice.app.application.api.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Builder
public class FileResponseVO {

    private byte[] content;
    private String fileName;
    private String contentType;
}
