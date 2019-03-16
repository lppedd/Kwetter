package rest;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.jetbrains.annotations.NotNull;

import exception.UserException;
import models.User;
import models.UserType;
import service.UserService;

@Singleton
@Path("/user")
public class UserRestService {
    @NotNull private final UserService userService;

    @Inject
    UserRestService(@NotNull final UserService userService) {
        this.userService = userService;
    }

    @GET
    @Produces("application/json")
    public List<User> get() {
        return userService.getAll();
    }

    @GET
    @Path("/all")
    @Produces("application/json")
    public List<User> getAll() throws UserException {
        userService.create(
                new User(
                        "Mike",
                        "mike@live.nl",
                        "Helmond",
                        "http://mike.nl",
                        "Just living life",
                        UserType.REGULAR
                )
        );

        // Avoid commenting out code too much. Confusing!
        return userService.getAll();
    }
}
