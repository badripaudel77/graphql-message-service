package edu.miu.ea.graphql.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue
    private Long id;
    private String username;
    @OneToMany(mappedBy = "user")
    private List<Message> messages = new ArrayList<>();
}
