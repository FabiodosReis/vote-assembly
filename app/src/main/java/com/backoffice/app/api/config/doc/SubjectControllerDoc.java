package com.backoffice.app.api.config.doc;

import com.backoffice.app.api.vo.SubjectRequestVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Subjects", description = "Management the subjects")
public interface SubjectControllerDoc {

    @Operation(
            summary = "Create subject",
            description = "To create a Subject it necessary create a Session before, " +
                "to create a subject it necessary pass the session, a description and optional endDate " +
                "if you don't define endDate, the end date receive current date plus 1 minute"
    )
    void create(@RequestBody(description = "Subject representation", required = true) SubjectRequestVO subjectRequestVO);
}
