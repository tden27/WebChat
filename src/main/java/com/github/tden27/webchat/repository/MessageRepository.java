package com.github.tden27.webchat.repository;

import com.github.tden27.webchat.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

    List<Message> findFirst10ByDatetimeBeforeOrderByDatetimeDesc(LocalDateTime datetime);
}
