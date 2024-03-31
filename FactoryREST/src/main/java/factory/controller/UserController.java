package factory.controller;


import factory.model.User;
import factory.service.UserService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

@Path("/users")
public class UserController {

    UserService userService = new UserService();


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void registerUser(User user) {
        userService.registerUser(user);
    }
}
