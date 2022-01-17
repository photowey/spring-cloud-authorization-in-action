/*
 * Copyright © 2022 photowey (photowey@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
