package edu.miu.ea.graphql.repository;

import edu.miu.ea.graphql.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
