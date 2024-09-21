package com.backoffice.app.test;

import com.backoffice.core.vote.v1.model.Vote;
import org.springframework.stereotype.Component;

@Component
public class Process1 implements Service{
    @Override
    public void process(Vote vote) {
        System.out.println("Invoke Process1");
    }
}
