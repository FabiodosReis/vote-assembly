package com.backoffice.app.api.config.doc;

import com.backoffice.app.api.vo.CustomPage;
import com.backoffice.app.api.vo.RequestSessionVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

@Tag(name = "Sessions", description = "Management the Sessions")
public interface SessionControllerDoc {


    @Operation(
            summary = "Create Session",
            description = "This endpoint create the session, it necessary pass the description"
    )
    void createSession(@RequestBody(description = "Session representation", required = true) RequestSessionVO vo);

    @Operation(
            summary = "Close Session",
            description = "This endpoint close a specific Session you need pass session ID"
    )
    void closeSession(@Parameter(description = "Session ID", example = "0192aa5a-03ab-7eae-b75f-f3751cc7afa9", required = true) String id);

    @Operation(
            summary = "Find all Session",
            description = "This endpoint find all sessions saved"
    )
    CustomPage<?> findAll(
            @Parameter(description = "Size of content, default 10", example = "10", required = false) Integer size,
            @Parameter(description = "Page of resource, default 0", example = "0", required = false) Integer page);

    @Operation(
            summary = "Find all Session and download csv file",
            description = "This endpoint find all sessions and make csv file, " +
                    "it necessary using http header accept text/csv and content type txt/csv"
    )
    ResponseEntity<?> findAllCSV(
            @Parameter(description = "Size of content, default 10", example = "10", required = false) Integer size,
            @Parameter(description = "Page of resource, default 0", example = "0", required = false) Integer page);
}
