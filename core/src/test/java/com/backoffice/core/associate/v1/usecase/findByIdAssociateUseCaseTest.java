package com.backoffice.core.associate.v1.usecase;

import com.backoffice.core.associate.v1.enums.StatusAssociateEnum;
import com.backoffice.core.associate.v1.usecase.dataProvider.FindDataProcessTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class findByIdAssociateUseCaseTest {

    private FindByIdAssociateUseCase useCase;


    @BeforeEach
    public void setup() {
        var dataProvider = new FindDataProcessTest();
        useCase = new FindByIdAssociateUseCase(dataProvider);
    }


    @Test
    void shouldFindById() {

        var associate = useCase.execute("9941c81c-4f47-447e-b19e-11a75c74c9a7")
                .get();

        assertNotNull(associate);
        assertEquals("9941c81c-4f47-447e-b19e-11a75c74c9a7", associate.getId());
        assertEquals("test", associate.getName());
        assertEquals("055.613.735-36", associate.getCpf());
        assertEquals(StatusAssociateEnum.ABLE_TO_VOTE, associate.getStatus());

    }

}
