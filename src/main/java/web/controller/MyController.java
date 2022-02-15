package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.entity.User;
import web.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/user")
public class MyController {

    private final UserService userService;

    @Autowired
    public MyController(UserService userService) {
        this.userService = userService;
    }

    public String showFirstView() {
        return "first-view";
    }

    @RequestMapping("/allUsers")
    public String showAllUsers(Model model) {
        List<User> allUsers = userService.getAllUsers();
        model.addAttribute("allUsers", allUsers);
        return "allUsers";
    }

    @RequestMapping("/new")
    public String addNewUser(Model model) {
        model.addAttribute("user", new User());
        return "newUser";
    }

    @PostMapping()
    public String createNewUser(@ModelAttribute("user") User user) {
        userService.addUser(user);
        return "redirect:/user/allUsers";
    }
/*
    @PostMapping()
    public String create(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName,
                         @RequestParam("age") int age, Model model) {
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setAge(age);

        model.addAttribute("user", user);
        model.addAttribute("user", new User());
        return "allUsers";
    }*/
}
