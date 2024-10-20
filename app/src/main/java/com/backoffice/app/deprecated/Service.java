package com.backoffice.app.deprecated;

import com.backoffice.core.vote.v1.model.Vote;

@Deprecated
public interface Service {

    void process(Vote vote);
}
