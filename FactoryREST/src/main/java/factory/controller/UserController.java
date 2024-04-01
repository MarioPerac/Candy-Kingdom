package factory.controller;


import factory.model.User;
import factory.service.UserService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/users")
public class UserController {

    UserService userService = new UserService();


    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String get() {
        return "radi";
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response registerUser(User user) {
        userService.registerUser(user);
        return Response.status(200).build();
    }
}
