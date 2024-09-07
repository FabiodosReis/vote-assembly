package com.backoffice.core.associate.v1.usecase;

import com.backoffice.core.associate.enums.StatusAssociateEnum;
import com.backoffice.core.associate.v1.usecase.dataProvider.FindDataProviderTest;
import com.backoffice.core.associate.vo.AssociateFilterVO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FindAllAssociateUseCaseTest {

    private FindAllAssociateUseCase useCase;


    @BeforeEach
    public void setup() {
        var dataProvider = new FindDataProviderTest();
        useCase = new FindAllAssociateUseCase(dataProvider);
    }

    @Test
    void shouldFindById() {

        var associateList = useCase.execute(
                AssociateFilterVO.builder()
                        .cpf("055.613.735-36")
                        .build()
        );


        assertEquals(1, associateList.size());

        assertTrue(associateList.stream().anyMatch(associate -> "055.613.735-36".equals(associate.getCpf())));
        assertTrue(associateList.stream().anyMatch(associate -> "9941c81c-4f47-447e-b19e-11a75c74c9a7".equals(associate.getId())));
        assertTrue(associateList.stream().anyMatch(associate -> "test".equals(associate.getName())));
        assertTrue(associateList.stream().anyMatch(associate -> StatusAssociateEnum.ABLE_TO_VOTE.name().equals(associate.getStatus().name())));

    }

}
