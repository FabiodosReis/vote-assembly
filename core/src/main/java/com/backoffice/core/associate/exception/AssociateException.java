package com.backoffice.core.associate.exception;

import com.backoffice.core.exception.VoteAssemblyException;

public class AssociateException extends VoteAssemblyException {
    
    public AssociateException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public AssociateException(String message) {
        super(message);
    }
}
