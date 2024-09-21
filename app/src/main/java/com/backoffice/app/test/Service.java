package com.backoffice.app.test;

import com.backoffice.core.vote.v1.model.Vote;

public interface Service {

    void process(Vote vote);
}
