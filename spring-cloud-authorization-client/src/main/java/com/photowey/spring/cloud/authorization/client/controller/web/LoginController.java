package com.photowey.spring.cloud.authorization.client.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * {@code LoginController}
 *
 * @author photowey
 * @date 2022/01/13
 * @since 1.0.0
 */
@Controller
public class LoginController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return login();
    }

}
