package com.backoffice.app.adapter.vote;

import com.backoffice.app.application.client.AwsSnsClient;
import com.backoffice.core.vote.adapter.VoteDataProvider;
import com.backoffice.core.vote.model.Vote;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Slf4j
@Component @Primary
@RequiredArgsConstructor
public class VoteProcessDecorator implements VoteDataProvider {

    private final VoteRepository voteRepository;
    private final AwsSnsClient snsClient;

    @Override
    public Optional<Vote> save(Vote vote) {
        var currentVote = voteRepository.save(vote);
        snsClient.publish(vote, "Create", vote.getClass().getSimpleName());
        return currentVote;
    }

    @Override
    public boolean isAssociateAlreadyVoted(String subjectId, String associateId) {
        return voteRepository.isAssociateAlreadyVoted(subjectId, associateId);
    }

    @Override
    public Optional<Vote> findById(String id) {
        return voteRepository.findById(id);
    }
}
