package factory.controller;


import factory.model.*;
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

    @GET
    @Path("/{username}/email")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserEmail(@PathParam("username") String username) {
        String email = userService.getUserEmail(username);
        if (email != null) {
            return Response.status(200).entity(email).build();
        } else {
            return Response.status(400).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response registerUser(User user) {
        if (userService.registerUser(user)) {
            return Response.status(200).build();
        } else {
            return Response.status(400).build();
        }
    }

    @PUT
    @Path("/{username}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response changeState(@PathParam("username") String username, StatusRequest req) {

        if (userService.changeUserStatus(username, req.getStatus())) {
            return Response.status(200).build();
        } else {
            return Response.status(400).build();
        }
    }

    @DELETE
    @Path("/{username}")
    public Response deleteUser(@PathParam("username") String username) {
        if (userService.deleteUser(username)) {
            return Response.status(204).build();
        } else {
            return Response.status(404).build();
        }
    }

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Login userAuthentication(UserRequest req) {

        return userService.userAuthentication(req.getUsername(), req.getPassword());
    }
}
