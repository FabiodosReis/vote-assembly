package com.backoffice.app.session;

import com.backoffice.core.session.v1.usecase.CloseSessionUseCase;
import com.backoffice.core.session.v1.usecase.CreateSessionUseCase;
import com.backoffice.core.session.v1.usecase.FindAllSessionUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class SessionUseCaseFacade {

    final CreateSessionUseCase createSessionUseCase;
    final CloseSessionUseCase closeSessionUseCase;
    final FindAllSessionUseCase findAllSessionUseCase;

}
