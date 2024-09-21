package com.backoffice.core.associate.v1.usecase;


import com.backoffice.core.associate.v1.adapter.AssociateDataProcess;
import com.backoffice.core.associate.v1.enums.StatusAssociateEnum;
import com.backoffice.core.associate.v1.exception.AssociateException;
import com.backoffice.core.associate.v1.model.Associate;
import com.backoffice.core.associate.v1.usecase.dataProvider.CpfAlreadyExistsAssociateDataProcessTest;
import com.backoffice.core.associate.v1.usecase.dataProvider.NotFoundAssociateDataProcessTest;
import com.backoffice.core.associate.v1.usecase.dataProvider.UpdateAssociateDataProcessTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UpdateAssociateUseCaseTest {

    private UpdateAssociateUseCase updateAssociateUseCase;

    @Test
    public void shouldExecuteAssociate() {
        setupMock(new UpdateAssociateDataProcessTest());

        var associate = new Associate(
                "d324767c-0ec7-43d1-ac7f-9ffc6ba05b80",
                "Caroline Rocha",
                "055.613.735-36"
        );

        var associateUpdated = updateAssociateUseCase.execute(associate).get();

        assertEquals("d324767c-0ec7-43d1-ac7f-9ffc6ba05b80", associateUpdated.getId());
        assertEquals("Caroline Rocha", associateUpdated.getName());
        assertEquals("055.613.735-36", associateUpdated.getCpf());
        assertEquals(StatusAssociateEnum.ABLE_TO_VOTE, associateUpdated.getStatus());
    }

    @Test
    public void shouldNotExecuteAssociateBecauseIdIsNull() {
        setupMock(new UpdateAssociateDataProcessTest());

        var associate = new Associate(
                null,
                "Caroline Rocha",
                "055.613.735-36"
        );


        var exception = assertThrows(AssociateException.class, () -> {
            updateAssociateUseCase.execute(associate);
        });

        assertEquals("Associate id is required", exception.getMessage());
    }

    @Test
    public void shouldNotExecuteAssociateBecauseCpfAlreadyExistsInAnotherAssociate() {
        setupMock(new CpfAlreadyExistsAssociateDataProcessTest());

        var associate = new Associate(
                "d324767c-0ec7-43d1-ac7f-9ffc6ba05b80",
                "Caroline Rocha",
                "055.613.735-36"
        );


        var exception = assertThrows(AssociateException.class, () -> {
            updateAssociateUseCase.execute(associate);
        });

        assertEquals("Cpf 055.613.735-36 already exist", exception.getMessage());
    }

    @Test
    public void shouldNotExecuteAssociateBecauseAssociateNotFoundInDatabase() {
        setupMock(new NotFoundAssociateDataProcessTest());

        var associate = new Associate(
                "d324767c-0ec7-43d1-ac7f-9ffc6ba05b80",
                "Caroline Rocha",
                "055.613.735-36"
        );

        var exception = assertThrows(AssociateException.class, () -> {
            updateAssociateUseCase.execute(associate);
        });

        assertEquals("Associate d324767c-0ec7-43d1-ac7f-9ffc6ba05b80 not found", exception.getMessage());
    }

    private void setupMock(AssociateDataProcess dataProvider) {
        updateAssociateUseCase = new UpdateAssociateUseCase(dataProvider);
    }

}
