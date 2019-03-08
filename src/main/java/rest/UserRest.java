package rest;

import models.User;
import models.UserType;
import service.UserService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.List;

@RequestScoped
@Path("/user")
public class UserRest {

    @Inject
    private UserService userService;

    @GET
    @Produces({"application/json"})
    public List<User> get() {
        userService.create(new User("Mike", "Helmond", "http://mike.nl", "Just living life", UserType.REGULAR));
        return userService.getAll();
    }
}
