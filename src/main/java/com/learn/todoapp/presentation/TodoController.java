package com.learn.todoapp.presentation;

import com.learn.todoapp.domain.model.Todo;
import com.learn.todoapp.domain.service.TodoService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.time.LocalDate;
import java.util.List;

//@Controller
@SessionAttributes("name")
public class TodoController {

    private TodoService todoService;
    private Logger log;

    public TodoController(TodoService todoService, Logger log){
        this.todoService = todoService;
        this.log = log;
    }

    @RequestMapping("/list-todos")
    public String listAllTodos(ModelMap model){
        String username = getLoggedInUser();
        List<Todo> todoList =  todoService.findByUsername(username);
        log.info("Todo list: " + todoList);

        model.put("todos", todoList);
        return "todos";
    }

    @RequestMapping(value = "add-todo", method = RequestMethod.GET)
    public String showTodoPage(ModelMap model){
        // Setting default values
        Todo todo = new Todo(0, getLoggedInUser(), "Default value", LocalDate.now(), false);
        model.put("todo", todo);
        return "addTodo";
    }

    private static String getLoggedInUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();

    }

    // Using concept of Command bean to bind incoming form fields to todo object
    @RequestMapping(value = "add-todo", method = RequestMethod.POST)
    public String addTodo(ModelMap model, @Valid Todo todo, BindingResult result){

        if(result.hasErrors()){
            return "addTodo";
        }

        log.info("Incoming todo object from form: {}",  todo);
        Todo localTodo = (Todo) model.get("todo");
        todoService.addTodo(getLoggedInUser(), todo.getDescription(), todo.getTargetDate(), false);
        return "redirect:list-todos";
    }

    @RequestMapping(value = "delete-todo", method = RequestMethod.GET)
    public String deleteTodo(@RequestParam Integer id){
        todoService.deleteById(id);
        return "redirect:list-todos";
    }

    @RequestMapping(value = "update-todo", method = RequestMethod.GET)
    public String showUpdateTodoPage(@RequestParam Integer id, ModelMap model){
        Todo todo = todoService.findById(id);
        model.put("todo", todo);
        return "addTodo";
    }

    @RequestMapping(value = "update-todo", method = RequestMethod.POST)
    public String updateTodo(@RequestParam Integer id, @Valid Todo todo, BindingResult result){

        if(result.hasErrors()){
            return "addTodo";
        }

        log.info("Incoming todo object from form: {}",  todo);
        todoService.update(todo);
        return "redirect:list-todos";
    }
}
