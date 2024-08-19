package com.test.rest.core;

import javax.servlet.ServletContext;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Context;
import java.util.HashSet;
import java.util.Set;

import com.test.services.RequestResource;
import com.test.common.health.HealthResource;
import com.test.common.health.HealthMonitor;
import com.test.server.core.EmailProcessor;

public class PatternRestApplication extends Application {
    private HealthMonitor healthMonitor = null;
    private EmailProcessor emailProcessor = null;

    public PatternRestApplication(@Context final ServletContext context) {
        healthMonitor = (HealthMonitor) context.getAttribute(HealthMonitor.BEAN_ID);
        emailProcessor = (EmailProcessor) context.getAttribute(EmailProcessor.BEAN_ID);
    }

    @Override
    public Set<Object> getSingletons() {
        Set<Object> singletons = new HashSet<>();
        singletons.add(new RequestResource(emailProcessor));
        singletons.add(new HealthResource(healthMonitor));
        return singletons;
    }

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> classes = new HashSet<>();
        return classes;
    }
}
