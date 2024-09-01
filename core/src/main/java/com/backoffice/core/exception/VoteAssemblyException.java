package com.backoffice.core.exception;

public abstract class VoteAssemblyException extends RuntimeException {

    public VoteAssemblyException(String message){
        super(message);
    }

    public VoteAssemblyException(String message, Throwable throwable){
        super(message, throwable);
    }
}
