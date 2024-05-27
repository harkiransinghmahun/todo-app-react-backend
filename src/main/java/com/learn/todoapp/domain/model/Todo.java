package com.learn.todoapp.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@ToString
@Entity
public class Todo {

    @Id
    @SequenceGenerator(name = "EntityTwoSequence", initialValue = 1000)
    @GeneratedValue(generator = "EntityTwoSequence")
    private int id;
    private String username;
    @Size(min = 10, message="Enter at least 10 characters")
    private String description;
    private LocalDate targetDate;
    private boolean done;

    public Todo() {
    }
}
