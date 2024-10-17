package com.backoffice.app.test;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter @Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class TestVO {

    private OffsetDateTime date;
}
