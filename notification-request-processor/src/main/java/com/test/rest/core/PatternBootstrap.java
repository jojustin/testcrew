package com.test.rest.core;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.test.common.health.HealthMonitor;
import com.test.server.core.EmailProcessor;
import com.test.server.core.CbBroker;

import java.util.logging.Logger;

public class PatternBootstrap implements ServletContextListener {

    Logger logger = Logger.getLogger(PatternBootstrap.class.getName());
    private CbBroker broker = null;
    private HealthMonitor healthMonitor = null;
    private EmailProcessor emailProcessor = null;

    @Override
    public void contextInitialized(final ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        init();
        context.setAttribute(HealthMonitor.BEAN_ID, healthMonitor);
        context.setAttribute(EmailProcessor.BEAN_ID, emailProcessor);
    }

    @Override
    public void contextDestroyed(final ServletContextEvent sce) {
        sce.getServletContext().removeAttribute(HealthMonitor.BEAN_ID);
        sce.getServletContext().removeAttribute(EmailProcessor.BEAN_ID);
    }

    public final void init() {
        healthMonitor = new HealthMonitor("Requetprocessor");
        logger.info("in init");

        broker = new CbBroker();
        if(healthMonitor != null) {
            emailProcessor = new EmailProcessor(broker);
            // SmsProcessor smsProcessor = new SmsProcessor(broker);
            // healthMonitor.register(smsProcessor);
            healthMonitor.register(emailProcessor);
            healthMonitor.start();
        }
    }
}