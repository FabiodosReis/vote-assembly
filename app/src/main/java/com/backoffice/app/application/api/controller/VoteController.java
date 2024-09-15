package com.backoffice.app.application.api.controller;

import com.backoffice.app.application.api.facade.VoteUseCaseFacade;
import com.backoffice.app.application.api.vo.VoteRequestVO;
import com.backoffice.core.vote.model.Vote;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/votes")
@RequiredArgsConstructor
public class VoteController {

    private final VoteUseCaseFacade facade;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(VoteRequestVO vo) {
        var vote = new Vote(
                null,
                vo.getAssociateId(),
                vo.getSubjectId(),
                vo.getStatus()
        );

        facade.createVoteUseCase.execute(vote);
    }
}
