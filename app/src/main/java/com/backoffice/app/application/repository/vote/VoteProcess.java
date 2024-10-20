package com.backoffice.app.application.repository.vote;

import com.backoffice.app.application.client.AwsSnsClient;
import com.backoffice.core.vote.v1.adapter.VoteDataProcess;
import com.backoffice.core.vote.v1.model.Vote;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Slf4j
@Component
@Primary
@RequiredArgsConstructor
public class VoteProcess implements VoteDataProcess {

    private final VoteRepository voteRepository;
    private final AwsSnsClient snsClient;

    @Override
    @Transactional
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
