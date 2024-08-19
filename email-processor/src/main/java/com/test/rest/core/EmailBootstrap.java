package com.test.rest.core;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import com.test.common.health.HealthMonitor;
import java.util.logging.Logger;

public class EmailBootstrap implements ServletContextListener {

    Logger logger = Logger.getLogger(EmailBootstrap.class.getName());
    private HealthMonitor healthMonitor = null;

    @Override
    public void contextInitialized(final ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        init();
        logger.info("----healthMonitor 2 " + healthMonitor);
        context.setAttribute(HealthMonitor.BEAN_ID, healthMonitor);
    }

    @Override
    public void contextDestroyed(final ServletContextEvent sce) {
        sce.getServletContext().removeAttribute(HealthMonitor.BEAN_ID);
    }

    public final void init() {
        healthMonitor = new HealthMonitor("EmailProcessor");
        logger.info("----healthMonitor 1 " + healthMonitor);
    }
}