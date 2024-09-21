package com.backoffice.core.vote.v1.adapter;

import com.backoffice.core.vote.v1.model.Vote;

import java.util.Optional;

public interface VoteDataProcess {

    Optional<Vote> save(Vote vote);

    boolean isAssociateAlreadyVoted(String subjectId, String associateId);

    Optional<Vote> findById(String id);
}
