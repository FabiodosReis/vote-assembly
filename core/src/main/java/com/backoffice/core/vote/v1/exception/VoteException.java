package com.backoffice.core.vote.v1.exception;

import com.backoffice.core.exception.VoteAssemblyException;

public class VoteException extends VoteAssemblyException {
    
    public VoteException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public VoteException(String message) {
        super(message);
    }
}
