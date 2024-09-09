package com.backoffice.app.subject;

import com.backoffice.core.subject.v1.usecase.CreateSubjectUseCase;
import com.backoffice.core.subject.v1.usecase.DisableSubjectUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SubjectMediator {

    final CreateSubjectUseCase createSubjectUseCase;
    final DisableSubjectUseCase disableSubjectUseCase;
  }
