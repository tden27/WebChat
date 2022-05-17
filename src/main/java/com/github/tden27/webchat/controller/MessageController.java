package com.github.tden27.webchat.controller;

import com.github.tden27.webchat.model.Message;
import com.github.tden27.webchat.model.User;
import com.github.tden27.webchat.service.MessageService;
import com.github.tden27.webchat.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/chat")
public class MessageController {

    private final MessageService messageService;
    private final UserService userService;

    @GetMapping
    public String chat(Model model) {
        List<Message> messageList = messageService.getMessages();
        Collections.reverse(messageList);
        List<User> userList = userService.getUsersOnline();
        model.addAttribute("messageList", messageList);
        model.addAttribute("userList", userList);
        model.addAttribute("message", new Message());
        model.addAttribute("user", new User());
        model.addAttribute("text", null);
        return "chat";
    }

    @PostMapping
    public String sendMessage(@AuthenticationPrincipal User user,
                              @ModelAttribute("message") @Valid Message message,
                              BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "chat";
        }
        message.setUser(user);
        message.setDatetime(LocalDateTime.now());
        messageService.save(message);
        return "redirect:/chat";
    }
}
