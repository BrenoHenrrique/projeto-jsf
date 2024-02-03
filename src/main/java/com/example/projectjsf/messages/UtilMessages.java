package com.example.projectjsf.messages;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class UtilMessages {
    public static void warnningMessage(String detail) {
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso", detail);
        context.addMessage("messages", msg);
    }

    public static void errorMessage(String detail) {
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", detail);
        context.addMessage("messages", msg);
    }
}
