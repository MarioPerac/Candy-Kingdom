package factory.controller;


import factory.model.User;
import factory.model.UserInfo;
import factory.service.UserService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/users")
public class UserController {

    UserService userService = new UserService();


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<UserInfo> get() {
        return userService.getUsers();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response registerUser(User user) {
        userService.registerUser(user);
        return Response.status(200).build();
    }
}
