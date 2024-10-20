package com.backoffice.app.api.vo;

import com.backoffice.core.vote.v1.enums.VoteStatusEnum;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class VoteRequestVO {

    @Schema(example = "0192aa70-6e62-72cb-af1b-3035f88c3a89")
    private String associateId;

    @Schema(example = "0192aa70-ab29-7f29-b3c7-59cca8ccd251")
    private String subjectId;

    @Schema(example = "YES or NO")
    private VoteStatusEnum status;
}
