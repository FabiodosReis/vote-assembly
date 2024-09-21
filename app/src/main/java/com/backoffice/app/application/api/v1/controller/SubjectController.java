package com.backoffice.app.application.api.v1.controller;

import com.backoffice.app.application.api.v1.facade.SubjectUseCaseFacade;
import com.backoffice.app.application.api.v1.vo.SubjectRequestVO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/subjects")
@RequiredArgsConstructor
public class SubjectController {

    private final SubjectUseCaseFacade facade;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody SubjectRequestVO vo) {
        facade.createSubjectUseCase.execute(vo.toEntity());
    }

  }
