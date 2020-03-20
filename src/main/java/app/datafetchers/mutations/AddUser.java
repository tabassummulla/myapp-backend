package app.datafetchers.mutations;

import app.model.User;
import app.repo.UserRepository;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AddUser implements DataFetcher {
    @Autowired

    private UserRepository userRepository;

    @Override
    public Object get(DataFetchingEnvironment dataFetchingEnvironment) throws Exception {
        Integer id = dataFetchingEnvironment.getArgument("id");
        String firstName = dataFetchingEnvironment.getArgument("first_name");
        String lastName = dataFetchingEnvironment.getArgument("last_name");
        String email = dataFetchingEnvironment.getArgument("email");
        String about_me = dataFetchingEnvironment.getArgument("about_me");
        String profilePic = dataFetchingEnvironment.getArgument("profile_pic");

        User user = new User(id, firstName, lastName, email, about_me, profilePic);
        userRepository.save(user);
        return user;
    }
}
