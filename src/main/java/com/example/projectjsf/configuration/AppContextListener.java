package com.example.projectjsf.configuration;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class AppContextListener implements ServletContextListener {
    @Override
    public void contextDestroyed(ServletContextEvent event) {
        System.out.println("##################################");
        System.out.println("##################################");
        System.out.println("contextDestroyed");
    }

    @Override
    public void contextInitialized(ServletContextEvent event) {
        System.out.println("##################################");
        System.out.println("##################################");
        System.out.println("TomCat - Inicializado");
        // NÃO É PRA TENTAR CONVERTER PRA ZERO. DOCUMENTAÇÃO DO TOMCAT
        System.setProperty("org.apache.el.parser.COERCE_TO_ZERO", "false");
    }
}
