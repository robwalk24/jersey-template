package io.rowtech.api.resource;

import io.rowtech.api.domain.Hero;
import io.rowtech.api.model.HeroModel;
import io.rowtech.api.service.HeroesService;
import io.rowtech.api.Mapping.Mapper;
import org.modelmapper.TypeToken;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@Path("/heroes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class HeroesResource {
    private final HeroesService heroesService;
    private final Mapper mapper;

    @Context
    private UriInfo uriInfo;

    @Inject
    public HeroesResource(HeroesService heroesService, Mapper mapper){
        this.heroesService = heroesService;
        this.mapper = mapper;
    }
    @GET
    public Response getAll(){
        List<HeroModel> models = mapper.map(heroesService.getAll(), new TypeToken<List<Hero>>() {}.getType());
        return Response.ok(models).build();
    }

    @GET
    @Path("/{id}")
    public Response get(@PathParam("id") Integer id){
        Optional<Hero> hero = heroesService.getOne(id);

        if (hero.isPresent()) {

            HeroModel model = mapper.map(hero.get(), HeroModel.class);
            return Response.ok(model).build();
        }

        return Response.ok("[]").build();
    }

    @POST
    public Response create(HeroModel model){
        Hero hero = new Hero();
        hero.setName(model.getName());
        Hero newHero = heroesService.create(hero);
        return Response.created(buildCreatedURI(newHero.getId())).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Integer id, HeroModel model){
        Hero hero = new Hero();
        hero.setId(id);
        hero.setName(model.getName());
        heroesService.update(hero);
        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Integer id){
        heroesService.delete(id);
        return Response.ok().build();
    }

    private URI buildCreatedURI(int id){
        UriBuilder builder = uriInfo.getAbsolutePathBuilder();
        Class c = this.getClass();
        return builder
                .path(c, "get")
                .build(id);
    }
}
