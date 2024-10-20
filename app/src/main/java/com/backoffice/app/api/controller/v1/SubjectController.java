package com.backoffice.app.api.controller.v1;

import com.backoffice.app.api.config.doc.SubjectControllerDoc;
import com.backoffice.app.api.facade.SubjectUseCaseFacade;
import com.backoffice.app.api.vo.SubjectRequestVO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/subjects")
@RequiredArgsConstructor
public class SubjectController implements SubjectControllerDoc {

    private final SubjectUseCaseFacade facade;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody SubjectRequestVO vo) {
        facade.createSubjectUseCase.execute(vo.toEntity());
    }

  }
