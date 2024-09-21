package com.backoffice.app.application.api.v1.controller;

import com.backoffice.app.application.api.v1.facade.VoteUseCaseFacade;
import com.backoffice.app.application.api.v1.vo.VoteRequestVO;
import com.backoffice.core.vote.v1.model.Vote;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/votes")
@RequiredArgsConstructor
public class VoteController {

    private final VoteUseCaseFacade facade;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody VoteRequestVO vo) {
        var vote = new Vote(
                null,
                vo.getAssociateId(),
                vo.getSubjectId(),
                vo.getStatus()
        );

        facade.createVoteUseCase.execute(vote);
    }
}
