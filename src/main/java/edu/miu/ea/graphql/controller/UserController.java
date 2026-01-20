package edu.miu.ea.graphql.controller;

import edu.miu.ea.graphql.model.Message;
import edu.miu.ea.graphql.model.User;
import edu.miu.ea.graphql.repository.UserRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @QueryMapping
    public List<User> users() {
        return userRepository.findAll();
    }

    @MutationMapping
    public User createUser(@Argument String username) {
        User user = new User();
        user.setUsername(username);
        return userRepository.save(user);
    }

    /**
     * Query:
     * query {
     *   users {
     *     id
     *     username
     *     messages {
     *       content
     *     }
     *   }
     * }
     */
    // field resolver for messages
    @SchemaMapping(typeName = "User", field = "messages")
    public List<Message> messages(User user) {
        return user.getMessages(); // Or fetch from repo if lazy
    }
}

