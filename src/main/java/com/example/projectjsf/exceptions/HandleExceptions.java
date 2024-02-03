package com.example.projectjsf.exceptions;

import com.example.projectjsf.messages.UtilMessages;

import javax.faces.FacesException;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerWrapper;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ExceptionQueuedEvent;
import javax.faces.event.ExceptionQueuedEventContext;
import javax.persistence.NoResultException;
import java.io.IOException;
import java.util.Iterator;

public class HandleExceptions extends ExceptionHandlerWrapper {
    private final ExceptionHandler wrapped;

    public HandleExceptions(ExceptionHandler wrapped) {
        this.wrapped = wrapped;
    }

    @Override
    public ExceptionHandler getWrapped() {
        return this.wrapped;
    }

    @Override
    public void handle() throws FacesException {
        Iterator<ExceptionQueuedEvent> events = getUnhandledExceptionQueuedEvents().iterator();
        while (events.hasNext()) {
            ExceptionQueuedEvent event = events.next();
            ExceptionQueuedEventContext context = (ExceptionQueuedEventContext) event.getSource();
            Throwable exception = context.getException();

            String path = getPath();
            NoResultException entityNotFoundException = getEntityNotFoundException(exception);
            if (entityNotFoundException != null) {
                try {
                    if (path.contains("Login")) {
                        UtilMessages.warnningMessage("Usuário ou senha inválidos.");
                        break;
                    }
                    UtilMessages.warnningMessage("Entidade não encontrada.");
                    break;
                } finally {
                    events.remove();
                }
            }

        }
        getWrapped().handle();
    }

    private NoResultException getEntityNotFoundException(Throwable exception) {
        if (exception instanceof NoResultException) {
            return (NoResultException) exception;
        } else if (exception.getCause() != null) {
            return getEntityNotFoundException(exception.getCause());
        }

        return null;
    }

    @SuppressWarnings("UnusedDeclaration")
    private void redirect() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        String contextPath = externalContext.getRequestHeaderMap().get("referer");
        try {
            externalContext.redirect(contextPath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        facesContext.responseComplete();
    }

    private String getPath() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        return externalContext.getRequestHeaderMap().get("referer");
    }
}
