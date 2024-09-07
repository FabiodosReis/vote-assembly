package com.backoffice.app.associate;

import com.backoffice.core.associate.v1.usecase.CreateAssociateUseCase;
import com.backoffice.core.associate.v1.usecase.FindAllAssociateUseCase;
import com.backoffice.core.associate.v1.usecase.FindByIdAssociateUseCase;
import com.backoffice.core.associate.v1.usecase.UpdateAssociateUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AssociateUseCaseMediator {

    final CreateAssociateUseCase createAssociateUseCase;
    final UpdateAssociateUseCase updateAssociateUseCase;
    final FindByIdAssociateUseCase findByIdAssociateUseCase;
    final FindAllAssociateUseCase findAllAssociateUseCase;

}
