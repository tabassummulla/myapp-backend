package app.datafetchers;


import app.datafetchers.mutations.AddUser;
import app.datafetchers.query.AllUsersFetcher;
import app.datafetchers.query.UserByEmail;
import app.datafetchers.query.UserById;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;

@Component
public class GraphqlService {

    @Autowired
    AllUsersFetcher allUsersFetcher;

    @Autowired
    AddUser addUser;

    @Autowired
    UserById userById;

    @Autowired
    UserByEmail userByEmail;


    private GraphQL graphQL;

    public GraphqlService(AllUsersFetcher allUsersFetcher, AddUser addUser, UserById userById, UserByEmail userByEmail) {

        this.allUsersFetcher = allUsersFetcher;
        this.addUser = addUser;
        this.userById = userById;
        this.userByEmail = userByEmail;
    }


    @PostConstruct
    public GraphQL loadSchema() throws IOException {
        InputStream schemaFile = Thread.currentThread().getContextClassLoader().getResourceAsStream("schema.graphql");
        TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(schemaFile);
        RuntimeWiring wiring = buildRuntimeWiring();
        GraphQLSchema schema = new SchemaGenerator().makeExecutableSchema(typeRegistry, wiring);
        graphQL = GraphQL.newGraphQL(schema).build();

        return graphQL;
    }

    private RuntimeWiring buildRuntimeWiring() {
        return RuntimeWiring.newRuntimeWiring()
                .type("Query", typewiring -> typewiring.dataFetcher("allUsers", allUsersFetcher)
                        .dataFetcher("userById",userById). dataFetcher("userByEmail",userByEmail))
                .type("Mutation", typewiring -> typewiring.dataFetcher("addUser", addUser)).build();

    }

    public GraphQL getGraphQL() throws IOException {
        graphQL = loadSchema();
        return graphQL;
    }

}