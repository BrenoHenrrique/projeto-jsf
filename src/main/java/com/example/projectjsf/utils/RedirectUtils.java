package com.example.projectjsf.utils;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import java.io.IOException;

public class RedirectUtils {

    public static void redirectTo(String path) {
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        String urlRedirecionamento = externalContext.getRequestContextPath() + path;
        try {
            externalContext.redirect(urlRedirecionamento);
        } catch (IOException ignored) {
        }
    }
}
