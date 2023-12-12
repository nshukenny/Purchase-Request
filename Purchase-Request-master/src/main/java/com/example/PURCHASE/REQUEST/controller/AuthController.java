package com.example.PURCHASE.REQUEST.controller;

import com.example.PURCHASE.REQUEST.dto.UserDto;
import com.example.PURCHASE.REQUEST.entity.Employee;
import com.example.PURCHASE.REQUEST.entity.User;
import com.example.PURCHASE.REQUEST.service.EmployeeService;
import com.example.PURCHASE.REQUEST.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
public class AuthController {

    private UserService userService;

    private EmployeeService employeeService;

//    public AuthController(UserService userService) {
//        this.userService = userService;
//    }
public AuthController(UserService userService, EmployeeService employeeService) {
    this.userService = userService;
    this.employeeService = employeeService;
}
    // handler method to handle home page request
    @GetMapping("/index")
    public String home(){
        return "index";
    }

    // handler method to handle login request
    @GetMapping("/login")
    public String login(){
        return "login";
    }

    // handler method to handle user registration form request
    @GetMapping("/register")
    public String showRegistrationForm(Model model){
        // create model object to store form data
        UserDto user = new UserDto();
        model.addAttribute("user", user);
        return "register";
    }

    @PostMapping ("/login")
    public String login(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login"; // redirect to login page if user is not logged in
        }
        else
        {
        // Add model attributes or business logic related to the dashboard page
        return "/showNewEmployeeForm";
        }
    }



    // handler method to handle user registration form submit request
    @PostMapping("/register/save")
    public String registration(@Valid @ModelAttribute("user") UserDto userDto,
                               BindingResult result,
                               Model model){
        User existingUser = userService.findUserByEmail(userDto.getEmail());

        if(existingUser != null && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty()){
            result.rejectValue("email", null,
                    "There is already an account registered with the same email");
        }

        if(result.hasErrors()){
            model.addAttribute("user", userDto);
            return "/register";
        }

        userService.saveUser(userDto);
        return "redirect:/register?success";
    }

    // handler method to handle list of users
//    @GetMapping("/users")
//    public String users(Model model){
//        List<UserDto> users = userService.findAllUsers();
//        model.addAttribute("users", users);
//        return "users";
//    }
//
//    @GetMapping("/users")
//    public String viewHomePage(Model model) {
//        List<Employee> Employee= employeeService.getAllEmployees();
//        model.addAttribute("listEmployees", employeeService.getAllEmployees());
//        return "empindex";
//    }
//    @GetMapping("/users")
//    public String users(Model model) {
//        List<UserDto> users = userService.findAllUsers();
//        List<Employee> employees = employeeService.getAllEmployees();
//        model.addAttribute("users", users);
//        model.addAttribute("employees", employees);
//        return "users";
//    }
    @GetMapping("/users")
    public String users(Model model) {
        List<UserDto> users = userService.findAllUsers();
        List<Employee> employees = employeeService.getAllEmployees();
        model.addAttribute("users", users);
        model.addAttribute("listEmployees", employees);
        return "users";
    }

}
