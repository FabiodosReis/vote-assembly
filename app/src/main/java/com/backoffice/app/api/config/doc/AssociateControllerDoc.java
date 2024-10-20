package com.backoffice.app.api.config.doc;

import com.backoffice.app.api.vo.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Associates", description = "Management the Associates")
public interface AssociateControllerDoc {

    @Operation(
            summary = "Find associate by ID",
            description = "Find specific associate saved"
    )
    AssociateResponseVO findById(@Parameter(description = "Associate Id", example = "0192aa5a-03ab-7eae-b75f-f3751cc7afa9", required = true) String id);

    @Operation(
            summary = "Find all associates",
            description = "Return all associates saved"
    )
    CustomPage<?> findAll(
            @Parameter(description = "Size of content, default 10", example = "10", required = false) Integer size,
            @Parameter(description = "Page of resource, default 0", example = "0", required = false) Integer page,
            @Parameter(description = "Associate cpf", example = "845.871.517-18", required = false) String cpf);

    @Operation(
            summary = "Save associate",
            description = "Save a associate, it necessary formatted cpf and name"
    )
    void save(@RequestBody(description = "Associate representation", required = true) AssociateCreateRequestVO vo);

    @Operation(
            summary = "Update associate",
            description = "Update name or cpf associate"
    )
    void update(@RequestBody(description = "Represent associate attributes for update", required = false) AssociateUpdateRequestVO vo, String id);
}
