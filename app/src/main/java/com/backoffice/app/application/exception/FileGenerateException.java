package com.backoffice.app.application.exception;

import com.backoffice.core.exception.VoteAssemblyException;

public class FileGenerateException extends VoteAssemblyException {

    public FileGenerateException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
