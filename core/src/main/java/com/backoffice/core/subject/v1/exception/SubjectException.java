package com.backoffice.core.subject.v1.exception;

import com.backoffice.core.exception.VoteAssemblyException;

public class SubjectException extends VoteAssemblyException {
    
    public SubjectException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public SubjectException(String message) {
        super(message);
    }
}
