package io.rowtech.api.configuration;

import io.rowtech.api.exception.mapper.ThrowableExceptionMapper;
import io.rowtech.api.filter.CorsFilter;
import io.rowtech.api.bootstrap.ContainerLocator;
import io.rowtech.api.resource.HeroesResource;
import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.logging.LoggingFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import org.glassfish.jersey.server.filter.CsrfProtectionFilter;
import org.jvnet.hk2.guice.bridge.api.GuiceBridge;
import org.jvnet.hk2.guice.bridge.api.GuiceIntoHK2Bridge;

import javax.inject.Inject;
import javax.ws.rs.ApplicationPath;

@ApplicationPath("/api")
public class RestConfig extends ResourceConfig {
    @Inject
    public RestConfig(ServiceLocator serviceLocator){
        createGuiceBridge(serviceLocator);

        property(ServerProperties.WADL_FEATURE_DISABLE, true);

/*        property(ServerProperties.MONITORING_ENABLED, Boolean.TRUE);
        property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, Boolean.TRUE);
        property(LoggingFeature.LOGGING_FEATURE_LOGGER_LEVEL_SERVER, "INFO");
        property(LoggingFeature.LOGGING_FEATURE_VERBOSITY_SERVER, LoggingFeature.Verbosity.PAYLOAD_ANY);*/

        register(JsonProvider.class);
        register(JacksonFeature.class);
        register(CsrfProtectionFilter.class);
        register(CorsFilter.class);
        register(LoggingFeature.class);

        packages(HeroesResource.class.getPackage().getName());
        packages(ThrowableExceptionMapper.class.getPackage().getName());
    }

    private void createGuiceBridge(ServiceLocator serviceLocator){
        GuiceBridge.getGuiceBridge().initializeGuiceBridge(serviceLocator);
        GuiceIntoHK2Bridge g2h = serviceLocator.getService(GuiceIntoHK2Bridge.class);
        g2h.bridgeGuiceInjector(ContainerLocator.getInstance());
    }
}
