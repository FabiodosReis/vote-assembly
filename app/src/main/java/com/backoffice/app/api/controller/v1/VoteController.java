package com.backoffice.app.api.controller.v1;

import com.backoffice.app.api.config.doc.VoteControllerDoc;
import com.backoffice.app.api.facade.VoteUseCaseFacade;
import com.backoffice.app.api.vo.VoteRequestVO;
import com.backoffice.core.vote.v1.model.Vote;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/votes")
@RequiredArgsConstructor
public class VoteController implements VoteControllerDoc {

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
