package com.github.tden27.webchat.controller;

import com.github.tden27.webchat.model.User;
import com.github.tden27.webchat.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/registration")
public class RegistrationController {

    private final UserService userService;

    @GetMapping()
    public String registration(@ModelAttribute("user") User user) {
        return "registration";
    }

    @PostMapping()
    public String addUser(@ModelAttribute("user") @Valid User user,
                          BindingResult bindingResult) {
        if (bindingResult.hasErrors()) return "registration";

        userService.addUser(user);
        return "redirect:/login";
    }
}
