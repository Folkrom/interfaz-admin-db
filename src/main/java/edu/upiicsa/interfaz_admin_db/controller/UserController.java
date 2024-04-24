package edu.upiicsa.interfaz_admin_db.controller;

import edu.upiicsa.interfaz_admin_db.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.Connection;
import java.sql.DriverManager;

@Controller
@RequestMapping("/")
@Slf4j
public class UserController {

    @Value("${edu.upiicsa.interfaz_admin_db.database-url}")
    private String databaseUrl;

    @GetMapping
    public String loadLogin(User user) {
        return "login";
    }

    @PostMapping("login")
    public String login(User user, Model model, final RedirectAttributes redirectAttributes) {
        var connection = getConexion(user.getUsername(), user.getPassword());

        if (connection == null) {
            model.addAttribute("error", "Wrong username/password, try again!");
            return null;
        } else {
            redirectAttributes.addFlashAttribute("connection", connection);
        }

        return "redirect:/animales";
    }

    private Connection getConexion(String username, String password) {
        Connection con = null;

        try {
            con = DriverManager.getConnection(databaseUrl, username, password);
        } catch (Exception e) {
            log.error("Error: " + e.getMessage());
        }
        return con;
    }

}
