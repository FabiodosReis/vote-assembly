package com.backoffice.app.deprecated;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Deprecated
@Getter @Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class TestVO {

    private OffsetDateTime date;
}
