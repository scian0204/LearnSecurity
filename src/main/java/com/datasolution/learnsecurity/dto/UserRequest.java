package com.datasolution.learnsecurity.dto;

import com.datasolution.learnsecurity.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collections;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequest {
    private String userId;
    private String password;
    private String userName;
    private String roleId;

    public User convert() {
        return User.builder()
                .userId(this.userId)
                .password(this.password)
                .userName(this.userName)
                .roles(Collections.singletonList(this.roleId))
                .build();
    }
}
