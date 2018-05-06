package io.rowtech.api.bootstrap;

import com.google.inject.Guice;
import com.google.inject.Injector;

public final class ContainerLocator {
    private static Injector injector;

    private ContainerLocator(){

    }

    public static Injector getInstance(){
        if (injector == null){
            injector = Guice.createInjector(new AppModule());
        }
        return injector;
    }
}
