package ma.ensa.thymeleafUser.controllers;


import ma.ensa.thymeleafUser.entities.User;
import ma.ensa.thymeleafUser.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class UserController {


    @Autowired
    private UserRepository userRepository;



    @GetMapping(path = "/index")
    public String Users(Model model){
        List<User> users=userRepository.findAll();
        model.addAttribute("listeUsers",users);
        return "users";

    }

    @GetMapping(path="/delete")
    public String delete(Long id){
        userRepository.deleteById(id);
        return "redirect:/index";
    }


    @GetMapping(path ="add-user")
    public String formUsers(Model model){
        model.addAttribute("user",new User());
        return "add-user";
    }

    @PostMapping(path = "/save")
    public String save(Model model, @ModelAttribute("user") User user){
        userRepository.save(user);
        return "redirect:/index";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id")Long id, Model model) {
        User user = userRepository.findById(id)
                .orElseThrow(() ->new IllegalArgumentException("Invalid user Id:" + id));
        model.addAttribute("user", user);
        return"update-user";
    }
    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable("id") Long id, User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            user.setId(id);
            return"update-user";
        }

        userRepository.save(user);
        model.addAttribute("users", userRepository.findAll());
        return"redirect:/index";
    }



}
