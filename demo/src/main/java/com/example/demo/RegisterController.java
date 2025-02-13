package com.example.demo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegisterController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/")
    public String showForm() {
        return "index";
    }
    @GetMapping("/index")
    public String showHome() {
        return "index";
    }
    @GetMapping("/register")
    public String showRegister() {
        return "register";
    }
    @GetMapping("/checkout-pa")
    public String showCheckoutpa() {
        return "checkout-pa";
    }
    @GetMapping("/checkout-ws")
    public String showCheckoutws() {
        return "checkout-ws";
    }
    @GetMapping("/checkout-fn")
    public String showCheckoutfn() {
        return "checkout-fn";
    }
    @PostMapping("/register")
public String registerUser (@RequestParam String username, @RequestParam String password, Model model) {
    if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
        model.addAttribute("message", "Username and password cannot be empty.");
        return "result";
    }
    String sql = "SELECT COUNT(*) FROM users WHERE username = ?";
    int count = jdbcTemplate.queryForObject(sql, Integer.class, username);
    if (count > 0) {
        model.addAttribute("message", "Username already exists. Please choose a different username.");
        return "result";
    }
    sql = "INSERT INTO users (username, password) VALUES (?, ?)";
    jdbcTemplate.update(sql, username, password);
    model.addAttribute("message", "User  registered successfully!");
    return "result";
}
    @GetMapping("/profile")
    public String showProfileForm() {
        return "profile";
    }

    @PostMapping("/profile")
    public String userProfile(@RequestParam String name, @RequestParam String age,@RequestParam String gender, @RequestParam String disease, Model model) {
        if (name == null || name.isEmpty() || age == null || age.isEmpty()) {
            model.addAttribute("", "Name and age cannot be empty.");
            return "result";
        }
        String sql = "INSERT INTO profile (name, age, gender, disease) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, name, age, gender, disease);
        model.addAttribute("message", "Profile details saved successfully!");
        return "result";
    }
    
}
