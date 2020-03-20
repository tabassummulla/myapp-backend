package app.controller;

import app.datafetchers.GraphqlService;
import com.okta.jwt.AccessTokenVerifier;
import com.okta.jwt.JwtVerificationException;
import com.okta.jwt.JwtVerifiers;
import graphql.ExecutionResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.time.Duration;

@RestController
public class Controller {

    @Autowired
    GraphqlService graphqlService;

    @Value("${OKTA_ISSUER}")
    private String issuerUrl;

    private AccessTokenVerifier jwtVerifier;

    public Controller() {

    }

    public Controller(GraphqlService graphqlService) {
        this.graphqlService = graphqlService;
    }

    public Controller(GraphqlService graphqlService, AccessTokenVerifier jwtVerifier) {
        this.jwtVerifier = jwtVerifier;
    }

    @PostConstruct
    public void buildVerifier() {
        jwtVerifier = JwtVerifiers.accessTokenVerifierBuilder()
                .setIssuer(issuerUrl)
                .setConnectionTimeout(Duration.ofSeconds(1))
                .setReadTimeout(Duration.ofSeconds(1))
                .build();

    }


    @RequestMapping(value = "/graphql")
    @CrossOrigin
    @PostMapping
    public ResponseEntity<Object> getResp(@RequestBody String query, @RequestHeader(name = "Authorization") String bearerToken) throws IOException {

        try {

            String rawToken = bearerToken.substring(7);

            jwtVerifier.decode(rawToken);

            ExecutionResult executionResult = graphqlService.getGraphQL().execute(query);
            return new ResponseEntity<>(executionResult, HttpStatus.OK);
        } catch (JwtVerificationException e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

    }
}


