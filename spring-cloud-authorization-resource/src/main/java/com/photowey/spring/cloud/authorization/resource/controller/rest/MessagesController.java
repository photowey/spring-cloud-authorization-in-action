package com.photowey.spring.cloud.authorization.resource.controller.rest;

import com.photowey.spring.cloud.authorization.resource.service.JwtService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * {@code MessagesController}
 *
 * @author photowey
 * @date 2022/01/13
 * @since 1.0.0
 */
@RestController
public class MessagesController {

    @GetMapping("/messages")
    @PreAuthorize("hasAuthority('USER') or hasAuthority('message.read') ")
    public String[] getMessages(@AuthenticationPrincipal Jwt jwt) {
        Long userId = JwtService.getUserId(jwt);
        System.out.println("the userId is:" + userId);
        return new String[]{"Message 1", "Message 2", "Message 3"};
    }

}
