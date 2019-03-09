import dao.UserDao;
import models.Tweet;
import models.User;
import models.UserType;

import javax.annotation.PostConstruct;
import javax.ejb.Startup;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
@Startup
public class KwetterApp {

    @Inject
    private UserDao dao;

//    @Inject
//    private IKweetDao kdao;

    @PostConstruct
    public void init(){
        try{
            User bas = new User("Henk", "henk@live.nl", "Valkenburg", "http://henk.nl", "Hallo, ik ben Henk!", UserType.REGULAR);
            User levi = new User("Levi", "levi@live.nl", "Eindhoven", "http://levi.nl", "Hallo, ik ben Levi!", UserType.REGULAR);
            User nick = new User("Nick", "nick@live.nl", "Helmond", "http://nick.nl", "Hallo, ik ben Nick!", UserType.REGULAR);

            Tweet tweet = new Tweet("eten...", bas);
            Tweet tweet1 = new Tweet("zwemmen...", bas);
            Tweet tweet2 = new Tweet("voetballen...", bas);

            bas.addTweet(tweet);
            bas.addTweet(tweet1);
            levi.addTweet(tweet2);
            dao.create(bas);
            dao.create(levi);
            dao.create(nick);
            dao.addFollower(bas, levi);
            dao.addFollower(bas, nick);
            dao.addFollower(levi, bas);
            dao.addFollower(levi, nick);
//            Tweet reply = new Tweet("test", bas);
//            kdao.postKweet(reply);
//            kdao.addReply(bas.getKwetters().get(0),reply);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}