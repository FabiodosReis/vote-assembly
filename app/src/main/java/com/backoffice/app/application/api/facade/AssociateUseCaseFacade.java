package com.backoffice.app.application.api.facade;

import com.backoffice.core.associate.v1.usecase.CreateAssociateUseCase;
import com.backoffice.core.associate.v1.usecase.FindAllAssociateUseCase;
import com.backoffice.core.associate.v1.usecase.FindByIdAssociateUseCase;
import com.backoffice.core.associate.v1.usecase.UpdateAssociateUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AssociateUseCaseFacade {

    public final CreateAssociateUseCase createAssociateUseCase;
    public final UpdateAssociateUseCase updateAssociateUseCase;
    public final FindByIdAssociateUseCase findByIdAssociateUseCase;
    public final FindAllAssociateUseCase findAllAssociateUseCase;
}
