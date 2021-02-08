package fr.polytech.TPRest.Servlet;

import fr.polytech.TPRest.Servlet.DBManager.PokemonManager;
import fr.polytech.TPRest.Servlet.Metier.Pokemon;

import java.util.List;
import javax.servlet.http.*;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/api")
public class BaseServlet extends HttpServlet {


    @GET
    @Path("sayHello")
    public String deletePerson() {
        return "hello";
    }


    @GET
    @Path("/pokemon")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Pokemon> getPokemon()
    {
        PokemonManager pm = new PokemonManager();
        return pm.getAll();
    }

    @GET
    @Path("/pokemon/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Pokemon getPokemonById(@PathParam("id") int id)
    {
        PokemonManager pm = new PokemonManager();
        return pm.getById(id);
    }

    @POST
    @Path("/pokemon")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createPokemon(Pokemon pokemon)
    {
        PokemonManager pm = new PokemonManager();
        return Response.status(Response.Status.CREATED).entity(pm.create(pokemon)).build();
    }

    @PUT
    @Path("/pokemon")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Pokemon updatePokemon(Pokemon pokemon)
    {
        PokemonManager pm = new PokemonManager();
        return pm.update(pokemon);
    }

    @DELETE
    @Path("/pokemon")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deletePokemon(Pokemon pokemon)
    {
        PokemonManager pm = new PokemonManager();
        return pm.delete(pokemon);
    }
}