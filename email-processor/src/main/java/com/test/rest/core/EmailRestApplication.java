package com.test.rest.core;

import javax.servlet.ServletContext;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Context;
import java.util.HashSet;
import java.util.Set;

import com.test.services.EmailResource;
import com.test.common.health.HealthResource;
import com.test.common.health.HealthMonitor;

public class EmailRestApplication extends Application {
    private HealthMonitor healthMonitor = null;

    public EmailRestApplication(@Context final ServletContext context) {
        healthMonitor = (HealthMonitor) context.getAttribute(HealthMonitor.BEAN_ID);
    }

    @Override
    public Set<Object> getSingletons() {
        Set<Object> singletons = new HashSet<>();
        singletons.add(new EmailResource());
        singletons.add(new HealthResource(healthMonitor));
        return singletons;
    }

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> classes = new HashSet<>();
        return classes;
    }
}
