package spring.sem3.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import spring.sem3.domain.User;
import spring.sem3.services.RegistrationService;

import java.util.List;

@RestController
@RequestMapping("/user")//localhost:8080/user
public class UserController {

    @Autowired
    private RegistrationService service;

    @GetMapping
    public List<User> userList() {
        return service.getDataProcessingService().getRepository().getUsers();
    }

    @PostMapping("/body")
    public String userAddFromBody(@RequestBody User user) {
        service.getDataProcessingService().addUserToList(user);
        return "User added from body!";
    }

    @PostMapping
    public User userAddFromParam(
            @RequestParam String name,
            @RequestParam int age,
            @RequestParam String email) {
        return service.processRegistration(name, age, email);
    }

}