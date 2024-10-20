package com.backoffice.app.deprecated;

import com.backoffice.core.vote.v1.model.Vote;
import org.springframework.stereotype.Component;

@Deprecated
@Component
public class Process2 implements Service{
    @Override
    public void process(Vote vote) {
        System.out.println("Invoke Process2");
    }
}
