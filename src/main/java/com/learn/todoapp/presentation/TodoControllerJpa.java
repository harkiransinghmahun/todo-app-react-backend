//package com.learn.todoapp.presentation;
//
//import com.learn.todoapp.domain.model.Todo;
//import com.learn.todoapp.domain.service.TodoService;
//import com.learn.todoapp.respository.TodoRepository;
//import jakarta.validation.Valid;
//import org.slf4j.Logger;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.ModelMap;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.SessionAttributes;
//
//import java.time.LocalDate;
//import java.util.List;
//import java.util.Optional;
//
////@Controller
//@SessionAttributes("name")
//public class TodoControllerJpa {
//
//    private TodoRepository todoRepository;
//    private Logger log;
//
//    public TodoControllerJpa(Logger log, TodoRepository todoRepository){
//        this.log = log;
//        this.todoRepository = todoRepository;
//    }
//
//    @RequestMapping("/list-todos")
//    public String listAllTodos(ModelMap model){
//        String username = getLoggedInUser();
//        List<Todo> todoList = todoRepository.findByUsername(username);
//        log.info("Todo list: " + todoList);
//
//        model.put("todos", todoList);
//        return "todos";
//    }
//
//    @RequestMapping(value = "add-todo", method = RequestMethod.GET)
//    public String showTodoPage(ModelMap model){
//        // Setting default values
//        Todo todo = new Todo(0, getLoggedInUser(), "Default value", LocalDate.now(), false);
//        model.put("todo", todo);
//        return "addTodo";
//    }
//
//    private static String getLoggedInUser() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        return authentication.getName();
//    }
//
//    // Using concept of Command bean to bind incoming form fields to todo object
//    @RequestMapping(value = "add-todo", method = RequestMethod.POST)
//    public String addTodo(ModelMap model, @Valid Todo todo, BindingResult result){
//
//        if(result.hasErrors()){
//            return "addTodo";
//        }
//
//        log.info("Incoming todo object from form: {}",  todo);
//        todo.setUsername(getLoggedInUser());
//        todoRepository.save(todo);
//        return "redirect:list-todos";
//    }
//
//    @RequestMapping(value = "delete-todo", method = RequestMethod.GET)
//    public String deleteTodo(@RequestParam Integer id){
//        todoRepository.deleteById(id);
//        return "redirect:list-todos";
//    }
//
//    @RequestMapping(value = "update-todo", method = RequestMethod.GET)
//    public String showUpdateTodoPage(@RequestParam Integer id, ModelMap model){
//        Todo todo = todoRepository.findById(id).get();
//        model.put("todo", todo);
//        return "addTodo";
//    }
//
//    @RequestMapping(value = "update-todo", method = RequestMethod.POST)
//    public String updateTodo(@RequestParam Integer id, @Valid Todo todo, BindingResult result){
//
//        if(result.hasErrors()){
//            return "addTodo";
//        }
//
//        log.info("Incoming todo object from form: {}",  todo);
//        todo.setUsername(getLoggedInUser());
//        todoRepository.save(todo);
//        return "redirect:list-todos";
//    }
//}
