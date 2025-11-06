package net.proselyte.controller;

import net.proselyte.model.User;
import net.proselyte.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")                    //при переходе по ссылке users будет выполнен этот функционал
    public String findAll(Model model) {
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "user-list";                             //верни мне страницу user-list
    }

    @GetMapping("/user-create")                 //это отдельная форма для отображения результатов Сохранения объектов
    public String createUserForm(User user) {
        return "user-create";
    }

    @PostMapping("/user-create")                           //а это уже отдельный метод именно для создания объекта
    public String createUser(User user) {
        userService.saveUser(user);
        return "redirect:/users";               //после выполнения автоматически отправь нас на страницу "users"
    }

    @GetMapping("user-delete/")
    public String deleteUser(@RequestParam("id") Long id) {
        userService.deleteById(id);
        return "redirect:/users";
    }

    @GetMapping("/user-update/")
    public String updateUserForm(@RequestParam("id") Long id, Model model) {
        User user = userService.findById(id);
        model.addAttribute("user", user);
        return "user-update";
    }

    @PostMapping("/user-update")
    public String updateUser(User user) {
        userService.update(user);
        return "redirect:/users";
    }
}
