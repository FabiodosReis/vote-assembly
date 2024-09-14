package com.backoffice.core.exception;

public class FileGenerateException extends VoteAssemblyException {

    public FileGenerateException(String message) {
        super(message);
    }

    public FileGenerateException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
