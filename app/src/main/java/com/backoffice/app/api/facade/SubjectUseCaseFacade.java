package com.backoffice.app.api.facade;

import com.backoffice.core.subject.v1.usecase.CreateSubjectUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SubjectUseCaseFacade {

    public final CreateSubjectUseCase createSubjectUseCase;

  }
