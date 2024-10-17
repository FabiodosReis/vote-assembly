package com.backoffice.app.test;

import com.backoffice.core.vote.v1.model.Vote;
import org.springframework.stereotype.Component;

@Component
public class Process2 implements Service{
    @Override
    public void process(Vote vote) {
        System.out.println("Invoke Process2");
    }
}
