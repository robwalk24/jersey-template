package io.rowtech.api.bootstrap;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.TypeLiteral;
import com.google.inject.servlet.RequestScoped;
import io.rowtech.api.Mapping.Mapper;
import io.rowtech.api.Mapping.MapperImpl;
import io.rowtech.api.dao.*;
import io.rowtech.api.domain.Hero;
import io.rowtech.api.service.HeroesService;
import io.rowtech.api.service.HeroesServiceImpl;
import org.modelmapper.Provider;
import org.modelmapper.guice.GuiceIntegration;
import ru.vyarus.guice.validator.ImplicitValidationModule;

public class AppModule extends AbstractModule {
    @Override
    protected void configure() {

        install(new ImplicitValidationModule());

        // providers
        bind(DbConnection.class).to(PostgresDbConnectionImpl.class);
        //bind(DbConnection.class).to(SqlliteDbConnectionImpl.class)

        // service
        bind(HeroesService.class).to(HeroesServiceImpl.class);

        // dao
        bind(new TypeLiteral<CrudDao<Hero>>(){}).to(HeroesDaoImpl.class);

        bind(Mapper.class).to(MapperImpl.class).in(Singleton.class);
    }

    @Provides
    Provider<?> provideModelMapper(){
        return GuiceIntegration.fromGuice(ContainerLocator.getInstance());
    }
}
