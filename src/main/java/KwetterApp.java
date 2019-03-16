import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Singleton;

import org.jetbrains.annotations.NotNull;

import dao.UserDao;
import models.User;
import models.UserType;

@Singleton
public class KwetterApp {
    @NotNull private final UserDao dao;

    @Inject
    KwetterApp(@NotNull final UserDao dao) {
        this.dao = dao;
    }

    private void init(
            @Observes
            @Initialized(ApplicationScoped.class) final Object ignore) {
        try {
            final User bas =
                    new User(
                            "Henk",
                            "henk@live.nl",
                            "Valkenburg",
                            "http://henk.nl",
                            "Hallo, ik ben Henk!",
                            UserType.REGULAR
                    );
            final User levi =
                    new User(
                            "Levi",
                            "levi@live.nl",
                            "Eindhoven",
                            "http://levi.nl",
                            "Hallo, ik ben Levi!",
                            UserType.REGULAR
                    );
            final User nick =
                    new User(
                            "Nick",
                            "nick@live.nl",
                            "Helmond",
                            "http://nick.nl",
                            "Hallo, ik ben Nick!",
                            UserType.REGULAR
                    );

            // FIXME handle infinite recursion first! Or you'll get a StackOverflow Error
            // final Tweet tweet = new Tweet("eten...", bas);
            // final Tweet tweet1 = new Tweet("zwemmen...", bas);
            // final Tweet tweet2 = new Tweet("voetballen...", bas);

            // bas.addTweet(tweet);
            // bas.addTweet(tweet1);
            // levi.addTweet(tweet2);

            dao.create(bas);
            dao.create(levi);
            dao.create(nick);

            // FIXME same here
            // dao.addFollower(bas, levi);
            // dao.addFollower(bas, nick);
            // dao.addFollower(levi, bas);
            // dao.addFollower(levi, nick);
        } catch (final Exception ignored) {
            // FIXME robust logging
        }
    }
}
