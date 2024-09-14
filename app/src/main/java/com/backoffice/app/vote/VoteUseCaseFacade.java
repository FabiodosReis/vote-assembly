package com.backoffice.app.vote;

import com.backoffice.core.vote.v1.usecase.CreateVoteUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class VoteUseCaseFacade {

    final CreateVoteUseCase createVoteUseCase;
}
