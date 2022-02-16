package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.entity.User;
import web.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/")
public class MyController {

    private final UserService userService;

    @Autowired
    public MyController(UserService userService) {
        this.userService = userService;
    }



    @RequestMapping("/")
    public String showAllUsers(Model model) {
        System.out.println("showAllUsers/allUsers");
        List<User> allUsers = userService.getAllUsers();
        model.addAttribute("allUsers", allUsers);
        return "allUsers";
    }

    @RequestMapping("/new")
    public String addNewUser(Model model) {
        System.out.println("addNewUser/new");
        model.addAttribute("user", new User());
        return "newUser";
    }

    @PostMapping()
    public String createNewUser(@ModelAttribute("user") User user) {
        System.out.println("createNewUser");
        userService.addUser(user);
        return "redirect:/";
    }

    @GetMapping("/updateUser/{id}")
    public String updateUser(@PathVariable("id") int id, Model model){
        System.out.println("updateUser/updateUser");
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "editUser";
    }

    @PatchMapping("/{id}")
    public String edit(Model model, @PathVariable("id") int id, User user) {
        System.out.println("edit");
        userService.updateUser(id,user);
        return "redirect:/";
    }


//    @GetMapping("/updateUser/{id}")
    @RequestMapping("/updateUser")
    public String update(@ModelAttribute("user") User user, @PathVariable("id") long id) {
        System.out.println("update" + user.toString());
        userService.updateUser(id, user);
        return "editUser";
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
