package com.photowey.spring.cloud.authorization.client.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * {@code IndexController}
 *
 * @author photowey
 * @date 2022/01/13
 * @since 1.0.0
 */
@Controller
public class IndexController {

    /**
     * http://127.0.0.1:8080/auth-client
     *
     * @return
     */
    @GetMapping("/")
    public String root() {
        return "redirect:/index";
    }

    @GetMapping("/index")
    public String index() {
        return "index";
    }

}
