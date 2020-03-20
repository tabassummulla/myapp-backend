package app.datafetchers.query;

import app.model.User;
import app.repo.UserRepository;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AllUsersFetcher implements DataFetcher<List<User>> {

    @Autowired
    UserRepository repository;

    @Override
    public List<User> get(DataFetchingEnvironment dataFetchingEnvironment) throws Exception {

        return repository.findAll();
    }


}
