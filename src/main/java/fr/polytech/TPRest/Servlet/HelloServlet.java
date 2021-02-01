package fr.polytech.TPRest.Servlet;

import com.sun.research.ws.wadl.Response;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/hello")
public class HelloServlet extends HttpServlet {

    @GET
    @Path("sayHello")
    public String sayHello() {
        return "hello";
    }

}
