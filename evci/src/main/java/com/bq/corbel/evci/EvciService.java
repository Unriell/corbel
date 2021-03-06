package com.bq.corbel.evci;

import io.dropwizard.setup.Environment;

import org.springframework.context.ApplicationContext;

import com.bq.corbel.evci.api.EventResource;
import com.bq.corbel.evci.api.EworkerInfoResource;
import com.bq.corbel.evci.ioc.EvciIoc;
import com.bq.corbel.lib.ws.cli.ServiceRunnerWithVersionResource;
import com.bq.corbel.lib.ws.health.AuthorizationRedisHealthCheck;
import com.bq.corbel.lib.ws.health.BasicHealthCheck;
import com.bq.corbel.lib.ws.health.RabbitMQHealthCheck;

public class EvciService extends ServiceRunnerWithVersionResource<EvciIoc> {

    private final ApplicationContext springContext;

    public EvciService(ApplicationContext springContext) {
        this.springContext = springContext;
    }

    @Override
    protected String getArtifactId() {
        // This has to be the same as in pom.xml
        return "evci";
    }

    @Override
    protected ApplicationContext loadSpringContext() {
        return springContext;
    }

    @Override
    protected void configureService(Environment environment, ApplicationContext context) {
        super.configureService(environment, context);
        environment.jersey().register(context.getBean(EventResource.class));
        environment.jersey().register(context.getBean(EworkerInfoResource.class));
        environment.healthChecks().register("basic", new BasicHealthCheck());
        environment.healthChecks().register("redis", context.getBean(AuthorizationRedisHealthCheck.class));
        environment.healthChecks().register("rabbit", context.getBean(RabbitMQHealthCheck.class));
    }
}
