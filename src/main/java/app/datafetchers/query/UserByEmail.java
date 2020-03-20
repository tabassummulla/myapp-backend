package app.datafetchers.query;


import app.model.User;
import app.repo.UserRepository;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserByEmail implements DataFetcher<List<User>> {

    @Autowired
    UserRepository repository;

    @Override
    public List<User> get(DataFetchingEnvironment dataFetchingEnvironment) throws Exception {

        List allUsers = repository.findAll();
        String email = dataFetchingEnvironment.getArgument("email");

        List userByEmail = new ArrayList();

        for (int i = 0; i < allUsers.size(); i++) {

            User user = (User) allUsers.get(i);

            if (user.getEmail().equals(email)) {
                userByEmail.add(user);
            }
        }

        return userByEmail;
    }
}
