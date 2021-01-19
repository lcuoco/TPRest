package fr.polytech.TPRest.Servlet;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;

@Path("/hello")
public class HelloServlet extends HttpServlet {

    @GET
    @Path("sayHello")
    public String deletePerson() {
        return "hello";
    }


    @POST
    @Path("post")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addPerson(Person person) {
        return Response.ok().entity(person).cookie(new NewCookie("person", person.toString())).build();
    }

    @GET
    @Path("get")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPerson() {
        return Response.ok().entity(new Person("Lucas", "Cuoco")).build();
    }

    @PUT
    @Path("put")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response putPerson(Person person) {
        return Response.ok().entity(person).cookie(new NewCookie("person", person.toString())).build();
    }

    @DELETE
    @Path("delete")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletePerson(Person person) {
        return Response.ok().entity(person).build();
    }
}