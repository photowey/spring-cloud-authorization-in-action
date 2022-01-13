package com.photowey.spring.cloud.authorization.server.jackson2.mixin;

import com.fasterxml.jackson.annotation.*;

/**
 * {@code UserAuthorityMixin}
 *
 * @author photowey
 * @date 2022/01/13
 * @since 1.0.0
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE, getterVisibility = JsonAutoDetect.Visibility.PUBLIC_ONLY, isGetterVisibility = JsonAutoDetect.Visibility.NONE)
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class UserAuthorityMixin {

    @JsonCreator
    public UserAuthorityMixin(@JsonProperty("authority") String authority) {
    }

}
