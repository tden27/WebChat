package com.github.tden27.webchat.service;

import com.github.tden27.webchat.model.Message;
import com.github.tden27.webchat.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;

    public void save(Message message) {
        messageRepository.save(message);
    }

    public List<Message> getMessages() {
        return messageRepository.findFirst10ByDatetimeBeforeOrderByDatetimeDesc(LocalDateTime.now());
    }
}
