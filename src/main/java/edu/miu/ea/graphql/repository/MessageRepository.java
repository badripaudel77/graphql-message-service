package edu.miu.ea.graphql.repository;

import edu.miu.ea.graphql.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {

}
