package app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import app.model.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {

}