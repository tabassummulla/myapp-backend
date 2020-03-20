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
public class UserById implements DataFetcher<List<User>> {

    @Autowired
    UserRepository repository;

    @Override
    public List<User> get(DataFetchingEnvironment dataFetchingEnvironment) throws Exception {

        List allUsers = repository.findAll();
        Integer id = dataFetchingEnvironment.getArgument("id");

        List userById = new ArrayList();

        for (int i = 0; i < allUsers.size(); i++) {

            User user = (User) allUsers.get(i);

            if (user.getId().equals(id)) {
                userById.add(user);
            }
        }

        return userById;
    }
}
