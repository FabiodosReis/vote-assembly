package com.backoffice.app.api.facade;

import com.backoffice.core.session.v1.usecase.CloseSessionUseCase;
import com.backoffice.core.session.v1.usecase.CreateSessionUseCase;
import com.backoffice.core.session.v1.usecase.FindAllSessionUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class SessionUseCaseFacade {

    public final CreateSessionUseCase createSessionUseCase;
    public final CloseSessionUseCase closeSessionUseCase;
    public final FindAllSessionUseCase findAllSessionUseCase;

}
