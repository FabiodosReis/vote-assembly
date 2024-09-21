package com.backoffice.app.application.exception;

import com.backoffice.core.exception.VoteAssemblyException;

public class SnsException extends VoteAssemblyException {

    public SnsException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
