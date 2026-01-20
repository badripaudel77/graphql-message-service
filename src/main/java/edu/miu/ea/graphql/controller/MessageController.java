package edu.miu.ea.graphql.controller;

import edu.miu.ea.graphql.model.Message;
import edu.miu.ea.graphql.model.User;
import edu.miu.ea.graphql.repository.MessageRepository;
import edu.miu.ea.graphql.repository.UserRepository;
import org.springframework.stereotype.Controller;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.*;

import java.time.OffsetDateTime;
import java.util.List;

/**
 * Use Controller because it is not a rest API
 * Graphql always have only one endpoint /graphql
 * eg: http://localhost:8080/graphql
 */
@Controller
@RequiredArgsConstructor
public class MessageController {

    private final MessageRepository messageRepository;
    private final UserRepository userRepository;

    // Query: messages
    @QueryMapping
    public List<Message> messages() {
        return messageRepository.findAll();
    }

    // Query: messageById
    @QueryMapping
    public Message messageById(@Argument Long id) {
        return messageRepository.findById(id).orElse(null);
    }

    // Mutation: createMessage
    @MutationMapping
    public Message createMessage(
            @Argument String content,
            @Argument Long userId
    ) {
        User sender = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Message message = Message.builder()
                .user(sender)
                .content(content)
                .timestamp(OffsetDateTime.now())
                .build();
        return messageRepository.save(message);
    }
}

