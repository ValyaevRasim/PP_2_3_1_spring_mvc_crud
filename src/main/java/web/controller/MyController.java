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


    // начальная страница
    @RequestMapping("/")
    public String showAllUsers(Model model) {
        System.out.println("showAllUsers/allUsers");
        List<User> allUsers = userService.getAllUsers();
        model.addAttribute("allUsers", allUsers);
        return "allUsers";
    }

    // добавление нового пользователяю, используем 2 метода
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

    //    обновление данных пользователя, используем 2 метода
    @GetMapping("/updateUser/{id}")
    public String updateUser(@PathVariable("id") long id, Model model){
        System.out.println("updateUser/updateUser");
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "editUser";
    }

    @RequestMapping("/{id}")
    public String edit(@PathVariable("id") long id, User user) {
        System.out.println("edit");
        userService.updateUser(id,user);
        return "redirect:/";
    }

//    удаление пользователя, используем 2 метода
    @GetMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable("id") long id){
        System.out.println("updateUser/updateUser");
        userService.deleteUserById(id);
        return "redirect:/";
    }
}
