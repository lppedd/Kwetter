package rest;

import exception.UserException;
import models.User;
import models.UserType;
import service.KwetterAppl;
import service.UserService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.ArrayList;
import java.util.List;

@RequestScoped
@Path("/user")
public class DefaultUserRest {
    private KwetterAppl kwetterApp;
    private UserService userService;

    protected DefaultUserRest() {

    }

    @Inject
    public DefaultUserRest(KwetterAppl kwetterApp) {
        this.kwetterApp = kwetterApp;
        this.userService = kwetterApp.getService();
    }

    @GET
    @Produces({"application/json"})
    public List<User> get() throws UserException {

        List<User> users = userService.getAll();
//
//        userService.create(new User("Mike", "mike@live.nl", "Helmond", "http://mike.nl", "Just living life", UserType.REGULAR));
//        return users;
//        List<User> list = new ArrayList<>();
//        list.add(new User("la", "la", "la", "la", "la", UserType.REGULAR));
//        return list;
        return users;
    }

    @GET
    @Path("/all")
    @Produces({"application/json"})
    public List<User> getAll() throws UserException {

        userService.create(new User("Mike", "mike@live.nl", "Helmond", "http://mike.nl", "Just living life", UserType.REGULAR));
        List<User> users = userService.getAll();
//
//        userService.create(new User("Mike", "mike@live.nl", "Helmond", "http://mike.nl", "Just living life", UserType.REGULAR));
//        return users;
//        List<User> list = new ArrayList<>();
//        list.add(new User("la", "la", "la", "la", "la", UserType.REGULAR));
//        return list;
        return users;
    }
}
