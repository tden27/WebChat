package com.github.tden27.webchat.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "message")
@Getter
@Setter
@RequiredArgsConstructor
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @NotEmpty(message = "Введите текст сообщения")
    @Size(max = 2048, message = "Текст сообщения слишком длинный")
    @Column(name = "text")
    private String text;

    @Column(name = "datetime")
    private LocalDateTime datetime;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @Override
    public String toString() {
        return datetime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + " " + user.getUsername() + ": " + text;
    }
}
