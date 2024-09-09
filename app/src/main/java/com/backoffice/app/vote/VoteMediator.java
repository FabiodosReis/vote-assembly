package com.backoffice.app.vote;

import com.backoffice.core.vote.v1.usecase.CreateVoteUserCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class VoteMediator {

    final CreateVoteUserCase createVoteUserCase;
}
