package com.example.aulajsf.messages;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class UtilMessages {
    public static void warningMessage(String detail) {
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso", detail);
        context.addMessage("messages", msg);
    }
}
