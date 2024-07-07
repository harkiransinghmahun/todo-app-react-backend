package com.learn.todoapp.presentation;

import com.learn.todoapp.domain.model.Todo;
import com.learn.todoapp.domain.service.TodoService;
import com.learn.todoapp.respository.TodoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("http://localhost:3000")
@Slf4j
public class TodoControllerReact {

    public TodoRepository todoRepository;

    public TodoControllerReact(TodoRepository todoRepository){
        this.todoRepository = todoRepository;
    }

    @GetMapping("/users/{username}/todos")
    public List<Todo> retrieveTodos(@PathVariable String username){
        log.info("Got a Get request for : {}", username);
        return todoRepository.findByUsername(username);
    }

    @GetMapping("/users/{username}/todos/{id}")
    public Optional<Todo> retrieveTodo(@PathVariable String username, @PathVariable int id){
        return todoRepository.findById(id);
    }

    @DeleteMapping("/users/{username}/todos/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable String username, @PathVariable int id){
        todoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/users/{username}/todos/{id}")
    public Todo updateTodo(@PathVariable String username, @PathVariable int id, @RequestBody Todo todo){
        Optional<Todo> todoToUpdate = todoRepository.findById(id);
        todoToUpdate.ifPresent(value -> {
            value.setDescription(todo.getDescription());
            value.setTargetDate(todo.getTargetDate());
            todoRepository.save(value);
        });
        return todo;
    }

    @PostMapping("/users/{username}/todos")
    public Todo createTodo(@PathVariable String username, @RequestBody Todo todo) {
        todoRepository.save(todo);
        return todo;
    }

}
