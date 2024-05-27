package com.learn.todoapp.domain.service;

import com.learn.todoapp.domain.model.Todo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Service
@Slf4j
public class TodoService {

    private final static List<Todo> todos = new ArrayList<>();
    private int todosCount = 1;

    public void addTodo(String username, String description, LocalDate targetDate, boolean done){
        todos.add(new Todo(todosCount++, username, description, targetDate, done));
    }

    public List<Todo> findByUsername(String username){
        Predicate<? super Todo> predicate = todo -> todo.getUsername().equalsIgnoreCase(username);
        return todos.stream().filter(predicate).toList();
    }

    public void deleteById(Integer id){
        Predicate<? super Todo> predicate = todo -> todo.getId() == id;
        todos.removeIf(predicate);
    }

    public Todo findById(Integer id){
        Predicate<? super Todo> predicate = todo -> todo.getId() == id;
        return todos.stream().filter(predicate).findFirst().get();
    }

    public void update(Todo todoToUpdate){
        Todo todo = findById(todoToUpdate.getId());
        todo.setDescription(todoToUpdate.getDescription());
        todo.setTargetDate(todoToUpdate.getTargetDate());
    }

}
