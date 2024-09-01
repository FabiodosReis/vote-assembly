package com.backoffice.core.session.exception;

import com.backoffice.core.exception.VoteAssemblyException;

public class SessionException extends VoteAssemblyException {
    
    public SessionException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public SessionException(String message) {
        super(message);
    }
}
