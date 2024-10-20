package com.backoffice.app.api.config.doc;

import com.backoffice.app.api.vo.VoteRequestVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Votes", description = "Management the votes")
public interface VoteControllerDoc {

    @Operation(
            summary = "Create vote",
            description = "This endpoint receive the associate ID, the subject ID and status vote in order to create a vote"
    )
    void create(@RequestBody(description = "Vote representation", required = true) VoteRequestVO voteRequestVO);
}
