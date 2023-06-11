package stefanmonko.sk.servisimofrontend.controllers;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;


@Controller
public class UserController {

    @GetMapping("/")
    String index(HttpSession session) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // System.out.println("Logged-in user: {user full name}" + authentication.getName());

        session.setAttribute("loggedUser", authentication.getName());
        return "index";
    }

}