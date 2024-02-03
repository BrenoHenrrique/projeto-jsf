package com.example.projectjsf.exceptions;

import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerFactory;

public class HandleExceptionsFactory extends ExceptionHandlerFactory {
    private final ExceptionHandlerFactory parent;

    public HandleExceptionsFactory(ExceptionHandlerFactory parent) {
        this.parent = parent;
    }

    @Override
    public ExceptionHandler getExceptionHandler() {
        return new HandleExceptions(parent.getExceptionHandler());
    }
}
