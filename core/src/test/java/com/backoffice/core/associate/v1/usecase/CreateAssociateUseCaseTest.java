package com.backoffice.core.associate.v1.usecase;


import com.backoffice.core.associate.enums.StatusAssociateEnum;
import com.backoffice.core.associate.exception.AssociateException;
import com.backoffice.core.associate.model.Associate;
import com.backoffice.core.associate.v1.usecase.dataProvider.CreateAssociateDataProviderTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class CreateAssociateUseCaseTest {

    private CreateAssociateUseCase createAssociateUseCase;


    @BeforeEach
    public void setup() {
        var dataProvider = new CreateAssociateDataProviderTest();
        createAssociateUseCase = new CreateAssociateUseCase(dataProvider);
    }


    @Test
    public void shouldCreateAssociateAbleToVote() {
        var associate = new Associate(
                null,
                "Joao da Silva",
                "055.613.735-36"
        );

        var currentAssociate = createAssociateUseCase.execute(associate).get();

        assertNotNull(currentAssociate.getId());
        assertEquals("Joao da Silva", currentAssociate.getName());
        assertEquals("055.613.735-36", currentAssociate.getCpf());
        assertEquals(StatusAssociateEnum.ABLE_TO_VOTE, currentAssociate.getStatus());
    }

    @Test
    public void shouldCreateAssociateUnableToVote() {
        var associate = new Associate(
                null,
                "Joao da Silva",
                "125.613.735-31"
        );

        var currentAssociate = createAssociateUseCase.execute(associate).get();

        assertNotNull(currentAssociate.getId());
        assertEquals("Joao da Silva", currentAssociate.getName());
        assertEquals("125.613.735-31", currentAssociate.getCpf());
        assertEquals(StatusAssociateEnum.UNABLE_TO_VOTE, currentAssociate.getStatus());

    }

    @Test
    public void shouldNotCreateAssociateBecauseNameIsRequired() {
        var associate = new Associate(
                null,
                "",
                "125.613.735-31"
        );

        var exception = assertThrows(AssociateException.class, () -> {
            createAssociateUseCase.execute(associate);
        });

        assertEquals("Associate name is required", exception.getMessage());
    }

    @Test
    public void shouldNotCreateAssociateBecauseCpfIsRequired() {
        var associate = new Associate(
                null,
                "Naiane Oliveira",
                ""
        );

        var exception = assertThrows(AssociateException.class, () -> {
            createAssociateUseCase.execute(associate);
        });

        assertEquals("Associate cpf is required", exception.getMessage());
    }

    @Test
    public void shouldNotCreateAssociateBecauseCpfIsNotFormated() {
        var associate = new Associate(
                null,
                "Naiane Oliveira",
                "05561373536"
        );

        var exception = assertThrows(AssociateException.class, () -> {
            createAssociateUseCase.execute(associate);
        });

        assertEquals("cpf need to formated", exception.getMessage());
    }

}
