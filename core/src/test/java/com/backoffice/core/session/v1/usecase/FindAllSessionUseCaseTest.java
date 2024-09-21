package com.backoffice.core.session.v1.usecase;

import com.backoffice.core.session.v1.usecase.dataprovider.CreateSessionDataProcessTest;
import com.backoffice.core.session.v1.vo.SessionFilterVO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FindAllSessionUseCaseTest {

    private FindAllSessionUseCase useCase;

    @Test
    void shouldFindALl(){
        useCase = new FindAllSessionUseCase(new CreateSessionDataProcessTest());

        var sessionListVO = useCase.execute(SessionFilterVO.builder().build());

        assertFalse(sessionListVO.isEmpty());
        assertTrue(sessionListVO.stream().anyMatch(s -> s.getId().equals("0191f0fa-ffc5-7267-8704-db0702624c5b")));
        assertTrue(sessionListVO.stream().anyMatch(s -> s.getDescription().equals("test")));
        assertTrue(sessionListVO.stream().anyMatch(s -> s.getStatus().equals("OPEN")));
        assertTrue(sessionListVO.stream().anyMatch(s -> s.getVotes().get(0).getCpf().equals("055.613.735.36")));
        assertTrue(sessionListVO.stream().anyMatch(s -> s.getVotes().get(0).getVoteStatus().equals("YES")));
        assertTrue(sessionListVO.stream().anyMatch(s -> s.getVotes().get(0).getId().equals("0191f0fa-ffc5-7267-8704-db0702624c5c")));
        assertTrue(sessionListVO.stream().anyMatch(s -> s.getVotes().get(0).getSubjectId().equals("0191f0fa-ffc5-7267-8704-db0702624c5d")));
        assertTrue(sessionListVO.stream().anyMatch(s -> s.getVotes().get(0).getSubjectDescription().equals("judgment")));
    }
}
