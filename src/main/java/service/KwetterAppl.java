package service;

import dao.UserDao;
import models.Tweet;
import models.User;
import models.UserType;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Startup
@Singleton
public class KwetterAppl {

    @Inject
    private UserService service;

    private List<User> users = new ArrayList<>();

    @PostConstruct
    public void init() {
        try {
            User bas = new User("Henk", "henk@live.nl", "Valkenburg", "http://henk.nl", "Hallo, ik ben Henk!", UserType.REGULAR);

            service.create(bas);
            users = service.getAll();
            System.out.println(users);

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public UserService getService() {
        return service;
    }

}